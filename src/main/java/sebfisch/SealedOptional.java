package sebfisch;

public sealed interface SealedOptional<T>
// permits SealedOptional.Empty<T>, SealedOptional.Present<T>
{
    final class Empty<T> implements SealedOptional<T> {
    }

    final class Present<T> implements SealedOptional<T> {
        private final T value;

        public Present(T value) {
            this.value = value;
        }

        public T value() {
            return value;
        }
    }
}
