package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    final char NEGATIVE_SIGN = '-';
    if ("0".equals(numAsString)) {
      return numAsString;
    }
    int firstCharacter = numAsString.charAt(0);
    boolean isNegative = (firstCharacter == NEGATIVE_SIGN);
    int startingPosition = isNegative ? 1 : 0;
    double totalValue = 0;
    int basePower = numAsString.length() - 1 - startingPosition;
    for (int i = startingPosition; i < numAsString.length(); i++) {
      char digit = numAsString.charAt(i);
      int digitValue = NumberRepresentation.valueOf(digit);
      double value = digitValue * Math.pow(b1, basePower--);
      totalValue += value;
    }
    StringBuilder convertedNumberStringBuilder = new StringBuilder();
    while (totalValue >= b2) {
      double remainder = totalValue % b2;
      char convertedRemainder = NumberRepresentation.valueOf((int)remainder);
      convertedNumberStringBuilder.append(convertedRemainder);
      totalValue /= b2;
    }
    if (totalValue >= 1) {
      char convertedRemainder = NumberRepresentation.valueOf((int)totalValue);
      convertedNumberStringBuilder.append(convertedRemainder);
    }
    if (isNegative) {
      convertedNumberStringBuilder.append(NEGATIVE_SIGN);
    }
    StringBuilder invertStringBuilder = new StringBuilder();
    for (int i = convertedNumberStringBuilder.length() - 1; i >= 0; i--) {
      char character = convertedNumberStringBuilder.charAt(i);
      invertStringBuilder.append(character);
    }
    return invertStringBuilder.toString();
  }

  enum NumberRepresentation {
    ZERO('0', 0),
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5',5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('A', 10),
    ELEVEN('B', 11),
    TWELVE('C', 12),
    THIRTEEN('D', 13),
    FOURTEEN('E', 14),
    FIFTEEN('F', 15)
    ;

    private char character;
    private int value;

    NumberRepresentation(char character, int value) {
      this.character = character;
      this.value = value;
    }

    static int valueOf(char character) {
      for (NumberRepresentation numberRepresentation : values()) {
        if (character == numberRepresentation.character) {
          return numberRepresentation.value;
        }
      }
      throw new IllegalArgumentException("This is an invalid character: " + character);
    }

    static char valueOf(int integer) {
      for (NumberRepresentation numberRepresentation : values()) {
        if (integer == numberRepresentation.value) {
          return numberRepresentation.character;
        }
      }
      throw new IllegalArgumentException("This is an invalid number: " + integer);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
