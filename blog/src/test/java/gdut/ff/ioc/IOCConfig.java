package gdut.ff.ioc;

import org.springframework.stereotype.Component;

/**
 * @Author bluesnail95
 * @Date 2020/7/25 10:06
 * @Description
 */
//@Component
public class IOCConfig {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public IOCConfig(String name) {
        this.name = name;
    }

    private User user;

    public  IOCConfig(User user) {
        System.out.println("AAA");
        this.user = user;
    }

    public IOCConfig() {
        System.out.println("BBB");
    }

    @Override
    public String toString() {
        return "IOCConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
