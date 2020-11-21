package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> array) {
    int lessThanBound = 0;
    int moreThanBound = array.size() - 1;
    int countOfEqual = 0;
    Color pivotColor = array.get(pivotIndex);

    while (lessThanBound + countOfEqual <= moreThanBound) {
      int index = lessThanBound + countOfEqual;
      Color indexColor = array.get(index);
      if (pivotColor.compareTo(indexColor) > 0) {
        if (lessThanBound != index) {
          swap(array, lessThanBound, index);
        }
        lessThanBound++;
      } else if (pivotColor.compareTo(indexColor) < 0) {
        swap(array, moreThanBound, index);
        moreThanBound--;
      } else { // == 0
        countOfEqual++;
      }
    }
    for (int i = 0; i < countOfEqual; i++) {
      array.set(i + lessThanBound, pivotColor);
    }
  }

  private static void swap(List<Color> array, int a, int b) {
    Color temp = array.get(a);
    array.set(a, array.get(b));
    array.set(b, temp);
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + i +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
