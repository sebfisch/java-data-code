package sebfisch;

import java.util.function.Function;

public interface BasicOptional<T> {
    static class Empty<T> implements BasicOptional<T> {
        public <U> BasicOptional<U> map(Function<T,U> fun) {
            return new Empty<>();
        }
    }

    static class Present<T> implements BasicOptional<T> {
        private final T value;

        public Present(T value) {
            // TODO: null check
            this.value = value;
        }

        public T value() {
            return value;
        }

        public <U> BasicOptional<U> map(Function<T,U> fun) {
            return new Present<>(fun.apply(this.value()));
        }
    }

    default <U> BasicOptional<U> map(Function<T,U> fun) {
        if (this instanceof Empty) {
            return new Empty<>();
        }

        if (this instanceof Present) {
            final Present<T> self = (Present<T>) this;
            return new Present<>(fun.apply(self.value()));
        }

        throw new IllegalStateException("neither empty nor present");
    }
}
