package blog.reflect;

public class Student {
    public Student() {
        System.out.println("我是无参构造方法");
    }
    public Student(String name) {
        System.out.println("我是有参构造方法，我的名字叫:"+ name);
    }
    public void method() {
        System.out.println("我是无参的方法");
    }
    public void method(String name) {
        System.out.println("我是有参的方法，参数为：" + name);
    }
    public String name;
    private int age;
    public int getAge() {
        return age;
    }
}
