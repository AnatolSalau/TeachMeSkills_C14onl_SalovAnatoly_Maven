package exchange_between_threads;

import java.util.concurrent.Exchanger;

public class Main_Exchange {
    public static void main(String[] args) {
        Exchanger<String> exgr = new Exchanger<String>();
        new Producer(exgr,"PRODUCER MESSAGE");
        new Concumer(exgr,"CONCUMER MESSAGE");
    }
}
