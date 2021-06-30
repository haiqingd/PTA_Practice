package PAT_Practice_1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;


public class Main {
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
        HashMap<String, Integer> map = new HashMap<>();
        BigInteger inputNum = new BigInteger(line);
        StringBuilder result = new StringBuilder();
        String[] inputNumChars = line.split("");
        for (String s : inputNumChars) {
            map.merge(s, 1, Integer::sum);
        }
        BigInteger doubleNum = inputNum.multiply(new BigInteger("2"));
        String[] doubleNumChars = doubleNum.toString().split("");
        boolean noFlag = false;
        for (String s : doubleNumChars) {
            if(map.get(s) == null || map.get(s) == 0){
                result.append("No\n");
                noFlag = true;
                break;
            }
            map.put(s, map.get(s) - 1);
        }
        if(!noFlag) result.append("Yes\n");
        for (String doubleNumChar : doubleNumChars) {
            result.append(doubleNumChar);
        }

        return result.toString();
    }
}