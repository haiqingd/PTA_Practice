package PAT_Practice_1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
        String[] split = line.split(" |\n");
        int K = Integer.parseInt(split[1]);
        for (int i = 0; i < split[2].length() - K + 1; i++) {
            String substring = split[2].substring(i, i + K);
            if (isPrime(Integer.parseInt(substring))) return substring;
        }

        return "404";
    }

    static boolean isPrime(int num){
        //两个较小数另外处理
        if(num ==2|| num==3 )
            return true;
        //不在6的倍数两侧的一定不是质数
        if(num %6!= 1&&num %6!= 5)
            return false ;

        for (int i = 5; i <= Math.sqrt(num); i+=6) {
            if(num %i== 0||num %(i+ 2)==0 )
                return false;
        }

        return true;

    }
}