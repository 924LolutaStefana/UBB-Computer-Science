package model.ADT.List;

import java.util.List;
import Exception.MyException;
public interface MyIList <T>{

    T pop() throws MyException;
    boolean isEmpty();
    List<T> getList();
    void add(T elem);
}
