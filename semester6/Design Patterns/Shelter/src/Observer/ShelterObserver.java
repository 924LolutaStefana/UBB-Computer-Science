package Observer;

import Entity.Animal;

public interface ShelterObserver {
    void update(String operation, Animal animal);
}
