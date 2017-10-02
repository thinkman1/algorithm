package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands {

    /**
     * with additional vistited matrix
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count ++;
                    merge(grid, i, j, visited);
                }
            }
        }
        return count;
    }

    private void merge(char[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        if (i<0 || j<0 || i>=m || j>=n ) {
            return;
        }
        if (grid[i][j] == '1' && !visited[i][j]) {
            visited[i][j] = true;
            int[] dx = {0, 1, -1, 0};
            int[] dy = {1, 0, 0, -1};
            for (int k=0; k<4; k++) {
                int newx = dx[k] + i;
                int newy = dy[k] + j;
                merge(grid, newx, newy, visited);
            }
        }

    }

    /**
     * mark the given grid[][]
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1') {
                    count ++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }

    private void merge(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i<0 || j<0 || i>=m || j>=n || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 'X';

        merge(grid, i+1, j);
        merge(grid, i-1, j);
        merge(grid, i, j+1);
        merge(grid, i, j-1);
    }

    /**
     *  the identifier of an island: int id = n * x + y;
     *  If roots[c] = p means the parent of node c is p, we can climb up the parent chain to find out the identifier of an island, i.e., which island this point belongs to:
     */
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    //O(N * 4 * logN)
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(m <= 0 || n <= 0) return result;

        int count = 0;                      // number of islands
        int[] roots = new int[m * n];       // one island = one tree
        Arrays.fill(roots, -1);

        for(int[] p : positions) {
            int currId = n * p[0] + p[1];     // assume new point is isolated island
            roots[currId] = currId;             // add new island
            count++;

            for(int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;

                int rootId = findIslandRootId(roots, nb);
                if(currId != rootId) {        // if neighbor is in another island
                    roots[currId] = rootId;   // union two islands
                    currId = rootId;          // current tree root = joined tree root
                    count--;
                }
            }

            result.add(count);
        }
        return result;
    }

    //climb up the tree to find root, O(logN)
    private int findIslandRootId(int[] roots, int id) {
        while(id != roots[id]) {
            int parentId = roots[id];
            int parentparentId = roots[parentId];
            roots[id] = parentparentId;   // Path Compression, since they're in same island, can have same parent
            id = parentparentId;
        }
        return id;
    }

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();
        int[][] positions = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        List<Integer> res = solution.numIslands2(3, 3, positions);

        System.out.println(res);
    }

    /**
     * 463. Island Perimeter
     * Solution 1: when we meet an island, perimeter + 4,
     *             if the island has right neighbour, perimeter -2,
     *             if the island has down neighbour, perimeter -2.
     *
     * Solution 2: perimeter of each island = (4 - neighbours) up,down,left,right
     * @param grid
     * @return
     */
    public int islandPerimeterBest(int[][] grid) {
        int perimeter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i+1 < grid.length && grid[i + 1][j] == 1) perimeter -= 2;// count down neighbours
                    if (j+1 < grid[i].length && grid[i][j + 1] == 1) perimeter -= 2; // count right neighbours
                }
            }
        }

        return perimeter;
    }

    public int islandPerimeterSecond(int[][] grid) {
        int perimeter = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int neighbors = getNeighbors(i, j, grid);
                    perimeter += (4 - neighbors);
                }
            }
        }
        return perimeter;
    }

    private int getNeighbors(int i, int j, int[][] grid) {
        int res = 0;
        //up
        if ((i-1 >= 0) &&grid[i-1][j] == 1) {
            res++;
        }
        //down
        if ((i+1 < grid.length) &&grid[i+1][j] == 1) {
            res++;
        }
        //left
        if ((j-1 >= 0) &&grid[i][j-1] == 1) {
            res++;
        }

        //right
        if ((j+1 < grid[0].length) &&grid[i][j+1] == 1) {
            res++;
        }

        return res;
    }
}
