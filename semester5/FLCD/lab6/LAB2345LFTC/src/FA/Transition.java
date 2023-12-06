package FA;

public class Transition {
    private final String start_state;
    private final String labeled_symbol;
    private final String target_state;

    public Transition(String start_state, String labeled_symbol, String target_state) {
        this.start_state = start_state;
        this.labeled_symbol = labeled_symbol;
        this.target_state = target_state;
    }
    public String getStart_state() {
        return start_state;
    }
    public String getTarget_state() {
        return target_state;
    }
    public String getLabeled_symbol() {
        return labeled_symbol;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "start_state='" + start_state + '\'' +
                ", labeled_symbol='" + labeled_symbol + '\'' +
                ", target_state='" + target_state + '\'' +
                '}';
    }
}
