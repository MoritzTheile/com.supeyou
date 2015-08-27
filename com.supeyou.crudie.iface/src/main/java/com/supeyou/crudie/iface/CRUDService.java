package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface CRUDService<D, F extends FetchQuery> extends CRUDObservable<D> {

	/**
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param page
	 *            Defines the page of data. If null all available data is returned.
	 * @param query
	 *            Defines the dtos to be returned.
	 * 
	 */
	DTOFetchList<D> fetchList(
			UserDTO actorDTO,
			Page page,
			F query
			) throws CRUDException;

	/**
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param dtoId
	 *            The id of the dto to be returned.
	 * 
	 */
	D get(UserDTO actorDTO, AbstrType<Long> dtoId) throws CRUDException;

	/**
	 * Updates or adds the given dto. If the dto.getId() is null the DTO is created. Otherwise updated.
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param dto
	 * 
	 */
	D updadd(
			UserDTO actorDTO,
			D dto
			) throws CRUDException;

	/**
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param dtoId
	 * 
	 */
	void delete(
			UserDTO actorDTO,
			AbstrType<Long> dtoId
			) throws CRUDException;

	/**
	 * Saves all entries defined by FetchQuery as CSV in a file.
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param query
	 *            Defines the dtos to be returned.
	 * @return The file id of the file with CSV
	 * @throws CRUDException
	 */
	FileIDType exportData(
			UserDTO actorDTO,
			F query
			) throws CRUDException;

	/**
	 * Creates DTOs from CSV-File.
	 * 
	 * @param actorDTO
	 *            The person or system that is responsible for the service call.
	 * @param fileId
	 *            The file defined by file id must contain valid CSV. The header line contains attribute names with capital starting letter. Separator is ';'.
	 * @throws CRUDException
	 */
	void importData(
			UserDTO actorDTO,
			FileIDType fileId) throws CRUDException;

}