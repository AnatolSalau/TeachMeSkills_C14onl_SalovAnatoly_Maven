package deadlock_by_two_classes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SynchroThreadByTwoThread extends Thread {
    private AClass aClass;
    private BClass bClass;

    @Override
    public void run() {

    }
}
