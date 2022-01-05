package sebfisch;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Optional<T> {
    record Empty<T> () implements Optional<T> {
    }

    record Present<T> (T value) implements Optional<T> {
        // TODO: Task 1.1 - add null check
    }

    default <U> Optional<U> map(Function<T, U> fun) {
        if (this instanceof Present<T> self) {
            return new Present<>(fun.apply(self.value));
        }

        return new Empty<>();
    }

    default Optional<T> filter(Predicate<T> pred) {
        if (this instanceof Present<T> self && pred.test(self.value)) {
            return this;
        }

        return new Empty<>();
    }
}
