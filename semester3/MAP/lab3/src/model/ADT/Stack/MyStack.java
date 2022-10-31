package model.ADT.Stack;
import Exception.MyException;


import java.util.Stack;

public class MyStack <T> implements MyIStack<T> {
    private Stack <T> stack;

    public MyStack(Stack<T> stack) {
        stack = new Stack <T>();
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
        return stack.empty();}
    //public List <T> getReverse (){
      //  List <T> l= Array.asList( (T[]) stack.toArray());
        //Collections.reverse(l);
        //return l;}
    public String toString(){
        return stack.toString();}
}
