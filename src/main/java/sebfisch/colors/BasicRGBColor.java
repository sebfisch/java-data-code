package sebfisch.colors;

public class BasicRGBColor {
    private final double red;
    private final double green;
    private final double blue;

    public BasicRGBColor(double red, double green, double blue) {
        // TODO: check between 0 and 1
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double red() {
        return red;
    }

    public double green() {
        return green;
    }

    public double blue() {
        return blue;
    }

    public double saturation() {
        final double max = Math.max(red, Math.max(green, blue));

        if (max == 0) {
            return 0;
        }

        final double min = Math.min(red, Math.min(green, blue));

        return (max - min) / max;
    }
}
