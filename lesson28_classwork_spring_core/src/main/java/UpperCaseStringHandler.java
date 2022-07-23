public class UpperCaseStringHandler implements StringHandler{
    @Override
    public void print(String string) {
        System.out.println(string.toUpperCase());
    }
}
