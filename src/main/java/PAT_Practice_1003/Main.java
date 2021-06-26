package PAT_Practice_1003;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.max;

public class Main {
    static Integer INF = MAX_VALUE;
    static int N, M, C1, C2;
    static int[] rescueTeamsAmount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        C1 = sc.nextInt();
        C2 = sc.nextInt();
        rescueTeamsAmount = new int[N];
        for (int i = 0; i < N; i++) {
            rescueTeamsAmount[i] = sc.nextInt();
        }
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

        int numOfShortest = 0, totalTeams = 0;
        Map<String, Integer> cityMap = getCityMap(SList, C2);
        numOfShortest = cityMap.get("numOfMinDist");
        totalTeams = cityMap.get("maxRescueTeam");

        System.out.println(numOfShortest + " " + totalTeams);


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


}
