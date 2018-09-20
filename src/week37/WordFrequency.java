package week37;

import helpers.Kattio;
import week38.RedBlackBST;



/**
 * @author Torstein Strømme
 */
public class WordFrequency {

    /**
     * An immutable word class which holds two values - a string 'word'
     * and an integer 'count.' It is sorted first by count (bigger counts
     * before smaller counts), then ASCII-alphabetically (A before Z before a)
     */
    static final class Word implements Comparable<Word> {
        final String word;
        final int count;

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


    /**
     * Solve the problem https://uib.kattis.com/problems/uib.wordfrequency
     * Demonstrating a "Map" (sometimes called Symbol Table or Dictionary)
     *
     * Task: find k most frequent words of length >= m in a text of length n
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt(), k = io.getInt(), m = io.getInt();

        ISymTable<String, Integer> wCount = new RedBlackBST<>();

        // For every word:
        //     Add 1 to its count
        // Initialise to 0 the first time we see a particular word
        for (int i = 0; i < n; i++) {
            String word = io.getWord();
            if (word.length() < m) continue;
            if (!wCount.containsKey(word)) wCount.put(word, 0);
            wCount.put(word, wCount.get(word) + 1);
        }

        // Create an array with all pairs (word, count)
        Word[] allWords = new Word[wCount.size()];
        int j = 0;
        for (String key : wCount.keys()) {
            allWords[j++] = new Word(key, wCount.get(key));
        }

        // Sort the array, then print the first k entries
        HeapSort.sort(allWords);
        for (int i = 0; i < k; i++) {
            io.printf("%s %d\n", allWords[i].word, allWords[i].count);
        }
        io.close();

    }

}
