package com.supeyou.core.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class HeroDTO extends AbstrDTO<HeroIDType> implements Serializable {

	private static final long serialVersionUID = 824618946818L;

	private SingleLineString256Type name;
	private SingleLineString256Type imageURL;
	private SingleLineString256Type websiteURL;
	private SingleLineString256Type comment;

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

	public SingleLineString256Type getName() {
		return name;
	}

	public void setName(SingleLineString256Type name) {
		this.name = name;
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

	public SingleLineString256Type getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(SingleLineString256Type websiteURL) {
		this.websiteURL = websiteURL;
	}

}
