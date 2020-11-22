package epi;

import java.util.List;

public class ArrayListUtils {
  public static String printList(List<?> list) {
    StringBuffer stringBuffer = new StringBuffer();
    list.forEach(element -> stringBuffer.append( element.toString() + ", "));
    return stringBuffer.toString();
  }

  public static String print2dList(List<List<?>> twoDList) {
    StringBuffer stringBuffer = new StringBuffer();
    twoDList.forEach(list -> stringBuffer.append(printList(list) + System.lineSeparator()));
    return stringBuffer.toString();
  }
}
