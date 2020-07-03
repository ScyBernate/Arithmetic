package c.p.t.geektime.lru;

import java.util.HashMap;
import java.util.Map;


/**
 * lru 缓存
 * 利用hash表+双向链表实现
 * 查询、插入、删除均为O(1)
 * @param <K>
 * @param <V>
 */
public class LruCacheImpl<K, V> extends LruCache<K, V> {

    protected Map<K, Node> queryMap = new HashMap<K, Node>();

    protected Node head = null;

    protected Node end = null;

    public LruCacheImpl(int capacity, Storage<K, V> storage) {
        super(capacity, storage);
    }

    @Override
    public V get(K key) {
        if (queryMap.containsKey(key)) {
            Node node = queryMap.get(key);
            remove(node);  //移除该结点
            setHead(node); //将该结点移至头部
            return node.getValue();
        }
        V val = storage.get(key);
        if (val != null) {
            set(key, val); //将storage缓存中的数据添加到map中
            return val;
        }
        return null;
    }

    public void set(K key, V value) {
        if (queryMap.containsKey(key)) { //覆盖值
            Node old = queryMap.get(key);
            old.setValue(value);
            remove(old);
            setHead(old);
        } else {
            Node create = new Node(key, value);
            if(queryMap.size()>=capacity){ //容量满了
                remove(end);
                queryMap.remove(end.key);
            }
            setHead(create);
            queryMap.put(key,create);
        }
    }

    /**
     * 将结点设置为head结点
     *
     * @param node
     */
    public void setHead(Node node) {
        node.prev = null;
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (end == null) {
            end = node;
        }
    }

    public void remove(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else { //node是头结点
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            end = node.prev;
        }
    }

    class Node {

        private Node prev;

        private Node next;

        private K key;

        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
