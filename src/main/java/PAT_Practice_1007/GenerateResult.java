package PAT_Practice_1007;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class GenerateResult {
    public static String TEST_CASE_PATH = "/testcase.json";
    public static void main(String[] args) {
        Boolean isNeedGenerated = false;
        JSONArray jsonArray = null;
        try {
            jsonArray = new TestMain().readTestCases(TEST_CASE_PATH);
        } catch (IOException e) {
            System.out.println("Json文件格式有误");
        }
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String result = Main.submit(jsonObject.getStr("input"));
                if(!result.equals(jsonObject.getStr("answer"))){
                    System.out.println("id : " + jsonObject.getStr("id") + "\noldAnswer:" + jsonObject.getStr("answer") + "\nnewAnswer:" + result + "\n");
                    jsonObject.set("answer", result);
                    isNeedGenerated = true;
                }
            }
        }
        if(! isNeedGenerated) System.out.println("所有测试用例数据均已完成且符合要求");
        else {
            writeToTestcase(jsonArray, TEST_CASE_PATH);
        }
    }

    static void writeToTestcase(JSONArray jsonArray, String filepath) {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.set("testcase", jsonArray);
        String jsonString = jsonObject.toStringPretty();
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/java/" + GenerateResult.class.getPackage().getName() + filepath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
