package sebfisch.colors;

public enum BaseColor {
  RED, GREEN, BLUE;

  public static String asHexString(BaseColor color) {
    return switch (color) {
      case RED -> "#ff0000";
      case GREEN -> "#00ff00";
      case BLUE -> "#0000ff";
    };
  }

  public static String describe1(BaseColor color) {
    switch (color) {
      case RED, GREEN:
        return "ends with consonant";
      case BLUE:
        return "ends with vowel";
      default:
        throw new IllegalArgumentException("unreachable");
    }
  }

  public static String describe2(BaseColor color) {
    switch (color) {
      case RED:
      case GREEN:
        return "ends with consonant";
      case BLUE:
        return "ends with vowel";
      default:
        throw new IllegalArgumentException("unreachable");
    }
  }

  public static String describe3(BaseColor color) {
    switch (color) {
      case RED, GREEN -> {
        return "ends with consonant";
      }
      case BLUE -> {
        return "ends with vowel";
      }
      default -> throw new IllegalArgumentException("unreachable");
    }
  }

  public static String describe4(BaseColor color) {
    return switch (color) {
      case RED, GREEN:
        yield "ends with consonant";
      case BLUE:
        yield "ends with vowel";
    };
  }

  public static String describe5(BaseColor color) {
    return switch (color) {
      case RED:
      case GREEN:
        yield "ends with consonant";
      case BLUE:
        yield "ends with vowel";
    };
  }

  public static String describe6(BaseColor color) {
    return switch (color) {
      case RED, GREEN -> "ends with consonant";
      case BLUE -> {
        final String result = "ends" + " with " + "vowel";
        yield result;
      }
    };
  }

  public static String describe7(BaseColor color) {
    return switch (color) {
      case RED, GREEN -> "ends with consonant";
      case BLUE -> "ends with vowel";
    };
  }
}
