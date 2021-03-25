package core;

public interface Calculator<T> {
    T add(final T t1, final T t2);

    T subtract(final T t1, final T t2);

    T multiply(final T t1, final T t2);

    T divide(final T t1, final T t2);
}
