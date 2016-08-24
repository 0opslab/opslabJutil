package com.opslab.util;

/**
 * 一些常用的常量
 */
public final class constant {
    String AMPERSAND         = "&";
    String AND               = "and";
    String AT                = "@";
    String ASTERISK          = "*";
    String STAR              = ASTERISK;
    String BACK_SLASH        = "\\";
    String COLON             = ":";
    String COMMA             = ",";
    String DASH              = "-";
    String DOLLAR            = "$";
    String DOT               = ".";
    String DOTDOT            = "..";
    String DOT_CLASS         = ".class";
    String DOT_JAVA          = ".java";
    String EMPTY             = "";
    String EQUALS            = "=";
    String FALSE             = "false";
    String SLASH             = "/";
    String HASH              = "#";
    String HAT               = "^";
    String LEFT_BRACE        = "{";
    String LEFT_BRACKET      = "(";
    String LEFT_CHEV         = "<";
    String NEWLINE           = "\n";
    String N                 = "n";
    String NO                = "no";
    String NULL              = "null";
    String OFF               = "off";
    String ON                = "on";
    String PERCENT           = "%";
    String PIPE              = "|";
    String PLUS              = "+";
    String QUESTION_MARK     = "?";
    String EXCLAMATION_MARK  = "!";
    String QUOTE             = "\"";
    String RETURN            = "\r";
    String TAB               = "\t";
    String RIGHT_BRACE       = "}";
    String RIGHT_BRACKET     = ")";
    String RIGHT_CHEV        = ">";
    String SEMICOLON         = ";";
    String SINGLE_QUOTE      = "'";
    String BACKTICK          = "`";
    String SPACE             = " ";
    String LEFT_SQ_BRACKET   = "[";
    String RIGHT_SQ_BRACKET  = "]";
    String TRUE              = "true";
    String UNDERSCORE        = "_";
    String UTF_8             = "UTF-8";
    String US_ASCII          = "US-ASCII";
    String ISO_8859_1        = "ISO-8859-1";
    String Y                 = "y";
    String YES               = "yes";
    String ONE               = "1";
    String ZERO              = "0";
    String DOLLAR_LEFT_BRACE = "${";
    String CRLF              = "\r\n";

    String HTML_NBSP  = "&nbsp;";
    String HTML_AMP   = "&amp";
    String HTML_QUOTE = "&quot;";
    String HTML_LT    = "&lt;";
    String HTML_GT    = "&gt;";

    String HXWFH  = "░▒▣▤▥▦▧▨▩▪▫▬◆◇◈◎●◐◑☉☎☏☜☞☺☻☼♠♡♢♣♤♥♦♧♨♩♪♫♬♭♯";
    String HXWFHS = ".。，、;：？!ˉˇ¨`~ 々～‖∶'`|·… — ～ - 〃‘’“”〝〞〔〕〈〉《》「」『』〖〗【】()[]{｝︻︼﹄﹃";

    //常用的数学符号
    String MATH_SYMBOL = "+-×÷﹢﹣±/=∥∠≌∽≦≧≒﹤﹥≈≡≠=≤≥<>≮≯∷∶∫∮∝∞∧∨∑∏∪∩∈∵∴⊥∥∠⌒⊙√∟⊿㏒㏑%‰";

    //计量符号
    String UNIT_SYMBOL = "㎎㎏㎜㎝㎞㎡㏄㏎㏑㏒㏕℡%‰℃℉°′″$￡￥￠♂♀℅";

    //常用的数学符号
    String NUMBER_SYMBOL = "①②③④⑤⑥⑦⑧⑨⑩㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩№" + "⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇" +
            "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ";

    //希腊符号
    String GREEK_SYMBOL = "ΓΔΛΞΠΣΦΨΩαβγδεζνξοπρσηθικλμτυφχψω";

    //俄语
    String RUSSIAN_SYMBOL = "БВГДЁЖИЙКЛПФЦЧШЩЪЫЬЭЮЯ";

    //中文符号
    String ZHCN_SYMBOL = "卍卐卄巜氺兀々〆のぁ〤〥";

    //日语
    String JAPANESE_SYMBOL = "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ" +
            "ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾ" +
            "タダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ";

    //常用的符号
    String SYMBOL_ALL = "、。·ˉˇ¨〃々—～‖…‘’“”〔〕〈 〉《》「」『』〖〗【】±+-×÷∧∨∑∏∪∩∈√⊥∥∠⌒⊙∫∮≡≌≈∽∝≠≮≯≤≥∞∶ ∵∴∷♂♀°′" +
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
}
