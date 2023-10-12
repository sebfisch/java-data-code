package sebfisch.live;

public enum BaseColor {
    RED, GREEN, BLUE;

    // asHexString(RED) -> "#ff0000"
    // asHexString(GREEN) -> "#00ff00"
    // asHasString(BLUE) -> "#0000ff"

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
