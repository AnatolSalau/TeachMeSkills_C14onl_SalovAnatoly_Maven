import java.util.Map;

public class DBStoreSingleton {
    //Create map by using java9 method Map.of
    Map<String,String> dbMap = Map.of("k1","v1","k2","v2");

    //The singleton class holds a reference to itself
    private static DBStoreSingleton dbStoreSingleton = null;

    //create private constructor
    private DBStoreSingleton() {

    }

    //Method to get DBStoreSingleton (create it)
    public static DBStoreSingleton getSingleton() {
        //Fast realization thread save
        if (dbStoreSingleton != null) {
            return dbStoreSingleton;
        } else {
            synchronized (DBStoreSingleton.class) {
                if (dbStoreSingleton == null) {
                    dbStoreSingleton = new DBStoreSingleton();
                }
            }
            return dbStoreSingleton;
        }
    }
    //Method to get map
    public Map<String,String> getDbMap() {
        return dbMap;
    }
}
