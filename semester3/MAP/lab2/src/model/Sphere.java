package model;

public class Sphere extends Entity{
    private String type;

    public Sphere(float volume, String type) {
        super(volume);
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sphere " +
                "type='" + type + '\'' +
                ", volume=" + volume +"cm3"
                ;
    }
}
