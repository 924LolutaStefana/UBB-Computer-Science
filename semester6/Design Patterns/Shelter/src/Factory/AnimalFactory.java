package Factory;

import Entity.Animal;

public abstract class AnimalFactory {
    public abstract Animal createAnimal(String name, int age);
}
