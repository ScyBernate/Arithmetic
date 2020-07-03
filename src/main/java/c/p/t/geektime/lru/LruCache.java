package c.p.t.geektime.lru;

public abstract class LruCache<K,V> implements Storage<K,V> {

    /**
     *  容量大小
     */
    protected int capacity;

    protected final Storage<K,V> storage;

    public LruCache(int capacity,Storage<K,V> storage){
        this.capacity = capacity;
        this.storage = storage;
    }

}
