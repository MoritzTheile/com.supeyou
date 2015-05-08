package com.supeyou.crudie.iface.datatype;

public class CRUDException extends Exception {

	private static final long serialVersionUID = 6336061572268007243L;

	public enum CODE {
		NO_AUTHENTICATED_ACTOR_FOUND,
		NO_ENTITY_FOUND_BY_GIVEN_ID,
		ERROR_TO_BE_INVESTIGATED,
		AUTHORIZATION_EXCEPTION,
		USER_IS_NOT_AN_HERO,
		USER_IS_ALREADY_HERO,
		NAME_IS_NOT_UNIQUE,
		INVALID_PAGESIZE,
		INVALID_STARTINDEX,
		NO_REFERENZ,
		HERO_HAS_ALREADY_IMAGE,
		NOT_IMPLEMENTED_YET,
		INVALID_ARGUMENT,
		UNEXPECTED_EXCEPTION,
		INITIALADMIN_CANT_BE_DELETED
	}

	private CODE code;

	// required for some frameworks..
	@SuppressWarnings("unused")
	private CRUDException() {
	};

	public CRUDException(CODE code) {

		super(code.name());

		this.code = code;

	}

	public CRUDException(CODE code, String message) {

		super(message);

		this.code = code;
	}

	public CODE getCode() {
		return code;
	}

}
