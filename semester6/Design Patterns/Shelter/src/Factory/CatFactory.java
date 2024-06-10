package Factory;

import Entity.Animal;

public class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal(String name, int age) {
        return new Animal(name, "Cat", age);
    }
}