package com.dakinegroup.storesApp.master;

import java.util.ArrayList;
import java.util.List;

import com.dakinegroup.storesApp.store.StoreItem;

public class StoreItemMaster {
private List<StoreItem> storeItems = new ArrayList<StoreItem>();

public List<StoreItem> getStoreItems() {
	return storeItems;
}

public void setStoreItems(List<StoreItem> storeItems) {
	this.storeItems = storeItems;
}

}
