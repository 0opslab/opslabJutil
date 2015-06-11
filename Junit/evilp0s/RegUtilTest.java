package evilp0s;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-05-29.
 */
public class RegUtilTest extends TestCase {

    @Test
    public void testIsMatche(){
        System.out.println(RegUtil.isMatche("1234","\\d{4}"));
        System.out.println(RegUtil.isMatche("123as","\\d{4}"));
    }

}