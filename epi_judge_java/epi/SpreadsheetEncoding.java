package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    final int ASCII_OFFSET = 64;
    final int ALPHABET_NUMBER = 26;
    final int INVALID_COLUMN_REPRESENTATION = 0;
    if (col == null || col.isBlank()) {
      return INVALID_COLUMN_REPRESENTATION;
    }
    int totalValue = 0;
    for (int i = col.length() - 1; i >= 0; i--) {
      int power = col.length() - 1 - i;
      char character = col.charAt(i);
      int characterValue = character - ASCII_OFFSET;
      double value = characterValue * Math.pow(ALPHABET_NUMBER, power);
      totalValue += (int) value;
    }
    return totalValue;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
