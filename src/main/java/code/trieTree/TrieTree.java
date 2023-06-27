package code.trieTree;

/**
 * @author: wxm
 * @created: 2023/06/27
 */
public class TrieTree {

    /**
     * 前缀树
     */
    public static class TrieTree1 {
        private Node root;

        public TrieTree1() {
            this.root = new Node();
        }

        /**
         * 添加字符串
         *
         * @param word 添加的字符串
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] arr = word.toCharArray();
            root.pass++;
            Node node = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 搜索字符串出现的次数
         *
         * @param word 目标字符串
         * @return 出现的次数
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] arr = word.toCharArray();
            Node node = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        /**
         * 搜索有多少个字符串以指定字符串为前缀
         *
         * @param word 目标字符串
         * @return 以目标字符串为前缀的结果
         */
        public int prefixNum(String word) {
            if (word == null) {
                return 0;
            }
            char[] arr = word.toCharArray();
            Node node = root;
            for (int i = 0; i < arr.length; i++) {
                int index = arr[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.pass;
        }

        /**
         * 删除指定字符串
         *
         * @param word 删除字符串
         */
        public void delete(String word) {
            if (search(word) != 0) {
                char[] arr = word.toCharArray();
                root.pass--;
                Node node = root;
                for (int i = 0; i < arr.length; i++) {
                    int index = arr[i] - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }
    }


    private static class Node {
        // 经过的数量
        public int pass;
        // 以当前字符结尾的字符串数量
        public int end;
        // 下一个字符
        public Node[] next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            // 小写的26个字母
            // Node[i] == null i方向的路不存在
            // Node[i] != null i方向的路存在
            this.next = new Node[26];
        }
    }
}
