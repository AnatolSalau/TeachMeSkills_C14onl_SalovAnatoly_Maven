package store;

import entity.Horse;
import entity.Pair;
import entity.Rider;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
public class Store {

    private HashSet<Horse> horses;
    private HashSet<Rider> riders;
    private HashSet<Pair> pairs;

    public Store() {
        this.horses = new HashSet<>();
        this.riders = new HashSet<>();
        this.pairs = new HashSet<>();
    }
}
