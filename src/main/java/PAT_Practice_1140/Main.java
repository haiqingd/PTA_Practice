package PAT_Practice_1140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (!line.equals("\nnull")){
            sb.append(line);
            line = "\n" + in.readLine();
        }
        System.out.println(submit(sb.toString()));
    }
    static String submit(String line){
        String[] inputs = line.split("\n")[1].split(" ");
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String input : inputs) {
            int numberSum = 0;
            char[] chars = input.toCharArray();
            for (char aChar : chars) {
                numberSum += aChar - '0';
            }
            map.merge(numberSum, 1, Integer::sum);
        }

        ArrayList<Integer> res = new ArrayList<>(map.keySet());
        StringBuilder sb = new StringBuilder();
        sb.append(res.size()).append("\n");
        res.sort(Comparator.comparingInt(o -> o));
        sb.append(String.join(" ", Arrays.toString(res.toArray()).split("[\\[\\]]")[1].split(", ") ));

        return sb.toString();
    }
}