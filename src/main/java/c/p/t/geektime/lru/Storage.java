package c.p.t.geektime.lru;

public interface Storage<K, V> {

    V get(K key);

}
