package demo.time;

import com.opslab.DateUtil;
import com.opslab.FileUtil;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.List;

/**
 * 统计一些数据中的工作市场
 */
public class TestWorkTime {

    @Test
    public void countWorkTime() throws ParseException {
        File file = new File(System.getProperty("user.dir")+"/Junit/Resource/time.txt");
        List<String> lines = FileUtil.lines(file);

        int counts = 0;
        for(String line :lines){
            String[] tt = line.split("/");
            counts += DateUtil.subtimeBurst(tt[0],tt[1],"08:00-19:30");
        }
        System.out.println("sum->"+counts /60 /60);
        System.out.println("count->"+lines.size());
        System.out.println("svg->"+counts /60 /60/lines.size());
    }
}
