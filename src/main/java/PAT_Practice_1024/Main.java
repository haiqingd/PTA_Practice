package PAT_Practice_1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


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
        String[] lineStr = line.split(" ");
        long N = Long.parseLong(lineStr[0]);
        int K = Integer.parseInt(lineStr[1]);
        int i;
        BigInteger number = new BigInteger(String.valueOf(N));
        for (i = 0; i < K; i++) {
            if(!isPalindromic(number)) {
                number = palindromicAdd(number);
            }else {
                break;
            }
        }
        return number + "\n" + i;
    }
    static boolean isPalindromic(BigInteger number){
        byte[] bytes = number.toString().getBytes();
        for (int i = 0; i < bytes.length/2 ; i++) {
            if(bytes[i] != bytes[bytes.length -1 - i])  return false;
        }
        return true;
    }
    static BigInteger palindromicAdd (BigInteger number){
        return number.add(new BigInteger((new StringBuffer(number.toString())).reverse().toString()));
    }
}