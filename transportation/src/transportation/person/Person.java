package transportation.person;

public class Person {
    private String name;
    protected double money;
    public enum Gender {MALE,FEMALE};
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
}
