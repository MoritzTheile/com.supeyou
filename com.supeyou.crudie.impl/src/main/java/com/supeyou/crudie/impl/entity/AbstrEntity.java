package com.supeyou.crudie.impl.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;

import com.supeyou.crudie.iface.datatype.types.AbstrType;

@MappedSuperclass
public abstract class AbstrEntity<T extends AbstrType<Long>> {

	@Id
	// @GeneratedValue //Generate Value can not be used because custom ids are not possible as needed during import
	protected Long dbid;

	private Long creationTimestamp = System.currentTimeMillis();

	private Long lastModifiedTimestamp = null;

	@PreUpdate
	public void updateTimeStamps() {
		lastModifiedTimestamp = System.currentTimeMillis();

	}

	public Long getCreationTimestamp() {
		return creationTimestamp;
	}

	public abstract T getId();

	public abstract void setId(T typedId);

	public void setId(Long id) {
		this.dbid = id;
	}

	public Long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

}