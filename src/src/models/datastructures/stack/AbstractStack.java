package src.models.datastructures.stack;

public abstract class AbstractStack<E> implements StackInterface<E> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (E e : this) {
            sb.insert(1, ", ").insert(1, e);
        }
        if (sb.length() > 1) sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
