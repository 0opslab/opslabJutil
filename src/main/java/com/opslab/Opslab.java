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


    public static final String AMPERSAND = "&";
    public static final String AND = "and";
    public static final String AT = "@";
    public static final String ASTERISK = "*";
    public static final String STAR = ASTERISK;
    public static final String BACK_SLASH = "\\";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String DASH = "-";
    public static final String DOLLAR = "$";
    public static final String DOT = ".";
    public static final String DOTDOT = "..";
    public static final String DOT_CLASS = ".class";
    public static final String DOT_JAVA = ".java";
    public static final String EMPTY = "";
    public static final String EQUALS = "=";
    public static final String FALSE = "false";
    public static final String SLASH = "/";
    public static final String HASH = "#";
    public static final String HAT = "^";
    public static final String LEFT_BRACE = "{";
    public static final String LEFT_BRACKET = "(";
    public static final String LEFT_CHEV = "<";
    public static final String NEWLINE = "\n";
    public static final String N = "n";
    public static final String NO = "no";
    public static final String NULL = "null";
    public static final String OFF = "off";
    public static final String ON = "on";
    public static final String PERCENT = "%";
    public static final String PIPE = "|";
    public static final String PLUS = "+";
    public static final String QUESTION_MARK = "?";
    public static final String EXCLAMATION_MARK = "!";
    public static final String QUOTE = "\"";
    public static final String RETURN = "\r";
    public static final String TAB = "\t";
    public static final String RIGHT_BRACE = "}";
    public static final String RIGHT_BRACKET = ")";
    public static final String RIGHT_CHEV = ">";
    public static final String SEMICOLON = ";";
    public static final String SINGLE_QUOTE = "'";
    public static final String BACKTICK = "`";
    public static final String SPACE = " ";
    public static final String LEFT_SQ_BRACKET = "[";
    public static final String RIGHT_SQ_BRACKET = "]";
    public static final String TRUE = "true";
    public static final String UNDERSCORE = "_";
    public static final String Y = "y";
    public static final String YES = "yes";
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String DOLLAR_LEFT_BRACE = "${";
    public static final String CRLF = "\r\n";

    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";

    public static final String HXWFH = "░▒▣▤▥▦▧▨▩▪▫▬◆◇◈◎●◐◑☉☎☏☜☞☺☻☼♠♡♢♣♤♥♦♧♨♩♪♫♬♭♯";
    //常用的的标点符号
    public static final String HXWFHS = ".。，、;：？!ˉˇ¨`~ 々～‖∶'`|·… — ～ - 〃‘’“”〝〞〔〕〈〉《》「」『』〖〗【】()[]{｝︻︼﹄﹃";

    //常用的数学符号
    public static final String MATH_SYMBOL = "+-×÷﹢﹣±/=∥∠≌∽≦≧≒﹤﹥≈≡≠=≤≥<>≮≯∷∶∫∮∝∞∧∨∑∏∪∩∈∵∴⊥∥∠⌒⊙√∟⊿㏒㏑%‰";

    //计量符号
    public static final String UNIT_SYMBOL = "㎎㎏㎜㎝㎞㎡㏄㏎㏑㏒㏕℡%‰℃℉°′″$￡￥￠♂♀℅";

    //常用的数学符号
    public static final String NUMBER_SYMBOL = "①②③④⑤⑥⑦⑧⑨⑩㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩№" + "⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇" +
            "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ";

    //希腊符号
    public static final String GREEK_SYMBOL = "ΓΔΛΞΠΣΦΨΩαβγδεζνξοπρσηθικλμτυφχψω";

    //俄语
    public static final String RUSSIAN_SYMBOL = "БВГДЁЖИЙКЛПФЦЧШЩЪЫЬЭЮЯ";

    //中文符号
    public static final String ZHCN_SYMBOL = "卍卐卄巜氺兀々〆のぁ〤〥";

    //日语
    public static final String JAPANESE_SYMBOL = "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ" +
            "ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾ" +
            "タダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ";

    //常用的符号
    public static final String SYMBOL_ALL = "、。·ˉˇ¨〃々—～‖…‘’“”〔〕〈 〉《》「」『』〖〗【】±+-×÷∧∨∑∏∪∩∈√⊥∥∠⌒⊙∫∮≡≌≈∽∝≠≮≯≤≥∞∶ ∵∴∷♂♀°′" +
            "″℃$¤￠￡‰§№☆★〇○●◎◇◆ 回□■△▽⊿▲▼◣◤◢◥▁▂▃▄▅▆▇█▉▊▋▌▍▎▏▓※→←↑↓↖↗↘↙〓 ⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ①②③④⑤⑥⑦⑧⑨⑩" +
            "⒈⒉⒊⒋ ⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩ⅠⅡⅢⅣⅤⅥ" +
            "ⅦⅧⅨⅩⅪⅫ!\"#￥%&'()*+，-./0123456789：;<=>？@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrs" +
            "tuvwxyz{|｝ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ" +
            "ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセ" +
            "ゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵ" +
            "ヶΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩαβγδεζηθ ικλμνξοπρστυφχψ ω︵︶︹︺︿﹀︽︾﹁﹂﹃﹄︻︼︷︸АБВГДЕЁЖЗИЙКЛМНОПРСТУ" +
            "ФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыь эюāáǎàēéěèī íǐìōóǒòūúǔùǖǘǚǜüêɑ\uE7C7ńň\uE7C8ɡㄅㄆㄇㄈㄉㄊㄋㄌ" +
            "ㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩ︱\uE796︳︴﹏﹋﹌─━│┃┄┅┆ ┇┈┉┊┋┌┍┎┏┐┑┒┓└" +
            "┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄ ╅╆╇╈╉╊╋？㊣㈱曱甴" +
            "囍∟┅﹊﹍╭ ╮╰ ╯ \uE83A_\uE83A ^︵^﹕﹗/\\ \" < > `,·。{}~～() -√ $ @ * & # 卐℡ ぁ〝〞ミ灬№*\uE7E7\uE7F3ㄨ" +
            "≮≯ ﹢﹣/∝≌∽≦≧≒﹤﹥じぷ┗┛￥￡§я-―‥…‰′″℅℉№℡∕∝∣═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╱ ╲╳▔▕" +
            "〆〒〡〢〣〤〥〦〧〨〩㎎ ㎏ ㎜ ㎝ ㎞ ㎡ ㏄ ㏎㏑㏒㏕\uE7C7\uE7C8\uE7E7\uE7E8\uE7E9\uE7EA\uE7EB\uE7EC\uE7ED\uE7EE" +
            "\uE7EF\uE7F0\uE7F1\uE7F2\uE7F3兀︰﹍﹎ ------";


    //必选包含数字、大写字母、小写字母、特殊字符，长度在8到15位
    public static final String SEC_PASSWORD = "^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[@!#$%^&*()_+\\.\\-\\?<>'\"|=]+).{8,15}$";
}
