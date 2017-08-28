package work;

import java.util.List;

@FunctionalInterface
public interface Loader<V> {

    List<V> load();
}
