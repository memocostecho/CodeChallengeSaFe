package guillermorosales.com.codechallenge.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Guillermo Romero on 1/13/16.
 */
public class UtilMap implements Comparator {

    Map map;

    public UtilMap(Map map) {
        this.map = map;
    }

    public static Map sortByValue(Map unsortedMap) {
        Map sortedMap = new TreeMap(new UtilMap(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        if (valueA.equals(valueB))
            return 1;
        else
            return valueA.compareTo(valueB);
    }

}
