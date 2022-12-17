package fork_join_framework.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

@Slf4j
public class RecursiveTaskEx extends RecursiveTask<String> {
    @Override
    protected String compute() {
        log.info("Compute from RecursiveTaskEx");
        return null;
    }
}
