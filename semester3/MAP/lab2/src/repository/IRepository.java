package repository;

import model.Entity;

public interface IRepository {
    public void add(Entity element);
    public Entity[] remove(int index);
    public void update(Entity element, Entity new_element);
    public Entity[] getEntities();
    public int size();
}
