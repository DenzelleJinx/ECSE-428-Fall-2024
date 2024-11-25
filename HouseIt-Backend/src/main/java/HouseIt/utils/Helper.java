package HouseIt.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Helper class that contains methods for list comparison useful for the override of equals in the models
 * and methods to create models for testing purposes.
 */
public class Helper {

  /**
   /**
   * Compare two lists element-wise
   * @param firstList
   * @param secondList
   * @return boolean indicating equality
   */
  public static boolean compareListsElementWise(List <?> firstList, List <?> secondList) {
    if (firstList.size() != secondList.size()) {
      return false;
    }

    Iterator <?> firstIterator = firstList.iterator();
    Iterator <?> secondIterator = secondList.iterator();

    while (firstIterator.hasNext() && secondIterator.hasNext()) {
      Object firstElement = firstIterator.next();
      Object secondElement = secondIterator.next();

      if (!Objects.equals(firstElement, secondElement)) {
        return false;
      }
    }
    return true;
  }

  /**
   * HelperMethods method to create a list from an iterable.
   */
  public static <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}