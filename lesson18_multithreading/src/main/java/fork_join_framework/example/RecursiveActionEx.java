package fork_join_framework.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;

@Slf4j
public class RecursiveActionEx extends RecursiveAction {
    @Override
    protected void compute() {
        log.info("Compute from RecursiveActionEx");
    }
}
