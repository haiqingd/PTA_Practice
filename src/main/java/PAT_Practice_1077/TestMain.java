package PAT_Practice_1077;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

public class TestMain {
    public static String TEST_CASE_PATH = "/testcase.json";

    @Test
    void testSubmit() {
        JSONArray jsonArray = null;
        boolean noErrorFlag = true;
        try {
            jsonArray = readTestCases(TEST_CASE_PATH);
        } catch (IOException e) {
            System.out.println("Json文件格式有误");
        }
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.print("id : " + jsonObject.getStr("id"));
                try {
                    assert Objects.equals(Main.submit(jsonObject.getStr("input")), jsonObject.getStr("answer"));
                    System.out.println(" OK");
                } catch (AssertionError e) {
                    System.out.println(" Error! \n answer: " + jsonObject.getStr("answer") + "\n result: " + Main.submit(jsonObject.getStr("input")) + "\n");
                    noErrorFlag = false;
                }
            }
            assert noErrorFlag;
        }

    }

    JSONArray readTestCases(String filepath) throws IOException {
        InputStream is = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/" + getClass().getPackage().getName() + filepath);
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        return new JSONObject(sb, true).getJSONArray("testcase");
    }
}
