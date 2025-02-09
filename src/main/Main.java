import com.vikas.cache.Cache;
import com.vikas.cache.MultiKeyLRUCache;
import com.vikas.cache.factory.CacheFactory;

public class Main {
    public static void main(String[] args) {
//        Cache<Integer, Integer> myCache = new LRUCache<>(new HashMapStorage<Integer, Integer>(5));
//        myCache.set(1, 10);
//        myCache.set(2, 20);
//        myCache.set(3, 30);
//        myCache.set(4, 40);
//        myCache.set(4, 80);
//        myCache.set(5, 50);
//        System.out.println(myCache.get(1));
//        myCache.set(6, 60);
//        myCache.printCache();
//        System.out.println(myCache.remove(4));
//        myCache.set(7, 70);
//        myCache.printCache();

        Cache<Integer, Integer> myCache = new MultiKeyLRUCache<>(5);
        myCache.set(1, 10);
        myCache.set(2, 10);
        myCache.set(3, 30);
        myCache.set(4, 10);
        myCache.set(5, 50);
        System.out.println(myCache.get(1));
        myCache.set(6, 60);
        myCache.printCache();
        myCache.set(7, 70);
        myCache.printCache();
        myCache.set(8, 80);
        myCache.printCache();
        myCache.set(4, 40);
        myCache.printCache();

        System.out.println("===========");
        CacheFactory cacheFactory = new CacheFactory();
        Cache<Integer, Integer> myMultiKeyCache = cacheFactory.getCache("MultiKeyLRU", 3);
        myMultiKeyCache.set(1, 10);
        myMultiKeyCache.set(2, 10);
        myMultiKeyCache.set(3, 30);
        myMultiKeyCache.set(4, 40);
        myMultiKeyCache.printCache();
        myMultiKeyCache.set(2, 20);
        myMultiKeyCache.printCache();
        myMultiKeyCache.set(5, 50);
        myMultiKeyCache.printCache();

    }
}