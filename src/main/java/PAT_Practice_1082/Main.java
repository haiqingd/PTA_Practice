package PAT_Practice_1082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static final String[] numOfDigits = {"", "Shi", "Bai", "Qian", "Wan", "Shi", "Bai", "Qian", "Yi"};
    static final String[] digits = {"", "yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu"};
    static final String zero = "ling";

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
        StringBuilder sb = new StringBuilder();
        boolean isZeroBefore = false;
        int positionOfDigit;
        int index = 0;
        if (line.equals("0")) return "ling";

        if (line.startsWith("-")){
            index ++;
            sb.append("Fu ");
        }


        while (index < line.length()){
            positionOfDigit = line.length() -1 -index;

            // 每到 亿 和 万 都是必读并且重置前置0
            if (line.charAt(index) == '0'){
                if( positionOfDigit != 4) isZeroBefore = true;
                else sb.append(numOfDigits[positionOfDigit]).append(" ");
            }else {
                if (isZeroBefore) sb.append(zero).append(" ");
                sb.append(digits[line.charAt(index) - '0']).append(" ").append(numOfDigits[positionOfDigit]).append(" ");
                isZeroBefore = false;
            }
            index ++;
        }

        while (sb.toString().endsWith(" ")){
            sb.deleteCharAt(sb.lastIndexOf(" "));
        }

        return sb.toString();
    }
}