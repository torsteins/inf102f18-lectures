package week46;

import helpers.Kattio;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Torstein Strømme
 */
public class BackpackBuddiesPQ {

    private final Kattio io;

    public BackpackBuddiesPQ(Kattio io) {
        this.io = io;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        BackpackBuddiesPQ problem = new BackpackBuddiesPQ(io);
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
        PriorityQueue<VertexArrivalTime> pq = new PriorityQueue<>(n);
        ArrivalTime[] arriveAt = new ArrivalTime[n];
        for (int i = 0; i < n; i++) {
            arriveAt[i] = ArrivalTime.INF;
        }

        pq.add(new VertexArrivalTime(0, new ArrivalTime(0, 0)));
        while (!pq.isEmpty()) {
            VertexArrivalTime vat = pq.poll();
            int here = vat.vertex;
            ArrivalTime now = vat.time;
            if (arriveAt[here].compareTo(now) <= 0) continue; // ignore; this is already reached

            arriveAt[here] = now;
            for (int[] nbr : graph[here]) {
                int there = nbr[0];
                int walkTime = nbr[1];
                ArrivalTime proposedArrivalTime = now.drKnightWalk(walkTime);
                if (proposedArrivalTime.compareTo(arriveAt[there]) < 0)
                    pq.add(new VertexArrivalTime(there, proposedArrivalTime));
            }
        }
        int drKnighHours = arriveAt[n-1].day * 24 + arriveAt[n-1].hour;


        // Step 2: Dijkstra for Mr. Day
        pq = new PriorityQueue<>(n);
        arriveAt = new ArrivalTime[n];
        for (int i = 0; i < n; i++) {
            arriveAt[i] = ArrivalTime.INF;
        }

        pq.add(new VertexArrivalTime(0, new ArrivalTime(0, 0)));
        while (!pq.isEmpty()) {
            VertexArrivalTime vat = pq.poll();
            int here = vat.vertex;
            ArrivalTime now = vat.time;
            if (arriveAt[here].compareTo(now) <= 0) continue; // ignore; this is already reached

            arriveAt[here] = now;
            for (int[] nbr : graph[here]) {
                int there = nbr[0];
                int walkTime = nbr[1];
                ArrivalTime proposedArrivalTime = now.mrDayWalk(walkTime);
                if (proposedArrivalTime.compareTo(arriveAt[there]) < 0)
                    pq.add(new VertexArrivalTime(there, proposedArrivalTime));
            }
        }
        int mrDayHours = arriveAt[n-1].day * 24 + arriveAt[n-1].hour;


        // Step 3: Output arrivalTimeDay - arrivalTimeDrKnight
        io.println(mrDayHours - drKnighHours);
    }

    static class VertexArrivalTime implements Comparable<VertexArrivalTime> {
        private final int vertex;
        private final ArrivalTime time;

        public VertexArrivalTime(int vertex, ArrivalTime time) {
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(VertexArrivalTime that) {
            return this.time.compareTo(that.time);
        }
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

        public ArrivalTime mrDayWalk(int walkTime) {
            if (this.hour + walkTime <= 12) {
                return new ArrivalTime(this.day, this.hour + walkTime);
            }
            return new ArrivalTime(this.day + 1, walkTime);
        }
    }
}
