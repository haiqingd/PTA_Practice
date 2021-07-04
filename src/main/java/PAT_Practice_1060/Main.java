package PAT_Practice_1060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (!line.equals(" null")) {
            sb.append(line);
            line = " " + in.readLine();
        }
        System.out.println(submit(sb.toString()));
    }

    static String submit(String line) {
        String[] split = line.split(" ");
        int N = Integer.parseInt(split[0]);

        BigDecimal A = new BigDecimal(split[1], new MathContext(N, RoundingMode.DOWN));
        BigDecimal B = new BigDecimal(split[2], new MathContext(N, RoundingMode.DOWN));

        StringBuilder sb = new StringBuilder();
        if (numToString(N, A).equals(numToString(N, B))) {
            sb.append("YES");
        } else {
            sb.append("NO");
            sb.append(" ").append(numToString(N, A));
        }
        sb.append(" ").append(numToString(N, B));


        return sb.toString();
    }

    static String numToString(int numOfDigits, BigDecimal num) {
        String[] split = num.toPlainString().split("\\.");
        StringBuilder numStrWithoutDot = new StringBuilder(String.join("", split).replaceFirst("^0*", ""));
        int weishu;

        if (split[0].equals("0")) {
            if (split.length == 1 || (new BigDecimal(split[1])).toPlainString().equals("0"))  weishu = 0;
            else weishu = (new BigDecimal(split[1])).toPlainString().length() - split[1].length();
        } else {
            weishu = split[0].length();
        }

        int digitSub = numStrWithoutDot.length() - numOfDigits;
        for (int i = 0; i < -digitSub; i++) {
            numStrWithoutDot.append("0");
        }
        String s = numStrWithoutDot.substring(0, numOfDigits);

        return "0." + s + "*10^" + weishu;
    }

}