package model.ADT.Dictionary;

public interface MyIDictionary <K,V>{
    void put(K key, V value);
    boolean isVarDef(K key);
    void update (K key, V value);
    V lookup(K key);
}
