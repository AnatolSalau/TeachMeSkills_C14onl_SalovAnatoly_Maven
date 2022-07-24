package dao;

import entity.Horse;
import entity.Pair;
import entity.Rider;
import store.Store;

public class StoreConnection {
    private final Store store;

    public StoreConnection(Store store) {
        this.store = store;
    }

    public void addHorse(Horse horse) {
        store.getHorses().add(horse);
    }

    public void addRider(Rider rider) {
        store.getRiders().add(rider);
    }

    public void addPair(Pair pair) {
        store.getPairs().add(pair);
    }
    public Horse getHorse(Horse horse) {
        return store.getHorses().contains(horse) ? store.getHorses().re
    }

    public Rider getRider(Rider rider) {
        store.getRiders().add(rider);
    }

    public Pair getPair(Pair pair) {
        store.getPairs().add(pair);
    }

}
