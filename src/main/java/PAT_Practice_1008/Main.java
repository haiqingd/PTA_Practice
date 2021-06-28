package PAT_Practice_1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        System.out.println(submit(line));
    }
    static String submit(String line){
        String[] inputs = line.split(" ");
        int N = Integer.parseInt(inputs[0]);
        int nowFloor = 0, totalTime = 0, upTime = 6, downTime = 4, stopTime = 5, data;
        for (int i = 0; i < N; i++) {
            data = Integer.parseInt(inputs[i+1]);
            int sub = data - nowFloor;
            totalTime += sub > 0 ? upTime * sub : downTime * -sub;
            totalTime += stopTime;
            nowFloor = data;
        }


        return String.valueOf(totalTime);
    }
}
