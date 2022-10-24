package Animal;

public class Bear extends Animal{
    private static String type_of_eater = "Omnivore";
    private static String name="Bear";
    public Bear(){}
    public Bear(String color, int age, String type_of_eater,String name){
        super(color,age);
        this.type_of_eater=type_of_eater;
        this.name=name;
    }

    public static void setType_of_eater(String type_of_eater) {
        Bear.type_of_eater = type_of_eater;
    }

    public static void setName(String name) {
        Bear.name = name;
    }

    public static String getType_of_eater() {
        return type_of_eater;
    }

    public static String getName() {
        return name;
    }

    public void display() {
        super.display();
        System.out.println("Type: " + getType_of_eater());
        System.out.println("Name: " + getName());


    }

}
