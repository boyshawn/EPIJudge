package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromic {
  @EpiTest(testDataFile = "is_string_palindromic.tsv")

  public static boolean isPalindromic(String s) {
    int leftPosition = 0;
    int rightPosition = s.length() - 1;
    boolean isPalindromic = true;
    while (leftPosition < rightPosition && isPalindromic) {
      char leftCharacter = s.charAt(leftPosition);
      if (!Character.isAlphabetic(leftCharacter)) {
        leftPosition++;
        continue;
      }
      char rightCharacter = s.charAt(rightPosition);
      if (!Character.isAlphabetic(rightCharacter)) {
        rightPosition--;
        continue;
      }
      if (leftCharacter != rightCharacter) {
        isPalindromic = false;
      }
    }
    return isPalindromic;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
