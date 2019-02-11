package com.opslab.util.encrypt;

import junit.framework.TestCase;
import org.junit.Test;

public class Base64Test extends TestCase {
    String text = "There can be miracles \n when you believe";

    String base64 = "VGhlcmUgY2FuIGJlIG1pcmFjbGVzIAogd2hlbiB5b3UgYmVsaWV2ZQ==";

    @Test
    public void testBase64() {
//        System.out.println(Base64.encodeToString(text));
//        System.out.println(Base64.decodeToString(Base64.encodeToString(text)));
        assertEquals(base64, Base64.encodeToString(text));
        assertEquals("VA==", Base64.encodeToString("T"));
        assertEquals("T", Base64.decodeToString("VA=="));
        assertEquals("T", Base64.decodeToString("VA="));
        assertEquals("T", Base64.decodeToString("VA"));
    }

    @Test
    public void testBase642() {
        String utf8string = "物有本末 事有始终";
        String base64 = "54mp5pyJ5pys5pyrIOS6i+acieWni+e7iA==";

        String encoded = Base64.encodeToString(utf8string);
        String decoded = Base64.decodeToString(encoded);
        assertEquals(base64, encoded);
        assertEquals(utf8string, decoded);

    }


}