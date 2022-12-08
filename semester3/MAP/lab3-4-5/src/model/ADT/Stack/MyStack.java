package model.ADT.Stack;
import Exception.MyException;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack <T> implements MyIStack<T> {
    Stack <T> stack;

    public MyStack() {
        this.stack = new Stack <>();
    }

    @Override
    public void push(T e) {
        stack.push(e);
    }

    @Override
    public T pop() throws MyException {
        T e =stack.pop();
        if(e==null)
            throw new MyException("Empty stack");
        return e;
    }
    public boolean isEmpty(){
        return stack.isEmpty();}
    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
    public String toString(){
        return stack.toString();}
}
