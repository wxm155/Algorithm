package code.queue;

/**
 * 数组实现队列
 *
 * @author: wxm
 * @created: 2022/03/07
 */
public class ArrayQueue {

    public class MyQueue {
        private int[] arr;
        private int pushIndex;
        private int popIndex;
        private int limit;
        private int size;

        public MyQueue(int size) {
            this.arr = new int[size];
            this.pushIndex = 0;
            this.popIndex = 0;
            this.limit = 0;
            this.size = size;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能在添加了。。。");
            }
            limit++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public int pop(){
            if (limit == 0){
                throw new RuntimeException("队列空了。。。");
            }
            limit--;
            int result = arr[popIndex];
            popIndex = nextIndex(popIndex);
            return result;
        }

        public int peek(){
            if (limit == 0){
                throw new RuntimeException("队列空了。。。");
            }
            return arr[popIndex];
        }

        public boolean isEmpty(){
            return limit == 0;
        }

        private int nextIndex(int currentIndex) {
            return currentIndex > size - 1 ? 0 : currentIndex++;
        }
    }
}
