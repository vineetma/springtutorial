package com.dakinegroup.storesApp.master;

import java.util.ArrayList;
import java.util.List;

import com.dakinegroup.storesApp.store.Store;

public class StoreMaster {
	private List<Store> stores = new ArrayList<Store>();

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
}
