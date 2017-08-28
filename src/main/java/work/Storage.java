package work;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

class Storage<K, V> {

    private final Cache<K, V> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    V get(K key) {
        return cache.getIfPresent(key);
    }

    void put(K key, V value) {
        cache.put(key, value);
    }
}
