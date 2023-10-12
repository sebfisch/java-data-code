package sebfisch.live;

public enum BaseColor {
  RED, GREEN, BLUE;

  public static String asHexString(BaseColor color) {
    return switch (color) {
      case RED -> "#ff0000";
      case GREEN -> "#00ff00";
      case BLUE -> "#0000ff";
    };
  }

  public static String describe(BaseColor color) {
    return switch (color) {
      case RED, GREEN -> "ends with consonant";
      case BLUE -> {
        String result = "ends" + " with " + "vowel";
        yield result;
      }
    };
  }
}
