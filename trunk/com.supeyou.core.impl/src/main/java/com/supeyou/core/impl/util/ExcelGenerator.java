package com.supeyou.core.impl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.supeyou.core.iface.common.HOTELHELPER;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.Guest2RoomDTO;
import com.supeyou.core.iface.dto.paying.GuestCalculationDTO;
import com.supeyou.core.iface.dto.paying.GuestsCalculationDTO;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.types.DateType;

public class ExcelGenerator {

	public static void generate(List<GuestsCalculationDTO> guestsCalculationDTOList, ByteArrayOutputStream byteArrayOutputStream) throws IOException {

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Abrechnungsübersicht");

		int rowIndex = 0;
		fillNameRow(sheet.createRow((short) rowIndex++), guestsCalculationDTOList);
		fillHeaderRow(sheet.createRow((short) rowIndex++), wb.createCellStyle());
		int lfdNr = 0;
		int sum = 0;
		for (GuestsCalculationDTO guestsCalculationDTO : guestsCalculationDTOList) {
			lfdNr++;
			String guestCount = guestsCalculationDTO.getGuestCalculationDTOs().size() + "";

			int overallCosts = guestsCalculationDTO.getOverallCosts().value();
			sum += overallCosts;
			String overallCostsString = HELPER.cent2euro(overallCosts);
			String overallOwnCostsString = HELPER.cent2euro(guestsCalculationDTO.getOverallOwnCosts().value());
			String overallOpenCostsString = HELPER.cent2euro(guestsCalculationDTO.getOverallOpenCosts().value());

			int i = 0;
			List<GuestCalculationDTO> calculationDTOs = guestsCalculationDTO.getGuestCalculationDTOs();
			// moving group leader to first position
			for (GuestCalculationDTO guestCalculationDTO : new ArrayList<GuestCalculationDTO>(calculationDTOs)) {
				if (guestsCalculationDTO.getGroupLeader() != null && guestsCalculationDTO.getGroupLeader().equals(guestCalculationDTO)) {
					calculationDTOs.remove(guestCalculationDTO);
					calculationDTOs.add(0, guestCalculationDTO);
				}
			}

			for (GuestCalculationDTO guestCalculationDTO : calculationDTOs) {
				i++;

				if (i != 1) {

					guestCount = "";

				}

				if (i == guestsCalculationDTO.getGuestCalculationDTOs().size()) {

					fillDataRow(sheet.createRow((short) rowIndex), guestCalculationDTO, guestsCalculationDTO.getGuestDTO2Guest2GuestGroupDTO().get(guestCalculationDTO.getGuestDTO()), lfdNr + "", guestCount, overallCostsString, overallOwnCostsString, overallOpenCostsString);

				} else {

					fillDataRow(sheet.createRow((short) rowIndex), guestCalculationDTO, guestsCalculationDTO.getGuestDTO2Guest2GuestGroupDTO().get(guestCalculationDTO.getGuestDTO()), lfdNr + "", guestCount, "", "", "");

				}
				rowIndex++;
			}

			insertSeparationRow(sheet.createRow((short) rowIndex), wb.createCellStyle());
			rowIndex++;

		}

		rowIndex += 2;

		fillSumRow(sheet.createRow((short) rowIndex), sum);

		wb.write(byteArrayOutputStream);

		wb.close();
	}

