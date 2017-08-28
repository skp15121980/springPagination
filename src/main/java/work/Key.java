package work;

class Key<K> {

    private final K index;
    private final int pageNumber;
    private final int pageSize;

    Key(K index, int pageNumber, int pageSize) {
        this.index = index;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Key<?> that = (Key<?>) o;

        if (!index.equals(that.index))
            return false;
        if (pageNumber != that.pageNumber)
            return false;
        if (pageSize != that.pageSize)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = index.hashCode();
        result = 31 * result + pageNumber;
        result = 31 * result + pageSize;
        return result;
    }
}