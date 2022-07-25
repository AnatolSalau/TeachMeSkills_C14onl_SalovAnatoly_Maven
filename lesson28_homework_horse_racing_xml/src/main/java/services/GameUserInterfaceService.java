package services;

import dao.StoreConnection;
import entity.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class GameUserInterfaceService {
    private StoreConnection storeConnection;
    private Integer numberPair = 0;
    Scanner scanner;

    public GameUserInterfaceService(StoreConnection storeConnection) {
        this.storeConnection = storeConnection;
        scanner = new Scanner(System.in);
    }

    public void getFromUserNumberPair() {
        System.out.println("Enter number your pair");
        numberPair = scanner.nextInt();
    }

    public void logCurrentCircle(int circleNumber) {
        System.out.println("Circle number : " + circleNumber);
    }

    private void destroy() {
        scanner.close();
        System.out.println("SoreConnection destroy : scanner closed");
    }
}
