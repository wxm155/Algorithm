package code.sort;

import java.util.*;

/**
 * 加强堆
 *
 * @author: wxm
 * @created: 2022/04/01
 */
public class StrongHeap {

    // 堆数组
    private ArrayList<ValueWrapper<?>> heap;
    // 索引map
    private Map<ValueWrapper<?>, Integer> indexMap;
    // 堆size
    private int heapSize;
    // 比较器
    private Comparator<ValueWrapper<?>> comparator;

    public StrongHeap(Comparator comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    // 添加
    public void push(ValueWrapper<?> value) {
        heap.add(value);
        indexMap.put(value, heapSize);
        heapInsert(heapSize++);
    }

    // 弹出
    public ValueWrapper<?> pop() {
        if (heap.isEmpty()) {
            throw new RuntimeException("head is null");
        }
        ValueWrapper<?> result = heap.get(0);
        swap(0, heapSize - 1);
        heap.remove(--heapSize);
        indexMap.remove(result);
        heapIfy(0);
        return result;
    }

    // 删除
    public void remove(ValueWrapper<?> value) {
        Integer index = indexMap.get(value);
        ValueWrapper<?> temp = heap.get(heapSize - 1);
        heap.remove(--heapSize);
        indexMap.remove(value);
        if (value != temp) {
            heap.set(index, temp);
            indexMap.put(temp, index);
            resign(temp);
        }
    }

    // 是否为空
    public boolean isEmpty() {
        return heapSize == 0;
    }

    // 调整堆
    private void resign(ValueWrapper<?> value) {
        heapIfy(indexMap.get(value));
        heapInsert(indexMap.get(value));
    }

    // 下移
    private void heapIfy(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int maxIndex = (left + 1) < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) > 0 ? left + 1 : left;
            maxIndex = comparator.compare(heap.get(index), heap.get(maxIndex)) > 0 ? index : maxIndex;
            if (maxIndex == index) {
                break;
            }
            swap(index, maxIndex);
            index = maxIndex;
            left = index * 2 + 1;
        }
    }

    // 上移
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 交换
    private void swap(int i, int j) {
        ValueWrapper<?> temp1 = heap.get(i);
        ValueWrapper<?> temp2 = heap.get(j);
        heap.set(i, temp2);
        heap.set(j, temp1);
        indexMap.put(temp1, i);
        indexMap.put(temp2, j);
    }

    // 包装类
    static class ValueWrapper<T> {
        public T value;

        public ValueWrapper(T value) {
            this.value = value;
        }
    }

    // 比较器
    public static class WrapperComparator implements Comparator<ValueWrapper<Integer>> {

        @Override
        public int compare(ValueWrapper<Integer> o1, ValueWrapper<Integer> o2) {
            return o1.value - o2.value;
        }
    }

    public static void main(String[] args) {
        StrongHeap heap = new StrongHeap(new WrapperComparator());
        ValueWrapper<Integer> value5 = new ValueWrapper<>(5);
        ValueWrapper<Integer> value2 = new ValueWrapper<>(2);
        ValueWrapper<Integer> value3 = new ValueWrapper<>(3);
        ValueWrapper<Integer> value1 = new ValueWrapper<>(1);
        ValueWrapper<Integer> value4 = new ValueWrapper<>(4);
        heap.push(value5);
        System.out.println(heap.pop().value);
        heap.push(value5);
        heap.push(value2);
        heap.push(value3);
        heap.push(value4);
        heap.push(value1);
        heap.push(value5);
        heap.push(value5);
        heap.remove(value1);
        while (!heap.isEmpty()) {
            System.out.println(heap.pop().value);
        }
    }
}
