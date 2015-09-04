package com.supeyou.core.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.URLType;
import com.supeyou.crudie.impl.entity.AbstrEntity;

@Entity
@Table(name = "HeroEntity")
public class HeroEntity extends AbstrEntity<HeroIDType> {

	private String name;

	private String imageURL;

	private String websiteURL;

	private String videoURL;

	private String comment;

	private String paypalAccount;

	private boolean active = false;

	@OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<User2HeroEntity> user2HeroCollection = new ArrayList<>();

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

	public SingleLineString256Type getPaypalAccount() {
		if (paypalAccount == null) {
			return null;
		}
		return new SingleLineString256Type(paypalAccount);
	}

	public void setPaypalAccount(SingleLineString256Type paypalAccount) {
		if (paypalAccount != null) {
			this.paypalAccount = paypalAccount.value();
		} else {
			this.paypalAccount = null;
		}
	}

	public SingleLineString256Type getName() {
		if (name == null) {
			return null;
		}
		return new SingleLineString256Type(name);
	}

	public void setName(SingleLineString256Type name) {
		if (name != null) {
			this.name = name.value();
		} else {
			this.name = null;
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

	public void setWebsiteURL(URLType websiteURL) {
		if (websiteURL != null) {
			this.websiteURL = websiteURL.value();
		} else {
			this.websiteURL = null;
		}
	}

	public URLType getWebsiteURL() {
		if (websiteURL == null) {
			return null;
		}
		return new URLType(websiteURL);
	}

	public void setVideoURL(SingleLineString256Type videoURL) {
		if (videoURL != null) {
			this.videoURL = videoURL.value();
		} else {
			this.videoURL = null;
		}
	}

	public SingleLineString256Type getVideoURL() {
		if (videoURL == null) {
			return null;
		}
		return new SingleLineString256Type(videoURL);
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

	public Collection<User2HeroEntity> getUser2HeroCollection() {
		return user2HeroCollection;
	}

	public void setUser2HeroCollection(Collection<User2HeroEntity> user2HeroCollection) {
		this.user2HeroCollection = user2HeroCollection;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean hidden) {
		this.active = hidden;
	}

}