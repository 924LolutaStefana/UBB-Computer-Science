package model.Expression;

import model.ADT.Dictionary.MyIDictionary;
import model.Type.BoolType;
import model.Type.IntType;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.Value;
import Exception.MyException;

import java.util.Objects;

public class LogicalExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public LogicalExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.isVal();
                n2 = i2.isVal();
                if (op == 0) return new BoolValue(n1||n2);
                if (op == 1) return new BoolValue(n1 &&n2);


            } else
                throw new MyException("second operand is not boolean");
        } else
            throw new MyException("first operand is not boolean");


        return null;
    }

    @Override
    public String toString() {
        if (op==0)
            return e1.toString()+"or"+e2.toString();
        if (op==1)
            return e1.toString()+"and"+e2.toString();

        return null;
    }
}

