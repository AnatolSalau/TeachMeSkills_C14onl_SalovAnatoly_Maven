package fork_join_framework.binary_search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainBinarySearch {
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
        BinarySearch_Recursion recursionSearch = new BinarySearch_Recursion();

        //Iterable search
        int iterate = iterableSearch.search(sortedArray, 0);
        log.info("Iterable searc is : {}", iterate);
        //Recursion search
        int recursion = recursionSearch.search(sortedArray, 0, sortedArray.length, 2);
        log.info("Recursion searc is : {}", recursion);
    }
}
