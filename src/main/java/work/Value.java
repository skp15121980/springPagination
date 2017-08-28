package work;

import java.util.List;

class Value<V> {

    private final Key key;
    private final List<V> content;
    private final int total;

    Value(Key key, List<V> content, int total) {
        this.key = key;
        this.content = content;
        this.total = total;
    }

    public Key getKey() {
        return key;
    }

    public List<V> getContent() {
        return content;
    }

    public int getTotal() {
        return total;
    }
}
