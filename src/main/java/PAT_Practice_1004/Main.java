package PAT_Practice_1004;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int depth;
    static int numOfNoneLeafNodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[N];
        for (int i = 0; i < N; i++) {
            String id = String.format("%2d", i+1);
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

        PriorityQueue<DefaultMutableTreeNode> queue = new PriorityQueue<>();

        bfs(nodes[0], queue);
        System.out.println();
    }

    static void bfs(DefaultMutableTreeNode root, Queue<DefaultMutableTreeNode> queue ){
        queue.add(root);
        while(!queue.isEmpty()){
            DefaultMutableTreeNode node = queue.poll();
            if(node.getDepth() != depth){
                if (depth != 0){
                    System.out.print(" ");
                }
                System.out.print(numOfNoneLeafNodes);
                numOfNoneLeafNodes = 0;
                depth ++;
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                if ( node.getChildAt(i).isLeaf() ) numOfNoneLeafNodes ++;
                queue.add((DefaultMutableTreeNode) node.getChildAt(i));
            }

        }
    }
}
