package code.queue;

/**
 * 链表实现队列
 * @author: wxm
 * @created: 2022/03/07
 */
public class LinkedQueue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class MyQueue{
       private Node popNode;
       private Node pushNode;
       private int size;
       private int limit;

       public MyQueue(int size){
           this.limit = size;
           this.size = 0;
       }

       public void push(int value){
           if (size == 0){
               Node node = new Node(value);
               popNode = node;
               pushNode = node;
               size++;
           }else {
               if (size == limit){
                   throw new RuntimeException("队列满了。。。");
               }
               pushNode.next = new Node(value);
               pushNode = pushNode.next;
               size++;
           }
       }

       public int pop(){
           if (size == 0){
               throw new RuntimeException("队列空了。。。");
           }
           int result = popNode.value;
           popNode = popNode.next;
           size--;
           return result;
       }

       public int peek(){
           if (size == 0){
               throw new RuntimeException("队列空了。。。");
           }
           return popNode.value;
       }

       public boolean isEmpty(){
           return size == 0;
       }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());

    }
}
