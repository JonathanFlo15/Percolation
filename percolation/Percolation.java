import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n; // size of grid
    private boolean[][] grid; // grid in 2d array
    private int top; // virtual top site
    private int bottom; // virtual bottom site
    private int amtOpen; // sites open
    private WeightedQuickUnionUF uf; // union find object

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // exception thrown if n is non positive
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive or 0");
        }
        // sets up n
        this.n = n;
        // creates grid of size n
        grid = new boolean[n][n];
        // top value is 0
        top = n * n;
        // bottom value is grid size plus virtual last site
        bottom = n * n + 1;
        // sets are made to be size of grid plus virtual top and virtual bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
        // open sites are 0 since they start off blocked
        amtOpen = 0;


    }

    // converts 2d index to 1d index
    private int toIndex(int row, int col) {
        return n * row + col;
    }

    // checks if row or column are valid
    private void validIndex(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("out of bounds");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validIndex(row, col); // checks if index is valid
        if (!isOpen(row, col)) {
            grid[row][col] = true; // opens the site at said indices
            amtOpen++; // increases sites open

            // top row
            if (row == 0) {
                uf.union(toIndex(row, col), top);
            }
            // bottom row
            if (row == n - 1) {
                uf.union(toIndex(row, col), bottom);
            }
            // every other row
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(toIndex(row, col), toIndex(row - 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(toIndex(row, col), toIndex(row, col - 1));
            }
            if (row < n - 1 && isOpen(row + 1, col)) {
                uf.union(toIndex(row, col), toIndex(row + 1, col));
            }
            if (col < n - 1 && isOpen(row, col + 1)) {
                uf.union(toIndex(row, col), toIndex(row, col + 1));
            }
        }


    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validIndex(row, col);
        return grid[row][col]; // returns site
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validIndex(row, col);
        // if top connects to input row and column, then it is full
        return uf.find(top) == uf.find(toIndex(row, col));
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return amtOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom); // checks if top and bottom are
        // part of same set, meaning it percolates
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(0, 1);
        System.out.println(percolation.isOpen(0, 1)); // should print true
        System.out.println(percolation.isOpen(2, 2)); // should print false
        System.out.println(percolation.isFull(2, 2)); // should print false
        System.out.println(percolation.numberOfOpenSites()); // should print one
        percolation.open(0, 1);
        percolation.open(1, 1);
        System.out.println(percolation.isFull(1, 1)); // should print true
        percolation.open(2, 1);
        System.out.println(percolation.percolates()); // should print true
    }


}
