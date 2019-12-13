#opslabJutil
---
封装了一些常用Java操作方法,便于重复开发利用.
另外希望身为Java牛牛的你们一起测试和完善 一起封装和完成常用的Java代码。
节约撸码时间以方便有更多的时间去把妹子～

<img src="https://0opslab.github.io/img/wpwx.png" alt="关注我" style="max-width:20%;" />
<img src="https://0opslab.github.io/img/wxpay.png" alt="请问喝杯咖啡" style="max-width:20%;" />

---
#开发环境
    Win7x64 && ubuntu14 && MacOS
    JDK1.8
    IDEA14


#usage
```xml
<dependency>
    <groupId>com.0opslab</groupId>
    <artifactId>opslabJutil</artifactId>
    <version>1.0.8</version>
</dependency>

<!--后续版本-->
<!-- 由于上传maven中心比较慢，相当浪费时间，因此后续版本不在上传的maven中心，可以像如下方式使用 -->
<!-- Since uploading the maven center is slow and time consuming, the subsequent 
       version is not in the uploaded maven center and can be used as follows -->
<dependency>
    <groupId>com.0opslab</groupId>
    <artifactId>opslabJutil</artifactId>
    <version>3.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/opslabJutil-2.0.2.jar</systemPath>
</dependency>
```

## 在线api
[opslabJutil2.x API Doc](https://0opslab.github.io/opslabJutil/opslabJutil2.0.html)
[opslabJutil3.0.0 API Doc](https://0opslab.github.io/opslabJutil/opslabJutil3.0.0.html)

## 下一步计划
* 加入WEB开发中常用的代码,如HTTP的安全校验,HTTP请求信的过滤,已经HTTP的请求的性能分析等工作。
* 安全校验
* 代码优化

## config
如果需要修改默认配置可以通过在classpath下添加0opslab.properties文件来进行一些配置
