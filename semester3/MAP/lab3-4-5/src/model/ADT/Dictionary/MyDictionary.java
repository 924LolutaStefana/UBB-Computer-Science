package model.ADT.Dictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Exception.MyException;
public class MyDictionary <K,V> implements MyIDictionary<K,V>{
    private Map<K,V> map;
    public MyDictionary(){
        map=new HashMap<K,V>();}
    @Override
    public void put(K key, V value) {
        map.put(key,value);
    }

    @Override
    public boolean isVarDef(K key) {
        return map.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        map.put(key,value);
    }

    @Override
    public V lookup(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key) throws MyException {
        if (!isVarDef(key))
            throw new MyException(key + " is not defined.");
        this.map.remove(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }



    public String toString(){
        return map.toString();}
}
