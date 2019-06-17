#opslabJutil
---
封装了一些常用Java操作方法,便于重复开发利用.
另外希望身为Java牛牛的你们一起测试和完善 一起封装和完成常用的Java代码。
节约撸码时间以方便有更多的时间去把妹子～

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
    <version>2.0.2</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/opslabJutil-2.0.2.jar</systemPath>
</dependency>
```



## 下一步计划
* 加入WEB开发中常用的代码,如HTTP的安全校验,HTTP请求信的过滤,已经HTTP的请求的性能分析等工作。
* 安全校验
* 代码优化

## config
如果需要修改默认配置可以通过在classpath下添加0opslab.properties文件来进行一些配置

#API
---
```java
#CharsetUtil字符串相关的工具类
##常量字段
    static String	GBK             中文超大字符集
    static String	ISO_8859_1      ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
    static String	US_ASCII        7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
    static String	UTF_16          16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
    static String	UTF_16BE        16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
    static String	UTF_16LE        16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
    static String	UTF_8           8 位 UCS 转换格式

##常量方法
    static String	changeCharset(String str, String newCharset)
    字符串编码转换的实现方法
    static String	changeCharset(String str, String oldCharset, String newCharset)
    字符串编码转换的实现方法
    static String	toASCII(String str)
    将字符编码转换成US-ASCII码
    static String	toGBK(String str)
    将字符编码转换成GBK码
    static String	toISO_8859_1(String str)
    将字符编码转换成ISO-8859-1码
    static String	toUTF_16(String str)
    将字符编码转换成UTF-16码
    static String	toUTF_16BE(String str)
    将字符编码转换成UTF-16BE码
    static String	toUTF_16LE(String str)
    将字符编码转换成UTF-16LE码
    static String	toUTF_8(String str)
    将字符编码转换成UTF-8码


#ClassUtil-Class与反射相关的一些工具类
##常量方法
    static List<String>	getClassName(String packageName, boolean childPackage)
    static String[]	getField(String className)
    获取指定类的全部属性字段
    static String[]	getMethod(String className)
    获取对象的全部方法
    static void	getter(Object obj, String att)
    调用对象的getter方法
    static String	initStr(String old)
    Class	loadClass(String className)
    加载指定的类
    static <T> T	propertiesCopy(Object obj, Class<T> type)
    同名属性值复制 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static <T> T	propertiesCopyIgnore(Object obj, Class<T> type)
    同名属性值复制（忽略大小写） 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static <T> T	propertiesCopyIgnoreFilter(Object obj, Class<T> type, String IgnoreStr)
    同名属性值复制（比较时忽略字符IgnoreStr的内容） 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static void	setter(Object obj, String att, Object value, Class<?> type)
    调用对象的setter方法

#ChinesUtil中文相关的工具类
##常量方法
    static String	getFirstSpell(String chinese)
    获取汉字串拼音首字母，英文字符不变
    static String	getFullSpell(String chinese)
    获取汉字串拼音，英文字符不变
    static String	getPingYin(String inputString)
    将字符串中的中文转化为拼音,其他字符不变
    static boolean	isChinese(String strName)
    static boolean	isChineseByName(String str)
    static boolean	isChineseByREG(String str)
    static boolean  isMessyCode(String strName)
    判断是否是乱码

#ConvertUtil转换相关的工具类
##常量方法
    static int	bytesToInt(byte[] bytes)
    static long	byteToLong(byte[] b)
    static short	byteToShort(byte[] b)
    static byte[]	intToByte(int i)
    static byte[]	longToByte(long number)
    static byte[]	shortToByte(short number)

