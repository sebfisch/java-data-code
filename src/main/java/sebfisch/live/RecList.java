package sebfisch.live;

public sealed interface RecList<T> {
  record Empty<T>() implements RecList<T> {
  }

  record Populated<T>(T head, RecList<T> tail) {
  }
}
