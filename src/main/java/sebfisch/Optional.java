package sebfisch;

public sealed interface Optional<T> {
    record Empty<T>() implements Optional<T> {}
    record Present<T>(T value) implements Optional<T> {}
}
