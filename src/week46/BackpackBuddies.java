package week46;

import helpers.Kattio;
import week42.IndexMinPQ;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Torstein Strømme
 */
@SuppressWarnings("unchecked")
public class BackpackBuddies {

    private final Kattio io;

    public BackpackBuddies(Kattio io) {
        this.io = io;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        BackpackBuddies problem = new BackpackBuddies(io);
        problem.solve();
        io.close();
    }

    private void solve() {
        int n = io.getInt();
        int m = io.getInt();

        // Step 0: Read input graph
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            int u = io.getInt();
            int v = io.getInt();
            int w = io.getInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        // Step 1: Dijkstra for Dr. Knight
        IndexMinPQ<ArrivalTime> pq = new IndexMinPQ<>(n);
        ArrivalTime[] arriveAt = new ArrivalTime[n];
        for (int i = 0; i < n; i++) {
            pq.add(i, ArrivalTime.INF);
            arriveAt[i] = ArrivalTime.INF;
        }

        pq.changeKey(0, new ArrivalTime(0, 0));
        while (!pq.isEmpty()) {
            int here = pq.peek();
            ArrivalTime now = pq.getKey(here);
            pq.poll();

            arriveAt[here] = now;
            for (int[] nbr : graph[here]) {
                int there = nbr[0];
                int walkTime = nbr[1];
                ArrivalTime proposedArrivalTime = now.drKnightWalk(walkTime);
                if (pq.contains(there) && pq.getKey(there).compareTo(proposedArrivalTime) > 0) {
                    pq.changeKey(there, proposedArrivalTime);
                }
            }
        }
        int drKnighHours = arriveAt[n-1].day * 24 + arriveAt[n-1].hour;


        // Step 2: Dijkstra for Mr. Day
        pq = new IndexMinPQ<>(n);
        arriveAt = new ArrivalTime[n];
        for (int i = 0; i < n; i++) {
            pq.add(i, ArrivalTime.INF);
            arriveAt[i] = ArrivalTime.INF;
        }

        pq.changeKey(0, new ArrivalTime(0, 0));
        while (!pq.isEmpty()) {
            int here = pq.peek();
            ArrivalTime now = pq.getKey(here);
            pq.poll();

            arriveAt[here] = now;
            for (int[] nbr : graph[here]) {
                int there = nbr[0];
                int walkTime = nbr[1];
                ArrivalTime proposedArrivalTime = now.drDayWalk(walkTime);
                if (pq.contains(there) && pq.getKey(there).compareTo(proposedArrivalTime) > 0) {
                    pq.changeKey(there, proposedArrivalTime);
                }
            }
        }
        int mrDayHours = arriveAt[n-1].day * 24 + arriveAt[n-1].hour;


        // Step 3: Output arrivalTimeDay - arrivalTimeDrKnight
        io.println(mrDayHours - drKnighHours);
    }


    static class ArrivalTime implements Comparable<ArrivalTime> {
        static final ArrivalTime INF = new ArrivalTime(Integer.MAX_VALUE, 0);

        private final int day, hour;

        public ArrivalTime(int day, int hour) {
            this.day = day;
            this.hour = hour;
        }

        @Override
        public int compareTo(ArrivalTime that) {
            if (this.day < that.day) return -1;
            if (this.day > that.day) return 1;
            return new Integer(this.hour).compareTo(that.hour);
        }

        public ArrivalTime drKnightWalk(int walkTime) {
            if (this.hour + walkTime <= 12) {
                return new ArrivalTime(this.day, this.hour + walkTime);
            }
            return new ArrivalTime(this.day + 1, this.hour + walkTime - 12);
        }

        public ArrivalTime drDayWalk(int walkTime) {
            if (this.hour + walkTime <= 12) {
                return new ArrivalTime(this.day, this.hour + walkTime);
            }
            return new ArrivalTime(this.day + 1, walkTime);
        }
    }

}
