package JDK.Collection;

import evilp0s.PrintUtil;
import model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 对JDK提供的集合相关的一些测试
 */
public class CollectionTest {

    /**
     * 测试利用list转set或set转list的方法是否能解决深拷贝的问题
     * <p/>
     * 测试结果:不能解决深拷贝的问题
     */
    @Test
    public void testDeepCopy() {
        List<User> list1 = new ArrayList<>();
        User user1 = new User("111", "AAA");
        User user2 = new User("222", "BBB");
        User user3 = new User("333", "CCC");
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);
        PrintUtil.print("list:" + list1);
        user1.setUserId("444");
        PrintUtil.print("list" + list1);

        Set<User> set = new HashSet<>(list1);
        PrintUtil.print("Set:" + set);

        PrintUtil.print("==============");
        user2.setUserId("5555");
        list1.add(new User("666", "EEE"));
        PrintUtil.print("list" + list1);
        PrintUtil.print("Set:" + set);


    }
}
