package evoblackjack;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

// The fitness function.
public class BlackjackStrategyFitnessFunction implements FitnessEvaluator<BlackjackStrategy>
{

	BlackjackGameSimulator sim;
	int runs;
	
	// Create a new fitness function based on a number of runs.
	public BlackjackStrategyFitnessFunction(int noRuns)
	{
		sim = new BlackjackGameSimulator();
		runs = noRuns;
	}

	public double getFitness(BlackjackStrategy candidate,
			List<? extends BlackjackStrategy> list) {
			
		// Creates a winning percentage. For example, if you start with $100 and end with $110
		// this function will return 1.1(double)
		return (2*runs + sim.simulateNumberOfHands(runs, candidate))/(2.0*runs);
	}

	public boolean isNatural() {
		// Tells the algorithm that a higher number is better.
		return true;
	}
	
	
	
}
