# PAT_Practice_1023 Have Fun with Numbers (20 分)

## 类型

- 输入输出

## 题目描述
![](https://image.haiqingd.top/20210630131101.png)

判断加倍前后的数字是否是由原来的几个数字排列组合而成
## 解题思路
由于题目中说明了数字不超过20位，而 java 中的long类型的最大值约为 9*10^18，位数不够。

这里使用 BigInteger 类来存储数值。判断逻辑采用Map形式，结构为"digit": NumberOfDigit。

## 提交过程
### 1
![](https://image.haiqingd.top/20210630133724.png)

### 2
发现是遍历double后的数值是漏写了map中出现的数字-1

![](https://image.haiqingd.top/20210630135129.png)
## 总结

##参考文献
[类 BigInteger](http://www.javaweb.cc/help/JavaAPI1.6/java/math/BigInteger.html#method_summary)