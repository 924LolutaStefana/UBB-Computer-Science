package model.ADT.Stack;
import Exception.MyException;

import java.util.List;

public interface MyIStack <T>{
    void push (T e);
    T pop() throws MyException;
    boolean isEmpty();

    List<T> getReversed();
}
