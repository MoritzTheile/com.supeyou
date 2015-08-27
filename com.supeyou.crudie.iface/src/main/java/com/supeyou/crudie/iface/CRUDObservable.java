package com.supeyou.crudie.iface;

public interface CRUDObservable<D> {

	void addCRUDObserver(CRUDObserver<D> crudObserver);

}
