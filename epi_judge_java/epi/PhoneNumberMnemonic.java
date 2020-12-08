package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
  public static List<String> phoneMnemonic(String phoneNumber) {
    if (phoneNumber == null || phoneNumber.length() == 0) {
      return new ArrayList<>();
    }
    String substring = phoneNumber.substring(0, phoneNumber.length() - 1);
    List<String> subStringCombination = phoneMnemonic(substring);
    if (subStringCombination.size() == 0) {
      subStringCombination.add("");
    }
    char lastNumberChar = phoneNumber.charAt(phoneNumber.length() - 1);
    int lastNumber = Integer.parseInt(String.valueOf(lastNumberChar));
    char[] lastNumberRepresentations = NumberMapping.resolve(lastNumber);
    List<String> phoneNumberCombination = new ArrayList<>();
    subStringCombination.forEach(subCombination -> {
      for (char lastNumberRepresentation : lastNumberRepresentations) {
        String combination = subCombination.concat(String.valueOf(lastNumberRepresentation));
        phoneNumberCombination.add(combination);
      }
    });
    return phoneNumberCombination;
  }

  enum NumberMapping {
    ONE(1, new char[]{'1'}),
    TWO(2, new char[]{'A', 'B','C'}),
    THREE(3, new char[]{'D', 'E', 'F'}),
    FOUR(4, new char[]{'G', 'H', 'I'}),
    FIVE(5, new char[]{'J', 'K', 'L'}),
    SIX(6, new char[]{'M', 'N', 'O'}),
    SEVEN(7, new char[]{'P', 'Q', 'R', 'S'}),
    EIGHT(8, new char[]{'T', 'U', 'V'}),
    NINE(9, new char[]{'W', 'X', 'Y', 'Z'}),
    ZERO(0, new char[]{'0'});
    private int number;
    private char[] characterRepresentations;

    NumberMapping(int number, char[] characterRepresentations) {
      this.number = number;
      this.characterRepresentations = characterRepresentations;
    }

    static char[] resolve(int number) {
      for (NumberMapping numberMapping : values()) {
        if (numberMapping.number == number) {
          return numberMapping.characterRepresentations;
        }
      }
      return null;
    }
  }

  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
