package Command;


import Entity.Animal;
import Service.Shelter;

public class AddAnimalCommand implements Command {
    private Shelter shelter;
    private Animal animal;

    public AddAnimalCommand(Shelter shelter, Animal animal) {
        this.shelter = shelter;
        this.animal = animal;
    }

    @Override
    public void execute() {
        shelter.addAnimal(animal);
    }

    @Override
    public void undo() {
        shelter.removeAnimal(animal);
    }
}

