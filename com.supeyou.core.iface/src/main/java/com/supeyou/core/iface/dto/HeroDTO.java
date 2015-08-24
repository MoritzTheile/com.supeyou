package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.URLType;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class HeroDTO extends AbstrDTO<HeroIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type imageURL;
	private URLType websiteURL;
	private URLType videoURL;
	private SingleLineString256Type comment;
	private SingleLineString256Type name;
	private SingleLineString256Type paypalAccount;
	private boolean active = true;

	public HeroDTO() {

	}

	public HeroDTO(HeroIDType id) {
		setId(id);
	}

	public HeroDTO(String id) {
		setId(new HeroIDType(id));
	}

	@Override
	public void setId(HeroIDType id) {
		super.setId(id);
	}

	@Override
	public HeroIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getComment() {
		return comment;
	}

	public void setComment(SingleLineString256Type comment) {
		this.comment = comment;
	}

	public SingleLineString256Type getImageURL() {
		return imageURL;
	}

	public void setImageURL(SingleLineString256Type imageURL) {
		this.imageURL = imageURL;
	}

	public URLType getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(URLType websiteURL) {
		this.websiteURL = websiteURL;
	}

	public URLType getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(URLType videoURL) {
		this.videoURL = videoURL;
	}

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean hidden) {
		this.active = hidden;
	}

	public SingleLineString256Type getPaypalAccount() {
		return paypalAccount;
	}

	public void setPaypalAccount(SingleLineString256Type paypalAccount) {
		this.paypalAccount = paypalAccount;
	}

}
