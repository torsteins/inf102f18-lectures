package week46;

import helpers.Kattio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Torstein Strømme
 */
public class HiddenWords {

    private final Kattio io;
    private char[][] grid;
    private int cols, rows;
    private boolean[][] visited;

    public HiddenWords(Kattio io) {
        this.io = io;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        HiddenWords problem = new HiddenWords(io);
        problem.solve();
        io.close();
    }

    private void solve() {
        this.rows = io.getInt();
        this.cols = io.getInt();
        this.grid = new char[rows][];
        for (int i = 0; i < rows; i++) {
            grid[i] = io.getWord().toCharArray();
        }

        Node root = new Node();
        this.visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                explore(r, c, 10, root);
            }
        }

        int queries = io.getInt();
        int count = 0;
        for (int i = 0; i < queries; i++) {
            String word = io.getWord();
            if (find(word, 0, root)) {
                count++;
            }
        }

        io.println(count);

    }

    private boolean find(String word, int i, Node node) {
        if (i == word.length()) {
            return true;
        }
        if (node.children[word.charAt(i) - 'A'] == null) {
            return false;
        }

        return find(word, i+1, node.children[word.charAt(i)-'A']);
    }

    private void explore(int r, int c, int maxdepth, Node root) {
        if (maxdepth < 0) return;
        visited[r][c] = true;

        char ch = grid[r][c];
        if (root.children[ch-'A'] == null) {
            root.children[ch-'A'] = new Node();
        }
        Node nextNode = root.children[ch-'A'];

        for (int[] nbr : neighbours(r, c)) {
            int nr = nbr[0];
            int nc = nbr[1];
            if (!visited[nr][nc]) {
                explore(nr, nc, maxdepth-1, nextNode);
            }
        }
        visited[r][c] = false;
    }

    private Iterable<int[]> neighbours(int r, int c) {
        List<int[]> res = new ArrayList<>();
        if (r > 0) res.add(new int[]{r-1, c});
        if (c > 0) res.add(new int[]{r, c-1});
        if (r + 1 < rows) res.add(new int[]{r+1, c});
        if (c + 1 < cols) res.add(new int[]{r, c+1});
        return res;
    }


    static class Node {
        Node[] children = new Node[26];
    }

}
