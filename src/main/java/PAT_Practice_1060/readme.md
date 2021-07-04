# PAT_Practice_1060 Are They Equal (25 分)

## 类型
- 输入输出
- 高精度浮点数计算
- 科学计数法


## 题目描述
![](https://image.haiqingd.top/20210704210433.png)


## 解题思路
先判断位数是否相等，位数相等时判断前N位的值

## 提交过程
### 1
![](https://image.haiqingd.top/20210704231248.png)

### 2
对于小数点前的数值是"0"的数，位数计算为 0，而不是"0"的长度1（testcase：3）

![](https://image.haiqingd.top/20210704231615.png)

但是没有用

### 3
对于位数不足的（testcase：5），在结果的末位后面补0

![](https://image.haiqingd.top/20210704231838.png)

但是没有用

### 4
对于 N 是0的情况，由于BigDecimal的构造函数0代表不进行round操作，所以不能直接比较round后的A和B，改成了比较格式化后的字符串

![](https://image.haiqingd.top/20210704232223.png)

通过了测试点6

### 5
对于A或者B是小于0.1的数时，没有处理幂次为负的情况。(testcase: 10)
使用了将小数点后的数字字符串的位数减去将其转化成BigDecimal再toString后的位数，其相反数作为幂次。

![](https://image.haiqingd.top/20210704232500.png)

通过了测试点3，但是改出了bug导致测试点6没有通过。

### 6
修改了第五次提交产生的BUG(testcase: 11)

![](https://image.haiqingd.top/20210704232627.png)

## 总结

## 参考文献

[java使用BigDecimal转换科学计数法数字和浮点数转换方法](https://blog.csdn.net/qq_42132556/article/details/106483855)

[Java去掉数字字符串开头的0三种方法(推荐)](https://www.jb51.net/article/113472.htm)

[java join字符串_Java字符串join（）](https://blog.csdn.net/cunchi4221/article/details/107476054)