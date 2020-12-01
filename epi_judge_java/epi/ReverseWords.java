package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;

public class ReverseWords {

  public static void reverseWords(char[] input) {
    final char BLANK_SPACE = ' ';
    StringBuilder stringBuilder = new StringBuilder();
    ArrayList<Integer> blankSpacePositions = new ArrayList<>();
    blankSpacePositions.add(-1);
    for (int i = 0; i < input.length; i++) {
      if (input[i] == BLANK_SPACE) {
        blankSpacePositions.add(i);
      }
    }
    int rightBoundary = input.length;
    for (int index = blankSpacePositions.size() - 1; index >= 0; index--) {
      int leftBoundary = blankSpacePositions.get(index);
      for (int position = leftBoundary + 1; position < rightBoundary; position++) {
        stringBuilder.append(input[position]);
      }
      stringBuilder.append(BLANK_SPACE);
      rightBoundary = leftBoundary;
    }
    for (int i = 0; i < stringBuilder.length() - 1; i++) {
      input[i] = stringBuilder.charAt(i);
    }
    return;
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
