package by.salov.dao;

import by.salov.entity.Pair;

import java.util.ArrayList;
import java.util.List;

public class AllPairsStore {
    private List<Pair> pairs = new ArrayList<>();
    private static AllPairsStore allPairsStore = null;

    private AllPairsStore() {
    }

    public static AllPairsStore getInstance() {
        System.out.println("------Init DB-----");
        if (allPairsStore != null) {
            return allPairsStore;
        } else {
            if (allPairsStore == null) {
                allPairsStore = new AllPairsStore();
            }
            return allPairsStore;
        }
    }

    public List<Pair> getList() {
        return pairs;
    }

    @Override
    public String toString() {
        return "AllPairsStore{" +
                "pairs=" + pairs +
                '}';
    }
}
