# 解题模板使用说明

## 1. 关于 Main 和 CorrectMain
### 1.1 输入输出
main 方法已经包装好，无需再动。它会将包含空格和回车的输入全部写入 StringBuilder sb 中。

使用了 BufferedReader 作为标准输入选取的类，因为在类似PTA的这种算法题中，输入是从控制台中读取，此时 BufferedReader 的效率相对于 Scanner要高得多。

若要在 submit 方法中使用 line 输入，可以使用以下的操作

```java
String[] inputs = line.split(" |\n");
int K = Integer.parseInt(inputs[0]);
```

输出时直接在 submit 方法中返回一串输出字符串（注意Java中的字符串是不可变的，每次修改都会重新 new 一个新的 String，非常耗时，建议使用 StringBuilder 或者 StringBuffer 的 append 方法）
### 1.2 关于 static 修饰的全局变量

**不要使用 static 修饰的全局变量！**

目前的测试类、生成测试用例的类和生成结果的类都只是对程序中的submit方法进行简单的调用。对于多条测试用例的情况，并不会重新初始化，多次调用可能会导致测试时程序的异常。

todo: 研究一下手动完成垃圾回收的方式，不知是否对此种操作有效。

### 1.3 CorrectMain 存在的意义

生成测试用例的参数需要，没有会报错。或者可以手动更改 GenerateTestCase 中的关于 CorrectMain 的调用。

## 2. 测试用例 testcase.json 和 testcaseGenerated.json

分别为手动输入的测试用例和程序生成的测试用例，格式如下：

```json
{
  "testcase": [
    {
      "id": 2,
      "input": "4\n-11 -28 -61 -63 ",
      "answer": "0 -11 -63"
    },
    {
      "id": 3,
      "input": "9\n-96 5 70 35 -83 56 -2 62 31 ",
      "answer": "174 5 31"
    }
  ]
}
```

- id: 测试时的标识，便于快速定位
- input: 程序的输入，可以包含"\n"
- answer: 在 testcase.json 中为程序的预期输出，若不符合则会在测试时报错。在 testcaseGenerated.json 中为指定程序（Main或CorrectMain）的输出结果；若设置了，也可以为空。

## 3. 测试类 TestMain

会跑完所有的测试用例，并比对结果。案例如下

```shell
id : 2 OK
id : 3 OK
id : 4 OK
id : 5 Error! 
 answer: 212 0 98
 result: 212 32 98

id : 6 OK
id : 7 Error! 
 answer: 56 0 56
 result: 56 56 56

id : 8 OK
id : 9 Error! 
 answer: 99 0 99
 result: 99 99 99

id : 10 OK
id : 11 OK
id : 12 OK
id : 13 OK
id : 14 OK
id : 15 Error! 
 answer: 126 0 71
 result: 126 55 71
 java.lang.AssertionError
	at PAT_Practice_1007.TestMain.testSubmit(TestMain.java:34)
	......
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)
```

## 4. 生成结果类 GenerateResult

将空着的和与预期不符合的结果都填充为 Main 程序的输出结果。案例如下
```shell
id : 5
oldAnswer:212 0 98
newAnswer:212 32 98

id : 7
oldAnswer:56 0 56
newAnswer:56 56 56

id : 9
oldAnswer:99 0 99
newAnswer:99 99 99

id : 15
oldAnswer:126 0 71
newAnswer:126 55 71
```

## 5. 生成测试用例类 GenerateTestCase

根据配置内容，随机生成大量的测试用例。对于不同的题目，需要增加不同的生成方式方法。

这里已给出了一个：`第一个数为N, 接下来有N个数` 这样的一个测试用例类型的生成方法。

### 5.1 配置项说明

```java
    // 存放输出文件的路径，没有会自动生成
    static String TEST_CASE_GENERATED_PATH = "/testcaseGenerated.json";
    // 生成测试用例的数量
    static int caseNumber = 100;
    // 起始ID
    static int startId = 2;
    // 数据为 0 的比例
    static double dataZeroRate = 0.2;
    // 数据为负数的比例
    static double dataNegativeRate = 0.4;
    // N 为 0 的比例
    static double NZeroRate = 0;
    // N 的取值范围（1, 10）
    static int rangeN = 10;
    // 数据的取值范围（-100, 100）
    static int minData = -100;
    static int maxData = 100;
    // 是否需要填充答案
    static boolean isNeedAnswer = true;
    // 是否用 CorrectMain 来填充答案 （否则为用Main）
    static boolean useCorrectMain = true;
```

## 5.2 使用说明
运行 main , 若造出的测试数据导致了程序异常，会有错误提示，并提示出造成错误的测试用例数据 inputs

正常运行生成的结果案例如下：

```json
{
  "testcase": [
    {
      "id": 2,
      "input": "4\n-11 -28 -61 -63 ",
      "answer": "0 -11 -63"
    },
    {
      "id": 3,
      "input": "9\n-96 5 70 35 -83 56 -2 62 31 ",
      "answer": "174 5 31"
    },
    {
      "id": 4,
      "input": "7\n79 0 52 49 -10 -24 -66 ",
      "answer": "180 79 49"
    },
    {
      "id": 5,
      "input": "10\n-96 -2 0 32 0 0 0 82 98 0 ",
      "answer": "212 0 98"
    },
    {
      "id": 6,
      "input": "10\n3 32 -3 75 0 0 -86 -52 59 0 ",
      "answer": "107 3 75"
    },
    {
      "id": 7,
      "input": "8\n55 -98 -51 -82 0 56 -91 -8 ",
      "answer": "56 0 56"
    },
    {
      "id": 8,
      "input": "10\n-3 52 10 44 65 -13 0 -17 -62 0 ",
      "answer": "171 52 65"
    }
  ]
}
```