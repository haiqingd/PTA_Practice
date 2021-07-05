package PAT_Practice_1061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static final String[] weekday = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};


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
        String[] split = line.split("\\s+");
        boolean firstFlag = true;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split[0].length() && i < split[1].length(); i++) {
            if (split[0].charAt(i) == split[1].charAt(i)) {
                char same = split[0].charAt(i);
                if (firstFlag && same >= 'A' && same <= 'G') {
                    sb.append(weekday[same - 'A']).append(" ");
                    firstFlag = false;
                } else if (!firstFlag && ((same >= '0' && same <= '9') || (same >= 'A' && same <= 'N'))) {
                    if (same <= '9') sb.append(same - '0').append(":");
                    else sb.append(String.format("%02d", (same - 'A' + 10))).append(":");
                    break;
                }
            }
        }

        for (int i = 0; i < split[2].length() && i < split[3].length(); i++) {
            if (split[2].charAt(i) == split[3].charAt(i)) {
                char same = split[2].charAt(i);
                if (Character.isLowerCase(same) || Character.isUpperCase(same)) {
                    sb.append(String.format("%02d", i));
                    break;
                }
            }
        }
        return sb.toString();
    }
}