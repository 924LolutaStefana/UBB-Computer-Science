package Factory;

import Entity.Animal;

public class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal(String name, int age) {
        return new Animal(name, "Dog", age);
    }
}