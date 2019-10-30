package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length ; i++) {
            if (table[i] == null) {
                continue;
            }
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                if (value.equals(e.value))
                    return true;
                if (e.value == null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null) {
            for (Entry e = table[0].getEntry(); e != null; e = e.next) {
                if (e.key == null) {
                    e.value = value;
                    return;
                }
            }
            addEntry(0, null, value, 0);
        }

        int hash = hash((long)key.hashCode());
        int i = indexFor(hash, table.length);
        if (table[i] == null) {
            createEntry(hash, key, value, i);
        } else {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                    return;
                }
            }

            addEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                if (value.equals(e.value))
                    return e.getKey();
                if (e.value == null)
                    return e.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        if(this.containsKey(key)) {
            return getEntry(key).getValue();
        }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }


    int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)].getEntry();
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (FileBucket fileBucket : newTable)
            fileBucket = new FileBucket();

        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j].getEntry();
            if (e != null) {
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
                src[j].remove();
                src[j] = null;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
            size++;
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }
}
