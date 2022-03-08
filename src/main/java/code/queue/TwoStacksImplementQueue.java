package code.queue;

import java.util.Stack;

/**
 * 两个栈实现队列
 * @author: wxm
 * @created: 2022/03/08
 */
public class TwoStacksImplementQueue {

    public static class MyQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;
        private int size;
        private int limit;

        public MyQueue(int limit){
            this.limit = limit;
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
            this.size = 0;
        }

        public void push(int num){
            if (size >= limit){
                throw new RuntimeException("队列满了。。。");
            }
            pushStack.push(num);
            pushToPop();
            size++;
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("队列空了。。。");
            }
            pushToPop();
            size--;
            return popStack.pop();
        }

        private void pushToPop(){
            if (popStack.empty()){
                while(!pushStack.empty()){
                    popStack.push(pushStack.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.pop());
        myQueue.push(3);
        System.out.println(myQueue.pop());
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        System.out.println(myQueue.pop());
//        myQueue.push(5);
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());


    }
}
