package sebfisch.live;

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

    // RGBColor(r,g,b).saturation() -> 0 if max(r,g,b) = 0
    // RGBColor(r,g,b).saturation() -> (max(r,g,b) - min(r,g,b)) / max(r,g,b)

    public double saturation() {
        final double max = Math.max(red, Math.max(green, blue));

        if (max == 0) {
            return 0;
        }

        final double min = Math.min(red, Math.min(green, blue));

        return (max - min) / max;
    }

    public static Optional<RGBColor> findBrightest(List<RGBColor> colors) {
        record AugmentedColor(RGBColor color, double brightness) {
            AugmentedColor(RGBColor color) {
                this(color, (color.red + color.green + color.blue) / 3);
            }
        }

        return colors.stream() //
                .map(AugmentedColor::new) //
                .max(Comparator.comparing(AugmentedColor::brightness)) //
                .map(AugmentedColor::color);
    }
}
