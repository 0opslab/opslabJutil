package JDK.JVM;

import com.opslab.StringUtil;
import com.opslab.SysUtil;

import javax.sound.midi.Instrument;
import java.lang.management.ManagementFactory;
import java.util.List;

public class JvmParamerters {
    public static void main(String[] args){
        List<String> paramters = ManagementFactory.getRuntimeMXBean().getInputArguments();
        //stem.out.println(StringUtil.join(paramters,":"));
        System.out.println("app starting...");
    }

}
