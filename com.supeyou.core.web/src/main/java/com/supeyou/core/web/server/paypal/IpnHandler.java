/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.supeyou.core.web.server.paypal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.DonationFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.core.impl.DonationCRUDServiceImpl;
import com.supeyou.core.impl.SupporterCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

/**
 * Paypal IPN Notification Handler Class
 * 
 */
public class IpnHandler {

	private Logger log = Logger.getLogger(IpnHandler.class.getName());

	private static String IPN_URL;

	public IpnHandler(String ipnURL) {

		IPN_URL = ipnURL;

	}

	/**
	 * This method handles the Paypal IPN Notification as follows: <br>
	 * 
	 * 1. Read all posted request parameters <br>
	 * 2. Prepare 'notify-validate' command with exactly the same parameters <br>
	 * 3. Post above command to Paypal IPN URL {@link IpnConfig#ipnUrl} <br>
	 * 4. Read response from Paypal <br>
	 * 5. Capture Paypal IPN information <br>
	 * 6. Validate captured Paypal IPN Information <br>
	 * 6.1. Check that paymentStatus=Completed <br>
	 * 6.2. Check that txnId has not been previously processed <br>
	 * 6.3. Check that receiverEmail matches with configured {@link IpnConfig#receiverEmail} <br>
	 * 6.4. Check that paymentAmount matches with configured {@link IpnConfig#paymentAmount} <br>
	 * 6.5. Check that paymentCurrency matches with configured {@link IpnConfig#paymentCurrency} <br>
	 * 7. In case of any failed validation checks, throw {@link Exception} <br>
	 * 8. If all is well, return {@link IpnInfo} to the caller for further business logic execution
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return {@link DonationDTO}
	 * @throws Exception
	 */
	public void handleIpn(HttpServletRequest request) throws Exception {

		log.info(this.getAllRequestParams(request));

		if (verifyPaypalRequest(request)) {

			DonationDTO donationDTO = createDonationDTOFromRequest(request);

			if (donationDTO.getPaymentStatus() == null || !donationDTO.getPaymentStatus().value().equalsIgnoreCase("COMPLETED")) {

				log.log(Level.INFO, "payment_status IS NOT COMPLETED {" + donationDTO.getPaymentStatus() + "}");

				return;

			}

			if (getDonationDTO(donationDTO.getTxnId()) != null) {

				log.log(Level.INFO, "txn_id=" + donationDTO.getTxnId() + "  is already processed ");

				return;

			}

			SupporterDTO supporterDTO = getSupporterFromDonationDTO(donationDTO);

			if (supporterDTO == null) {

				log.log(Level.WARNING, "exeption trying to find supporter (item_number=" + donationDTO.getItemNumber() + ")");

				return;

			}

			DonationCRUDServiceImpl.i().save(null, supporterDTO, donationDTO);

		} else {

			log.log(Level.WARNING, "request could not be verified");

		}

	}

	private boolean verifyPaypalRequest(HttpServletRequest request) throws Exception {

		// 2. Prepare 'notify-validate' command with exactly the same parameters
		Enumeration<String> en = request.getParameterNames();
		StringBuilder cmd = new StringBuilder("cmd=_notify-validate");
		String paramName;
		String paramValue;
		String charset = request.getParameter("charset");
		if (charset == null) {
			charset = "UTF-8";
		}

		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
			paramValue = request.getParameter(paramName);
			cmd.append("&").append(paramName).append("=")
					.append(URLEncoder.encode(paramValue, charset));
		}

		// 3. Post above command to Paypal IPN URL {@link IpnConfig#ipnUrl}
		URL u = new URL(IPN_URL);
		HttpsURLConnection uc = (HttpsURLConnection) u.openConnection();
		uc.setDoOutput(true);
		uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		uc.setRequestProperty("Host", "www.paypal.com");
		PrintWriter pw = new PrintWriter(uc.getOutputStream());
		pw.println(cmd.toString());
		pw.close();

		// 4. Read response from Paypal
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String res = in.readLine();
		in.close();

		// 6. Validate captured Paypal IPN Information
		if (res.equals("VERIFIED")) {

			return true;

		} else {

			return false;

		}
	}

	private SupporterDTO getSupporterFromDonationDTO(DonationDTO donationDTO) throws CRUDException {

		return SupporterCRUDServiceImpl.i().get(null, new SupporterIDType(donationDTO.getItemNumber().value()));

	}

	private DonationDTO createDonationDTOFromRequest(HttpServletRequest request) throws Exception {

		DonationDTO donationDTO = new DonationDTO();

		// 5. Capture Paypal IPN information
		donationDTO.setItemName(getParam(request, "item_name"));
		donationDTO.setItemNumber(getParam(request, "item_number"));
		donationDTO.setPaymentStatus(getParam(request, "payment_status"));
		donationDTO.setPaymentAmount(toAmount(getParam(request, "mc_gross")));
		donationDTO.setPaymentCurrency(getParam(request, "mc_currency"));
		donationDTO.setTxnId(getParam(request, "txn_id"));
		donationDTO.setReceiverEmail(getParam(request, "receiver_email"));
		donationDTO.setPayerEmail(getParam(request, "payer_email"));

		return donationDTO;

	}

	private DonationDTO getDonationDTO(SingleLineString256Type txnId) throws CRUDException {

		DonationFetchQuery dtoQuery = new DonationFetchQuery();

		dtoQuery.setTxnId(txnId);

		for (DonationDTO donationDTO : DonationCRUDServiceImpl.i().fetchList(null, new Page(), dtoQuery)) {

			return donationDTO;

		}

		return null;

	}

	private AmountType toAmount(SingleLineString256Type param) {

		if (param == null) {
			return null;
		}

		String amountString = param.value();
		try {
			Double amountInteger = Double.parseDouble(amountString);

			return new AmountType(new Double(amountInteger * 100).intValue());

		} catch (Exception e) {
			log.log(Level.WARNING, "Amount " + amountString + " could not be parsed", e);
		}

		return null;
	}

	private SingleLineString256Type getParam(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);
		if (paramValue == null) {
			return new SingleLineString256Type("null");
		}
		return new SingleLineString256Type(paramValue);
	}

	/**
	 * Utility method to extract all request parameters and their values from request object
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return all request parameters in the form: param-name 1 param-value param-name 2 param-value param-value (in case of multiple values)
	 */
	private String getAllRequestParams(HttpServletRequest request)
	{
		Map<String, String[]> map = request.getParameterMap();
		StringBuilder sb = new StringBuilder("\nREQUEST PARAMETERS\n");
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();)
		{
			String pn = (String) it.next();
			sb.append(pn).append("\n");
			String[] pvs = (String[]) map.get(pn);
			for (int i = 0; i < pvs.length; i++) {
				String pv = pvs[i];
				sb.append("\t").append(pv).append("\n");
			}
		}
		return sb.toString();
	}

}
