package model.ADT.Dictionary;

import java.util.HashMap;
import java.util.Map;

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
    public String toString(){
        return map.toString();}
}
