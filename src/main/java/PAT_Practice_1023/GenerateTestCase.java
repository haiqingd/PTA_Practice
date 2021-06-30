package PAT_Practice_1023;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;


public class GenerateTestCase {
    static String TEST_CASE_GENERATED_PATH = "/testcaseGenerated.json";
    static int caseNumber = 100;
    static int startId = 2;
    static double dataZeroRate = 0.2;
    static double dataNegativeRate = 0.4;
    static double NZeroRate = 0;
    static int rangeN = 10;
    static int minData = -100;
    static int maxData = 10;
    static int dataLength = 10;
    static boolean isNeedAnswer = true;
    static boolean useCorrectMain = false;


    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < caseNumber; i++) {
            JSONObject jsonObject = new JSONObject(true);
            String input = generateLong();
            jsonObject.set("id", startId + i);
            jsonObject.set("input", input);
            try{
                jsonObject.set("answer", isNeedAnswer ? (useCorrectMain? CorrectMain.submit(input) : Main.submit(input)) : "");
            }catch (Exception E){
                System.out.println("ERROR! \ninput:" + input + "\n" + E);
                throw E;
            }
            jsonArray.add(jsonObject);
        }
        GenerateResult.writeToTestcase(jsonArray, TEST_CASE_GENERATED_PATH);
        System.out.println("生成成功，路径为：" + System.getProperty("user.dir") + "/src/main/java/" + GenerateResult.class.getPackage().getName() + TEST_CASE_GENERATED_PATH);
    }

    static String generateNIntWithNBefore(){
        StringBuilder sb = new StringBuilder();
        double NJudge = Math.random();
        if(NJudge < NZeroRate) return "0";
        else {
            int N = (int) (Math.random() * rangeN) + 1;
            sb.append(N + "\n");
            for (int i = 0; i < N; i++) {
                double dataJudge = Math.random();
                if(dataJudge < dataZeroRate){
                    sb.append(0);
                }else if( dataJudge < dataZeroRate + dataNegativeRate){
                    sb.append((int) (Math.random() * minData));
                }else {
                    sb.append((int) (Math.random() * maxData));
                }
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    static String generateLong(){
        StringBuilder sb = new StringBuilder();
        int judgeLength = (int) (Math.random() * dataLength + 1);
        for (int i = 0; i < judgeLength; i++) {
            sb.append((int) (Math.random() * maxData));
        }
        return sb.toString();
    }
}

