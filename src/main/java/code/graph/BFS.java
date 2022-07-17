package code.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author wxm
 * @created 2022/7/17
 */
public class BFS {

    /**
     * 图的宽度优先遍历
     */

    public void bfs(Graph.Node node){
        Set<Graph.Node> set = new HashSet<>();
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            Graph.Node poll = queue.poll();
            System.out.println(poll.value);
            for (Graph.Node next : poll.nexts) {
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

}
