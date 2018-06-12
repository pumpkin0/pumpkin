package blog.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericErasure {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("你好");
        Class<? extends List> clazz = list.getClass();
        Method method = clazz.getMethod("add",Object.class);
        method.invoke(list, 1);
        for (Object ele : list) {
            System.out.println(ele);
        }
    }
}
