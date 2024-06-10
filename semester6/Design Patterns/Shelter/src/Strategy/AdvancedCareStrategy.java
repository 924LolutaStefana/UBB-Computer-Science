package Strategy;

import Entity.Animal;

public class AdvancedCareStrategy implements AnimalCareStrategy {
    @Override
    public void performCare(Animal animal) {
        System.out.println("Performing advanced care for " + animal.getName() + ". Special attention to diet and exercise.");
    }
}
