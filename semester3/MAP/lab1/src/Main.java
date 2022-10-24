import Animal.Animal;
import Animal.Bear;

public class Main {

    static void checkAge(int  age) throws Exception {
        if (age > 30) {
            throw new Exception("An animal  doesn't live that much :)");
        }

    }
    public static void main(String[] args) throws Exception {


        Animal bear1 = new Bear("Brown",20,"Omnivore","Baloo");
        int age=bear1.getAge();

        bear1.display();
        checkAge(age);







    }
}
