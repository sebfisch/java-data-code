package sebfisch;

import java.util.function.Function;

public interface BasicRecursiveList<T> {
    static class Empty<T> implements BasicRecursiveList<T> {}

    static class Populated<T> implements BasicRecursiveList<T> {
        private final T head;
        private final BasicRecursiveList<T> tail;

        public Populated(T head, BasicRecursiveList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public BasicRecursiveList<T> tail() {
            return tail;
        }
    }

    default <U> BasicRecursiveList<U> map(Function<T,U> fun) {
        if (this instanceof Empty) {
            return new Empty<>();
        }

        if (this instanceof Populated) {
            final Populated<T> self = (Populated<T>) this;
            return new Populated<>(fun.apply(self.head), self.tail.map(fun));
        }

        throw new IllegalStateException("neither empty nor populated");
    }
}
