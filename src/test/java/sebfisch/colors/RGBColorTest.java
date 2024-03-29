package sebfisch.colors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RGBColorTest {
  @Test
  void testRGBdescriptionWithDefaultBranchForNull() {
    assertThrows(NullPointerException.class, () -> {
      RGBColor.describeWithDefaultBranch(null);
    });
  }

  @Test
  void testRGBdescriptionWithNullCheckForNull() {
    assertEquals("other", RGBColor.describeWithNullCheck(null));
  }

  @Test
  void testRGBdescriptionWithTotalBranchForNull() {
    assertEquals("other", RGBColor.describeWithTotalBranch(null));
  }
}
