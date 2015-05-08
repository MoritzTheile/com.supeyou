package com.hotelorga.core.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.GuestGroupFetchQuery;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.core.impl.initialdata.InitialCoreData;
import com.hotelorga.core.impl.util.STATICS;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.impl.FileCRUDServiceImpl;
import com.hotelorga.foundation.impl.initialdata.InitialData;

public class CalculationsTest {

	@Test
	public void testGetDates() throws Exception {

		DateType from = new DateType("2015-01-01");
		DateType to = new DateType("2015-01-31");

		List<DateType> dates = STATICS.getDates(from, to);

		Assert.assertEquals(31, dates.size());

		Assert.assertTrue(dates.contains(new DateType("2015-01-01")));
		Assert.assertTrue(dates.contains(new DateType("2015-01-15")));
		Assert.assertTrue(dates.contains(new DateType("2015-01-31")));

	}

	@Test
	public void tesasdf() throws Exception {

		DateType fromDate = new DateType("2015-04-01");
		GuestCalculationDTO guestCalculationDTO = GuestCRUDServiceImpl.i().getGuestCalculationDTO(InitialData.i().initialAdmin, InitialCoreData.i().guestDTO_FatherBlue, fromDate, new DateType("2015-04-30"));

		Assert.assertNotNull(guestCalculationDTO);

		Assert.assertEquals(guestCalculationDTO.getRoomsOfDate(fromDate).size(), 1);

		Assert.assertEquals(guestCalculationDTO.getRoomsOfDate(fromDate).iterator().next().getDtoB().getName(), InitialCoreData.i().room1.getName());

		Assert.assertEquals(guestCalculationDTO.getRoomsOfDate(new DateType("2015-04-22")).iterator().next().getDtoB().getName(), InitialCoreData.i().room1.getName());

		String string = "guest=" + guestCalculationDTO.getGuestDTO().getFirstname() + " " + guestCalculationDTO.getGuestDTO().getLastname() + "(" + guestCalculationDTO.getGuestDTO().getDateOfBirth() + ")";

		for (DateType date : guestCalculationDTO.getDates()) {

			string += "\n    " + date + ":";

			for (Guest2RoomDTO guest2RoomDTO : guestCalculationDTO.getRoomsOfDate(date)) {
				string += "\n        room=" + guest2RoomDTO.getDtoB().getName();
			}
			for (Guest2AcceptanceDTO guest2AcceptanceDTO : guestCalculationDTO.getAcceptancesOfDate(date)) {
				string += "\n        Acceptance=" + guest2AcceptanceDTO.getDtoB().getTmpName() + " (" + guest2AcceptanceDTO.getDtoB().getFromDate() + "-" + guest2AcceptanceDTO.getDtoB().getToDate() + ")";
			}

		}

		System.out.println(string);

	}

	@Test
	public void testBillRendering() throws Exception {

		String filename = "HotelOrga_bill.doc.xml";

		try {

			File file = new File(filename);

			if (file.exists()) {
				file.delete();
			}

			GuestsCalculationDTO guestsCalculationDTO = GuestCRUDServiceImpl.i().getGuestsCalculationDTO(InitialData.i().initialAdmin, InitialCoreData.i().guestGroupDTO_blue, new DateType("2015-04-01"), new DateType("2015-04-30"));

			FileIDType fileId = GuestCRUDServiceImpl.i().renderBill(InitialData.i().initialAdmin, guestsCalculationDTO);

			byte[] data = FileCRUDServiceImpl.i().getData(InitialData.i().initialAdmin, fileId);

			FileOutputStream stream = new FileOutputStream(filename);

			try {
				stream.write(data);
			} finally {
				stream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertTrue(new File(filename).exists());

	}

	@Test
	public void testExcelGeneration() throws Exception {

		String filename = "RechnungsUeberblick.xlsx";

		try {

			File file = new File(filename);

			if (file.exists()) {
				file.delete();
			}

			List<GuestsCalculationDTO> guestsCalculationDTOs = new ArrayList<>();
			for (GuestGroupDTO guestGroupDTO : GuestGroupCRUDServiceImpl.i().fetchList(InitialData.i().initialAdmin, new Page(), new GuestGroupFetchQuery())) {
				guestsCalculationDTOs.add(GuestCRUDServiceImpl.i().getGuestsCalculationDTO(InitialData.i().initialAdmin, guestGroupDTO, new DateType("2015-04-01"), new DateType("2015-04-30")));
			}

			FileIDType fileId = GuestCRUDServiceImpl.i().renderExcelOverview(InitialData.i().initialAdmin, guestsCalculationDTOs);

			byte[] data = FileCRUDServiceImpl.i().getData(InitialData.i().initialAdmin, fileId);

			FileOutputStream stream = new FileOutputStream(filename);

			try {
				stream.write(data);
			} finally {
				stream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertTrue(new File(filename).exists());

	}

}
