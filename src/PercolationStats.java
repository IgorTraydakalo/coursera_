import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PercolationStats {
    private Percolation percolation;
    private int size;
    private int trials;

    public PercolationStats(int n, int trials){    // perform trials independent experiments on an n-by-n grid

        if(n < 1 || trials < 1 )
            throw new IllegalArgumentException();
        size = n;
        this.trials = trials;
    }

    public double mean() {                         // sample mean of percolation threshold
        throw new NotImplementedException();
    }

    public double stddev(){                        // sample standard deviation of percolation threshold
        throw new NotImplementedException();
    }

    public double confidenceLo(){                // low  endpoint of 95% confidence interval
        throw new NotImplementedException();
    }

    public double confidenceHi(){                  // high endpoint of 95% confidence interval
        throw new NotImplementedException();
    }

    public static void main(String[] args){        // test client (described below)

    }
}
