package by.salov.services;

import by.salov.dao.AllPairsStore;
import by.salov.entity.Pair;

import java.util.List;

public class StoreService {
    private AllPairsStore allPairsStore;

    public StoreService(List<Pair> pairs) {
        this.allPairsStore = AllPairsStore.getInstance();
        this.getAllPairs().addAll(pairs);
    }


    public List<Pair> getAllPairs() {
        List<Pair> pairs = allPairsStore.getList();
        return pairs;
    }

    @Override
    public String toString() {
        return "StoreService{" +
                "allPairsStore=" + allPairsStore +
                '}';
    }
}
