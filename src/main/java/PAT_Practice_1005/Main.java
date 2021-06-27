package PAT_Practice_1005;

import java.util.Scanner;

public class Main {
    static String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        String input = new Scanner(System.in).next();
        char[] chars = input.toCharArray();
        int sum = 0;
        for (char aChar : chars) {
            sum += aChar - '0';
        }
        char[] resultDigits = (String.valueOf(sum)).toCharArray();
        for (int i = 0; i < resultDigits.length; i++) {
            if(i != 0) System.out.print(" ");
            System.out.print(words[resultDigits[i] - '0']);
        }
    }
}
