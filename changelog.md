#DateUtil

    
    DateUtil.subtimeBurst(java.util.Date, java.util.Date, java.lang.String)
    关于上述方法当开始时间大于结束时间的时候能返回错误的值，故进行修改为当开始大于结束时间时返回负数的正确值
    
    DateUtil.calculate(java.util.Date, int, java.lang.String)
    原计算方式错误，导致结果错误。


#RegUtil
    RegUtil.countSubStrReg
    原函数有BUG导致统计数据的错误
    
    
#brach
    release1
        主要目的写着玩的因此结构比较混乱
        
    release2
        有几个朋友已经在项目中使用 但是因为之前的包名不够规范 所以在这个版本中进行了调整
        
    