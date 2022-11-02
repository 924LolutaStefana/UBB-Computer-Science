package model.ADT.Stack;
import Exception.MyException;
public interface MyIStack <T>{
    void push (T e);
    T pop() throws MyException;
    boolean isEmpty();

}
