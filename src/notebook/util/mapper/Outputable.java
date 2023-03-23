package notebook.util.mapper;

public interface Outputable<E, T> {
    E toOutput(T t);
    boolean isDigit(T t);
}
