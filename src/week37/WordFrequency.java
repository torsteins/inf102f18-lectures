package week37;

import helpers.Kattio;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Torstein Strømme
 */
public class WordFrequency {

    static class Word implements Comparable<Word> {
        String word;
        int count;

        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Word that) {
            if (this.count > that.count) return -1;
            if (this.count < that.count) return 1;
            return this.word.compareTo(that.word);
        }
    }


    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt(), k = io.getInt(), m = io.getInt();

        Map<String, Integer> wCount = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String word = io.getWord();
            if (word.length() < m) continue;
            if (!wCount.containsKey(word)) wCount.put(word, 0);
            wCount.put(word, wCount.get(word) + 1);
        }

        Word[] allWords = new Word[wCount.size()];
        int j = 0;
        for (String key : wCount.keySet()) {
            allWords[j++] = new Word(key, wCount.get(key));
        }

        HeapSort.sort(allWords);
        for (int i = 0; i < k; i++) {
            io.printf("%s %d\n", allWords[i].word, allWords[i].count);
        }
        io.close();

    }

}
