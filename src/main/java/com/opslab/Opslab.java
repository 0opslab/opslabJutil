package com.opslab;

/**
 * 一些常用的常量
 */
public final class Opslab {

    /**
     * 日期时间类型格式
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期类型格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时间类型的格式
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    public static final String US_ASCII = "US-ASCII";

    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * 8 位 UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
     */
    public static final String UTF_16BE = "UTF-16BE";

    /**
     * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
     */
    public static final String UTF_16LE = "UTF-16LE";

    /**
     * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
     */
    public static final String UTF_16 = "UTF-16";

    /**
     * 中文超大字符集
     */
    public static final String GBK = "GBK";

    /**
     * 最常见的中文字符集
     */
    public static final String GB2312 = "GB2312";

    /**
     * 空字符串
     */
    public static final String STR_EMPTY = "";


    /**
     * 未登陆
     */
    public final static String SYS_NOLOGIN = "Nologin";


    /**
     * 成功
     */
    public final static String SYS_SUCCESS = "Success";


    /**
     * 失败
     */
    public final static String SYS_ERROR = "Error";


    /**
     * 异常
     */
    public final static String SYS_EXCEPTION = "Exception";


    /**
     * 无记录
     */
    public final static String SYS_NORECORD = "NoRecord";

    /**
     * 业务访问
     */
    public final static String BUSINESS_ACCESS = "ACCESS";


    /**
     * 业务插入
     */
    public final static String BUSINESS_INSERT = "INSERT";


    /**
     * 业务更新
     */
    public final static String BUSINESS_UPDATE = "UPDATE";


    /**
     * 业务删除
     */
    public final static String BUSINESS_DELETE = "DELETE";


    /**
     * 业务文件上传
     */
    public final static String BUSINESS_UPLOAD = "UPLOAD";


    /**
     * 常用的符号
     */
    public final static String[] SYMBOL_FH = new String[]{"░", "▒", "▣", "▤", "▥", "▦", "▧", "▨", "▩",
            "▪", "▫", "▬", "◆", "◇", "◈", "◎", "●", "◐", "◑", "☉", "☎", "☏", "☜", "☞", "☺", "☻", "☼",
            "♠", "♡", "♢", "♣", "♤", "♥", "♦", "♧", "♨", "♩", "♪", "♫", "♬", "♭", ".", "。", "，", "、", ";", "：", "？", "!",
            "ˉ", "ˇ", "¨", "`", "~", "々", "～", "‖", "∶", "'", "`", "|", "·", "…", "—", "～", "-",
            "〃", "‘", "’", "“", "”", "〝", "〞", "〔", "〕", "〈", "〉", "《", "》", "「", "」", "『", "』", "〖", "〗", "【", "】", "(", ")", "[",
            "]", "{", "｝", "︻", "︼", "﹄", "﹃",};

    /**
     * 常用的数学符号
     */
    public final static String[] SYMBOL_MATH = new String[]{"+", "-", "×", "÷", "﹢", "﹣", "±", "/", "=", "∥", "∠", "≌", "∽",
            "≦", "≧", "≒", "﹤", "﹥", "≈", "≡", "≠", "=", "≤", "≥", "<", ">", "≮", "≯", "∷", "∶", "∫", "∮",
            "∝", "∞", "∧", "∨", "∑", "∏", "∪", "∩", "∈", "∵", "∴", "⊥", "∥", "∠", "⌒", "⊙", "√", "∟", "⊿", "㏒", "㏑", "%", "‰"};


    /**
     * 计量符号
     */
    public final static String[] SYMBOL_UNIT = new String[]{"㎎", "㎏", "㎜", "㎝", "㎞", "㎡", "㏄", "㏎", "㏑",
            "㏒", "㏕", "℡", "%", "‰", "℃", "℉", "°", "′", "″", "$", "￡", "￥", "￠", "♂", "♀", "℅"};

    /**
     * 常用的数学符号
     */
    public final static String SYMBOL_NUMBER = "①②③④⑤⑥⑦⑧⑨⑩㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇" +
            "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ";


    /**
     * 一些常用的无意义的符号(只相对于中文)
     */
    public final static char[] SYMBOL_UNMEANING = ("·ˉˇ¨〃々—～‖…「」『』〖〗【】±+-×÷∧∨∑∏∪∩∈√⊥∥∠⌒⊙∫∮≡≌≈∽∝≠≮≯≤≥∞∶ ∵∴∷♂♀°′" +
            "″℃$¤￠￡‰§№☆★〇○●◎◇◆ 回□■△▽⊿▲▼◣◤◢◥▁▂▃▄▅▆▇█▉▊▋▌▍▎▏▓※→←↑↓↖↗↘↙〓!￥^`{|｝ぁあぃいぅうぇえぉおかがきぎ" +
            "くぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ" +
            "ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセ" +
            "ゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵ" +
            "ヶΓΔΛΞΟΠΡΣΤΥΦΧΨΩαβγδεζηθ ικλμνξοπρστυφχψ ω︵︶︹︺︿﹀︽︾﹁﹂﹃﹄︻︼︷︸АБВГДЕЁЖЗИЙКЛМНОПРСТУ" +
            "ФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыь эюāáǎàēéěèī íǐìōóǒòūúǔùǖǘǚǜüêɑńňɡㄅㄆㄇㄈㄉㄊㄋㄌ" +
            "ㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩ︱︳︴﹏﹋﹌─━│┃┄┅┆┇┈┉┊┋┌┍┎┏┐┑┒┓└" +
            "┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄ ╅╆╇╈╉╊╋㊣㈱曱甴" +
            "∟┅﹍╭╮╰╯^︵^`√卐℡ぁ〝〞ミ灬№*ㄨ≮≯∝≌∽≦≧≒じぷ┗┛￥￡§я-―‥…‰′″℅℉№℡∕∝∣═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╱╲╳▔▕〆〒〡〢〣〤〥〦〧〨〩︰﹍﹎------").toCharArray();


}
