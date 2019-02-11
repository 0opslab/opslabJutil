package com.opslab.util.ftp;

import com.opslab.Opslab;

import java.util.HashMap;
import java.util.Map;

/**
 * FTP相关的一些配置
 */
public class FTPConstant {
    /**
     * FTP状态码及其描述
     */
    public static Map<Integer, String> REPLYCODE = new HashMap<Integer, String>();
    //用于编码转换
    private static String ISO_ECODING = "ISO-8859-1";
    //程序运行的编码
    private static String PROJECT_ENCODING = Opslab.UTF_8;

    static {
        REPLYCODE.put(120, "服务已就绪，在 nnn 分钟后开始。");
        REPLYCODE.put(125, "数据连接已打开，正在开始传输。");
        REPLYCODE.put(150, "文件状态正常，准备打开数据连接。");
        REPLYCODE.put(202, "未执行命令，站点上的命令过多。");
        REPLYCODE.put(211, "系统状态，或系统帮助答复。");
        REPLYCODE.put(212, "目录状态。");
        REPLYCODE.put(213, "文件状态。");
        REPLYCODE.put(214, "帮助消息。");
        REPLYCODE.put(215, "NAME 系统类型，其中，NAME 是 Assigned Numbers 文档中所列的正式系统名称。");
        REPLYCODE.put(220, "服务就绪，可以执行新用户的请求。");
        REPLYCODE.put(221, "服务关闭控制连接。如果适当，请注销。");
        REPLYCODE.put(225, "数据连接打开，没有进行中的传输。");
        REPLYCODE.put(226, "关闭数据连接。请求的文件操作已成功（例如，传输文件或放弃文件）。");
        REPLYCODE.put(227, "进入被动模式 (h1,h2,h3,h4,p1,p2)。");
        REPLYCODE.put(230, "用户已登录，继续进行。");
        REPLYCODE.put(250, "请求的文件操作正确，已完成。");
        REPLYCODE.put(257, "已创建“PATHNAME”。");
        REPLYCODE.put(332, "需要登录帐户。");
        REPLYCODE.put(350, "请求的文件操作正在等待进一步的信息。");
        REPLYCODE.put(425, "无法打开数据连接。");
        REPLYCODE.put(426, "Connection closed; transfer aborted.");
        REPLYCODE.put(450, "未执行请求的文件操作。文件不可用（例如，文件繁忙）。");
        REPLYCODE.put(451, "请求的操作异常终止：正在处理本地错误。");
        REPLYCODE.put(452, "未执行请求的操作。系统存储空间不够。");
        REPLYCODE.put(501, "在参数中有语法错误。");
        REPLYCODE.put(502, "未执行命令。");
        REPLYCODE.put(503, "错误的命令序列。");
        REPLYCODE.put(504, "未执行该参数的命令。");
        REPLYCODE.put(530, "未登录。");
        REPLYCODE.put(532, "存储文件需要帐户。");
        REPLYCODE.put(550, "未执行请求的操作。文件不可用（例如，未找到文件，没有访问权限）。");
        REPLYCODE.put(551, "请求的操作异常终止：未知的页面类型。");
        REPLYCODE.put(552, "请求的文件操作异常终止：超出存储分配（对于当前目录或数据集）。");
        REPLYCODE.put(553, "未执行请求的操作。不允许的文件名。");
    }

}
