package PAT_Practice_1035;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        String[] inputs = line.split(" |\n");
        int N = Integer.parseInt(inputs[0]);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        int resultNum = 0;
        for (int i = 0; i < N; i++) {
            String pwd = inputs[2 * i + 2];
            if (pwd.contains("1") || pwd.contains("0") || pwd.contains("l") || pwd.contains("O")) {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", inputs[2 * i + 1]);
                pwd = pwd.replace('1', '@')
                        .replace('0', '%')
                        .replace('l', 'L')
                        .replace('O', 'o');
                resultNum ++;
                map.put("password", pwd);
                list.add(map);
            }
        }
        StringBuffer sb = new StringBuffer();
        if(resultNum != 0){
            sb.append(resultNum);
            for (Map<String, String> map : list) {
                sb.append("\n").append(map.get("id")).append(" ").append(map.get("password"));
            }
        }else {
            if(N == 1) sb.append("There is 1 account and no account is modified");
            else sb.append("There are ").append(N).append(" accounts and no account is modified");
        }
        return sb.toString();
    }
}