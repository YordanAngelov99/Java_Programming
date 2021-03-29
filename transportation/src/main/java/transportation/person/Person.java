package transportation.person;

public class Person {
    private String name;
    protected double money;
    private Gender gender;
    private int age;

    public Person(String name,double money,Gender gender,int age){
        if(name == null || name.equals("")){
            this.name = "Airport";
        }else {
            this.name = name;
        }
        if(money > 0) {
            this.money = money;
        }else{
            this.money = 0;
        }
        if(gender != Gender.MALE && gender != Gender.FEMALE) {
            this.gender = Gender.FEMALE;
        }else{
            this.gender = gender;
        }
        if(age > 0) {
            this.age = age;
        }else{
            this.age = 0;
        }
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

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public enum Gender {
        MALE,FEMALE;
    };
}
