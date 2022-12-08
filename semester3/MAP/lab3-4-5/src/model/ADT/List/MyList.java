package model.ADT.List;

import java.util.ArrayList;
import java.util.List;
import Exception.MyException;

public class MyList <T> implements MyIList<T>{
    private List<T> out;
    public MyList(){
        out=new ArrayList<T>();}
    @Override
    public void add(T elem) {
        out.add(elem);


    }
    @Override
    public T pop() throws MyException{
        if (out.isEmpty())
            throw new MyException("The list is empty!");
        return this.out.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return this.out.isEmpty();
    }

    @Override
    public List<T> getList() {
        return out;
    }
    public String toString(){
        return out.toString();}
}
