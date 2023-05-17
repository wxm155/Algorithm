package code.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2023/05/17
 */
public class LFUCache<K, V> {

    private Map<K, LFUNode<K, V>> map;

    /** 容量 */
    private int capacity;

    public LFUCache(int capacity) {
        map = new HashMap<>(16);
        this.capacity = capacity;
    }

    public V get(K key) {
        LFUNode<K, V> node = map.get(key);
        if (node != null) {
            node.accessTimes++;
            node.accessTimes = System.nanoTime();
            return node.value;
        }
        return null;
    }

    public boolean put(K key, V value) {
        LFUNode<K, V> node = map.get(key);
        if (node == null) {
            if (map.size() == this.capacity) {
                this.removeNode();
            }
            map.put(key, new LFUNode<>(key, value, 0, System.nanoTime()));
        } else {
            node.accessTimes++;
            node.time = System.nanoTime();
            node.value = value;
            map.put(key, node);
        }
        return true;
    }

    public void forEach(){
        map.forEach((k,v) -> System.out.println(k + ":" + v.value));
    }

    private void removeNode(){
        LFUNode<K,V> min = Collections.min(map.values());
        map.remove(min.key);
    }

    public static class LFUNode<K, V> implements Comparable<LFUNode<K, V>> {
        public K key;
        public V value;
        public long accessTimes;
        public long time;

        public LFUNode(K key, V value, long accessTimes, long time) {
            this.key = key;
            this.value = value;
            this.accessTimes = accessTimes;
            this.time = time;
        }

        @Override
        public int compareTo(LFUNode<K, V> o) {
            int access = Long.compare(this.accessTimes, o.accessTimes);
            return access == 0 ? Long.compare(this.time, o.time) : access;
        }
    }


    public static void main(String[] args) {
        LFUCache<String, String> lfuCache = new LFUCache<>(4);

        lfuCache.put("1","1");
        lfuCache.put("2","2");
        lfuCache.get("1");
        lfuCache.put("3","3");
        lfuCache.put("4","4");
        lfuCache.put("5","5");
        lfuCache.put("6","6");
        lfuCache.forEach();
    }
}
