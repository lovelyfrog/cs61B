package bearmaps.hw4;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{

    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private Map<Vertex, Double> h;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;
    private double timeSpent;
    private int numStatesExplored;
    private ArrayHeapMinPQ<Vertex> fringe;
    private Vertex start;
    private Vertex end;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.start = start;
        this.end = end;

        Stopwatch sw = new Stopwatch();

        h = new HashMap<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        fringe = new ArrayHeapMinPQ<>();
        solution = new LinkedList<>();
        numStatesExplored = 0;

        distTo.put(start, 0.0);
        h.put(start, input.estimatedDistanceToGoal(start, end));
        fringe.add(start, h.get(start));

        if (start.equals(end)) {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = 0;
            solution.add(start);
        }
        else {
            while (fringe.size() != 0 && !fringe.getSmallest().equals(end) && sw.elapsedTime() < timeout) {
                Vertex cur = fringe.removeSmallest();
                numStatesExplored += 1;
                relax(cur, end, input, h, edgeTo, distTo, fringe);
            }
            if (fringe.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                solutionWeight = 0;
            } else if (fringe.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distTo.get(end);
                solution.add(0, end);
                Vertex tmp = edgeTo.get(end);
                while (!tmp.equals(start)) {
                    solution.add(0, tmp);
                    tmp = edgeTo.get(tmp);
                }
                solution.add(0, start);
            } else {
                solutionWeight = 0;
                outcome = SolverOutcome.TIMEOUT;
            }
        }
        timeSpent = sw.elapsedTime();
    }

    public void relax(Vertex cur, Vertex end, AStarGraph<Vertex> input, Map<Vertex, Double> h,  Map<Vertex, Vertex> edgeTo, Map<Vertex, Double> distTo, ArrayHeapMinPQ<Vertex> fringe) {
        for (WeightedEdge<Vertex> edge: input.neighbors(cur)) {
            Vertex q = edge.to();
            double w = edge.weight();
            h.put(q, input.estimatedDistanceToGoal(q, end));
            if (!distTo.containsKey(q) || distTo.get(cur) + w < distTo.get(q)) {
                distTo.put(q, distTo.get(cur) + w);
                edgeTo.put(q, cur);
                if (fringe.contains(q)) {
                    fringe.changePriority(q, distTo.get(q) + h.get(q));
                } else {
                    fringe.add(q, distTo.get(q) + h.get(q));
                }
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
