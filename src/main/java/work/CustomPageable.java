package work;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomPageable<K, V> {

    private final Storage<Key, Value<V>> storage = new Storage<>();

    private Value<V> get(Key key) {
        return storage.get(key);
    }

    private void put(Key key, Value<V> value) {
        storage.put(key, value);
    }

    protected Page<V> find(K index, Pageable pageable, Loader<V> loader) {
        if (pageable.getPageNumber() == 0) {
            List<V> list = loader.load();
            saveAll(index, list, pageable);
        }

        Key<K> key = new Key<>(index, pageable.getPageNumber(), pageable.getPageSize());
        Value<V> value = get(key);

        if (value == null) {
            if (pageable.getPageNumber() == 0) {
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
            } else {
                throw new IllegalArgumentException(String.format("Value not found in storage for index=%s and pageable=%s", index, pageable.toString()));
            }
        }

        return new PageImpl<>(value.getContent(), pageable, value.getTotal());
    }

    private void saveAll(K index, List<V> list, Pageable pageable) {
        int total = list.size();
        int pageSize = pageable.getPageSize();

        for (int pageNumber = 0; pageNumber <= total / pageSize; pageNumber++) {
            Key<K> key = new Key<>(index, pageNumber, pageable.getPageSize());

            int fromIndex = pageNumber * pageSize;
            int toIndex = (fromIndex + pageSize < total) ? fromIndex + pageSize : total;

            List<V> content = list.subList(fromIndex, toIndex);
            Value<V> value = new Value<>(key, content, total);

            put(key, value);
        }
    }
}
