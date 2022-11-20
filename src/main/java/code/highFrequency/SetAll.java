package code.highFrequency;

import java.util.HashMap;

/**
 * @author wxm
 * @created 2022/11/20
 */
public class SetAll {

    /**
     * 设计有setAll功能的哈希表，put、get、setAll方法，时间复杂度O(1)
     */


    /**
     * 利用时间戳记录每个节点的添加或更新时间
     * 节点时间在setAll的时间节点前，说明之前的节点之后存在setAll
     * 直接拦截获取setAll的值，反之获取value的值
     * @param <K> key
     * @param <V> value
     */
    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            this.map = new HashMap<>();
            this.time = 0;
            setAll = new MyValue<>(-1, null);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<>(time++, value));
        }

        public void setAll(V value) {
            this.setAll = new MyValue<>(time++, value);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            MyValue<V> value = map.get(key);
            return value.time < setAll.time ? setAll.value : value.value;
        }
    }

    public static class MyValue<V> {
        public long time;
        public V value;

        public MyValue(long time, V value) {
            this.time = time;
            this.value = value;
        }
    }

}
