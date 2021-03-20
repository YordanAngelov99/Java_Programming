package Person;

import java.math.BigDecimal;

public class Person {
    private String name;
    protected BigDecimal money;
    private boolean isMale;
    private int age;

    public Person(String name,BigDecimal money,boolean isMale,int age){
        this.name = name;
        this.money = money;
        this.isMale = isMale;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
