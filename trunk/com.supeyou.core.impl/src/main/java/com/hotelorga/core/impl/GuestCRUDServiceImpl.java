package com.hotelorga.core.impl;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.hotelorga.core.iface.GuestCRUDService;
import com.hotelorga.core.iface.dto.Guest2AcceptanceDTO;
import com.hotelorga.core.iface.dto.Guest2AcceptanceFetchQuery;
import com.hotelorga.core.iface.dto.Guest2GuestGroupDTO;
import com.hotelorga.core.iface.dto.Guest2GuestGroupFetchQuery;
import com.hotelorga.core.iface.dto.Guest2RoomDTO;
import com.hotelorga.core.iface.dto.Guest2RoomFetchQuery;
import com.hotelorga.core.iface.dto.GuestDTO;
import com.hotelorga.core.iface.dto.GuestFetchQuery;
import com.hotelorga.core.iface.dto.GuestGroupDTO;
import com.hotelorga.core.iface.dto.paying.GuestCalculationDTO;
import com.hotelorga.core.iface.dto.paying.GuestsCalculationDTO;
import com.hotelorga.core.impl.entity.GuestEntity;
import com.hotelorga.core.impl.paying.CalculationHelper;
import com.hotelorga.core.impl.rendering.Renderer;
import com.hotelorga.core.impl.util.ExcelGenerator;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.Page;
import com.hotelorga.foundation.iface.datatype.enums.MIMETYPE;
import com.hotelorga.foundation.iface.datatype.types.AmountType;
import com.hotelorga.foundation.iface.datatype.types.DateType;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.impl.AbstrCRUDServiceImpl;
import com.hotelorga.foundation.impl.FileCRUDServiceImpl;
import com.hotelorga.foundation.impl.entity.UserEntity;

