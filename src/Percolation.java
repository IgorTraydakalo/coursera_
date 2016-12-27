import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Percolation {

    private WeightedQuickUnionUF quickUnionUF;
    private final boolean[] siteStatus;
    private final int size;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        validate(n);

        quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        siteStatus = new boolean[n * n + 2];
        size = n;

        siteStatus[0] = true;
        siteStatus[n * n + 1] = true;
    }

    public void open(int row, int col) {      // open site (row, col) if it is not open already
        validate(row, col);
        int i = convert(row, col);
        siteStatus[i] = true;
        union(i, getTopSite(row, col));
        union(i, getButtonSite(row, col));
        union(i, getLeftSite(row, col));
        union(i, getRightSite(row, col));
    }

    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        validate(row, col);
        return siteStatus[convert(row, col)];
    }

    public boolean isFull(int row, int col){  // is site (row, col) full?
        validate(row, col);
        return percolates() && quickUnionUF.connected(0, convert(row, col));
    }

    public boolean percolates() {              // does the system percolate?
        return quickUnionUF.connected(0, size * size + 1);
    }

    private void union(int i, int j){
        if(j != -1 && siteStatus[j])
            quickUnionUF.union(i, j);
    }

    private int convert(int i, int j) {
        return (i - 1) * size + j;
    }

    private int getTopSite(int i, int j) {
        if (i == 1) {
            return 0;
        }
        return convert(i - 1, j);
    }

    private int getButtonSite(int i, int j) {
        if (i == size) {
            return size * size + 1;
        }
        return convert(i + 1, j);
    }

    private int getLeftSite(int i, int j) {
        if (j - 1 < 1) {
            return -1;
        }
        return convert(i, j - 1);
    }

    private int getRightSite(int i, int j) {
        if (j + 1 > size) {
            return -1;
        }
        return convert(i, j + 1);
    }

    private void validate(int row, int col){
        if(row > size || row < 1
                || col > size || col < 1)
            throw new IndexOutOfBoundsException();
    }
    private void validate(int n){
        if( n < 1)
            throw new IllegalArgumentException();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input10.txt"));
        int n = scanner.nextInt();

        Percolation p = new Percolation(n);

        while(scanner.hasNextInt()){
            int row = scanner.nextInt();
            int col =  scanner.nextInt();
            p.open(row, col);
        }

        p.sout();
        System.out.println(p.percolates());
    }


    public void sout(){
        for(int i = 1; i <= size * size; i ++){
            System.out.print(siteStatus[i] ? "*" : "-");
            if(i%size == 0) System.out.println();
        }
    }
}