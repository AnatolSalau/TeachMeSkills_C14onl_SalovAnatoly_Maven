package ServiceLoader;

public class LowerCaseStringHandler implements StringHandler{
    @Override
    public void print(String string) {
        System.out.println(string.toLowerCase());
    }
}
