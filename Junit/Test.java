import java.nio.charset.Charset;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-06-17.
 */
public class Test {

    public static void main(String[] args){
        //Win7默认会都会输出GBK(不过会应为应用程序的编码会发生相应的变化)
        System.out.println("File encoding:"+System.getProperty("file.encoding"));
        System.out.println("Default Charset:"+ Charset.defaultCharset());
    }
}
