package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    final int FIRST_LAST_NUMBER = 1;
    List<List<Integer>> pascalTriangle = new ArrayList<>();
    List<Integer> previousRow = new ArrayList<>();
    for (int row = 0; row < numRows; row++) {
      List<Integer> newRow = new ArrayList<>();
      for (int column = 0; column <= row; column++) {
        if (column == 0 || column == row) {
          newRow.add(FIRST_LAST_NUMBER);
          continue;
        }
        int leftAdjacentValue = (column - 1 < 0) ? 0 : previousRow.get(column - 1);
        int rightAdjacentValue = (column >= previousRow.size()) ? 0 : previousRow.get(column);
        int cellValue = leftAdjacentValue + rightAdjacentValue;
        newRow.add(cellValue);
      }
      pascalTriangle.add(newRow);
      previousRow = newRow;
    }
    return pascalTriangle;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
