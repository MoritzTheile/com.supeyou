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
 * User: smhumayun Date: 7/6/13 Time: 5:48 PM
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
	public DonationDTO handleIpn(HttpServletRequest request) throws Exception {
		log.info("inside ipn");
		DonationDTO donationDTO = new DonationDTO();

		// 1. Read all posted request parameters
		String requestParams = this.getAllRequestParams(request);
		log.info(requestParams);

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

		// 5. Capture Paypal IPN information
		donationDTO.setItemName(getParam(request, "item_name"));
		donationDTO.setItemNumber(getParam(request, "item_number"));
		donationDTO.setPaymentStatus(getParam(request, "payment_status"));
		donationDTO.setPaymentAmount(toAmount(getParam(request, "mc_gross")));
		donationDTO.setPaymentCurrency(getParam(request, "mc_currency"));
		donationDTO.setTxnId(getParam(request, "txn_id"));
		donationDTO.setReceiverEmail(getParam(request, "receiver_email"));
		donationDTO.setPayerEmail(getParam(request, "payer_email"));
		donationDTO.setResponse(new SingleLineString256Type(res));
		// donationDTO.setRequestParams(new SingleLineString256Type(requestParams));

		// 6. Validate captured Paypal IPN Information
		if (res.equals("VERIFIED")) {

			// 6.1. Check that paymentStatus=Completed
			if (donationDTO.getPaymentStatus() == null || !donationDTO.getPaymentStatus().value().equalsIgnoreCase("COMPLETED"))
				donationDTO.setError(new SingleLineString256Type("payment_status IS NOT COMPLETED {" + donationDTO.getPaymentStatus() + "}"));

			// 6.2. Check that txnId has not been previously processed
			DonationDTO oldDonationDTO = getDonationDTO(donationDTO.getTxnId());
			if (oldDonationDTO != null) {

				String message = "txn_id is already processed {old ipn_info " + oldDonationDTO;
				log.log(Level.INFO, message);
				donationDTO.setError(new SingleLineString256Type(message));
			}

			// 6.3. Check that receiverEmail matches with configured {@link IpnConfig#receiverEmail}
			// if (!ipnInfo.getReceiverEmail().equalsIgnoreCase(this.getIpnConfig().getReceiverEmail()))
			// ipnInfo.setError("receiver_email " + ipnInfo.getReceiverEmail()
			// + " does not match with configured ipn email " + this.getIpnConfig().getReceiverEmail());

			// 6.4. Check that paymentAmount matches with configured {@link IpnConfig#paymentAmount}
			// if (Double.parseDouble(ipnInfo.getPaymentAmount()) != Double.parseDouble(this.getIpnConfig().getPaymentAmount()))
			// ipnInfo.setError("payment amount mc_gross " + ipnInfo.getPaymentAmount()
			// + " does not match with configured ipn amount " + this.getIpnConfig().getPaymentAmount());

			// 6.5. Check that paymentCurrency matches with configured {@link IpnConfig#paymentCurrency}
			// if (!ipnInfo.getPaymentCurrency().equalsIgnoreCase(this.getIpnConfig().getPaymentCurrency()))
			// ipnInfo.setError("payment currency mc_currency " + ipnInfo.getPaymentCurrency()
			// + " does not match with configured ipn currency " + this.getIpnConfig().getPaymentCurrency());
		} else {
			donationDTO.setError(new SingleLineString256Type("Invalid response {" + res + "} expecting {VERIFIED}"));
		}

		log.info("ipnInfo = " + donationDTO);

		SupporterDTO supporterDTO = null;
		try {
			supporterDTO = SupporterCRUDServiceImpl.i().get(null, new SupporterIDType(donationDTO.getItemNumber().value()));
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, "exeption trying to find supporter (item_number=" + donationDTO.getItemNumber() + ")", e);
		}

		if (supporterDTO != null) {
			DonationCRUDServiceImpl.i().save(null, supporterDTO, donationDTO);
		}

		// 7. In case of any failed validation checks, throw {@link Exception}
		if (donationDTO.getError() != null)
			throw new Exception(donationDTO.getError().value());

		// 8. If all is well, return {@link DonationDTO} to the caller for further business logic execution
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
