#0opslab-utils
---
封装了一些常用Java操作方法,便于重复开发利用.
另外希望身为Java牛牛的你们一起测试和完善 一起封装和完成常用的Java代码。
节约撸码时间以方便有更多的时间去把妹子～<br>

#me
---
<p align = "center">[新浪微博](http://weibo.com/5204661479)</p>
<p align = "center">少侠请老夫喝杯咖啡慢慢骚聊</p>
<img src="http://0opslab.com/0opslab%20util_files/90f1d25e7a6c3a956bf59bb8fb66318e.png"
 height="150" width="150" alt="少侠请老夫喝杯咖啡慢慢骚聊"/>


#API-DOC
[DOC](http://0opslab.com/0opslabutil.html)

#下一步计划
常用的方法已经添加的差不多了,下一步计划将现有的方法在windows和linux都进行相应的测试,并使用findbugs进行一定的错误排查.

#开发环境
    Win7x64 && ubuntu14
    JDK1.7
    IDEA14

#项目结构
    ├─Junit#测试类
    │  └─com.opslab
    │      │  CharsetTest.java
    │      │  DateUtilTest.java
    │      │  DateUtilThreadSafeTest.java
    │      │  EmailUtilTest.java
    │      │  FilePathUtilTest.java
    │      │  FileUtilTest.java
    │      │  PrintUtilTest.java
    │      │  ProUtilTest.java
    │      │  RandomUtilTest.java
    │      │  RegUtilTest.java
    │      │  SecUtilTest.java
    │      │  StringUtilTest.java
    │      │  SupportTest.java
    │      │  SysUtilTest.java
    │      │  ZIPUtilTest.java
    │      │
    │      └─algorithmImpl
    │              FileEncodingUtilTest.java
    │              FileImplTest.java
    │              FileTypeImplTest.java
    │
    ├─lib#需要的Jar
    │
    ├─out#相应的Jar
    │  ├─artifacts
    │  │  └─evilp0s_util
    │  │          evilp0s-util.jar
    │
    └─src
        └─com.opslab
            │  CharsetUtil.java     #字符串相关的工具类
            │  ClassUtil.java       #Java Class与反射相关的一些工具类
            │  ChinesUtil.java      #中文相关的工具类
            │  ConvertUtil.java     #转换相关的工具类
            │  DateUtil.java        #日期时间相关的工具类
            │  EmailUtil.java       #Email相关的工具类
            │  FilePathUtil.java    #文件路径相关的工具类
            │  FileUtil.java        #文件相关的工具类
            │  PrintUtil.java       #打印相关的工具类
            │  ProUtil.java         #属性文件相关的工具类
            │  RandomUtil.java      #随机操作的相关的工具类
            │  RegUtil.java         #正则相关的工具类
            │  SecUtil.java         #安全相关的工具类
            │  StreamUtil.java      #Stream相关的工具类
            │  StringUtil.java      #字符串相关的工具类
            │  SysUtil.java         #系统相关的工具类
            │  ValidUtil.java       #字符串相关的工具类
            │  WebUtil.java         #Web相关的工具类
            │  ZIPUtil.java         #zip相关的工具类
            │
            ├─algorithmImpl#一些算法的实现细节
            │      cpDetector.java
            │      FileEncodingUtil.java
            │      FileImpl.java
            │      FileReadImpl.java
            │      FileTypeImpl.java
            │      StringImpl.java
            │
            └─FTP#FTP相关操作的封装
                    FileAttr.java
                    FTPConstant.java
                    FTPFactory.java
                    FTPLog.java
                    FTPtest.java
                    FTPUtil.java
                    FTPUtilImpl.java
                    FTPVo.java
                    FunctionSet.java