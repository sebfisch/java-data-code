package sebfisch.live;

import java.util.function.Function;

import sebfisch.live.RecList.Empty;
import sebfisch.live.RecList.Populated;

public sealed interface RecList<T> {
    final class Empty<T> implements RecList<T> {
    }

    final class Populated<T> implements RecList<T> {
        private final T head;
        private final RecList<T> tail;

        public Populated(T head, RecList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public RecList<T> tail() {
            return tail;
        }
    }

    // Empty().map(fun) -> Empty()
    // Populated(head,tail).map(fun) -> Populated(fun.apply(head), tail.map(fun))

    default <U> RecList<U> map(Function<T, U> fun) {
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
