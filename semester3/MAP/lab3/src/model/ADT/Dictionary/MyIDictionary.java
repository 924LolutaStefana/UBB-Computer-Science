package model.ADT.Dictionary;
import Exception.MyException;
import view.Command;

import java.util.Collection;

public interface MyIDictionary <K,V>{
    void put(K key, V value);
    boolean isVarDef(K key);
    void update (K key, V value);
    V lookup(K key);
    void remove(K key) throws MyException;


}
