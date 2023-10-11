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

  // requires JDK 21
  //
  // public double saturation() {
  // return switch (this) {
  // case RGBColor(var r, var g, var b) when max(r, g, b) == 0 -> 0;
  // case RGBColor(var r, var g, var b) -> (max(r, g, b) - min(r, g, b)) / max(r,
  // g, b);
  // default -> throw new IllegalStateException("unreachable");
  // };
  // }

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

  public static String describeWithDefaultBranch(RGBColor color) {
    return switch (color) {
      case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
      case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
      case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
      default -> "other";
    };
  }

  public static String describeWithNullCheck(RGBColor color) {
    return switch (color) {
      case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
      case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
      case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
      case null, default -> "other";
    };
  }

  public static String describeWithTotalBranch(RGBColor color) {
    return switch (color) {
      case RGBColor c && c.red > c.green && c.red > c.blue -> "reddish";
      case RGBColor c && c.green > c.red && c.green > c.blue -> "greenish";
      case RGBColor c && c.blue > c.red && c.blue > c.green -> "blueish";
      case RGBColor c -> "other"; // includes null in JDK 17 but not in JDK 21
      // case null -> "other";
    };
  }
}
