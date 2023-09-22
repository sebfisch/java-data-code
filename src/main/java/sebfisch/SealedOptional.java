package sebfisch;

import java.util.function.Function;

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

  default <U> SealedOptional<U> map(Function<T, U> fun) {
    if (this instanceof Present) {
      final Present<T> self = (Present<T>) this;
      return new Present<>(fun.apply(self.value));
    }

    return new Empty<>();
  }
}
