package code.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author wxm
 * @created 2022/7/17
 */
public class DFS {

    /**
     * 图的深度优先遍历：以起始节点一直往下找，找到没路返回上一个节点往另一个分支找
     */
    public void dfs(Graph.Node node){
        Set<Graph.Node> set = new HashSet<>();
        Stack<Graph.Node> stack = new Stack<>();
        set.add(node);
        stack.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Graph.Node pop = stack.pop();
            for (Graph.Node next : pop.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    stack.push(pop);
                    stack.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
