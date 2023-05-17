package code.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2023/05/17
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    /** 容量 */
    private int capacity;

    public LRUCache(int capacity) {
        super(16, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > this.capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(4);

        lruCache.put("1","1");
        lruCache.put("2","2");
        lruCache.put("3","3");
        lruCache.put("4","4");
        lruCache.put("5","5");
        System.out.println(lruCache.get("2"));
        System.out.println(lruCache);
        lruCache.put("6","6");
        lruCache.put("7","7");
        System.out.println(lruCache);
    }
}
