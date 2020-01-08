import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MerlinWordsCount {
	
	public static void main(String[] args) {
		if(args!=null && args.length>0) {
			String string1 = args[0];
			if(string1!=null && !string1.isEmpty()) {
				HashMap<String, Integer> wordsMap = generateWordsCountMap(string1);
				System.out.println("Words Map:"+ wordsMap);
				printUniqueWordsAndGreaterValue(wordsMap);
			} else {
				System.err.println("Palabra ingresada es nula o vacía");
			}
			
		} else {
			System.err.println("Parámetros en null o vacíos");
		}
	}

	private static void printUniqueWordsAndGreaterValue(HashMap<String, Integer> wordsMap) {
		Set<Entry<String, Integer>> entrySet = wordsMap.entrySet();
		Entry<String, Integer> greaterValue = null;
		System.out.println("Palabras únicas:");
		for (Iterator<Entry<String, Integer>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, Integer> entry = iterator.next();
			if(greaterValue == null) {
				greaterValue = entry;
			}
			
			if(entry.getValue()==1) {
				System.out.println(entry.getKey());
			} else {
				if(entry.getValue() >= greaterValue.getValue() ) {
					greaterValue = entry;
				}
			}
		}
		System.out.println("Palabra más repetida:"+ greaterValue.getKey());
	}

	private static HashMap<String,Integer> generateWordsCountMap(String string1) {
		HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
		String[] wordsArray = string1.split(" ");
		System.out.println("String: "+string1);
		for (int i = 0; i < wordsArray.length; i++) {
			String word = wordsArray[i];
			if(wordsMap.containsKey(word)) {
				wordsMap.put(word, wordsMap.get(word)+1);
			} else {
				wordsMap.put(word,1);
			}
		}
		return wordsMap;
	}
}
