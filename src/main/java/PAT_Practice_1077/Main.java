package PAT_Practice_1077;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (!line.equals("\nnull")) {
            sb.append(line);
            line = "\n" + in.readLine();
        }
        System.out.println(submit(sb.toString()));
    }

    static String submit(String line) {
        String[] split = line.split("\n");
        int N = Integer.parseInt(split[0]);
        int shortestIndex = 0;
        for (int i = 0; i < N; i++) {
            if (split[i+1].length() < split[shortestIndex+1].length() ) shortestIndex = i;
        }
        StringBuilder maxSuffix = new StringBuilder(split[shortestIndex+1]);
        for (int i = 0; i < N; i++) {
            if (!split[i + 1].endsWith(maxSuffix.toString())) {
                while (!split[i + 1].endsWith(maxSuffix.toString())) {
                    // 不断缩小max直到endWith
                    maxSuffix.replace(0, 1, "");
                }
                i = 0;
            }
            if (maxSuffix.length() == 0) return "nai";
        }

        return maxSuffix.toString();
    }
}