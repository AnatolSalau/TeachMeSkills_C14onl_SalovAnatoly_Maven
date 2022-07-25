package dao;

import entity.Horse;
import entity.Pair;
import entity.Rider;
import store.Store;

import java.util.Map;

public class StoreConnection {
    private  Store store;

    public StoreConnection(Store store) {
        this.store = store;
    }

    public void add(Pair pair) {
        store.getStore().put(pair.getNumber(),pair);
    }
    public Pair get(Integer key) {
        return store.getStore().get(key);
    }
    public Map<Integer,Pair> getAllPairs() {
        return store.getStore();
    }
    private void initialize() {
        store = Store.getInstance();
        System.out.println("SoreConnection initialize : Store initialized like singleton");
    }
}
