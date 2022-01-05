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
            case RED:
                return "hot";
            case GREEN, BLUE:
                return "cold";
            default:
                throw new IllegalArgumentException("unreachable");
        }
    }

    public static String describe2(BaseColor color) {
        switch (color) {
            case RED:
                return "hot";
            case GREEN:
            case BLUE:
                return "cold";
            default:
                throw new IllegalArgumentException("unreachable");
        }
    }

    public static String describe3(BaseColor color) {
        switch (color) {
            case RED -> {
                return "hot";
            }
            case GREEN, BLUE -> {
                return "cold";
            }
            default -> throw new IllegalArgumentException("unreachable");
        }
    }

    public static String describe4(BaseColor color) {
        return switch (color) {
            case RED:
                yield "hot";
            case GREEN, BLUE:
                yield "cold";
        };
    }

    public static String describe5(BaseColor color) {
        return switch (color) {
            case RED:
                yield "hot";
            case GREEN:
            case BLUE:
                yield "cold";
        };
    }

    public static String describe6(BaseColor color) {
        return switch (color) {
            case RED -> "hot";
            case GREEN, BLUE -> {
                final String cold = "c" + "o" + "l" + "d";
                yield cold;
            }
        };
    }

    public static String describe7(BaseColor color) {
        return switch (color) {
            case RED -> "hot";
            case GREEN, BLUE -> "cold";
        };
    }
}
