#Java Utils
---
封装了一些常用Java操作方法,便于重复开发利用.
另外希望身为Java牛牛的你们一起测试和完善，欢迎入群263641914 一起封装和完成常用的Java代码。
节约撸码时间以方便有更多的时间去把妹子～

#开发环境
    Win7x64
    JDK1.7
    IDEA14

#项目结构

    │  README.md
    │  utils.iml
    │
    ├─.idea
    │
    ├─conf
    │      configFTP.properties
    │      ftp.properties
    │
    ├─Junit#测试类
    │  │  Test.properties
    │  │
    │  └─evilp0s
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
        └─evilp0s
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
                    
#APIDOC
#CharsetUtil字符串相关的工具类

##常量字段
    static java.lang.String	GBK             中文超大字符集
    static java.lang.String	ISO_8859_1      ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
    static java.lang.String	US_ASCII        7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
    static java.lang.String	UTF_16          16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
    static java.lang.String	UTF_16BE        16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
    static java.lang.String	UTF_16LE        16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
    static java.lang.String	UTF_8           8 位 UCS 转换格式

##常量方法
    static java.lang.String	changeCharset(java.lang.String str, java.lang.String newCharset)
    字符串编码转换的实现方法
    static java.lang.String	changeCharset(java.lang.String str, java.lang.String oldCharset, java.lang.String newCharset)
    字符串编码转换的实现方法
    static java.lang.String	toASCII(java.lang.String str)
    将字符编码转换成US-ASCII码
    static java.lang.String	toGBK(java.lang.String str)
    将字符编码转换成GBK码
    static java.lang.String	toISO_8859_1(java.lang.String str)
    将字符编码转换成ISO-8859-1码
    static java.lang.String	toUTF_16(java.lang.String str)
    将字符编码转换成UTF-16码
    static java.lang.String	toUTF_16BE(java.lang.String str)
    将字符编码转换成UTF-16BE码
    static java.lang.String	toUTF_16LE(java.lang.String str)
    将字符编码转换成UTF-16LE码
    static java.lang.String	toUTF_8(java.lang.String str)
    将字符编码转换成UTF-8码


#ClassUtil-Class与反射相关的一些工具类

##常量方法
    static java.util.List<java.lang.String>	getClassName(java.lang.String packageName, boolean childPackage)
    static java.lang.String[]	getField(java.lang.String className)
    获取指定类的全部属性字段
    static java.lang.String[]	getMethod(java.lang.String className)
    获取对象的全部方法
    static void	getter(java.lang.Object obj, java.lang.String att)
    调用对象的getter方法
    static java.lang.String	initStr(java.lang.String old)
    java.lang.Class	loadClass(java.lang.String className)
    加载指定的类
    static <T> T	propertiesCopy(java.lang.Object obj, java.lang.Class<T> type)
    同名属性值复制 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static <T> T	propertiesCopyIgnore(java.lang.Object obj, java.lang.Class<T> type)
    同名属性值复制（忽略大小写） 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static <T> T	propertiesCopyIgnoreFilter(java.lang.Object obj, java.lang.Class<T> type, java.lang.String IgnoreStr)
    同名属性值复制（比较时忽略字符IgnoreStr的内容） 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
    static void	setter(java.lang.Object obj, java.lang.String att, java.lang.Object value, java.lang.Class<?> type)
    调用对象的setter方法
#ChinesUtil中文相关的工具类

