package com.supeyou.crudie.web.client.rpc.abstr.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public abstract class RPCAbstractCRUDProxy<T extends AbstrDTO<?>, F extends FetchQuery> {

	public void updadd(final T dto) {
		updadd(dto, new AsyncCallback<T>() {

			@Override
			public void onFailure(Throwable caught) {
				// nothing, its just a default callback

			}

			@Override
			public void onSuccess(T result) {
				// nothing, its just a default callback

			}
		});
	}

	public void updadd(final T dto, final AsyncCallback<T> callback) {

		getAbstractCRUDService().updadd(dto, new AsyncCallback<T>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(T result) {

				if (dto.getId() != null) {
					wasUpdated(result);
				} else {
					wasCreated(result);
				}
				callback.onSuccess(result);
			}
		});

	}

	public void delete(final T dto) {
		delete(dto, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// nothing, its just a default callback

			}

			@Override
			public void onSuccess(Void result) {
				// nothing, its just a default callback
			}
		});
	}

	public void delete(final T dto, final AsyncCallback<Void> callback) {

		getAbstractCRUDService().delete(dto.getId(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				wasDeleted(dto);
				callback.onSuccess(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("codemarker=ahadhatz45tz\n" + caught.getMessage());
				callback.onFailure(caught);
			}
		});
	}

	public interface CRUDProxyListener<T> {

		void wasUpdated(T dto);

		void wasDeleted(T dto);

		void wasCreated(T dto);

	}

	private final Collection<CRUDProxyListener<T>> listeners = new ArrayList<CRUDProxyListener<T>>();
	private final Map<Long, List<CRUDProxyListener<T>>> dtoId2Listeners = new HashMap<Long, List<CRUDProxyListener<T>>>();

	public ListenerHandler addListenerForAllDTOs(final CRUDProxyListener<T> listener) {

		listeners.add(listener);

		return new ListenerHandler() {

			@Override
			public void remove() {
				removeListener(listener);

			}

		};
	}

	public void addListenersForSpecifiDTO(CRUDProxyListener<T> listener, T dto) {
		getListenersForDTO(dto).add(listener);
	}

	public void removeListener(CRUDProxyListener<T> listener) {

		listeners.remove(listener);
		for (List<CRUDProxyListener<T>> listeners : dtoId2Listeners.values()) {
			listeners.remove(listener);
		}

	}

	public void wasCreated(T dto) {

		for (CRUDProxyListener<T> listener : new ArrayList<CRUDProxyListener<T>>(listeners)) {
			listener.wasCreated(dto);
		}

	}

	public void wasUpdated(T dto) {

		for (CRUDProxyListener<T> listener : new ArrayList<CRUDProxyListener<T>>(listeners)) {
			listener.wasUpdated(dto);
		}

		for (CRUDProxyListener<T> listener : new ArrayList<CRUDProxyListener<T>>(getListenersForDTO(dto))) {
			listener.wasUpdated(dto);
		}

	}

	public void wasDeleted(T dto) {

		for (CRUDProxyListener<T> listener : new ArrayList<CRUDProxyListener<T>>(listeners)) {
			listener.wasDeleted(dto);
		}

		for (CRUDProxyListener<T> listener : new ArrayList<CRUDProxyListener<T>>(getListenersForDTO(dto))) {
			listener.wasDeleted(dto);
		}

	}

	private List<CRUDProxyListener<T>> getListenersForDTO(T dto) {
		List<CRUDProxyListener<T>> listeners = dtoId2Listeners.get(dto.getId().value());
		if (listeners == null) {
			listeners = new ArrayList<CRUDProxyListener<T>>();
			dtoId2Listeners.put(dto.getId().value(), listeners);
		}
		return listeners;
	}

	public abstract RPCAbstractCRUDServiceAsync<T, F> getAbstractCRUDService();

	public interface ListenerHandler {
		void remove();
	}
}
