package Observer;


import Entity.Animal;

public class ConsoleObserver implements ShelterObserver {
    @Override
    public void update(String operation, Animal animal) {
        System.out.println("Animal " + operation + ": " + animal);
    }
}
