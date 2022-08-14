package by.salov.services;

import by.salov.entity.Pair;

import java.util.Comparator;
import java.util.Scanner;

public class GameService {
    int quantityCircles;
    private StoreService storeService;

    public GameService(StoreService storeService, int quantityCircles) {
        this.storeService = storeService;
        this.quantityCircles = quantityCircles;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Indicate the number of pair");
            //Печатаем все номера
            storeService.getAllPairs().forEach(
                    pair -> System.out.print(pair.getNumber() + ", "));
            System.out.println();
            int numberPair = scanner.nextInt();
            //запоминаем пару которую выбрали
            Pair maybeMaxSpeed = storeService.getAllPairs().stream()
                    .filter(pair -> pair.getNumber() == numberPair)
                    .findFirst().get();
            System.out.println("You chose pair number : " + maybeMaxSpeed.getNumber());
            Pair pairMaxSpeed = null;
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.println("Cycle № " + i);
                //запоминаем пару с максимальной скоростью
                pairMaxSpeed = storeService.getAllPairs().stream()
                        .sorted(Comparator.comparing(Pair::getSpeed))
                        .reduce((first, second) -> second)
                        .get();
                System.out.println("First pair: " + pairMaxSpeed.getNumber());
            }
            //проверяем совпадает ли выбранная пара с парой приехавшей первой
            if (maybeMaxSpeed.equals(pairMaxSpeed)) {
                System.out.println("Yoy win");
            } else {
                System.out.println("You lose");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
