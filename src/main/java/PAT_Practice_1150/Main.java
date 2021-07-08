package PAT_Practice_1150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Main {
    static final String[] TYPE = {"(TS cycle)", "(TS simple cycle)", "(Not a TS cycle)"};



    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (!"\nnull".equals(line)){
            sb.append(line);
            line = "\n" + in.readLine();
        }
        System.out.println(submit(sb.toString()));
    }
    static String submit(String line){
        String[] split = line.split("[ \n]");

        Queue<String> inputQueue = new ConcurrentLinkedDeque<String>(Arrays.asList(split));

        int nodeNum = Integer.parseInt(inputQueue.poll());
        int edgeNum = Integer.parseInt(inputQueue.poll());

        // 初始化图，边集合edges，无需顶点集合，顶点即为对应的数字序号。
        int[][] edge = new int[nodeNum][];


        for (int i = 0; i < nodeNum; i++) {
            edge[i] = new int[nodeNum];
            Arrays.fill(edge[i], Integer.MAX_VALUE);
        }

        // 将输入的边赋值进邻接矩阵
        for (int i = 0; i < edgeNum; i++) {
            int from = Integer.parseInt(inputQueue.poll()) - 1;
            int to = Integer.parseInt(inputQueue.poll()) - 1;
            int dist = Integer.parseInt(inputQueue.poll()) ;
            edge[from][to] = dist;
            edge[to][from] = dist;
        }

        int K = Integer.parseInt(inputQueue.poll());
        int minDistId = 0, minDist = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            /*
             * 0: TS_CYCLE
             * 1: TS_SIMPLE_CYCLE
             * 2: NOT_CYCLE
             * 默认值为 1
             */

            int type = 1, totalDist = 0;


            int[][] thisWay = new int[nodeNum][];


            for (int k = 0; k < nodeNum; k++) {
                thisWay[k] = new int[nodeNum];
                thisWay[k][k] = 1;
            }

            int n = Integer.parseInt(inputQueue.poll());
            int thisPoint = 0, lastPoint, firstPoint = 0, endPoint = 0;
            HashSet<Integer> nodes = new HashSet<Integer>();
            for (int j = 0; j < n; j++) {
                if (j == 0){
                    thisPoint = Integer.parseInt(inputQueue.poll()) - 1;
                    firstPoint = thisPoint;
                    endPoint = thisPoint;
                    nodes.add(thisPoint);
                    continue;
                }
                lastPoint = thisPoint;
                thisPoint = Integer.parseInt(inputQueue.poll()) - 1;
                endPoint = thisPoint;
                if (edge[thisPoint][lastPoint] == Integer.MAX_VALUE){
                    type = 2;
                    totalDist = -1;
                    break;
                }
                if (thisWay[thisPoint][lastPoint] == 1 || thisWay[lastPoint][thisPoint] == 1){
                    type = 0;
                }
                thisWay[thisPoint][lastPoint] = 1;
                thisWay[lastPoint][thisPoint] = 1;
                totalDist += edge[thisPoint][lastPoint];
                nodes.add(thisPoint);
            }
            if (nodes.size() < nodeNum || firstPoint != endPoint) {
                type = 2;
            }else if (n > nodeNum + 1){
                type = 0;
            }

            sb.append("Path ").append(i+1).append(": ");
            if (totalDist == -1){
                sb.append("NA");
            }else {
                sb.append(totalDist);
                if (type != 2 && (minDist == -1 || totalDist < minDist) ){
                    minDist = totalDist;
                    minDistId = i+1;
                }
            }
            sb.append(" ").append(TYPE[type]).append("\n");

        }

        sb.append("Shortest Dist(").append(minDistId).append(") = ").append(minDist);

        return sb.toString();
    }



}