#DateUtil日期时间相关的工具类
##常量方法
    static Date	calculate(Date date, int second, String timeBurst)
    时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
    static Date	calculate(String date, int second, String timeBurst)
    时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
    static String	Date()
    获取当前的日期
    static String	Date(Date date)
    将指定的时间格式化成出返回
    static Date	Date(String dateStr)
    将指定的字符串解析为时间类型
    static String	DateTime()
    获取当前日期时间
    static String	DateTime(Date date)
    将指定的时间格式化成出返回
    static Date	DateTime(String datestr)
    将指定的字符串解析为时间类型
    static Date	day(Date date, int day)
    在指定的时间上加或减去几天
    static Date	day(int day)
    在当前时间的基础上加或减去几天
    static Date	hour(Date date, float hour)
    在制定的时间上加或减去几小时-支持浮点数
    static Date	hour(float hour)
    在当前时间的基础上加或减去几小时-支持浮点数
    static boolean	isDate(String date)
    判断字符串是否为日期字符串
    static Date	Minute(Date date, int minute)
    在制定的时间上加或减去几分钟
    static Date	Minute(int minute)
    在当前时间的基础上加或减去几分钟
    static Date	month(Date date, int month)
    在指定的时间上加或减去几月
    static Date	month(int month)
    在当前时间的基础上加或减去几月
    static int	subDay(Date startTime, Date endTime)
    获取俩个时间之前的相隔的天数
    static int	subDay(String startTime, String endTime)
    获取俩个时间之前的相隔的天数
    static long	subtimeBurst(Date startDate, Date endDate, String timeBurst)
    返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
    static long	subtimeBurst(String startDate, String endDate, String timeBurst)
    返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
    static long	Subtract(Date date1, Date date2)
    时间date1和date2的时间差-单位秒
    static long	Subtract(String date1, String date2)
    时间date1和date2的时间差-单位秒
    static String	SubtractDate(String date1, String date2)
    获取俩个时间的查结果用时秒表示
    static int	SubtractDay(Date date1, Date date2)
    时间date1和date2的时间差-单位天
    static int	SubtractDay(String date1, String date2)
    时间date1和date2的时间差-单位天
    static int	SubtractHour(Date date1, Date date2)
    时间date1和date2的时间差-单位小时
    static int	SubtractHour(String date1, String date2)
    时间date1和date2的时间差-单位小时
    static int	SubtractMinute(Date date1, Date date2)
    时间date1和date2的时间差-单位分钟
    static int	SubtractMinute(String date1, String date2)
    时间date1和date2的时间差 -单位分钟
    static int	SubtractMonth(Date date1, Date date2)
    时间date1和date2的时间差-单位月
    static int	SubtractMonth(String date1, String date2)
    时间date1和date2的时间差-单位月
    static String	SubtractTime(String date1, String date2)
    获取俩个时间的查结果用时秒表示
    static int	SubtractYear(Date date1, Date date2)
    时间date1和date2的时间差-单位年
    static int	SubtractYear(String date1, String date2)
    时间date1和date2的时间差-单位年
    static String	Time()
    获取当前的时间
    static String	Time(Date date)
    讲指定的时间格式化成出返回
    static Date	Time(String dateStr)
    将指定的字符串解析为时间类型
    static Date	year(Date date, int year)
    在指定的时间上加或减去几年
    static Date	year(int year)
    在当前时间的基础上加或减去year年

#EmailUtilEmail相关的工具类
##常量方法
    void	doSendHtmlEmail(String subject, String sendHtml, String receiveUser)
    发送邮件
    void	doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment)
    发送邮件

#FilePathUtil文件路径相关的工具类
##常量方法
    static String	commandPath(String file)
    返回一个通用的文件路径
    static String	getParentPath(String file)
    返回相一个目录的对于本身的相对父目录

#FileUtil文件相关的工具类
##常量方法
    static boolean	appendLine(File file, String str)
    在文件末尾追加一行
    static boolean	appendLine(File file, String str, String encoding)
    在文件末尾追加一行
    static boolean	cleanFile(File file)
    快速清空一个超大的文件
    static boolean	copy(File file, String targetFile)
    复制文件 通过该方式复制文件文件越大速度越是明显
    static boolean	copy(String resourcePath, String targetPath)
    复制文件
    static void	copyDir(File filePath, String targetPath)
    复制目录
    static void	copyDir(String filePath, String targetPath)
    复制目录
    static int	countLines(File file)
    获取文件的行数
    static String	cpdetector(File file)
    获取文件的编码(cpDetector)探测
    static boolean	createFiles(String filePaht)
    创建文件支持多级目录
    static void	createPaths(String paths)
    创建多级目录
    static boolean	deleteBigFile(File file)
    快速的删除超大的文件
    static boolean	deleteDir(File file)
    删除一个目录
    static boolean	deleteFile(File file)
    删除一个文件
    static String	FileType(File file)
    获取文件的类型
    static String	hash(File file)
    获取文件的Hash
    static List<String>	Lines(File file)
    以列表的方式获取文件的所有行
    static List<String>	Lines(File file, int lines)
    以列表的方式获取文件的指定的行数数据
    static List<String>	Lines(File file, int lines, String encoding)
    以列表的方式获取文件的指定的行数数据
    static List<String>	Lines(File file, String encoding)
    以列表的方式获取文件的所有行
    static List<File>	listFile(File path)
    罗列指定路径下的全部文件
    static List<File>	listFile(String path)
    罗列指定路径下的全部文件
    static List<File>	listFileAll(File path)
    罗列指定路径下的全部文件包括文件夹
    static List<File>	listFileFilter(File dirPath, String postfixs)
    获取指定目录下的特点文件,通过后缀名过滤
    static String	MimeType(String file)
    获取文件的Mime类型
    static Date	modifyTime(File file)
    获取文件最后的修改时间
    static List<File>	searchFile(File dirPath, String fileName)
    在指定的目录下搜寻文个文件
    static List<File>	searchFileReg(File dirPath, String reg)
    查找符合正则表达式reg的的文件
    static String	SimpleEncoding(String file)
    利用简单的文件头字节特征探测文件编码

