package semaphores;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResource {
    private int count = 0;
}
