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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dbid == null) ? 0 : dbid.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstrEntity other = (AbstrEntity) obj;
		if (dbid == null) {
			if (other.dbid != null)
				return false;
		} else if (!dbid.equals(other.dbid))
			return false;
		return true;
	}

}