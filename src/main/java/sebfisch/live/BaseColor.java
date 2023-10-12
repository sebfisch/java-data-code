package sebfisch.live;

public enum BaseColor {
  RED, GREEN, BLUE;

  public static String asHexString(BaseColor color) {
    switch (color) {
      case RED:
        return "#ff0000";
      case GREEN:
        return "#00ff00";
      case BLUE:
        return "#0000ff";
      default:
        throw new IllegalArgumentException("unreachable");
    }
  }
}
