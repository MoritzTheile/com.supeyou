package com.supeyou.actor.impl.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supeyou.crudie.impl.entity.UserEntity;

@Entity
@Table(name = "ActingUserEntity")
public class ActingUserEntity extends UserEntity {

	@OneToMany(mappedBy = "a"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Collection<ActingUser2SessionEntity> actingUser2sessionCollection = new ArrayList<ActingUser2SessionEntity>();

}