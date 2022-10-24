package controller;

import model.Entity;
import repository.IRepository;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addEntity(Entity entity){
        this.repository.add(entity);

    }
    public Entity[] removeEntity(int index){
        return this.repository.remove(index);

    }
    public Entity[] filter(float volume) {
        int filteredSize = 0;
        Entity[] filteredArray = new Entity[this.repository.size()];
        for (int i = 0; i < repository.size(); i++) {
            if (this.repository.getEntities()[i].getVolume() > volume)
                filteredArray[filteredSize++] = repository.getEntities()[i];
        }
        return filteredArray;

    }
    public int size(){
        return this.repository.size();
    }
}
