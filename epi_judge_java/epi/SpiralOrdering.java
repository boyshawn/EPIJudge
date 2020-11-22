package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Collections;
import java.util.List;
public class SpiralOrdering {
  private static int firstRow;
  private static int lastRow;
  private static int firstCol;
  private static int lastCol;

  @EpiTest(testDataFile = "spiral_ordering.tsv")
  public static List<Integer>  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    if (squareMatrix == null || squareMatrix.size() == 0) {
      return Collections.emptyList();
    }
    int[][] grid = convertTo2DArray(squareMatrix);
    ArrayList<Integer> result = new ArrayList<>();
    firstRow = 0;
    firstCol = 0;
    lastRow = grid.length - 1;
    lastCol = grid.length - 1;
    while(firstRow <= lastRow || firstCol <= lastCol) {
      fillUpArray(grid, result, Direction.RIGHT);
      fillUpArray(grid, result, Direction.DOWN);
      fillUpArray(grid, result, Direction.LEFT);
      fillUpArray(grid, result, Direction.UP);
    }
    return result;
  }

  private enum Direction {
    LEFT, RIGHT, UP, DOWN;
  }

  private static void fillUpArray(int[][] grid, ArrayList<Integer> result, Direction direction) {
    switch (direction) {
      case RIGHT:
        if (firstRow > lastRow) {
          break;
        }
        for (int i = firstCol; i <= lastCol; i++) {
          result.add(grid[firstRow][i]);
        }
        firstRow++;
        break;
      case DOWN:
        if (lastCol < firstCol) {
          break;
        }
        for (int i = firstRow; i <= lastRow; i++) {
          result.add(grid[i][lastCol]);
        }
        lastCol--;
        break;
      case LEFT:
        if (lastRow < firstRow) {
          break;
        }
        for (int i = lastCol; i >= firstCol; i--) {
          result.add(grid[lastRow][i]);
        }
        lastRow--;
        break;
      case UP:
        if (firstCol > lastCol) {
          break;
        }
        for (int i = lastRow; i >= firstRow; i--) {
          result.add(grid[i][firstCol]);
        }
        firstCol++;
        break;
    }
  }

  private static int[][] convertTo2DArray(List<List<Integer>> squareMatrix) {
    int[][] grid = new int[squareMatrix.size()][squareMatrix.size()];
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid.length; col++) {
        grid[row][col] = squareMatrix.get(row).get(col);
      }
    }
    return grid;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
