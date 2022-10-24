package model;

public class Cube extends Entity{
    private String color;

    public Cube(float volume, String color) {
        super(volume);
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cube " +
                "color='" + color + '\'' +
                ", volume=" + volume+"cm3"
                ;
    }
}
