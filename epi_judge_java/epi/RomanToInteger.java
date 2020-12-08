package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")
  public static int romanToInteger(String s) {
    HashMap<Character, Integer> characterValues = new HashMap<>();
    characterValues.put('I', 1);
    characterValues.put('V', 5);
    characterValues.put('X', 10);
    characterValues.put('L', 50);
    characterValues.put('C', 100);
    characterValues.put('D', 500);
    characterValues.put('M', 1000);
    int totalValue = 0;
    int charPosition = 0;
    while (charPosition < s.length()) {
      char character = s.charAt(charPosition);
      char nextCharacter = (charPosition + 1 < s.length()) ? s.charAt(charPosition + 1) : ' ';
      int characterValue = characterValues.get(character);
      switch (character) {
        case 'V':
        case 'L':
        case 'D':
        case 'M':
          totalValue += characterValue;
          break;
        case 'I':
          if (nextCharacter == 'V' || nextCharacter == 'X') {
            totalValue -= characterValue;
          } else {
            totalValue += characterValue;
          }
          break;
        case 'X':
          if (nextCharacter == 'L' || nextCharacter == 'C') {
            totalValue -= characterValue;
          } else {
            totalValue += characterValue;
          }
          break;
        case 'C':
          if (nextCharacter == 'D' || nextCharacter == 'M') {
            totalValue -= characterValue;
          } else {
            totalValue += characterValue;
          }
          break;
        default:
          throw new IllegalArgumentException(character + " is not allowed. string=" + s);
      }
      charPosition++;
    }
    return totalValue;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
