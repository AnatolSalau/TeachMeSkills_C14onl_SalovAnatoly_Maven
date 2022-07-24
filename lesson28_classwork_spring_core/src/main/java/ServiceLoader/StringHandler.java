package ServiceLoader;

public interface StringHandler {
    void print(String string);
    default String convertObjectToString(Object object) {
        return (String) object;
    }
}
