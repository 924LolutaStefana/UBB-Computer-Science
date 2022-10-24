package Animal;

public class Animal {
    protected static String color;
    protected  static int age;
    public Animal(){}
    public Animal(String color,int age){
        this.color=color;
        this.age=age;
    }


    public static void setColor(String color) {
        Animal.color = color;
    }

    public static void setAge(int age) {
        Animal.age = age;
    }

    public static String getColor() {
        return color;
    }



    public  int getAge() {
        return age;
    }

    public  void display() {
        System.out.println("Color: " + getColor());
        System.out.println("Age: " + getAge());

    }
}
