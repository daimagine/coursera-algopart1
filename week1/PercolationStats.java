public class PercolationStats {
	private double[] avgResult;
	private int openSite;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException("Illegal argument.");
		avgResult = new double[T];
		for (int i = 0; i < T; i++) {
			avgResult[i] = thresholdSimulation(N);
		}
	}

	public static void main(String[] args) {
		int N = 10;
		int T = 1000;
		if (args.length == 2) {
			N = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
		}
		PercolationStats stats = new PercolationStats(N, T);
		System.out.println("mean \t\t\t = " + stats.mean());
		System.out.println("stddev \t\t\t = " + stats.stddev());
		System.out.println("95% confidence interval  = " + stats.confidenceLo()
				+ ", " + stats.confidenceHi());
	}

	private double thresholdSimulation(int N) {
		Percolation perc = new Percolation(N);
		openSite = 0;
		while (!perc.percolates()) {
			openSite(perc, N);
			openSite++;
		}
		return (double) openSite / (N * N);
	}

	private void openSite(Percolation perc, int N) {
		while (true) {
			int i = StdRandom.uniform(N) + 1;
			int j = StdRandom.uniform(N) + 1;
			if (!perc.isOpen(i, j)) {
				perc.open(i, j);
				break;
			}
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(avgResult);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(avgResult);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(avgResult.length));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(avgResult.length));
	}
}
