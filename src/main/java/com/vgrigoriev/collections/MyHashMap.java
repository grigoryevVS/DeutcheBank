package com.vgrigoriev.collections;

import java.util.*;

/**
 * Own implementation of hashMap
 */
public class MyHashMap<K,V> implements Map<K,V>{

    static final int DEFAULT_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;


    private float loadFactor;
    private Entry[] table;
    private int threashold;
    private int size;


    public MyHashMap(){

        size = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        threashold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_CAPACITY];
    }

    public MyHashMap(int capacity) {

        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {

        if (capacity < 0) {
            throw new IllegalArgumentException("wrong capacity, can't be less then 0");
        }
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor < 0) {
            throw new IllegalArgumentException("wrong loadFactor, can't be less then 0");
        }
        size = capacity;
        this.loadFactor = loadFactor;
        threashold = (int) (size * this.loadFactor);
        table = new Entry[size];
    }

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        for (Entry<K, V> entry = table[indexFor(hash, table.length)]; entry != null; entry = entry.next) {
            Object k;
            k = entry.key;
            if (entry.hash == hash && (k == key || k != null && k.equals(key))) {
                return true;
            }
        }
        return false;
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public V get(Object key) {

        return null;
    }

    @Override
    public V put(K key, V value) {

        return null;
    }

    @Override
    public V remove(Object key) {

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * to rehash this map
     */
    public void resize() {

    }




    static class Entry<K, V> implements Map.Entry<K, V> {

        final K key;
        V value;
        final int hash;
        Entry<K,V> next;

        Entry(int hash, K key, V value, Entry<K, V> entry) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = entry;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o){
                return true;
            }
            if (o == null || !(o instanceof Map.Entry)){
                return false;
            }
            Entry entry = (Entry) o;

            if (hash != entry.hash) return false;
            if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
            if (next != null ? !next.equals(entry.next) : entry.next != null) return false;
            if (value != null ? !value.equals(entry.value) : entry.value != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + hash;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
