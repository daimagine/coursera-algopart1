public class Percolation {
	private final int N;
	private boolean[] open;

	private WeightedQuickUnionUF quf;
	private WeightedQuickUnionUF bquf;

	/**
	 * create N-by-N grid, with all sites blocked
	 * 
	 * @param N
	 */
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}

		this.N = N;
		open = new boolean[N * N + 2];
		for (int i = 0; i < open.length; i++) {
			open[i] = false;
		}

		quf = new WeightedQuickUnionUF(N * N + 2);
		bquf = new WeightedQuickUnionUF(N * N + 1);
	}

	/**
	 * open site (row i, column j) if it is not open already
	 * 
	 * @param i
	 * @param j
	 */
	public void open(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}

		int p = coorIdx(i, j);
		open[coorIdx(i, j)] = true;

		// top site
		if (i > 1 && isOpen(i - 1, j)) {
			quf.union(p, coorIdx(i - 1, j));
			bquf.union(p, coorIdx(i - 1, j));
		}
		// bottom site
		if (i < N && isOpen(i + 1, j)) {
			quf.union(p, coorIdx(i + 1, j));
			bquf.union(p, coorIdx(i + 1, j));
		}
		// left site
		if (j > 1 && isOpen(i, j - 1)) {
			quf.union(p, coorIdx(i, j - 1));
			bquf.union(p, coorIdx(i, j - 1));
		}
		// right site
		if (j < N && isOpen(i, j + 1)) {
			quf.union(p, coorIdx(i, j + 1));
			bquf.union(p, coorIdx(i, j + 1));
		}

		// connect to virtual top if top row
		if (i == 1) {
			quf.union(p, 0);
			bquf.union(p, 0);
		}

		// connect to virtual bottom if bottom row
		if (i == N) {
			quf.union(p, N * N + 1);
		}
	}

	/**
	 * is site (row i, column j) open?
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}
		return open[coorIdx(i, j)];
	}

	/**
	 * is site (row i, column j) full? full if site connected to virtual top
	 * site
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new IndexOutOfBoundsException();
		}
		return bquf.connected(coorIdx(i, j), 0);
	}

	private int coorIdx(int i, int j) {
		 return (i - 1) * N + j;
	}

	/**
	 * does the system percolate?
	 * 
	 * @return
	 */
	public boolean percolates() {
		return quf.connected(0, N * N + 1);
	}
}
