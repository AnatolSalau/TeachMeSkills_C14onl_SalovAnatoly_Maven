package fork_join_framework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        int left =  2147483647;
        log.info("Left : {}" , left);
        int right = 2147483647;
        int mid = left + (right - left) / 2;
        log.info("Mid : {}" , mid);

        //Create array
        int[] sortedArray = new int[10];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = i;
        }

        BinarySearch_Iterable iterableSearch = new BinarySearch_Iterable();
        BinarySearch_Recursion recursion = new BinarySearch_Recursion();

        //Iterable search
        iterableSearch.search(sortedArray, 2);
    }
}
