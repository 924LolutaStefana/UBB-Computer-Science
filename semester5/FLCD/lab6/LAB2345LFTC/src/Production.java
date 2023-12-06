import FA.MySet;
import java.util.List;

public class Production {
    private final String lhs;
    private final MySet<List<String>> rhs;

    public Production(String lhs, MySet<List<String>> rhs){
        this.lhs=lhs;
        this.rhs = rhs;
    }

    public String getLhs() {
        return lhs;
    }

    public MySet<List<String>> getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return "Production{" +
                "lhs='" + lhs + '\'' +
                ", rhs=" + rhs +
                '}';
    }
}
