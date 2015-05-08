package com.supeyou.core.impl.rendering;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.supeyou.core.iface.datatype.enums.GROUPROLE;
import com.supeyou.core.iface.datatype.enums.TITLE;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.core.iface.dto.RoomDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.core.impl.paying.CalculationHelper;
import com.supeyou.core.impl.util.STATICS;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.DateType;

public class Renderer {

	private static final Logger log = Logger.getLogger(Renderer.class.getName());

	public static String getTemplateString() {

		String name = "MultiRechnung.template.xml";

		InputStream inputStream = null;

		try {

			inputStream = Renderer.class.getResourceAsStream("/com/hotelorga/core/impl/rendering/" + name);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;

			byte[] data = new byte[16384];

			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			byte[] allData = buffer.toByteArray();

			Charset charset = Charset.forName("UTF-8");

			return new String(allData, charset);

		} catch (Exception e) {

			log.log(Level.WARNING, "problems on reading resource", e);

		} finally {

			try {
				inputStream.close();
			} catch (IOException e) {
				log.log(Level.WARNING, "problems on closing input stream", e);
			}

		}
		return null;

	}

	public static String renderBill(GuestsCalculationDTO guestsCalculationDTO, String templateString) throws CRUDException {

		{
			String token = "XDatum";
			try {
				String replacement = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
				templateString = templateString.replaceAll(token, replacement);
			} catch (Exception e) {
				log.info("problems during rendering, the value for " + token + " might not have been set");
			}
		}

		{
			String token = "XRechnungsnummer";
			try {
				String replacement = System.currentTimeMillis() + "";
				templateString = templateString.replaceAll(token, replacement);
			} catch (Exception e) {
				log.info("problems during rendering, the value for " + token + " might not have been set");
			}
		}
		{
			List<Map<String, String>> key2valueList = new ArrayList<Map<String, String>>();

			for (GuestCalculationDTO guestCalculationDTO : moveLeaderToFirstPosition(guestsCalculationDTO)) {
				Map<String, String> key2value = new HashMap<String, String>();
				key2valueList.add(key2value);
				{
					String replacement = renderDate(guestCalculationDTO.getGuestDTO().getDateOfBirth());
					key2value.put("XGebDatum", replacement);
				}
				{
					String replacement = renderName(guestCalculationDTO.getGuestDTO());
					key2value.put("XGast", replacement);
				}
				{
					String replacement = renderGroupRole(guestsCalculationDTO.getGuestDTO2Guest2GuestGroupDTO().get(guestCalculationDTO.getGuestDTO()));
					key2value.put("XRolle", replacement);
				}

			}
			templateString = processRepetitionArea(templateString, "XGuestListRepetition", "<w:tr", "</w:tr>", key2valueList);
		}
		{
			List<Map<String, String>> key2valueList = new ArrayList<Map<String, String>>();

			if (allStaysAreEqual(guestsCalculationDTO))

				for (GuestCalculationDTO guestCalculationDTO : moveLeaderToFirstPosition(guestsCalculationDTO)) {
					for (Guest2RoomDTO guest2RoomDTO : guestCalculationDTO.getGuest2RoomDTOs()) {

						Map<String, String> key2value = new HashMap<String, String>();
						key2valueList.add(key2value);
						{
							key2value.put("XGast", "siehe oben");
						}
						if (guest2RoomDTO.getDtoB().getName() != null) {
							String replacement = guest2RoomDTO.getDtoB().getName().value();
							key2value.put("XZimmer", replacement);
						}
						{
							String replacement = renderDate(getRelevantFromDate(guest2RoomDTO.getFromDate(), guestCalculationDTO.getFrom()));
							key2value.put("Xvon", replacement);

						}
						{
							String replacement = renderDate(getRelevantToDate(guest2RoomDTO.getToDate(), guestCalculationDTO.getTo()));
							key2value.put("Xbis", replacement);
						}

					}
					// there is only one line necessary in this case:
					break;
				}
			else
				for (GuestCalculationDTO guestCalculationDTO : moveLeaderToFirstPosition(guestsCalculationDTO)) {

					for (Guest2RoomDTO guest2RoomDTO : guestCalculationDTO.getGuest2RoomDTOs()) {
						Map<String, String> key2value = new HashMap<String, String>();
						key2valueList.add(key2value);
						{
							String replacement = renderName(guest2RoomDTO.getDtoA());
							key2value.put("XGast", replacement);
						}
						if (guest2RoomDTO.getDtoB().getName() != null) {
							String replacement = guest2RoomDTO.getDtoB().getName().value();
							key2value.put("XZimmer", replacement);
						}
						{
							String replacement = renderDate(getRelevantFromDate(guest2RoomDTO.getFromDate(), guestCalculationDTO.getFrom()));
							key2value.put("Xvon", replacement);

						}
						{
							String replacement = renderDate(getRelevantToDate(guest2RoomDTO.getToDate(), guestCalculationDTO.getTo()));
							key2value.put("Xbis", replacement);
						}
					}
				}

			templateString = processRepetitionArea(templateString, "XZiBelRepetition", "<w:tr", "</w:tr>", key2valueList);

		}
		{
			List<Map<String, String>> key2valueList = new ArrayList<Map<String, String>>();
			int i = 0;
			for (GuestCalculationDTO guestCalculationDTO : moveLeaderToFirstPosition(guestsCalculationDTO)) {
				i++;

				Map<String, String> key2value = new HashMap<String, String>();
				key2valueList.add(key2value);

				Integer acceptedCosts = CalculationHelper.getAcceptedCost(guestCalculationDTO).intValue();

				Integer costs = guestCalculationDTO.getCost().value();

				{
					String replacement = renderName(guestCalculationDTO.getGuestDTO());
					key2value.put("XGast", replacement);
				}
				{
					String replacement = getNumberOfBilledDays(guestCalculationDTO) + "";
					key2value.put("XAnzUebernachtungen", replacement);
				}
				{
					String replacement = cent2euro(costs);
					key2value.put("XUebernachtungskosten", replacement);
				}
				{
					String replacement = "0,00";
					// wanted from customer: in the first cell always the unchanged fixedOwnCost value...:
					if (i == 1) {
						for (AcceptanceDTO acceptanceDTO : guestCalculationDTO.getAcceptances()) {
							if (acceptanceDTO.getFixOwnCosts() != null) {
								replacement = cent2euro(acceptanceDTO.getFixOwnCosts().value());
								break;
							}

						}
					}
					// String replacement = cent2euro(costs - acceptedCosts);
					key2value.put("XEigenanteil", replacement);
				}
				{
					String replacement = cent2euro((acceptedCosts /*- ownCosts*/));
					key2value.put("XUeberweisungsbetrag", replacement);
				}

			}
			templateString = processRepetitionArea(templateString, "XSummeRep", "<w:tr", "</w:tr>", key2valueList);
		}
		{
			String token = "XSummeUeberweisungsbetrag";
			try {
				String replacement = cent2euro(guestsCalculationDTO.getOverallCosts().value());
				templateString = templateString.replaceAll(token, replacement);
			} catch (Exception e) {
				log.info("problems during rendering, the value for " + token + " might not have been set");
			}
		}

		return templateString;
	}

