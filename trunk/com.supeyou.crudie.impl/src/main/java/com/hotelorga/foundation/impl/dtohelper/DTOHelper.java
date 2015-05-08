package com.hotelorga.foundation.impl.dtohelper;


public interface DTOHelper<E, D> {

	public D entity2DTO(E entity) throws Exception;

	public E dto2Entity(D dto) throws Exception;

	public E updateEntityFromDTO(D dto, E entity) throws Exception;
}
