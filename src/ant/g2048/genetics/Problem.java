package ant.g2048.genetics;

public interface Problem<C> {

    C generateRandomCandidate();

    int getFitness(C candidate);

}
