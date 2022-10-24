package model;

public class Cylinder extends Entity{
    private String how_big;

    public Cylinder(float volume,String how_big) {
        super(volume);
        this.how_big=how_big;
    }

    public String getHow_big() {
        return how_big;
    }

    public void setHow_big(String how_big) {
        this.how_big = how_big;
    }

    @Override
    public String toString() {
        return "Cylinder " +
                "how_big='" + how_big + '\'' +
                ", volume=" + volume+"cm3"
                ;
    }
}