	private static DateType getRelevantFromDate(DateType fromDate, DateType timeframeFrom) {
		if (STATICS.getDateAsTimestamp(fromDate) > STATICS.getDateAsTimestamp(timeframeFrom)) {
			return fromDate;
		}
		return timeframeFrom;
	}

	private static DateType getRelevantToDate(DateType toDate, DateType timeframeTo) {
		if (STATICS.getDateAsTimestamp(toDate) < STATICS.getDateAsTimestamp(timeframeTo)) {
			return toDate;
		}
		return timeframeTo;
	}

	private static List<GuestCalculationDTO> moveLeaderToFirstPosition(GuestsCalculationDTO guestsCalculationDTO) {
		List<GuestCalculationDTO> calculationDTOs = guestsCalculationDTO.getGuestCalculationDTOs();
		// moving group leader to first position
		for (GuestCalculationDTO guestCalculationDTO : new ArrayList<GuestCalculationDTO>(calculationDTOs)) {
			if (guestsCalculationDTO.getGroupLeader() != null && guestsCalculationDTO.getGroupLeader().equals(guestCalculationDTO)) {
				calculationDTOs.remove(guestCalculationDTO);
				calculationDTOs.add(0, guestCalculationDTO);
			}
		}
		return calculationDTOs;
	}

	private static String getNumberOfBilledDays(GuestCalculationDTO guestCalculationDTO) {

		int i = 0;

		for (DateType date : guestCalculationDTO.getDates()) {
			if (guestCalculationDTO.getRoomsOfDate(date).size() > 0) {
				i++;
			}
		}

		if (i >= 31) {
			// in hotel business a month has max 30 accountable days:
			return "30";
		}

		return i + "";
	}

