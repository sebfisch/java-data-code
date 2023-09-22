package sebfisch;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Optional<T> {

  public static <U> Empty<U> empty() {
    return new Empty<>();
  }

  public static <U> Present<U> of(U value) {
    return new Present<>(value);
  }

  record Empty<T>() implements Optional<T> {
  }

  record Present<T>(T value) implements Optional<T> {
    public Present {
      Objects.requireNonNull(value, "value must not be null");
    }
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
      case Present<T> self when pred.test(self.value) -> self;
      case Present<T> self -> new Empty<>();
    };
  }
}
