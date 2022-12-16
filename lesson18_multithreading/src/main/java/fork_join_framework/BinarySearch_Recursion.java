package fork_join_framework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinarySearch_Recursion {
    private int count = 0;
    public int search(int[] sortedArray, int minIndex, int maxIndex, int searchingValue) {
        //Calculate middleIndex
        int middleIndex = minIndex + (maxIndex - minIndex)/2;

        log.info("Call search {}, middle is {}", count, middleIndex);
        count++;

        // Quiet from recursion if we find
        if (searchingValue == sortedArray[middleIndex]) {
            log.info("We find value, index is {}", middleIndex);
            return middleIndex;
        }
        // Run recursion
        //Move left boundary
        else if ( searchingValue > sortedArray[middleIndex] ) {
            minIndex = middleIndex;
            log.info("Move left boundary : left is {}",minIndex);
            return search(sortedArray,minIndex, maxIndex,searchingValue);
        }
        // Run recursion
        //Move right boundary
        else if ( searchingValue < sortedArray[middleIndex]) {
            maxIndex = middleIndex- 1;
            log.info("Move right boundary : right is {}",maxIndex);
            return search(sortedArray,minIndex, maxIndex,searchingValue);
        }
        else {
            //If we there we dont find result
            log.info("We didn't find value");
            return -1;
        }
    }
}
