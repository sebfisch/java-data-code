package sebfisch.colors;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public record RGBColor(double red, double green, double blue) {
    public RGBColor {
        check(red);
        check(green);
        check(blue);
    }

    private static void check(double val) {
        if (val < 0 || 1 < val) {
            throw new IllegalArgumentException("invalid color value " + val);
        }
    }

    public static Optional<RGBColor> findBrightest(List<RGBColor> colors) {
        record AugmentedColor(RGBColor color, double brightness) {
            AugmentedColor(RGBColor color) {
                this(color, (color.red + color.green + color.blue) / 3);
            }
        }

        return colors.stream()
                .map(AugmentedColor::new)
                .max(Comparator.comparing(AugmentedColor::brightness))
                .map(AugmentedColor::color);
    }

    public static String describeWithDefaultBranch(RGBColor color) {
        return switch (color) {
            case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
            case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
            case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
            default -> "colorless";
        };
    }

    public static String describeWithNullCheck(RGBColor color) {
        return switch (color) {
            case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
            case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
            case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
            case null, default -> "colorless";
        };
    }

    public static String describeWithTotalBranch(RGBColor color) {
        return switch (color) {
            case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
            case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
            case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
            case RGBColor c -> "colorless";
        };
    }
}
