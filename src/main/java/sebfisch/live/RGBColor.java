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
    return switch (this) {
      case RGBColor(var r, var g, var b) when max(r, g, b) == 0 -> 0;
      case RGBColor(var r, var g, var b) -> (max(r, g, b) - min(r, g, b)) / max(r, g, b);
    };
  }

  private static double max(double a, double b, double c) {
    return Math.max(a, Math.max(b, c));
  }

  private static double min(double a, double b, double c) {
    return Math.min(a, Math.min(b, c));
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

  public static String describe(RGBColor color) {
    return switch (color) {
      case RGBColor c when c.red > c.green && c.red > c.blue -> "reddish";
      case RGBColor c when c.green > c.red && c.green > c.blue -> "greenish";
      case RGBColor c when c.blue > c.green && c.blue > c.red -> "blueish";
      case RGBColor c -> "other";
      case null -> "other";
    };
  }
}
