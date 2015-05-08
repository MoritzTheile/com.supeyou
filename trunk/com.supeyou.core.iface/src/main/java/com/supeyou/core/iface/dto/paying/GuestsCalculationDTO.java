package com.supeyou.core.iface.dto.paying;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.supeyou.core.iface.dto.Guest2GuestGroupDTO;
import com.supeyou.core.iface.dto.GuestDTO;
import com.supeyou.crudie.iface.datatype.types.AmountType;

public class GuestsCalculationDTO implements Serializable {

	private static final long serialVersionUID = -70983926701881651L;

	private AmountType overallCosts;

	private AmountType overallOwnCosts;

	private AmountType overallOpenCosts;

	private Map<GuestDTO, Guest2GuestGroupDTO> guestDTO2Guest2GuestGroupDTO;

	private GuestCalculationDTO groupLeader;

	private List<GuestCalculationDTO> guestCalculationDTOs = new ArrayList<>();

	private String name;

	// zero arg constructor
	public GuestsCalculationDTO() {
	}

	public AmountType getOverallCosts() {
		return overallCosts;
	}

	public void setOverallCosts(AmountType overallCosts) {
		this.overallCosts = overallCosts;
	}

	public AmountType getOverallOwnCosts() {
		return overallOwnCosts;
	}

	public void setOverallOwnCosts(AmountType overallOwnCosts) {
		this.overallOwnCosts = overallOwnCosts;
	}

	public AmountType getOverallOpenCosts() {
		return overallOpenCosts;
	}

	public void setOverallOpenCosts(AmountType overallOpenCosts) {
		this.overallOpenCosts = overallOpenCosts;
	}

	public List<GuestCalculationDTO> getGuestCalculationDTOs() {
		return guestCalculationDTOs;
	}

	public void setGustCalculationDTOs(List<GuestCalculationDTO> gustCalculationDTOs) {
		this.guestCalculationDTOs = gustCalculationDTOs;
	}

	public Map<GuestDTO, Guest2GuestGroupDTO> getGuestDTO2Guest2GuestGroupDTO() {
		return guestDTO2Guest2GuestGroupDTO;
	}

	public void setGuestDTO2Guest2GuestGroupDTO(Map<GuestDTO, Guest2GuestGroupDTO> guestDTO2grouprole) {
		this.guestDTO2Guest2GuestGroupDTO = guestDTO2grouprole;
	}

	public GuestCalculationDTO getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(GuestCalculationDTO groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
