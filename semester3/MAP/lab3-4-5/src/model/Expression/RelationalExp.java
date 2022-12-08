package model.Expression;

import model.ADT.Dictionary.MyIDictionary;
import model.ADT.Heap.MyIHeap;
import model.Type.IntType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.Value;
import Exception.MyException;

import java.util.Objects;

public class RelationalExp implements Exp{
    Exp expression1;
    Exp expression2;
    String operator;

    public RelationalExp(String operator, Exp expression1, Exp expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable,heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable,heap);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getVal();
                v2 = val2.getVal();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(v1 < v2);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(v1 <= v2);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(v1 == v2);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(v1 != v2);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(v1 > v2);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand in not an integer.");
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(operator, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }
}
