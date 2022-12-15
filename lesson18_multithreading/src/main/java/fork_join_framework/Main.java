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

    }
}
