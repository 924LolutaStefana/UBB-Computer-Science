package Strategy;


import Entity.Animal;

public class BasicCareStrategy implements AnimalCareStrategy {
    @Override
    public void performCare(Animal animal) {
        System.out.println("Performing basic care for " + animal.getName());
    }
}
