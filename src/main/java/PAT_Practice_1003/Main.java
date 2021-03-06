package PAT_Practice_1003;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.max;


public class Main {
    static Integer INF = MAX_VALUE;
    static int N, M, C1, C2;
    static int[] rescueTeamsAmount;

    public static void main(String[] args) {
        System.out.println(submit());
    }

    public static String submit() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        C1 = sc.nextInt();
        C2 = sc.nextInt();
        rescueTeamsAmount = new int[N];
        for (int i = 0; i < N; i++) {
            rescueTeamsAmount[i] = sc.nextInt();
        }
        return deal(sc);
    }

    public static String deal(Scanner sc) {
        // 利用 dijkstra 算法完成单点无响图求最短路径

        // SList用于存放已经完成的节点信息
        List<Map<String, Integer>> SList = new ArrayList<>();
        // UList用于存放尚未完成的节点信息
        List<Map<String, Integer>> UList = new ArrayList<>();
        // 邻接矩阵matrix[i][j]用于记录题中给出的路线
        int[][] matrix = new int[N][N];

        // SList初始化
        SList.add(MapInit(C1, 0, rescueTeamsAmount[C1], 1));

        // UList初始化, dist 初始化
        for (int i = 0; i < N; i++) {
            if (i != C1) {
                UList.add(MapInit(i, INF, rescueTeamsAmount[i], 0));
            }
            matrix[i] = new int[N];
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }
        // dist赋值
        for (int i = 0; i < M; i++) {
            int begin = sc.nextInt();
            int end = sc.nextInt();
            matrix[begin][end] = matrix[end][begin] = sc.nextInt();
        }
        // 单源最短路径
        dijkstra(SList, UList, matrix);

        int numOfShortest = 0, totalTeams = 0;
        Map<String, Integer> cityMap = getCityMap(SList, C2);
        numOfShortest = cityMap.get("numOfMinDist");
        totalTeams = cityMap.get("maxRescueTeam");

        return numOfShortest + " " + totalTeams;
    }

    public static Map<String, Integer> MapInit(int cityNumber, int minDist, int maxRescueTeam, int numOfMinDist) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("cityNumber", cityNumber);
        map.put("minDist", minDist);
        map.put("maxRescueTeam", maxRescueTeam);
        map.put("numOfMinDist", numOfMinDist);
        return map;
    }

    /**
     * 找到list中最小距离的index，相同取最前
     */
    public static int getMinDistIndex(List<Map<String, Integer>> list) {
        int minDist = list.get(0).get("minDist");
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (minDist > list.get(i).get("minDist")) {
                minDist = list.get(i).get("minDist");
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * 获取给出城市index值对应的map
     */
    static Map<String, Integer> getCityMap(List<Map<String, Integer>> list, int city) {
        for (Map<String, Integer> map : list) {
            if (map.get("cityNumber") == city) {
                return map;
            }
        }
        return null;
    }

    static void dijkstra(List<Map<String, Integer>> SList, List<Map<String, Integer>> UList, int[][] matrix) {
        while (UList.size() != 0) {
            Map<String, Integer> cityMap1 = SList.get(SList.size() - 1 );
            int minDistIndex = cityMap1.get("cityNumber");

            for (Map<String, Integer> cityMap2 : UList) {
                int i = cityMap2.get("cityNumber");

                if(matrix[minDistIndex][i] != INF && cityMap1.get("minDist") + matrix[minDistIndex][i] <= cityMap2.get("minDist")){
                    // 小于的情况，数量设为 上一个节点的数量，救援队数量为新修改的（上一个节点最大的数量加这个节点的数量）
                    if(cityMap1.get("minDist") + matrix[minDistIndex][i] < cityMap2.get("minDist")){
                        cityMap2.put("numOfMinDist", cityMap1.get("numOfMinDist"));
                        cityMap2.put("maxRescueTeam", cityMap1.get("maxRescueTeam") + rescueTeamsAmount[i]);
                    }
                    // 等于的情况，数量++，救援队需要比较大小
                    else{
                        cityMap2.put("numOfMinDist", cityMap2.get("numOfMinDist") + cityMap1.get("numOfMinDist"));
                        cityMap2.put("maxRescueTeam",
                                max(cityMap1.get("maxRescueTeam") + rescueTeamsAmount[i], cityMap2.get("maxRescueTeam")) );
                    }
                    cityMap2.put("minDist",cityMap1.get("minDist") + matrix[minDistIndex][i]);
                }
            }

            int minDistIndexU = getMinDistIndex(UList);
            Map<String, Integer> minDistMap = UList.get(minDistIndexU);
            SList.add(minDistMap);
            UList.remove(minDistIndexU);
        }
    }


    static class PAT1003Test{
        @Test
        public void test(){
            N = 7;M = 11;C1 = 0;C2 = 2;
            rescueTeamsAmount = new int[]{1, 1, 1, 1, 1, 1, 1};
            int[] input = new int[]{0,1,2, 0,2,4, 0,3,1, 0,4,2, 1,2,2, 2,3,4, 2,5,1, 2,6,1, 3,4,1, 4,5,1, 4,6,1};

            List<Map<String, Integer>> SList = new ArrayList<>();
            List<Map<String, Integer>> UList = new ArrayList<>();
            int[][] matrix = new int[N][N];
            SList.add(MapInit(C1, 0, rescueTeamsAmount[C1], 1));
            for (int i = 0; i < N; i++) {
                if (i != C1) {
                    UList.add(MapInit(i, INF, rescueTeamsAmount[i], 0));
                }
                matrix[i] = new int[N];
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = (i == j) ? 0 : INF;
                }
            }
            // dist赋值
            int index = 0;
            for (int i = 0; i < M; i++) {
                int begin = input[index];
                index ++;
                int end = input[index];
                index ++;
                matrix[begin][end] = matrix[end][begin] = input[index];
                index ++;
            }
            dijkstra(SList, UList, matrix);

            int numOfShortest = 0, totalTeams = 0;
            Map<String, Integer> cityMap = getCityMap(SList, C2);
            numOfShortest = cityMap.get("numOfMinDist");
            totalTeams = cityMap.get("maxRescueTeam");

            System.out.println(numOfShortest + " " + totalTeams);
        }
    }

}
