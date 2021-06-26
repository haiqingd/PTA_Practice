package PAT_Practice_1004;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    static int N;
    static int M;
    static int depth;
    static int numOfNoneLeafNodes;
    class Node{
        String name;
        int depth;

        public Node(String name, int depth) {
            this.name = name;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        if(N == 1 && M == 0){
            System.out.println(1);
            return;
        }
        DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[N];
        for (int i = 0; i < N; i++) {
            String id = String.format("%2d", i + 1);
            nodes[i] = new DefaultMutableTreeNode(id);
        }
        for (int i = 0; i < M; i++) {
            int fatherIndex = sc.nextInt() - 1;
            int numOfSons = sc.nextInt();
            for (int j = 0; j < numOfSons; j++) {
                int sonIndex = sc.nextInt() - 1;
                nodes[fatherIndex].add(nodes[sonIndex]);
            }
        }

        ConcurrentLinkedQueue<DefaultMutableTreeNode> queue = new ConcurrentLinkedQueue<>();

        bfs(nodes[0].getDepth(), nodes[0], queue);
        System.out.println();
    }

    static void bfs(int totalDepth, DefaultMutableTreeNode root, Queue<DefaultMutableTreeNode> queue) {

        queue.add(root);
        ArrayList<Integer> leavesNum = new ArrayList<>();
        leavesNum.add(0);
        while (!queue.isEmpty()) {
            DefaultMutableTreeNode node = queue.poll();

            for (int i = 0; i < node.getChildCount(); i++) {
                int depth = ((DefaultMutableTreeNode) node.getChildAt(i)).getLevel();
                if (depth + 1 > leavesNum.size()) {
                    leavesNum.add(0);
                }
                if (node.getChildAt(i).isLeaf()) leavesNum.set(depth, leavesNum.get(depth) + 1 );
                queue.add((DefaultMutableTreeNode) node.getChildAt(i));
            }
        }
        for (int i = 0; i < leavesNum.size(); i++) {
            if(i != 0) System.out.print(" ");
            System.out.print(leavesNum.get(i));
        }
    }
}