	private static void insertSeparationRow(Row row, CellStyle cellStyle) {

		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < 15; i++) {
			row.createCell(i).setCellStyle(cellStyle);
		}
	}

	private static void fillNameRow(Row row, List<GuestsCalculationDTO> guestsCalculationDTOList) {

		for (GuestsCalculationDTO guestsCalculationDTO : guestsCalculationDTOList) {
			for (GuestCalculationDTO guestCalculationDTO : guestsCalculationDTO.getGuestCalculationDTOs()) {
				// using the very first guestCalculationDTO to figure out month and year
				row.createCell(1).setCellValue(toMonthAndYear(guestCalculationDTO.getFrom()));

				break;
			}
			break;
		}

		row.createCell(2).setCellValue("Hotel SupeYou");

	}

	private static String toMonthAndYear(DateType date) {
		String[] dateString = date.value().split("-");
		String result = "";
		if (dateString[1].equals("01")) {
			result += "Januar";
		}
		if (dateString[1].equals("02")) {
			result += "Februar";
		}
		if (dateString[1].equals("03")) {
			result += "März";
		}
		if (dateString[1].equals("04")) {
			result += "April";
		}
		if (dateString[1].equals("05")) {
			result += "Mai";
		}
		if (dateString[1].equals("06")) {
			result += "Juni";
		}
		if (dateString[1].equals("07")) {
			result += "Juli";
		}
		if (dateString[1].equals("08")) {
			result += "August";
		}
		if (dateString[1].equals("09")) {
			result += "Sept";
		}
		if (dateString[1].equals("10")) {
			result += "Oktober";
		}
		if (dateString[1].equals("11")) {
			result += "November";
		}
		if (dateString[1].equals("12")) {
			result += "Dezember";
		}
		result += " " + dateString[0];
		return result;
	}

	private static void fillHeaderRow(Row row, CellStyle cellStyle) {

		cellStyle.setFillForegroundColor((short) 31);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		int i = 0;

		// A
		createCell(row, cellStyle, i++, "lfd Nr");
		// B
		createCell(row, cellStyle, i++, "Name");
		// C
		createCell(row, cellStyle, i++, "Vorname");
		// D
		createCell(row, cellStyle, i++, "Geb-Dat");
		// E
		createCell(row, cellStyle, i++, "Zuständigkeit");
		// F
		createCell(row, cellStyle, i++, "Zimmer-Nr.");
		// G
		createCell(row, cellStyle, i++, "Personenzahl");
		// H
		createCell(row, cellStyle, i++, "von");
		// I
		createCell(row, cellStyle, i++, "bis");
		// J
		createCell(row, cellStyle, i++, "Tage");
		// K
		createCell(row, cellStyle, i++, "monatliche Kosten");
		// L
		createCell(row, cellStyle, i++, "Offener Betrag");
		// M
		createCell(row, cellStyle, i++, "Gesamtsumme");
		// N
		createCell(row, cellStyle, i++, "Eigenanteil");
		// O
		createCell(row, cellStyle, i++, "offener Betrag");

	}

	private static void createCell(Row row, CellStyle cellStyle, int i, String value) {
		Cell cell = row.createCell(i);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
	}

	private static Row fillDataRow(Row row, GuestCalculationDTO dto, Guest2GuestGroupDTO guest2GuestGroupDTO, String lfdNr, String guestCount, String overallCostsString, String overallOwnCostsString, String overallOpenCostsString) {

		row.createCell(0).setCellValue(lfdNr);

		row.createCell(1).setCellValue(dto.getGuestDTO().getLastname().value());
		row.createCell(2).setCellValue(dto.getGuestDTO().getFirstname().value());
		row.createCell(3).setCellValue(HELPER.date2string(dto.getGuestDTO().getDateOfBirth()));
		row.createCell(4).setCellValue(getAcceptanceNames(dto));
		row.createCell(5).setCellValue(getRoomNames(dto));
		row.createCell(6).setCellValue(guestCount);
		row.createCell(7).setCellValue(HELPER.date2string(dto.getFrom()));
		row.createCell(8).setCellValue(HELPER.date2string(dto.getTo()));
		row.createCell(9).setCellValue(HOTELHELPER.getDaysInHotel(dto) + "");
		row.createCell(10).setCellValue("510"); // Kind of nonsense but munich city wants it...
		row.createCell(11).setCellValue(HELPER.amount2eurostring(dto.getCost()));
		row.createCell(12).setCellValue(overallCostsString);
		row.createCell(13).setCellValue(overallOwnCostsString);
		row.createCell(14).setCellValue(overallOpenCostsString);

		return row;
	}

	private static void fillSumRow(Row row, int sum) {

		row.createCell(12).setCellValue(new Double(sum) / 100);

	}

	private static String getAcceptanceNames(final GuestCalculationDTO dto) {

		Set<String> names = new HashSet<>();

		for (final AcceptanceDTO acceptanceDTO : dto.getAcceptances()) {

			if (acceptanceDTO != null && acceptanceDTO.getTmpName() != null) {
				names.add(acceptanceDTO.getTmpName().value());
			}

		}

		final String seperator = ", ";
		String result = "";
		for (String name : names) {
			result += name + seperator;
		}
		if (result.endsWith(seperator)) {
			result = result.substring(0, result.length() - seperator.length());
		}
		return result;

	}

	private static String getRoomNames(final GuestCalculationDTO dto) {

		final String seperator = ", ";
		String result = "";

		for (final Guest2RoomDTO guest2RoomDTO : dto.getGuest2RoomDTOs()) {
			result += guest2RoomDTO.getDtoB().getName().value() + seperator;
		}

		if (result.endsWith(seperator)) {
			result = result.substring(0, result.length() - seperator.length());
		}
		return result;

	}

}
