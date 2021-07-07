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
        return "";
    }
}