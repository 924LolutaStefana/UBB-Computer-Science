package model.ADT.Dictionary;
import Exception.MyException;
import view.Command;

import java.util.Collection;
import java.util.Set;

public interface MyIDictionary <K,V>{
    void put(K key, V value);
    Set<K> keySet();
    boolean isVarDef(K key);
    void update (K key, V value);
    V lookup(K key);
    void remove(K key) throws MyException;


}
