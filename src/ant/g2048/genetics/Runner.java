package ant.g2048.genetics;

import java.util.ArrayList;
import java.util.List;

public class Runner<C extends Candidate> {

    private static final int GENERATION_SIZE = 10;

    private List<C> generation;

    private final double mutationProbabilty;

    private final Problem<C> problem;

    public Runner(Problem<C> problem, double mutationProbabilty) {
        this.problem = problem;
        this.mutationProbabilty = mutationProbabilty;
        generation = new ArrayList<C>(GENERATION_SIZE);
        while(generation.size() < GENERATION_SIZE) {
            generation.add(problem.generateRandomCandidate());
        }
    }

    public void newGeneration() {
    }


    private C getCandidate(int fitnessFactor, int[] fitnesses) {
        int totalFitness = 0;
        for (int i = 0; i < fitnesses.length; i++) {
            totalFitness += fitnesses[i];
            if (totalFitness > fitnessFactor) {
                return generation.get(i);
            }
        }
        return null;
    }


    private class FitnessWrapper<C> {
        int fitness;
        C candidate;
        public FitnessWrapper(C candidate, int fitness) {
            this.candidate = candidate;
            this.fitness = fitness;
        }
    }
}
