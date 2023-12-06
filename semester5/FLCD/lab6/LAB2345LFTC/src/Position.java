public class Position {
    private int firstPos;
    private int secondPos;

    public Position(int firstPos, int secondPos) {
        this.firstPos = firstPos;
        this.secondPos = secondPos;
    }
    public int getFirstPos() {
        return firstPos;
    }

    public int getSecondPos() {
        return secondPos;
    }

    @Override
    public String toString() {
        return "at position:" +
                "(" + firstPos + "," +
                secondPos + ")\n";
    }
}
