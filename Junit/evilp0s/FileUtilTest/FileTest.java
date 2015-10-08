package evilp0s.FileUtilTest;


import evilp0s.DateUtil;
import evilp0s.FileUtil;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class FileTest {
    @Test
    public void testListLine() throws ParseException {
        File file = new File(System.getProperty("user.dir") + "/Junit/Resource/time.txt");
        List<String> lines = FileUtil.lines(file);
        int count=0;

        for(String line :lines){
            String[] tt = line.split("/");
            count += DateUtil.subtimeBurst(tt[0], tt[1], "08:00-19:30");
        }
        System.out.println("sum > "+count/60/60);
        System.out.println("count > "+lines.size());

    }

}
