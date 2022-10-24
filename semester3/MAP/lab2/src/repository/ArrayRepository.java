package repository;

import model.Entity;

public class ArrayRepository implements IRepository {
    private Entity array[];
    private int capacity=10,size;

    public ArrayRepository() {
        size=0;
        array=new Entity[capacity];
    }

    @Override
    public void add(Entity element) {
        array[size++]=element;
    }

    @Override
    public Entity[] remove(int index) {

        if (array == null || index < 0
                || index >= array.length) {

            return array;
        }

        Entity[] anotherArray = new Entity[capacity];

        for (int i = 0, k = 0; i < array.length; i++) {

            if (i == index) {
                continue;
            }

            anotherArray[k++] = array[i];
        }

        // return the resultant array
        return anotherArray;

    }

    @Override
    public void update(Entity element, Entity new_element) {

    }

    @Override
    public Entity[] getEntities() {
        return array;
    }

    @Override
    public int size() {
        return size;
    }
}