	private static boolean allStaysAreEqual(GuestsCalculationDTO guestsCalculationDTO) {

		RoomDTO roomDTO = null;
		DateType fromDate = null;
		DateType toDate = null;

		for (GuestCalculationDTO guestCalculationDTO : guestsCalculationDTO.getGuestCalculationDTOs()) {

			for (Guest2RoomDTO guest2RoomDTO : guestCalculationDTO.getGuest2RoomDTOs()) {

				if (roomDTO != null && !roomDTO.equals(guest2RoomDTO.getDtoB())) {
					return false;
				}
				roomDTO = guest2RoomDTO.getDtoB();

				if (fromDate != null && !fromDate.equals(getRelevantFromDate(guest2RoomDTO.getFromDate(), guestCalculationDTO.getFrom()))) {
					return false;
				}
				fromDate = getRelevantFromDate(guest2RoomDTO.getFromDate(), guestCalculationDTO.getFrom());

				if (toDate != null && !toDate.equals(getRelevantToDate(guest2RoomDTO.getToDate(), guestCalculationDTO.getTo()))) {
					return false;
				}
				toDate = getRelevantToDate(guest2RoomDTO.getToDate(), guestCalculationDTO.getTo());
			}
		}

		return true;
	}

	private static String renderGroupRole(Guest2GuestGroupDTO guest2GuestGroupDTO) {
		if (GROUPROLE.FATHER.equals(guest2GuestGroupDTO.getGroupRole())) {
			return "(Vater)";
		}
		if (GROUPROLE.MOTHER.equals(guest2GuestGroupDTO.getGroupRole())) {
			return "(Mutter)";
		}
		if (GROUPROLE.CHILD.equals(guest2GuestGroupDTO.getGroupRole())) {
			return "(Kind)";
		}
		return "";
	}

	private static String renderName(GuestDTO guestDTO) {
		String renderedName = "";
		if (guestDTO.getTitle().equals(TITLE.UNDEFINED)) {
			// nothing
		} else if (guestDTO.getTitle().equals(TITLE.MR)) {
			renderedName += "Herr ";
		} else if (guestDTO.getTitle().equals(TITLE.MRS)) {
			renderedName += "Frau ";
		}
		if (guestDTO.getLastname() != null) {
			renderedName += guestDTO.getLastname().value().toUpperCase() + " ";
		}
		if (guestDTO.getFirstname() != null) {
			renderedName += ", " + guestDTO.getFirstname() + " ";
		}
		return renderedName;
	}

	private static String renderDate(DateType value) {
		if (value == null) {
			return "";
		}
		String[] splittedDate = value.value().split("-");
		return splittedDate[2] + "." + splittedDate[1] + "." + splittedDate[0];

	}

	private static String processRepetitionArea(String template, String repetitionAreaMarker, String repetitionStartMarker, String repetitionEndMarker, List<Map<String, String>> replacementParameterList) {
		String repetitionTemplate = getRepetitionTemplate(template, repetitionAreaMarker, repetitionStartMarker, repetitionEndMarker);
		// System.out.println("repetition template:-----------------\n" + repetitionTemplate + "\n ---------");
		String repeatedAndFilledTemplate = "";
		for (Map<String, String> replacementParameters : replacementParameterList) {
			String processedTemplate = repetitionTemplate;
			for (String key : replacementParameters.keySet()) {
				processedTemplate = processedTemplate.replace(key, replacementParameters.get(key));
			}
			repeatedAndFilledTemplate += processedTemplate;
		}
		template = template.replace(repetitionTemplate, repeatedAndFilledTemplate);

		return template.replace(repetitionAreaMarker, "");

	}

	private static String getRepetitionTemplate(String string, String marker, String starttag, String endtag) {
		marker = Pattern.quote(marker);
		String quotedstarttag = Pattern.quote(starttag);
		endtag = Pattern.quote(endtag);
		// the breaking up in lines is necessary to have only one starttag per analysed area,
		// otherwise the first starttag would always be taken.
		for (String line : string.split(quotedstarttag)) {
			String resultInLine = getFirstSubstring(starttag + line, quotedstarttag + ".+?" + marker + ".+?" + endtag);
			if (resultInLine != null && !resultInLine.isEmpty()) {
				return resultInLine;
			}
		}
		return "";
	}

	private static String getFirstSubstring(String string, String regex) {
		// making regex non-Greedy:
		// regex = "(" + regex + ")*?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			// System.out.print("Start index: " + matcher.start());
			// System.out.print(" End index: " + matcher.end() + " ");
			return matcher.group();
		}
		return "";
	}

	private static String cent2euro(Integer centValue) {
		String centString = centValue + "";
		String euroString = "";
		if ((centString).length() == 1) {
			euroString = "0,0" + centString;
		} else if ((centString).length() == 2) {
			euroString = "0," + centString;
		} else {
			euroString = centString.substring(0, centString.length() - 2) + "," + centString.substring(centString.length() - 2, centString.length());
		}
		return euroString;
	}
}
