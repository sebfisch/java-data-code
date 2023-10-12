package sebfisch.live;

import java.util.function.Function;

public sealed interface Optional<T> {
  record Empty<T>() implements Optional<T> {
  }

  record Present<T>(T value) implements Optional<T> {
  }

  // Empty().map(fun) -> Empty()
  // Present(value).map(fun) -> Present(fun.apply(value))

  default <U> Optional<U> map(Function<T, U> fun) {
    if (this instanceof Present) {
      final Present<T> self = (Present<T>) this;
      return new Present<>(fun.apply(self.value()));
    }

    return new Empty<>();
  }
}
