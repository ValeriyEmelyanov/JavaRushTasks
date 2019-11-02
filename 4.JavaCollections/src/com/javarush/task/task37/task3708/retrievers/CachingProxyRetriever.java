package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private static final int CASHE_CAPACITY = 16;
    private OriginalRetriever retriever;
    private LRUCache<Long, Object> cache = new LRUCache<>(CASHE_CAPACITY);

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object returnValue = cache.find(id);
        if (returnValue == null) {
            returnValue = retriever.retrieve(id);
            cache.set(id, returnValue);
        }
        return returnValue;
    }
}
