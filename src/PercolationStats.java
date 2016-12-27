public class PercolationStats {
	private Percolation percolation;
	private int[] results;
	private int trials;
	private int size;
	private double mean = 0.0;
	private double stddev = 0.0;

	public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
		if (n < 1 || trials < 1)
			throw new IllegalArgumentException();
		this.size = n;
		results = new int[trials];
	}

	public double mean() {                         // sample mean of percolation threshold
		for (int i = 0; i < results.length; i++) {
			mean += results[i];
		}
		mean = mean / results.length;
		return mean;
	}

	public double stddev() {                        // sample standard deviation of percolation threshold
		for (int i = 0; i < results.length; i++) {
			stddev += (mean - results[i]) * (mean - results[i]);
		}
		stddev /= results.length - 1;
		return stddev;
	}

	public double confidenceLo() {                // low  endpoint of 95% confidence interval
		return mean - getShift();
	}

	public double confidenceHi() {                  // high endpoint of 95% confidence interval
		return mean + getShift();
	}

	private void calculate() {
		for (int i = 0; i < results.length; i++) {
			percolation = new Percolation(size);
			do {
				percolation.open();
			}
			while (!percolation.percolates());
		}
	}

	private double getShift() {
		return 1.96 * stddev / results.length;
	}

	public static void main(String[] args) {        // test client (described below)

	}
}
