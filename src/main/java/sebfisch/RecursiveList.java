package sebfisch;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface RecursiveList<T> {
    record Empty<T> () implements RecursiveList<T> {
    }

    record Populated<T> (T head, RecursiveList<T> tail)
            implements RecursiveList<T> {
    }

    static <U> RecursiveList<U> of(U... args) {
        if (args.length == 0) {
            return new Empty<>();
        }

        return new Populated<>(
                args[0],
                of(Arrays.copyOfRange(args, 1, args.length)));
    }

    default int length() {
        if (this instanceof Populated<T> self) {
            return 1 + self.tail.length();
        }

        return 0;
    }

    default <U> RecursiveList<U> map(Function<T,U> fun) {
        if (this instanceof Populated<T> self) {
            return new Populated<>(fun.apply(self.head), self.tail.map(fun));
        }

        return new Empty<>();
    }

    default RecursiveList<T> filter(Predicate<T> pred) {
        if (this instanceof Populated<T> self) {
            final RecursiveList<T> filteredTail = self.tail.filter(pred);
            if (pred.test(self.head)) {
                return new Populated<>(self.head, filteredTail);
            }
            return filteredTail;
        }

        return this;
    }
}
