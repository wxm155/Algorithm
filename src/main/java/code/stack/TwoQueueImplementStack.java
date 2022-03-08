package code.stack;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 *
 * @author: wxm
 * @created: 2022/03/08
 */
public class TwoQueueImplementStack {

    public static class MyStack {
        private Queue<Integer> coreQueue;
        private Queue<Integer> helpQueue;
        private int limit;
        private int size;

        public MyStack(int limit) {
            this.limit = limit;
            this.size = 0;
            this.coreQueue = new LinkedList<>();
            this.helpQueue = new LinkedList<>();
        }

        public void push(int num) {
            if (size >= limit) {
                throw new RuntimeException("栈满了。。。");
            }
            coreQueue.offer(num);
            size++;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈空了。。。");
            }
            while (coreQueue.size() > 1) {
                helpQueue.offer(coreQueue.poll());
            }
            int result = coreQueue.poll();
            // 交换两个队列
            Queue<Integer> temp = coreQueue;
            coreQueue = helpQueue;
            helpQueue = temp;
            size--;
            return result;
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("栈空了。。。");
            }
            while (coreQueue.size() > 1) {
                helpQueue.offer(coreQueue.poll());
            }
            int result = coreQueue.poll();
            helpQueue.offer(result);
            // 交换两个队列
            Queue<Integer> temp = coreQueue;
            coreQueue = helpQueue;
            helpQueue = temp;
            return result;
        }

        public static void main(String[] args) {
            MyStack myStack = new MyStack(5);
            myStack.push(1);
            myStack.push(2);
            System.out.println(myStack.pop());
            myStack.push(3);
            myStack.push(4);
            System.out.println(myStack.peek());
            myStack.push(5);
//            myStack.push(6);
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
//            System.out.println(myStack.pop());
        }
    }
}
