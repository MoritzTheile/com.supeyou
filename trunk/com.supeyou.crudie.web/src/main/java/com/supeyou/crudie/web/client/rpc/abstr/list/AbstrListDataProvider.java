package com.supeyou.crudie.web.client.rpc.abstr.list;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.AbstrDTO;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.web.client.model.AbstrObservable;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDServiceAsync;

public abstract class AbstrListDataProvider<T extends AbstrDTO<?>, F extends FetchQuery> extends AbstrObservable<Void> {

	private F fetchQuery;
	private int startRow = 0;
	private int pageSize = Integer.MAX_VALUE;
	private int totalSize = -1;
	private List<T> data = new ArrayList<T>();

	public AbstrListDataProvider(F fetchQuery) {
		this.fetchQuery = fetchQuery;
	}

	public void fetchData() {

		getAbstractCRUDService().fetchList(new Page(startRow, pageSize), fetchQuery, new AsyncCallback<DTOFetchList<T>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<T> result) {

				data = result;
				totalSize = result.getTotalSize();
				hasChanged();

			}
		});

	}

	public F getFetchQuery() {
		return fetchQuery;
	}

	public void setFetchQuery(F query) {
		this.fetchQuery = query;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getData() {
		return data;
	}

	public abstract RPCAbstractCRUDServiceAsync<T, F> getAbstractCRUDService();

}
