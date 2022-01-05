package sebfisch;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Optional<T> {
    record Empty<T> () implements Optional<T> {
    }

    record Present<T> (T value) implements Optional<T> {
        // TODO: Task 1.1 - add null check to constructor of present optionals
    }

    default <U> Optional<U> map(Function<T, U> fun) {
        return switch (this) {
            case Empty<T> self -> new Empty<>();
            case Present<T> self -> new Present<>(fun.apply(self.value));
        };
    }

    default Optional<T> filter(Predicate<T> pred) {
        return switch (this) {
            case Empty<T> self -> self;
            case Present<T> self && pred.test(self.value) -> self;
            case Present<T> self -> new Empty<>();
        };
    }
}