public class GuestCRUDServiceImpl extends AbstrCRUDServiceImpl<GuestDTO, GuestEntity, GuestFetchQuery> implements GuestCRUDService {
	private static final Logger log = Logger.getLogger(GuestCRUDServiceImpl.class.getName());

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, GuestFetchQuery query) {

		String whereClause = "";

		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {

			whereClause += " " + "lower(firstname)" + " like '%" + query.getSearchString().toLowerCase() + "%' OR ";
			whereClause += " " + "lower(lastname)" + " like '%" + query.getSearchString().toLowerCase() + "%' OR ";
			whereClause += " " + "lower(comment)" + " like '%" + query.getSearchString().toLowerCase() + "%' ";

		}

		if (!"".equals(whereClause)) {
			whereClause = " WHERE " + whereClause;
		}

		return whereClause;
	}

	// Singleton

	private static GuestCRUDServiceImpl service;

	private GuestCRUDServiceImpl() {
		super(GuestDTO.class, GuestEntity.class);
	}

	public static GuestCRUDServiceImpl i() {
		if (service == null) {
			service = new GuestCRUDServiceImpl();
		}
		return service;
	}

	@Override
	public GuestCalculationDTO getGuestCalculationDTO(UserDTO actorDTO, GuestDTO guestDTO, DateType from, DateType to) throws CRUDException {

		Guest2RoomFetchQuery guest2RoomFetchQuery = new Guest2RoomFetchQuery();
		guest2RoomFetchQuery.setIdA(guestDTO.getId());
		guest2RoomFetchQuery.setFrom(from);
		guest2RoomFetchQuery.setTo(to);
		List<Guest2RoomDTO> guest2RoomDTOs = Guest2RoomCRUDServiceImpl.i().fetchList(actorDTO, new Page(), guest2RoomFetchQuery);

		Guest2AcceptanceFetchQuery guest2AcceptanceFetchQuery = new Guest2AcceptanceFetchQuery();
		guest2AcceptanceFetchQuery.setIdA(guestDTO.getId());
		guest2AcceptanceFetchQuery.setFrom(from);
		guest2AcceptanceFetchQuery.setTo(to);
		List<Guest2AcceptanceDTO> guest2AcceptanceDTOs = Guest2AcceptanceCRUDServiceImpl.i().fetchList(actorDTO, new Page(), guest2AcceptanceFetchQuery);

		return CalculationHelper.getGuestCalculationDTO(guestDTO, from, to, guest2RoomDTOs, guest2AcceptanceDTOs);

	}

	@Override
	public GuestsCalculationDTO getGuestsCalculationDTO(UserDTO actorDTO, GuestGroupDTO guestGroupDTO, DateType from, DateType to) throws CRUDException {

		GuestsCalculationDTO guestsCalculationDTO = new GuestsCalculationDTO();
		if (guestGroupDTO.getName() != null) {
			guestsCalculationDTO.setName(guestGroupDTO.getName().value());
		}
		Guest2GuestGroupFetchQuery dtoQuery = new Guest2GuestGroupFetchQuery();
		dtoQuery.setIdB(guestGroupDTO.getId());

		Map<GuestDTO, Guest2GuestGroupDTO> guestDTO2Guest2GuestGroupDTO = new HashMap<GuestDTO, Guest2GuestGroupDTO>();

		for (Guest2GuestGroupDTO guest2GuestGroupDTO : Guest2GuestGroupCRUDServiceImpl.i().fetchList(actorDTO, new Page(), dtoQuery)) {
			GuestCalculationDTO guestCalculationDTO = getGuestCalculationDTO(actorDTO, guest2GuestGroupDTO.getDtoA(), from, to);
			guestsCalculationDTO.getGuestCalculationDTOs().add(guestCalculationDTO);
			guestDTO2Guest2GuestGroupDTO.put(guest2GuestGroupDTO.getDtoA(), guest2GuestGroupDTO);

			if (guest2GuestGroupDTO.getGroupLeader() != null && guest2GuestGroupDTO.getGroupLeader()) {
				guestsCalculationDTO.setGroupLeader(guestCalculationDTO);
			}
		}

		guestsCalculationDTO.setGuestDTO2Guest2GuestGroupDTO(guestDTO2Guest2GuestGroupDTO);

		int overallCosts = CalculationHelper.calculateOverallCosts(guestsCalculationDTO.getGuestCalculationDTOs());
		int overallAcceptedCosts = CalculationHelper.calculateOverallAcceptedCosts(guestsCalculationDTO.getGuestCalculationDTOs());

		guestsCalculationDTO.setOverallCosts(new AmountType(overallCosts));
		guestsCalculationDTO.setOverallOwnCosts(new AmountType(overallCosts - overallAcceptedCosts));
		guestsCalculationDTO.setOverallOpenCosts(new AmountType(overallAcceptedCosts));

		return guestsCalculationDTO;

	}

	@Override
	public FileIDType renderBill(
			UserDTO actorDTO,
			GuestsCalculationDTO guestsCalculationDTO
			) throws CRUDException {

		String templateString = Renderer.getTemplateString();
		String renderedString = Renderer.renderBill(guestsCalculationDTO, templateString);

		FileDTO fileDTO = new FileDTO();

		fileDTO.setMimetype(MIMETYPE.APPLICATION_MSWORD);
		GuestCalculationDTO fileNameGivingGuestCalculationDTO = guestsCalculationDTO.getGroupLeader();
		if (fileNameGivingGuestCalculationDTO == null) {
			for (GuestCalculationDTO guestCalculationDTO : guestsCalculationDTO.getGuestCalculationDTOs()) {
				fileNameGivingGuestCalculationDTO = guestCalculationDTO;
				break;
			}
		}
		if (fileNameGivingGuestCalculationDTO != null) {
			fileDTO.setName(new SingleLineString256Type("Rechnung." + fileNameGivingGuestCalculationDTO.getFrom() + "." + fileNameGivingGuestCalculationDTO.getTo() + "." + clean(guestsCalculationDTO.getName()) + ".doc.xml"));
		} else {
			fileDTO.setName(new SingleLineString256Type("Rechnung." + clean(guestsCalculationDTO.getName()) + ".doc.xml"));
		}

		FileIDType fileIDType = FileCRUDServiceImpl.i().updadd(actorDTO, fileDTO).getId();

		FileCRUDServiceImpl.i().setData(actorDTO, fileIDType, renderedString.getBytes(Charset.forName("UTF-8")));

		return fileIDType;

	}

	@Override
	public FileIDType renderExcelOverview(
			UserDTO actorDTO,
			List<GuestsCalculationDTO> guestsCalculationDTOList
			) throws CRUDException {

		FileDTO fileDTO = new FileDTO();

		fileDTO.setMimetype(MIMETYPE.APPLICATION_MSEXCEL);

		fileDTO.setName(new SingleLineString256Type("Rechnungsuebersicht.xlsx"));

		FileIDType fileIDType = FileCRUDServiceImpl.i().updadd(actorDTO, fileDTO).getId();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			ExcelGenerator.generate(guestsCalculationDTOList, byteArrayOutputStream);
		} catch (Exception e) {
			log.log(Level.WARNING, "Problems during generation of excel", e);
		} finally {
			closeStream(byteArrayOutputStream);
		}

		FileCRUDServiceImpl.i().setData(actorDTO, fileIDType, byteArrayOutputStream.toByteArray());

		return fileIDType;

	}

	private void closeStream(ByteArrayOutputStream byteArrayOutputStream) {
		try {
			byteArrayOutputStream.close();
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception on rendering excel", e);
		}
	}

	private static String clean(String result) {
		return result.replaceAll("[^a-zA-Z0-9_\\.]", "");

	}

}
