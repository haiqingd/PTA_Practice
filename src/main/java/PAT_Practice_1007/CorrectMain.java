package PAT_Practice_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CorrectMain {

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
        int sum = 0;//数组中元素的和，在后面for循环中使用，用于和max比较
        int max = -1;//题目中所求的最大和，因为题中说明当最大和小于零时，只需要输出0即可，所以设置max为-1，便于比较
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = line.split(" |\n");
        String s = split[0];
        int K = Integer.parseInt(s);//K为输入数字的个数，也就是输入的第一行
        int[] list = new int[K];//输入的第二行，共有K个数字
        int left = 0;//最大和的左侧索引
        int right = K-1;//最大和的右侧索引
        int temp = 0;//进行动态规划时的子数组的左侧索引，当max>0时将题目所求的左侧索引temp赋值给left

        //使用for循环转化成int数组
        for(int i = 0;i<K;i++)
        {
            list[i] = Integer.parseInt(split[i + 1]);
        }

        //进行动态规划，通过索引分割出所求子数组
        for(int i = 0;i<K;i++)
        {
            sum+=list[i];
            if(sum<0)
            {
                sum = 0;//这里是用来求最大和，所以当sum小于0时进行重置
                temp=i+1;
            }
            else if(sum>max)
            {
                max = sum;
                left = temp;
                right = i;
            }
        }
        if(max<0) max = 0;//因为sum赋值给max，所以当max<0时令max=0
        return  (max+" "+list[left]+" "+list[right]);
    }


}