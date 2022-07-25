package store;

import entity.Horse;
import entity.Pair;
import entity.Rider;
import lombok.Getter;

import java.util.*;

@Getter
public class Store {

    private LinkedHashMap<Integer,Pair> pairs = new LinkedHashMap<>();

    private static Store store = null;

    private Store() {

    }
    public static Store getInstance() {
        if (store != null) {
            return store;
        } else {
            synchronized (Store.class) {
                if (store == null) {
                    store = new Store();
                }
            }
            return store;
        }
    }
    public Map<Integer,Pair> getStore() {
        return pairs;
    }
}
