package Command;


import Entity.Animal;
import Service.Shelter;

public class RemoveAnimalCommand implements Command {
    private Shelter shelter;
    private Animal animal;

    public RemoveAnimalCommand(Shelter shelter, Animal animal) {
        this.shelter = shelter;
        this.animal = animal;
    }

    @Override
    public void execute() {
        shelter.removeAnimal(animal);
    }

    @Override
    public void undo() {
        shelter.addAnimal(animal);
    }
}
