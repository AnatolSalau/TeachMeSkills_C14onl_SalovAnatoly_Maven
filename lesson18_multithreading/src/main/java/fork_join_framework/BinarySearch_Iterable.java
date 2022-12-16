package fork_join_framework;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BinarySearch_Iterable {
    public int search(int[] sortedArray, int searchingValue) {
        //Maximum index
        int maxIndex = sortedArray.length - 1;
        //Minimum index
        int minIndex = 0;
        int count = 1;
        while (minIndex <= maxIndex) {
            //Calculate middleIndex
            int middleIndex = minIndex + (maxIndex - minIndex)/2;

            log.info("Iterate {}, middle is {}", count, middleIndex);
            count++;

            // Quiet from cycle if we find
            if (searchingValue == sortedArray[middleIndex]) {
                log.info("We find value, index is {}", middleIndex);
                return maxIndex;
            }

            //Move left boundary
            if ( searchingValue > sortedArray[middleIndex] ) {
                minIndex = middleIndex;
                log.info("Move left boundary : left is {}",minIndex);
            }

            //Move right boundary
            if ( searchingValue < sortedArray[middleIndex]) {
                maxIndex = middleIndex- 1;
                log.info("Move right boundary : right is {}",maxIndex);
            }
        }
        //If we there we dont find result
        log.info("We didn't find value");
        return -1;
    }
}
