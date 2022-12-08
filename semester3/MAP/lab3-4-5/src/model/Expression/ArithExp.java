package model.Expression;
import Exception.MyException;
import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Type.IntType;
import model.Value.IntValue;
import model.Value.Value;

public class ArithExp implements Exp{
    private Exp e1;
    private Exp e2;
    private char op;

    public ArithExp(char op,Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws MyException, MyException, MyException {
        Value v1, v2;
        v1 = e1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == '+') return new IntValue(n1 + n2);
                else if (op == '_') return new IntValue(n1 - n2);
                else  if (op == '*') return new IntValue(n1 * n2);
                else  if (op == '/')
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not integer");
        } else
            throw new MyException("first operand is not an integer");
        return v1;
    }
    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
    @Override
    public Exp deepCopy() {
        return new ArithExp(op, e1.deepCopy(), e2.deepCopy());
    }


}
