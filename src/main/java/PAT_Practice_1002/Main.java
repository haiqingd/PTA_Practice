package PAT_Practice_1002;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k1, k2, k = 0, zhishu;
        float xishu;
        float[] res = new float[1001];

        k1 = sc.nextInt();
        while(k1 -- != 0){
            zhishu = sc.nextInt();
            xishu = sc.nextFloat();
            k++;
            res[zhishu] += xishu;
        }
        k2 = sc.nextInt();
        while (k2 -- != 0){
            zhishu = sc.nextInt();
            xishu = sc.nextFloat();
            if(res[zhishu] == 0){
                k++;
            }
            res[zhishu] += xishu;
            if(res[zhishu] == 0){
                k--;
            }
        }

        System.out.print(k);
        for (int i = res.length - 1; i >= 0; i--) {
            if(res[i] != 0){
                System.out.print(" " + i);
                System.out.print(" " + String.format("%.1f", res[i]));
            }
        }
        System.out.println();
    }
}
