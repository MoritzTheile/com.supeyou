package com.supeyou.core.web.client.view.heropage.donate.paypal;

import com.google.gwt.user.client.ui.HTML;

public class PayPalWidget extends WidgetView {

	public PayPalWidget(
			String cmd_TOKEN, // _xclick-subscriptions _donations
			String amount_TOKEN,
			String return_TOKEN, //
			String notify_url_TOKEN, //
			String business_TOKEN,
			String item_name_TOKEN,
			String item_number_TOKEN,
			String labelText)
	{

		label.setText(labelText);

		String html = htmlTemplate;
		html = html.replaceAll("cmd_TOKEN", cmd_TOKEN);
		html = html.replaceAll("amount_TOKEN", amount_TOKEN);
		html = html.replaceAll("return_TOKEN", return_TOKEN);
		html = html.replaceAll("notify_url_TOKEN", notify_url_TOKEN);
		html = html.replaceAll("business_TOKEN", business_TOKEN);
		html = html.replaceAll("item_name_TOKEN", item_name_TOKEN);
		html = html.replaceAll("item_number_TOKEN", item_number_TOKEN);

		// { // TODO: improve me
		// if (GWT.getHostPageBaseURL().toLowerCase().contains("supeyou.com")) {
		// html = html.replaceAll("endpoint_TOKEN", "https://www.paypal.com/cgi-bin/webscr");
		// } else {
		html = html.replaceAll("endpoint_TOKEN", "https://www.paypal.com/cgi-bin/webscr");
		// }
		// }
		HTML htmlWidget = new HTML(html);
		htmlWidget.addStyleName("buttonform");
		root.add(htmlWidget);
	}

	// https://developer.paypal.com/docs/classic/paypal-payments-standard/integration-guide/Appx_websitestandard_htmlvariables/
	private final String htmlTemplate = "" +
			"<form target=\"paypal\" action=\"endpoint_TOKEN\" method=\"post\">" +
			"<input type=\"hidden\" name=\"cmd\" value=\"cmd_TOKEN\">" +
			// "<input type=\"hidden\" name=\"cmd\" value=\"_xclick-subscriptions\">" +
			// "<!-- <input type=\"hidden\" name=\"redirect_cmd\" value=\"_xclick-subscriptions\"> -->" +
			"<input type=\"hidden\" name=\"business\" value=\"business_TOKEN\">" +
			"<input type=\"hidden\" name=\"item_name\" value=\"item_name_TOKEN\">" +
			"<input type=\"hidden\" name=\"item_number\" value=\"item_number_TOKEN\">" +
			"<input type=\"hidden\" name=\"no_note\" value=\"1\">" +
			"<!-- no_shipping value 2 needed to send the free gift -->" +
			"<input type=\"hidden\" name=\"no_shipping\" value=\"2\">" +
			"<input type=\"hidden\" name=\"tax\" value=\"0\">" +
			"<input type=\"hidden\" name=\"lc\" value=\"DE\">" +
			"<input type=\"hidden\" name=\"currency_code\" value=\"EUR\">" +
			"<input type=\"hidden\" name=\"return\" value=\"return_TOKEN\">" +
			"<input type=\"hidden\" name=\"notify_url\" value=\"notify_url_TOKEN\">" +
			// "Enter the amount you would like to donate:&nbsp;" +
			"<input type=\"hidden\" name=\"a3\" value=\"amount_TOKEN\" >" +
			// "<br><br>" +
			// "<!-- src, Optional, Recurring Payments, 0 no recur, 1 payments recur -->" +
			"<input type=\"hidden\" name=\"src\" value=\"1\">" +
			// "Select the number of Donation Payments you would like to make:&nbsp;" +
			// "<!-- srt, Optional, Recurring Times, must be used with src -->" +
			// "<input type=\"hidden\" name=\"srt\" value=\"\">" +
			// "<!--  -->" +
			// "Select the length of the desired Billing Cycle:&nbsp;" +
			// "<!-- p3, Required, Duration based on the value of t3 -->" +
			"<input type=\"hidden\" name=\"p3\" value=\"1\">" +
			// "<!--  -->" +
			// "Select how often you would like to Donate:&nbsp;" +
			// "<!-- t3, Required, Subscription Units D W M Y, -->" +
			// "<select name=\"t3\">" +
			"<input type=\"hidden\" name=\"t3\" value=\"M\">" +
			// "<!-- sra, Optional, Reattempt on failure 2 more time, value 0 or 1, if 0 no reattempt -->" +
			"<input type=\"hidden\" name=\"sra\" value=\"1\">" +

			// "<input type=\"hidden\" name=\"on0\" value=\"Free Gift Choice\">" +
			// "<select name=\"os0\">" +

			// "<input type=\"hidden\" name=\"bn\" value=\"PP-SubscriptionsBF:btn_donateCC_LG.gif:NonHosted\">" +

			"<div class=\"donate-button-slot\"><input type=\"image\" src=\"https://www.paypal.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\"></div>" +
			"<img alt=\"\" border=\"0\" src=\"https://www.paypal.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">" +
			"</form>";

}
