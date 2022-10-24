package model;

public class Entity {
    protected float volume;

    public Entity(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return
                "volume= " + volume+"cm3"
                ;
    }
}
