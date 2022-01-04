package sebfisch;

import java.util.Arrays;

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
}
