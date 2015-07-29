/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.supeyou.core.web.server.paypal;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterIDType;
import com.supeyou.core.impl.DonationCRUDServiceImpl;
import com.supeyou.core.impl.Supporter2DonationCRUDServiceImpl;
import com.supeyou.core.impl.SupporterCRUDServiceImpl;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;

/**
 * Arbitrary service class to simulate the storage and retrieval of Paypal IPN Notification related information
 * 
 * User: smhumayun Date: 7/6/13 Time: 6:23 PM
 */
public class IpnService {

	private Logger log = Logger.getLogger(IpnService.class.getName());

	/**
	 * Store Paypal IPN Notification related information for future use
	 * 
	 * @param ipnInfo
	 *            {@link IpnInfo}
	 * @throws Exception
	 */
	public void log(final IpnInfo ipnInfo) throws Exception {

		log.log(Level.INFO, "ipnInfo = " + ipnInfo);

		DonationDTO donationDTO = new DonationDTO();
		donationDTO.setComment(new SingleLineString256Type(ipnInfo.toString()));
		DonationCRUDServiceImpl.i().updadd(null, donationDTO);

		SupporterDTO supporterDTO = SupporterCRUDServiceImpl.i().get(null, new SupporterIDType(ipnInfo.getItemNumber()));

		if (supporterDTO == null) {

			log.log(Level.WARNING, "no supporter with id " + ipnInfo.getItemNumber() + "found");

			return;

		}

		Supporter2DonationDTO supporter2DonationDTO = new Supporter2DonationDTO();
		supporter2DonationDTO.setDtoA(supporterDTO);
		supporter2DonationDTO.setDtoB(donationDTO);
		Supporter2DonationCRUDServiceImpl.i().updadd(null, supporter2DonationDTO);

	}

	/**
	 * Fetch Paypal IPN Notification related information saved earlier
	 * 
	 * @param txnId
	 *            Paypal IPN Notification's Transaction ID
	 * @return {@link IpnInfo}
	 * @throws Exception
	 */
	public IpnInfo getIpnInfo(final String txnId) throws Exception {
		IpnInfo ipnInfo = null;
		/**
		 * Implementation...
		 */
		return ipnInfo;
	}

}
