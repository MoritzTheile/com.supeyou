package com.supeyou.core.web.client.view.heropage.donate.paypal;

import com.google.gwt.user.client.ui.HTML;

public class PayPalWidget extends WidgetView {

	public PayPalWidget(String business_TOKEN, String item_name_TOKEN, String item_number_TOKEN) {
		String html = htmlTemplate;
		html = html.replaceAll("business_TOKEN", business_TOKEN);
		html = html.replaceAll("item_name_TOKEN", item_name_TOKEN);
		html = html.replaceAll("item_number_TOKEN", item_number_TOKEN);
		root.add(new HTML(html));
	}

	private final String htmlTemplate = "" +
			"<form target=\"paypal\" action=\"https://www.sandbox.paypal.com/cgi-bin/webscr\" method=\"post\">" +
			"<input type=\"hidden\" name=\"cmd\" value=\"_donations\">" +
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
			"<input type=\"hidden\" name=\"return\" value=\"https://www.yourwebsite.com/success.html\">" +
			"<input type=\"hidden\" name=\"cancel_return\" value=\"https://www.yourwebsite.com/cancel.html\">" +
			// "Enter the amount you would like to donate:&nbsp;" +
			// "<!-- a3, Required, Regular Subscription Price -->" +
			// "<input type=\"text\" name=\"a3\" size=\"4\" maxlength=\"7\">" +
			"<input type=\"hidden\" name=\"a3\" value=\"1\" >" +
			// "<br><br>" +
			// "<!-- src, Optional, Recurring Payments, 0 no recur, 1 payments recur -->" +
			"<input type=\"hidden\" name=\"src\" value=\"1\">" +
			// "<!--  -->" +
			// "Select the length of the desired Billing Cycle:&nbsp;" +
			// "<!-- p3, Required, Duration based on the value of t3 -->" +
			// "<select name=\"p3\">" +
			// "<option value=\"1\">1</option>" +
			// "<option value=\"2\">2</option>" +
			// "<option value=\"3\">3</option>" +
			// "<option value=\"4\">4</option>" +
			// "<option value=\"5\">5</option>" +
			// "<option value=\"6\">6</option>" +
			// "<option value=\"7\">7</option>" +
			// "<option value=\"15\">15</option>" +
			// "<option value=\"30\">30</option>" +
			// "</select>" +
			// "<br><br>" +
			"<input type=\"hidden\" name=\"p3\" value=\"1\">" +
			// "<!--  -->" +
			// "Select how often you would like to Donate:&nbsp;" +
			// "<!-- t3, Required, Subscription Units D W M Y, -->" +
			// "<select name=\"t3\">" +
			// "<option value=\"D\">Day(s)</option>" +
			// "<option value=\"W\">Week(s)</option>" +
			// "<option value=\"M\">Month(s)</option>" +
			// "<option value=\"Y\">Year(s)</option>" +
			// "</select>" +
			// "<br><br>" +
			"<input type=\"hidden\" name=\"t3\" value=\"M\">" +
			// "<!--  -->" +
			// "Select the number of Donation Payments you would like to make:&nbsp;" +
			// "<!-- srt, Optional, Recurring Times, must be used with src -->" +
			// "<select name=\"srt\">" +
			// "<option value=\"\">Until Canceled</option>" +
			// "<option value=\"1\">1</option>" +
			// "<option value=\"2\">2</option>" +
			// "<option value=\"3\">3</option>" +
			// "<option value=\"6\">6</option>" +
			// "<option value=\"12\">12</option>" +
			// "<option value=\"18\">18</option>" +
			// "<option value=\"24\">24</option>" +
			// "<option value=\"36\">36</option>" +
			// "</select>" +
			"<input type=\"hidden\" name=\"srt\" value=\"\">" +
			// "<!-- sra, Optional, Reattempt on failure 2 more time, value 0 or 1, if 0 no reattempt -->" +
			"<input type=\"hidden\" name=\"sra\" value=\"1\">" +
			// "<br><br>" +
			// "Thank you for your Donation," +
			// "<br>" +
			// "Please Select your Free Gift:&nbsp;&nbsp;" +
			// "<input type=\"hidden\" name=\"on0\" value=\"Free Gift Choice\">" +
			// "<select name=\"os0\">" +
			// "<option value=\"Solar Calculator\">Solar Calculator</option>" +
			// "<option value=\"Key Chain Light\">Key Chain Light</option>" +
			// "</select>" +
			// "<br><br>" +
			// "<!--  -->" +
			"<input type=\"hidden\" name=\"bn\" value=\"PP-SubscriptionsBF:btn_donateCC_LG.gif:NonHosted\">" +

			"<div class=\"donate-button-slot\"><input type=\"image\" src=\"https://www.paypal.com/en_US/i/btn/btn_donateCC_LG.gif\" border=\"0\" name=\"submit\" alt=\"PayPal - The safer, easier way to pay online!\"><div class=\"\">1\u20AC per month</div></div>" +
			"<img alt=\"\" border=\"0\" src=\"https://www.paypal.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">" +
			"</form>";

}
