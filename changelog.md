#DateUtil

    
    evilp0s.DateUtil.subtimeBurst(java.util.Date, java.util.Date, java.lang.String)
    关于上述方法当开始时间大于结束时间的时候能返回错误的值，故进行修改为当开始大于结束时间时返回负数的正确值
    
    evilp0s.DateUtil.calculate(java.util.Date, int, java.lang.String)
    原计算方式错误，导致结果错误。


#RegUtil
    evilp0s.RegUtil.countSubStrReg
    原函数有BUG导致统计数据的错误