package PAT_Practice_1007;

import java.util.Scanner;

public class Main {
    static int sum, toAddSum, sumNext, toAddSumNext;
    static boolean isNext, isAllNegative = true;
    static int begin, end, beginNext, endNext;


    // 思路：遍历整个序列，记录两个数值，sum和toAddSum，分别表示截至目前找到的最大总和，以及待确认的和。sumNext和toAddSumNext表示下一个记录，isNext为标记位
    // 过程：从0开始，将读取到的数值记录在toAddSum中
    // 情形1：添加后 toAddSum 的值 大于 0，此时确认了toAddSum可以添加进目前的最大子序列，sum添加，toAddSum归零
    // 情形2：添加后 toAddSum 的值 小于等于（等于的地方需要根据题意确认）0，继续向后读取，并添加进 toAddSum
    // 情形3：添加后 toAddSum + sum 的和 小于 0，启用sumNext和toAddSumNext，从下一个值记录进toAddSumNext中，重复和之前一样的过程
    // 情形4：当sumNext中的值大于sum时，将sumNext赋值给sum，sumNext重置
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = Integer.parseInt(sc.nextLine());
        String[] inputs = sc.nextLine().split(" ");

        for (int i = 0; i < K; i++) {
            int data = Integer.parseInt(inputs[i]);
            if(isAllNegative && data >= 0){
                isAllNegative = false;
            }
            if(!isNext){
                toAddSum += data;
                if(toAddSum > 0){
                    if(sum == 0) begin = data;
                    end = data;
                    sum += toAddSum;
                    toAddSum = 0;
                }
                else if( sum + toAddSum < 0) {
                    toAddSum = 0;
                    isNext = true;
                }
            }
            else {
                toAddSumNext += data;
                if(toAddSumNext > 0){
                    if(sumNext == 0) beginNext = data;
                    endNext = data;
                    sumNext += toAddSumNext;
                    toAddSumNext = 0;
                    if( sumNext > sum) {
                        isNext = false;
                        begin = beginNext;
                        end = endNext;
                        sum = sumNext;
                        sumNext = 0;
                    }
                }
                else if( sumNext + toAddSumNext < 0){
                    toAddSumNext = 0;
                }
            }
        }

        if(isAllNegative) {
            System.out.println(0 + " " + Integer.parseInt(inputs[0]) + " " + Integer.parseInt(inputs[K-1]));
            return;
        }
        System.out.println(sum + " " + begin + " " + end);

    }

}