##常量方法
    static java.lang.String	getFirstSpell(java.lang.String chinese)
    获取汉字串拼音首字母，英文字符不变
    static java.lang.String	getFullSpell(java.lang.String chinese)
    获取汉字串拼音，英文字符不变
    static java.lang.String	getPingYin(java.lang.String inputString)
    将字符串中的中文转化为拼音,其他字符不变
    static boolean	isChinese(java.lang.String strName)
    static boolean	isChineseByName(java.lang.String str)
    static boolean	isChineseByREG(java.lang.String str)
    static boolean  isMessyCode(java.lang.String strName) 
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
    static java.util.Date	calculate(java.util.Date date, int second, java.lang.String timeBurst)
    时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
    static java.util.Date	calculate(java.lang.String date, int second, java.lang.String timeBurst)
    时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
    static java.lang.String	Date()
    获取当前的日期
    static java.lang.String	Date(java.util.Date date)
    将指定的时间格式化成出返回
    static java.util.Date	Date(java.lang.String dateStr)
    将指定的字符串解析为时间类型
    static java.lang.String	DateTime()
    获取当前日期时间
    static java.lang.String	DateTime(java.util.Date date)
    将指定的时间格式化成出返回
    static java.util.Date	DateTime(java.lang.String datestr)
    将指定的字符串解析为时间类型
    static java.util.Date	day(java.util.Date date, int day)
    在指定的时间上加或减去几天
    static java.util.Date	day(int day)
    在当前时间的基础上加或减去几天
    static java.util.Date	hour(java.util.Date date, float hour)
    在制定的时间上加或减去几小时-支持浮点数
    static java.util.Date	hour(float hour)
    在当前时间的基础上加或减去几小时-支持浮点数
    static boolean	isDate(java.lang.String date)
    判断字符串是否为日期字符串
    static java.util.Date	Minute(java.util.Date date, int minute)
    在制定的时间上加或减去几分钟
    static java.util.Date	Minute(int minute)
    在当前时间的基础上加或减去几分钟
    static java.util.Date	month(java.util.Date date, int month)
    在指定的时间上加或减去几月
    static java.util.Date	month(int month)
    在当前时间的基础上加或减去几月
    static int	subDay(java.util.Date startTime, java.util.Date endTime)
    获取俩个时间之前的相隔的天数
    static int	subDay(java.lang.String startTime, java.lang.String endTime)
    获取俩个时间之前的相隔的天数
    static long	subtimeBurst(java.util.Date startDate, java.util.Date endDate, java.lang.String timeBurst)
    返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
    static long	subtimeBurst(java.lang.String startDate, java.lang.String endDate, java.lang.String timeBurst)
    返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
    static long	Subtract(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位秒
    static long	Subtract(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差-单位秒
    static java.lang.String	SubtractDate(java.lang.String date1, java.lang.String date2)
    获取俩个时间的查结果用时秒表示
    static int	SubtractDay(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位天
    static int	SubtractDay(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差-单位天
    static int	SubtractHour(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位小时
    static int	SubtractHour(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差-单位小时
    static int	SubtractMinute(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位分钟
    static int	SubtractMinute(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差 -单位分钟
    static int	SubtractMonth(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位月
    static int	SubtractMonth(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差-单位月
    static java.lang.String	SubtractTime(java.lang.String date1, java.lang.String date2)
    获取俩个时间的查结果用时秒表示
    static int	SubtractYear(java.util.Date date1, java.util.Date date2)
    时间date1和date2的时间差-单位年
    static int	SubtractYear(java.lang.String date1, java.lang.String date2)
    时间date1和date2的时间差-单位年
    static java.lang.String	Time()
    获取当前的时间
    static java.lang.String	Time(java.util.Date date)
    讲指定的时间格式化成出返回
    static java.util.Date	Time(java.lang.String dateStr)
    将指定的字符串解析为时间类型
    static java.util.Date	year(java.util.Date date, int year)
    在指定的时间上加或减去几年
    static java.util.Date	year(int year)
    在当前时间的基础上加或减去year年
#EmailUtilEmail相关的工具类

##常量方法
    void	doSendHtmlEmail(java.lang.String subject, java.lang.String sendHtml, java.lang.String receiveUser)
    发送邮件
    void	doSendHtmlEmail(java.lang.String subject, java.lang.String sendHtml, java.lang.String receiveUser, java.io.File attachment)
    发送邮件

#FilePathUtil文件路径相关的工具类

##常量方法
    static java.lang.String	commandPath(java.lang.String file)
    返回一个通用的文件路径
    static java.lang.String	getParentPath(java.lang.String file)
    返回相一个目录的对于本身的相对父目录

#FileUtil文件相关的工具类
##常量方法
    static boolean	appendLine(java.io.File file, java.lang.String str)
    在文件末尾追加一行
    static boolean	appendLine(java.io.File file, java.lang.String str, java.lang.String encoding)
    在文件末尾追加一行
    static boolean	cleanFile(java.io.File file)
    快速清空一个超大的文件
    static boolean	copy(java.io.File file, java.lang.String targetFile)
    复制文件 通过该方式复制文件文件越大速度越是明显
    static boolean	copy(java.lang.String resourcePath, java.lang.String targetPath)
    复制文件
    static void	copyDir(java.io.File filePath, java.lang.String targetPath)
    复制目录
    static void	copyDir(java.lang.String filePath, java.lang.String targetPath)
    复制目录
    static int	countLines(java.io.File file)
    获取文件的行数
    static java.lang.String	cpdetector(java.io.File file)
    获取文件的编码(cpDetector)探测
    static boolean	createFiles(java.lang.String filePaht)
    创建文件支持多级目录
    static void	createPaths(java.lang.String paths)
    创建多级目录
    static boolean	deleteBigFile(java.io.File file)
    快速的删除超大的文件
    static boolean	deleteDir(java.io.File file)
    删除一个目录
    static boolean	deleteFile(java.io.File file)
    删除一个文件
    static java.lang.String	FileType(java.io.File file)
    获取文件的类型
    static java.lang.String	hash(java.io.File file)
    获取文件的Hash
    static java.util.List<java.lang.String>	Lines(java.io.File file)
    以列表的方式获取文件的所有行
    static java.util.List<java.lang.String>	Lines(java.io.File file, int lines)
    以列表的方式获取文件的指定的行数数据
    static java.util.List<java.lang.String>	Lines(java.io.File file, int lines, java.lang.String encoding)
    以列表的方式获取文件的指定的行数数据
    static java.util.List<java.lang.String>	Lines(java.io.File file, java.lang.String encoding)
    以列表的方式获取文件的所有行
    static java.util.List<java.io.File>	listFile(java.io.File path)
    罗列指定路径下的全部文件
    static java.util.List<java.io.File>	listFile(java.lang.String path)
    罗列指定路径下的全部文件
    static java.util.List<java.io.File>	listFileAll(java.io.File path)
    罗列指定路径下的全部文件包括文件夹
    static java.util.List<java.io.File>	listFileFilter(java.io.File dirPath, java.lang.String postfixs)
    获取指定目录下的特点文件,通过后缀名过滤
    static java.lang.String	MimeType(java.lang.String file)
    获取文件的Mime类型
    static java.util.Date	modifyTime(java.io.File file)
    获取文件最后的修改时间
    static java.util.List<java.io.File>	searchFile(java.io.File dirPath, java.lang.String fileName)
    在指定的目录下搜寻文个文件
    static java.util.List<java.io.File>	searchFileReg(java.io.File dirPath, java.lang.String reg)
    查找符合正则表达式reg的的文件
    static java.lang.String	SimpleEncoding(java.lang.String file)
    利用简单的文件头字节特征探测文件编码
#PrintUtil打印相关的工具类
##常量方法
    static void	print(java.util.Enumeration enums)
    遍历打印Enumeration
    static void	print(java.util.List<?> list)
    遍历打印遍历List集合
    static void	print(java.util.Map map)
    遍历打印Map集合
    static void	print(java.lang.String str)
    static void	println(java.lang.Object obj)
    static void	println(java.lang.String str)

#ProUtil属性文件相关的工具类
##常量方法
    static java.lang.String	GetAllProperties(java.lang.String filePath)
    读取Properties的全部信息
    static java.lang.String	GetValueByKey(java.lang.String filePath, java.lang.String key)
    根据Key读取Value
    static java.lang.String	key(java.lang.String key)
    static void	WriteProperties(java.lang.String filePath, java.lang.String pKey, java.lang.String pValue)
    写入Properties信息
#RandomUtil随机操作的相关的工具类
##常量方法
    static int	getNotSimple(int[] param, int len)
    每次生成的len位数都不相同
    static java.lang.String	LowerString(int length)
    返回一个定长的随机纯大写字母字符串(只包含大小写字母)
    static java.lang.String	MixString(int length)
    返回一个定长的随机纯字母字符串(只包含大小写字母)
    static java.lang.String	number(int length)
    static java.lang.String	String(int length)
    返回一个定长的随机字符串(只包含大小写字母、数字)
    static java.lang.String	toFixdLengthString(int num, int fixdlenth)
    根据数字生成一个定长的字符串，长度不够前面补0
    static java.lang.String	toFixdLengthString(long num, int fixdlenth)
    根据数字生成一个定长的字符串，长度不够前面补0
    static java.lang.String	UpperString(int length)
    返回一个定长的随机纯小写字母字符串(只包含大小写字母)
    static java.lang.String	ZeroString(int length)
    生成一个定长的纯0字符串
#RegUtil正则相关的工具类
##常量方法
    static int	countSubStrReg(java.lang.String str, java.lang.String reg)
    获取符合reg正则表达式的字符串在String中出现的次数
    static boolean	isABC(java.lang.String src)
    判断是否纯字母组合
    static boolean	isEmail(java.lang.String email) 
    static boolean	isFloatNumeric(java.lang.String src)
    判断是否浮点数字表示
    static boolean	isInteger(java.lang.String str) 
    static boolean	isMatche(java.lang.String str, java.lang.String reg)
    判断字符串str是否符合正则表达式reg
    static boolean	isNumeric(java.lang.String src)
    判断是否数字表示
    static boolean	isNumericString(java.lang.String src)
    判断是否数字表示
#SecUtil安全相关的工具类
##常量方法
    static java.lang.String	FileMD5(java.io.File file)
    static java.lang.String	md5(java.lang.String password)
#StreamUtil-Stream相关的工具类
##常量方法
    static java.io.InputStream	byte2InputStream(byte[] b)
    static byte[]	inputStream2Byte(java.io.InputStream inStream)
    static byte[]	stream2Byte(java.io.InputStream is)
    void	streamSaveAsFile(java.io.InputStream is, java.io.File outfile)
    将流另存为文件
    static java.lang.String	streamToString(java.io.InputStream in)
    Read an input stream into a string
#StringUtil字符串相关的工具类
##常量方法
    static int	countSubStr(java.lang.String string, java.lang.String str)
    获取字符串str在String中出现的次数
    java.lang.String	cpDetector(java.lang.String str)
    获取字符串的编码
    static java.lang.String	formatFloat(float f, java.lang.String format)
    格式化一个float
    static java.lang.String	full2Half(java.lang.String str)
    全角字符变半角字符
    static java.lang.String	getHideEmailPrefix(java.lang.String email)
    隐藏邮件地址前缀。
    static java.lang.String	getLimitLengthString(java.lang.String str, int len, java.lang.String symbol)
    截取字符串　超出的字符用symbol代替
    static int	getStringLen(java.lang.String SrcStr)
    取得字符串的实际长度（考虑了汉字的情况一个汉字按照俩个字符算）
    static boolean	isEmpty(java.lang.String s)
    判断是否是空字符串 null和"" 都返回 true
    static boolean	isIn(java.lang.String substring, java.lang.String[] source)
    判断字符串数组中是否包含某字符串元素
    static java.lang.String	joinString(java.util.List array, java.lang.String symbol)
    把string array or list用给定的符号symbol连接成一个字符串
    static java.lang.String	joinString(java.lang.String[] array, java.lang.String symbol)
    把string array or list用给定的符号symbol连接成一个字符串
    static java.lang.String	left(java.lang.String input, int count)
    截取字符串左侧指定长度的字符串
    static java.lang.String	listToStringSlipStr(java.util.List list, java.lang.String slipStr)
    将list 用传入的分隔符组装为String
    static java.lang.String	ltrim(java.lang.String str1, int num)
    截取字符串左侧的Num位
    static java.lang.String	middle(java.lang.String input, int index, int count)
    从指定位置开始截取指定长度的字符串
    static java.util.Map<java.lang.String,java.lang.String>	parseQuery(java.lang.String query, char split1, char split2, java.lang.String dupLink)
    解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
    static java.util.List<java.lang.String>	parseString2ListByCustomerPattern(java.lang.String pattern, java.lang.String src)
    根据指定的字符把源字符串分割成一个数组
    static java.lang.String	repeat(java.lang.String src, int num)
    repeat - 通过源字符串重复生成N次组成新的字符串。
    static java.lang.String	replaceAll(java.lang.String s, java.lang.String sf, java.lang.String sb)
    存文本替换
    static java.lang.String	replaceBlank(java.lang.String str)
    页面中去除字符串中的空格、回车、换行符、制表符
    static java.lang.String	replaceBracketStr(java.lang.String str)
    全角括号转为半角
    static boolean	requals(java.lang.String str1, java.lang.String str2)
    判定第一个字符串是否等于的第二个字符串中的某一个值
    static boolean	requals(java.lang.String str1, java.lang.String str2, java.lang.String split)
    判定第一个字符串是否等于的第二个字符串中的某一个值
    static java.lang.String	right(java.lang.String input, int count)
    截取字符串右侧指定长度的字符串
    static java.lang.String	rtrim(java.lang.String str1, int num)
    截取字符串右侧的Num位
    static double	SimilarDegree(java.lang.String str1, java.lang.String str2)
    字符串相似度比较(速度较快)
    static double	SimilarityRatio(java.lang.String str1, java.lang.String str2)
    字符串相似度比较(速度较快)
    java.lang.String	SimpleEncoding(java.lang.String str)
    获取字符串的编码
    static java.lang.String	string2Unicode(java.lang.String string) 
    static java.lang.String	subStringOmit(java.lang.String subject, int size)
    字符串省略截取 字符串截取到指定长度size+...的形式
    static java.lang.String	trimPunct(java.lang.String str)
    删除所有的标点符号
    static java.lang.String	unicode2String(java.lang.String unicode) 
#SysUtil系统相关的工具类
##常量字段
    static java.lang.String	STR_HOSTNAME
    static java.lang.String	STR_IP
#ValidUtil字符串相关的工具类
##常量方法
    static boolean	isValid(java.util.Collection col)
    判断集合的有效性
    static boolean	isValid(java.lang.Object obj)
    判断一个对象是否为空
    static boolean	isValid(java.lang.Object[] arr)
    判断数组是否有效
    static boolean	isValid(java.lang.String src)
    判断字符串有效性
#WebUtilWeb相关的工具类
##常量方法
    static java.lang.String	escape(java.lang.String src)
    static java.lang.String	getParamValue(java.lang.String url, java.lang.String paramName)
    static void	main(java.lang.String[] args)
    static void	print(javax.servlet.http.HttpServletRequest request)
    static java.lang.String	removeParam(java.lang.String url, java.lang.String... paramNames)
    static java.lang.String	removeParam(java.lang.String url, java.lang.String paramName)
    static java.lang.String	setParam(java.lang.String url, java.lang.String paramName, java.lang.String paramValue)
    static java.lang.String	unescape(java.lang.String src)
    static java.lang.String	urlJoin(java.net.URL url, java.lang.String locationHeader)
#ZIPUtil-zip相关的工具类
##常量方法
    static void	deCompress(java.io.File file, java.lang.String dest)
    文档压缩
    static void	unCompress(java.io.File source, java.lang.String path)
    文档解压
    static void	zipFile(java.io.File inFile, java.util.zip.ZipOutputStream zos, java.lang.String dir)