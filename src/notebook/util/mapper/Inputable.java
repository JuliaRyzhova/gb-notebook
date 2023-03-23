package notebook.util.mapper;

/**
 * ��������� ������� �� ������ ���� � ������.
 * @param <E> ���, �������� � ��
 * @param <T> ���, ���������� ��� ����������� E
 */
public interface Inputable<E, T>  {
    T toInput(E e);
}
