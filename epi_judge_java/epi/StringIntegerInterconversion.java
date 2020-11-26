package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    StringBuilder integerStringBuilder = new StringBuilder();
    if (x == 0) {
      return "0";
    }
    if (x == Integer.MIN_VALUE) {
      return "-2147483648";
    }
    boolean isNegative = (x < 0);
    if (isNegative) {
      x *= -1;
    }
    while (x > 0) {
      int reminder = x % 10;
      integerStringBuilder.append(reminder);
      x /= 10;
    }
    if (isNegative) {
      integerStringBuilder.append('-');
    }
    String integer = reverseStringBuilder(integerStringBuilder);
    return integer;
  }

  private static String reverseStringBuilder(StringBuilder integerStringBuilder) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = integerStringBuilder.length() - 1; i >= 0; i--) {
      stringBuilder.append(integerStringBuilder.charAt(i));
    }
    return stringBuilder.toString();
  }

  public static int stringToInt(String s) {
    final int CODE_POINT_OFFSET = 48;
    char firstCharacter = s.charAt(0);
    boolean isFirstCharacterDigit = Character.isDigit(firstCharacter);
    boolean isNegative = false;
    if (!isFirstCharacterDigit) {
      isNegative = checkForNegative(firstCharacter);
    }
    int parsingStartPoint = isFirstCharacterDigit ? 0 : 1;
    int integer = 0;
    int power = 0;
    for (int i = s.length() - 1; i >= parsingStartPoint; i--) {
      int codeValue = s.codePointAt(i);
      int adjustedCodeValue = codeValue - CODE_POINT_OFFSET;
      double value = adjustedCodeValue * Math.pow(10, power++);
      integer += (int) value;
    }
    integer = (isNegative) ? -1 * integer : integer;
    return integer;
  }

  private static boolean checkForNegative(char firstCharacter) {
    return firstCharacter == '-';
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
