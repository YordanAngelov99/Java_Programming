package transportation.person;

public class Person {
    private String name;
    protected double money;
    private Gender gender;
    private int age;

    public Person(String name,double money,Gender gender,int age){
        this.name = name;
        this.money = money;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public enum Gender {
        MALE,FEMALE;
    };
}
