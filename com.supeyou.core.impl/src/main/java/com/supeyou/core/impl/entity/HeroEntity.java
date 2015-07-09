package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "HeroEntity")
public class HeroEntity extends AbstrEntity<HeroIDType> {

	private String imageURL;

	private String websiteURL;

	private String comment;

	public SingleLineString256Type getComment() {
		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type comment) {
		if (comment != null) {
			this.comment = comment.value();
		} else {
			this.comment = null;
		}
	}

	public void setImageURL(SingleLineString256Type imageURL) {
		if (imageURL != null) {
			this.imageURL = imageURL.value();
		} else {
			this.imageURL = null;
		}
	}

	public SingleLineString256Type getImageURL() {
		if (imageURL == null) {
			return null;
		}
		return new SingleLineString256Type(imageURL);
	}

	public void setWebsiteURL(SingleLineString256Type websiteURL) {
		if (websiteURL != null) {
			this.websiteURL = websiteURL.value();
		} else {
			this.websiteURL = null;
		}
	}

	public SingleLineString256Type getWebsiteURL() {
		if (websiteURL == null) {
			return null;
		}
		return new SingleLineString256Type(websiteURL);
	}

	@Override
	public HeroIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new HeroIDType(dbid);
	}

	@Override
	public void setId(HeroIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}