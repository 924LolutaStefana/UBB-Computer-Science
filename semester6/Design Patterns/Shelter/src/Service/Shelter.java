package Service;

import Entity.Animal;
import Observer.ShelterObserver;
import Strategy.AnimalCareStrategy;

import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private static Shelter instance;
    private List<Animal> animals;
    private List<ShelterObserver> observers;
    private AnimalCareStrategy careStrategy;

    private Shelter() {
        animals = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void setCareStrategy(AnimalCareStrategy careStrategy) {
        this.careStrategy = careStrategy;
    }

    public void performCareForAll() {
        for (Animal animal : animals) {
            careStrategy.performCare(animal);
        }
    }
    public static synchronized Shelter getInstance() {
        if (instance == null) {
            instance = new Shelter();
        }
        return instance;
    }

    public void addObserver(ShelterObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ShelterObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String operation, Animal animal) {
        for (ShelterObserver observer : observers) {
            observer.update(operation, animal);
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        notifyObservers("added", animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        notifyObservers("removed", animal);
    }

    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals);
    }
}
