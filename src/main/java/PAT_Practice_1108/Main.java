package PAT_Practice_1108;

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
        StringBuilder sb = new StringBuilder();
        if(Integer.parseInt(line.split("\n")[0]) == 0) return "The average of 0 numbers is Undefined";
        String[] inputs = (line.split("\n")[1]).split(" ");
        int legalNums = 0;
        double averData = 0;

        for (String input : inputs) {
            double data = 0;
            try {
                data = Double.parseDouble(input);
                String[] split = input.split("\\.");
                if ( split.length > 1 && split[1].length() > 2) throw new Exception();
                if (data > 1000 || data < -1000) throw new Exception();
            } catch (Exception e) {
                sb.append("ERROR: ").append(input).append(" is not a legal number\n");
                continue;
            }
            averData = (legalNums * averData + data) / ++legalNums;
        }

        if (legalNums == 1)sb.append("The average of ").append(legalNums).append(" number is ");
        else sb.append("The average of ").append(legalNums).append(" numbers is ");
        if (legalNums > 0) sb.append(String.format("%.2f",averData));
        else sb.append("Undefined");

        return sb.toString();
    }
}