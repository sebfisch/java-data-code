package sebfisch.live;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Optional<T> {
  record Empty<T>() implements Optional<T> {
  }

  record Present<T>(T value) implements Optional<T> {
  }

  // Empty().map(fun) -> Empty()
  // Present(value).map(fun) -> Present(fun.apply(value))

  default <U> Optional<U> map(Function<T, U> fun) {
    return switch (this) {
      case Empty() -> new Empty<>();
      case Present(T value) -> new Present<>(fun.apply(value));
    };
  }

  // Empty().filter(pred) -> Empty()
  // Present(value).filter(pred) -> Present(value) if pred.test(value)
  // Present(value).filter(pred) -> Empty()

  default Optional<T> filter(Predicate<T> pred) {
    return switch (this) {
      case Empty() -> this;
      case Present(T value) when pred.test(value) -> this;
      case Present(T value) -> new Empty<>();
    };
  }
}
