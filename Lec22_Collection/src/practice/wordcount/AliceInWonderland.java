package practice.wordcount;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/* 1] 문서(문자열) 안의 단어의 빈도수를 계수해서 출력하기
 * 	- 대소문자 구분하지 않기 :   The 와 the 는 같은 단어
 *  - 2글자 이상만 계수하기
 *  - 구두점/기호 ",.\"\'`!?;:-()" 잘라내기
 *  - 공백 짤라내기
 * ex)
 * 	an : 234
 * 	the : 314
 * ...
 * 
 * hint]
 * 	split("\\s+")  --> String[]   
 * 	--> StringTokenizer  (혹은 정규표현식)
 *  	  --> HashMap<String, Integer>   <단어, 빈도수>  사용
 * ───────────────────────────────────────────────────────────    
 * 2] 빈도수 내림차순으로 정렬하여 출력하기
 * 	ex)
 *	1 the:113개
 *	2 she:95개
 *	3 to:85개
 *	...   
 *
 * hint]
 * 	Comparable<> 이나 Comparator<> 적용
 */

//  : 필요한 객체들 작성
// hint> 빈도수 담을 객체, Comparator<> ..
class WordFreq implements Comparable<WordFreq> {
	String word;
	int freq;

	public WordFreq(String word, int freq) {
		super();
		this.word = word;
		this.freq = freq;
	}

	@Override
	public int compareTo(WordFreq o) {
		if (o.freq < this.freq)
			return -1; // 내가 더 작으면 음수
		if (this.freq < o.freq)
			return 1; // 내가 더 크면 양수

		return 0;

	}

}

class Asc implements Comparator<WordFreq> {

	@Override
	public int compare(WordFreq o1, WordFreq o2) {

		return o1.compareTo(o2);
	}

}

public class AliceInWonderland {
	public AliceInWonderland(String a, int b) {

	}

	public static void main(String[] args) {
		System.out.println("실습: 단어 발생 빈도");
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		String[] words = C.ALICE30.trim().toLowerCase().split("\\s+");

		List<WordFreq> list = new LinkedList<WordFreq>();

		// 발생빈도 작성
		for (String s : words) {
			StringTokenizer tk = new StringTokenizer(s, ",.\"\'`!?;:-()_");
			while (tk.hasMoreTokens()) {
				String word = tk.nextToken();
				if (word.length() == 1)
					continue;
				Integer wordCnt = hmap.get(word);

				if (wordCnt == null) {
					hmap.put(word, 1);
				} else {
					hmap.put(word, wordCnt + 1);
				}
			}
		}

		for (Map.Entry<String, Integer> e : hmap.entrySet()) { // list에 저장
			WordFreq wordFreq = new WordFreq(e.getKey(), e.getValue());
			list.add(wordFreq);
		}

		// Collections.sort(list,new Asc());

		Collections.sort(list); // 정렬

		// 결과 출력
		for (WordFreq a : list) {
			System.out.println(a.word + " : " + a.freq);
		}

	} // end main()

} // end class