#PrintUtil打印相关的工具类
##常量方法
    static void	print(Enumeration enums)
    遍历打印Enumeration
    static void	print(List<?> list)
    遍历打印遍历List集合
    static void	print(Map map)
    遍历打印Map集合
    static void	print(String str)
    static void	println(Object obj)
    static void	println(String str)

#ProUtil属性文件相关的工具类
##常量方法
    static String	GetAllProperties(String filePath)
    读取Properties的全部信息
    static String	GetValueByKey(String filePath, String key)
    根据Key读取Value
    static String	key(String key)
    static void	WriteProperties(String filePath, String pKey, String pValue)
    写入Properties信息

#RandomUtil随机操作的相关的工具类
##常量方法
    static int	getNotSimple(int[] param, int len)
    每次生成的len位数都不相同
    static String	LowerString(int length)
    返回一个定长的随机纯大写字母字符串(只包含大小写字母)
    static String	MixString(int length)
    返回一个定长的随机纯字母字符串(只包含大小写字母)
    static String	number(int length)
    static String	String(int length)
    返回一个定长的随机字符串(只包含大小写字母、数字)
    static String	toFixdLengthString(int num, int fixdlenth)
    根据数字生成一个定长的字符串，长度不够前面补0
    static String	toFixdLengthString(long num, int fixdlenth)
    根据数字生成一个定长的字符串，长度不够前面补0
    static String	UpperString(int length)
    返回一个定长的随机纯小写字母字符串(只包含大小写字母)
    static String	ZeroString(int length)
    生成一个定长的纯0字符串
    static String uuid()
    返回一个小写的uuid
    public final static String UUID()
    返回一个大写的uuid
    public static String squid()
    返回一个大写的有序列的uuid

#RegUtil正则相关的工具类
##常量方法
    static int	countSubStrReg(String str, String reg)
    获取符合reg正则表达式的字符串在String中出现的次数
    static boolean	isABC(String src)
    判断是否纯字母组合
    static boolean	isEmail(String email)
    static boolean	isFloatNumeric(String src)
    判断是否浮点数字表示
    static boolean	isInteger(String str)
    static boolean	isMatche(String str, String reg)
    判断字符串str是否符合正则表达式reg
    static boolean	isNumeric(String src)
    判断是否数字表示
    static boolean	isNumericString(String src)
    判断是否数字表示

#SecUtil安全相关的工具类
##常量方法
    static String	FileMD5(File file)
    static String	md5(String password)

#StreamUtil-Stream相关的工具类
##常量方法
    static InputStream	byte2InputStream(byte[] b)
    static byte[]	inputStream2Byte(InputStream inStream)
    static byte[]	stream2Byte(InputStream is)
    void	streamSaveAsFile(InputStream is, File outfile)
    将流另存为文件
    static String	streamToString(InputStream in)
    Read an input stream into a string

