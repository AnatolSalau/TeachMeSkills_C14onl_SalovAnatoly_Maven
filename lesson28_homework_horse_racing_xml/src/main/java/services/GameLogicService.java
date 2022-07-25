package services;

import dao.StoreConnection;
import entity.Pair;
import store.Store;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogicService {
    private StoreConnection storeConnection;
    private int currentCircle = 0;
    private int countPairs = 0;
    GameUserInterfaceService gameUserInterfaceService;

    public GameLogicService(StoreConnection storeConnection) {
        this.storeConnection = storeConnection;
        gameUserInterfaceService = new GameUserInterfaceService(storeConnection);
    }

    public void start(int countCircles, Pair... pairs) {
            countPairs = pairs.length;
           //Добавляем пары
            Arrays.stream(pairs)
                    .peek(pair1 -> {storeConnection.add(pair1);});
            gameUserInterfaceService.getFromUserNumberPair();
            //Выбираем пару которая как думаем победит
            gameUserInterfaceService.getFromUserNumberPair();
            //Начинаем бежать
            for (int i = 1; i <= countCircles; i++) {
                currentCircle = i;
                //Меняем рандомно скорости
                storeConnection.getAllPairs().forEach(
                        (integer, pair) ->{changeSpeedPair(pair);}
                );
                //Выводим логи текущего круга
                gameUserInterfaceService.logCurrentCircle(currentCircle);
            }
    }

    private double changeSpeedRandom (double currentSpeed) {
        double min = currentSpeed - (currentSpeed*0.1);
        double max = currentSpeed + (currentSpeed*0.1);
        double random = ThreadLocalRandom.current().nextDouble(min, max);
        return random;
    }

    private void changeSpeedPair (Pair pair) {
        pair.setSpeed(changeSpeedRandom(pair.getSpeed()));
    }

    public int getCurrentCircle() {
        return currentCircle;
    }
}
