package hr.fer.oprpp1.custom.collections;

public class Dictionary<K, V> {
    private ArrayIndexedCollection<Entry<K,V>> set;

    public Dictionary() {
        this.set = new ArrayIndexedCollection<>();
    }

    private static class Entry<K, V> {
       private K key;
       private V value;
    }

    boolean isEmpty() {
        return this.set.isEmpty();
    }

    int size() {
        return this.set.size();
    }

    void clear() {
        this.set.clear();
    }

    V put(K key, V value) {
        if(key == null) {
            throw new NullPointerException("msg");
        } else {
            int size = this.set.size();
            for(int i = 0; i < size; i++) {
                if(this.set.get(i).key == key) {
                    V oldValue = this.set.get(i).value;
                    this.set.get(i).value = value;
                    return oldValue;
                }
            }
            Entry<K, V> e = new Entry<>();
            e.key = key;
            e.value = value;
            this.set.add(e);
            return null;
        }
    }

    V get(Object key) {
        int index;
        int size = this.set.size();
        for(index = 0; index < size; index++) {
            if(this.set.get(index).key == key) {
                return this.set.get(index).value;
            }
        }
       return null;
    }

    V remove(K key) {
        int size = this.set.size();
        for(int i = 0; i < size; i++) {
            if(this.set.get(i).key.equals(key)) {
                V oldValue = this.set.get(i).value;
                this.set.remove(i);
                return oldValue;
            }
        }
        return null;
    }
}