#StringUtil字符串相关的工具类
##常量方法
    static int	countSubStr(String string, String str)
    获取字符串str在String中出现的次数
    String	cpDetector(String str)
    获取字符串的编码
    static String	formatFloat(float f, String format)
    格式化一个float
    static String	full2Half(String str)
    全角字符变半角字符
    static String	getHideEmailPrefix(String email)
    隐藏邮件地址前缀。
    static String	getLimitLengthString(String str, int len, String symbol)
    截取字符串　超出的字符用symbol代替
    static int	getStringLen(String SrcStr)
    取得字符串的实际长度（考虑了汉字的情况一个汉字按照俩个字符算）
    static boolean	isEmpty(String s)
    判断是否是空字符串 null和"" 都返回 true
    static boolean	isIn(String substring, String[] source)
    判断字符串数组中是否包含某字符串元素
    static String	joinString(List array, String symbol)
    把string array or list用给定的符号symbol连接成一个字符串
    static String	joinString(String[] array, String symbol)
    把string array or list用给定的符号symbol连接成一个字符串
    static String	left(String input, int count)
    截取字符串左侧指定长度的字符串
    static String	listToStringSlipStr(List list, String slipStr)
    将list 用传入的分隔符组装为String
    static String	ltrim(String str1, int num)
    截取字符串左侧的Num位
    static String	middle(String input, int index, int count)
    从指定位置开始截取指定长度的字符串
    static Map<String,String>	parseQuery(String query, char split1, char split2, String dupLink)
    解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
    static List<String>	parseString2ListByCustomerPattern(String pattern, String src)
    根据指定的字符把源字符串分割成一个数组
    static String	repeat(String src, int num)
    repeat - 通过源字符串重复生成N次组成新的字符串。
    static String	replaceAll(String s, String sf, String sb)
    存文本替换
    static String	replaceBlank(String str)
    页面中去除字符串中的空格、回车、换行符、制表符
    static String	replaceBracketStr(String str)
    全角括号转为半角
    static boolean	requals(String str1, String str2)
    判定第一个字符串是否等于的第二个字符串中的某一个值
    static boolean	requals(String str1, String str2, String split)
    判定第一个字符串是否等于的第二个字符串中的某一个值
    static String	right(String input, int count)
    截取字符串右侧指定长度的字符串
    static String	rtrim(String str1, int num)
    截取字符串右侧的Num位
    static double	SimilarDegree(String str1, String str2)
    字符串相似度比较(速度较快)
    static double	SimilarityRatio(String str1, String str2)
    字符串相似度比较(速度较快)
    String	SimpleEncoding(String str)
    获取字符串的编码
    static String	string2Unicode(String string)
    static String	subStringOmit(String subject, int size)
    字符串省略截取 字符串截取到指定长度size+...的形式
    static String	trimPunct(String str)
    删除所有的标点符号
    static String	unicode2String(String unicode)

#SysUtil系统相关的工具类
##常量字段
    static String	CURRENT_USER
    当前用户
    static String	CURRENT_USER_DIR
    当用用户的工作目录
    static String	CURRENT_USER_HOME
    当前用户的家目录
    static String	FILE_SEPARATOR
    static String	HOST_IP
    主机IP
    static String	HOST_NAME
    主机名
    static String	JVM_ENCODING
    JVM的编码
    static String	JVM_TEMPDIR
    JVM默认的临时目录
    static String	JVM_VERSION
    JVM的版本
    static String	LINE_SEPARATOR
    static String	OS_ARCH
    主机架构
    static String	OS_NAME
    主机类型
    static String	OS_VERSION
    主机类型版本
    static String	PATH_SEPARATOR
    static String	SUN_DESKTOP
    操作系统类型
    static long	TotalMemorySize
    总的物理内存

#静态方法
    static long	JVMfreeMem()
    虚拟机空闲内存量
    static long	JVMmaxMem()
    虚拟机使用最大内存量
    static long	JVMtotalMem()
    获取JVM内存总量
    static long	usedMemory()
    已使用的物理内存

#CheckUtil相关的工具类
##常量方法
    static boolean	isValid(Collection col)
    判断集合的有效性
    static boolean	isValid(Object obj)
    判断一个对象是否为空
    static boolean	isValid(Object[] arr)
    判断数组是否有效
    static boolean	isValid(String src)
    判断字符串有效性

#WebUtilWeb相关的工具类
##常量方法
    static String	escape(String src)
    static String	getParamValue(String url, String paramName)
    static void	    print(HttpServletRequest request)
    static String	removeParam(String url, String... paramNames)
    static String	removeParam(String url, String paramName)
    static String	setParam(String url, String paramName, String paramValue)
    static String	unescape(String src)
    static String	urlJoin(java.net.URL url, String locationHeader)

#ZIPUtil-zip相关的工具类
##常量方法
    static void	deCompress(File file, String dest)
    文档压缩
    static void	unCompress(File source, String path)
    文档解压
    static void	zipFile(File inFile, ZipOutputStream zos, String dir)

#CollectionHelper集合相关的工具类
##常量方法
    static void handler( Collection<T> collection, ObjectHandler<T> handler)
    处理集合中的元素
    static <T, E> void process( Collection<T> collection,  Collection<E> result, ObjectProcess<T, E> process)
    处理集合中的元素
    
#FileHelper
##常量方法
    static void handlerWithLine(File file, String encoding, ObjectHandler<String> handler)
    逐行处理文件内容
    static <E> void  processWithLine(File file,String encoding, Collection<E> result, ObjectProcess<String,E> process)
    逐行处理文件内容

# ASEUtil
##静态方法
    static String encrypt(String secretKey, String Str)
    使用ASE加密字符串（返回纯16进制字符串）
    static String decode(String secretKey, String str)
    使用ASE解密字符串（解密纯16进制字符串）
# Blowfish
## 静态方法
    static byte[] encrypt(String key,String str)
    使用Blowfish加密算法加密字符串
    static String decrypt(String key,byte[] tt)
    使用Blowfish加密算法解密字符串