package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    if (n == 1) {
      return "1";
    }
    String result = "1";
    for (int i = 1; i < n; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      char previousChar = result.charAt(0);
      int countOfCharacter = 1;

      for (int c = 1; c < result.length(); c++) {
        char character = result.charAt(c);
        if (character == previousChar) {
          countOfCharacter++;
          continue;
        }
        stringBuilder.append(countOfCharacter);
        stringBuilder.append(previousChar);
        previousChar = character;
        countOfCharacter = 1;
      }
      stringBuilder.append(countOfCharacter);
      stringBuilder.append(previousChar);
      result = stringBuilder.toString();
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
