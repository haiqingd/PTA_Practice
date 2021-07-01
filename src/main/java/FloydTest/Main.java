package FloydTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static int INF = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (!line.equals(" null")){
            sb.append(line);
            line = " " + in.readLine();
        }
        System.out.println(submit(sb.toString()));
    }
    static String submit(String line){
        String[] split = line.split(" |\n");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int from = Integer.parseInt(split[2]);
        int to = Integer.parseInt(split[3]);
        int[][] D = new int[N][];
        int[][] S = new int[N][];
        for (int i = 0; i < D.length; i++) {
            D[i] = new int[N];
            Arrays.fill(D[i], INF);
            D[i][i] = 0;
            S[i] = new int[N];
            for (int j = 0; j < N; j++) {
                S[i][j] = j;
            }
        }

        for (int i = 0; i < M; i++) {
            int start = Integer.parseInt(split[3 * i + 4]);
            int end = Integer.parseInt(split[3 * i + 5]);
            int distance = Integer.parseInt(split[3 * i + 6]);
            D[start][end] = distance;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i != k){
                    for (int j = 0; j < N; j++) {
                        if(j != i && j!= k && D[i][k] + D[k][j] < D[i][j]){
                            D[i][j] = D[i][k] + D[k][j];
                            S[i][j] = S[i][k];
                        }
                    }
                }
            }
        }


        return D[from][to] + " " + S[from][to];
    }
}