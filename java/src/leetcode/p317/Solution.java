package leetcode.p317;


import java.util.*;
import java.util.stream.Collectors;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Point))
            return false;
        if (obj == this)
            return true;
        Point that = (Point) obj;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        //int [][] grid = {{1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};
        System.out.println(sol.shortestDistance(grid));
    }

    boolean isEmpty(Point p, int[][] grid) {
        if (p.x < grid.length && p.y < grid[0].length && p.x >= 0 && p.y >= 0 && grid[p.x][p.y] == 0) return true;
        else return false;
    }

    public int shortestDistance(int[][] grid) {
        //as leetcode likes to have empty / null inputs as test cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;


        //find all the buildings in the Matrix = (O (x * y) where x/y are the dims of the matrix)
        HashSet<Point> buildings = new HashSet<>();
        //go over grid and search for all 1's (building)
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 1)
                    buildings.add(new Point(x, y));
            }
        }
        //if there are no buildings there is no use to continue - return -1
        if (buildings.isEmpty()) return -1;

        //also the case where there is a single building is a corner case as its simple on of its free neighbors if there is any
        if (buildings.size() == 1) {
            Point theOne = buildings.iterator().next();
            if (isEmpty(new Point(theOne.x + 1, theOne.y), grid) ||
                    isEmpty(new Point(theOne.x - 1, theOne.y), grid) ||
                    isEmpty(new Point(theOne.x, theOne.y + 1), grid) ||
                    isEmpty(new Point(theOne.x, theOne.y - 1), grid)
            ) return 1;
            else return -1;
        }

        //for one of them do a bfs to see if it can reach the others (otherwise return -1)
        // O(x * y) -> in the worst case we touch each matrix element once
        if (bfs(buildings.iterator().next(), buildings, grid) == -1) return -1;

        //at this point we established that they are reachable between each other - therefore we switch mode

        //we run a BFS that starts simultaneously at all buildings - reasoning behind is:
        //https://en.wikipedia.org/wiki/Bidirectional_search

        //once they all meet - track back the path(s) witch reduce cost the most
        return multiBfs(buildings, grid);
    }


    /***
     * The idea behind this class is to store the path cost for each building to this point seperate and
     * keep a running sum at the same time. In addition we can always check if all buildings have a path to this
     * point already
     */
    class Visited {
        HashMap<Point, Integer> bfsWeight = new HashMap<>();
        int full = 0;

        Visited(int full) {
            this.full = full;
        }

        Integer sum = 0;

        void addPoint(Point building, Integer weight) {
            if (!bfsWeight.containsKey(building)) {
                bfsWeight.put(building, weight);
                sum = sum + weight;
            }
        }

        boolean isFull() {
            return bfsWeight.size() == full;
        }
    }


    int multiBfs(HashSet<Point> buildings, int[][] grid) {
        HashMap<Point, Visited> visited = new HashMap<>();
        HashMap<Point, Visited> cur = new HashMap<>();
        HashSet<Point> collect = new HashSet<>();

        final Integer nrBuildings = buildings.size();
        for (Point p : buildings) {
            cur.putIfAbsent(p, new Visited(nrBuildings));
            cur.get(p).addPoint(p, 0);
        }
        int steps = 0;
        while (!cur.isEmpty() && collect.isEmpty()) { //the first one should never happen as we should meet before we are not able to go further
            steps++;
            HashMap<Point, Visited> next = new HashMap<>();
            final int cursteps = steps;
            cur.entrySet().stream().forEach(entry -> {
                Point p = entry.getKey();
                Visited v = entry.getValue();
                v.bfsWeight.keySet().stream().forEach(fromBuilding -> {
                    addIfValid(new Point(p.x, p.y - 1), fromBuilding, grid, visited, next, cursteps, nrBuildings);
                    addIfValid(new Point(p.x, p.y + 1), fromBuilding, grid, visited, next, cursteps, nrBuildings);
                    addIfValid(new Point(p.x + 1, p.y), fromBuilding, grid, visited, next, cursteps, nrBuildings);
                    addIfValid(new Point(p.x - 1, p.y), fromBuilding, grid, visited, next, cursteps, nrBuildings);
                });
            });
            //add all cur entries to the visited set
            cur.entrySet().stream().forEach(entry -> {
                Visited sofar = visited.getOrDefault(entry.getKey(), new Visited(nrBuildings));
                entry.getValue().bfsWeight.entrySet().stream().forEach(nentry -> {
                    sofar.addPoint(nentry.getKey(), nentry.getValue());
                });
                visited.put(entry.getKey(), sofar);
            });
            collect = visited.entrySet()
                    .stream()
                    .filter(perPointentry -> perPointentry.getValue().isFull())
                    .map(entrySet -> entrySet.getKey())
                    .collect(Collectors.toCollection(HashSet::new));
            cur = next;
        }

        //at this point collect contains all the points where all building BFS's meet
        //The minimal Distance could be shifted towards a cluster of building as their paths add up
        //Therefore we backtrack from the meeting point -> we only consider points already visted that are neighbors and
        //decrease the total distance

        final HashSet<Point> collected = collect;
        HashMap<Point, Visited> meetingpoints =
                visited.entrySet()
                        .stream()
                        .filter(p -> collected.contains(p.getKey()))
                        .collect(
                                Collectors.toMap(
                                        x -> x.getKey(),
                                        x -> x.getValue(),
                                        (a, b) -> a,
                                        HashMap::new));
        cur = meetingpoints;

        int min = Math.min(getMin(cur), Integer.MAX_VALUE);
        while (!cur.isEmpty()) { //we add to next as long as we have a neighbor that reduces the total cost
            HashMap<Point, Visited> next = new HashMap<>();
            cur.entrySet().stream().forEach(entry -> {
                Point p = entry.getKey();
                addIfValidUpgrade(new Point(p.x, p.y - 1), p, grid, visited, next, nrBuildings);
                addIfValidUpgrade(new Point(p.x, p.y + 1), p, grid, visited, next, nrBuildings);
                addIfValidUpgrade(new Point(p.x + 1, p.y), p, grid, visited, next, nrBuildings);
                addIfValidUpgrade(new Point(p.x - 1, p.y), p, grid, visited, next, nrBuildings);
            });

            //after this next contains all entries that reduce the total sum and have not been visited yet
            min = Math.min(getMin(cur), min);
            cur = next;
        }
        return min;
    }


    int bfs(Point start, HashSet<Point> buildings, int[][] grid) {
        HashSet<Point> visited = new HashSet<>();
        HashSet<Point> cur = new HashSet<>();
        HashSet<Point> buildingsFound = new HashSet<>();
        cur.add(start);

        int steps = 0;

        while (!cur.isEmpty() && buildingsFound.size() < buildings.size()) {
            steps++;
            final int fsteps = steps;
            HashSet<Point> next = new HashSet<>();
            cur.stream().forEach(p -> {
                        addIfValid(new Point(p.x, p.y - 1), grid, next, visited, buildings, buildingsFound, fsteps);
                        addIfValid(new Point(p.x, p.y + 1), grid, next, visited, buildings, buildingsFound, fsteps);
                        addIfValid(new Point(p.x + 1, p.y), grid, next, visited, buildings, buildingsFound, fsteps);
                        addIfValid(new Point(p.x - 1, p.y), grid, next, visited, buildings, buildingsFound, fsteps);
                    }
            );
            //we updated the front to the next one
            visited.addAll(cur);
            cur = next;
        }

        if (buildings.size() == buildingsFound.size()) return steps;
        else return -1;
    }


    void addIfValid(Point p, int[][] grid, HashSet<Point> set, HashSet<Point> visited, HashSet<Point> buildings, HashSet<Point> buildingsFound, Integer steps) {
        if (p.x < grid.length && p.y < grid[0].length && p.x >= 0 && p.y >= 0 && grid[p.x][p.y] != 2) {
            if (grid[p.x][p.y] == 0)
                if (!visited.contains(p))
                    set.add(p);
            if (grid[p.x][p.y] == 1 && steps != 1)
                if (buildings.contains(p))
                    buildingsFound.add(p);
        }
    }


    void addIfValid(Point p, Point fromBuilding, int[][] grid, HashMap<Point, Visited> visitedMap, HashMap<Point, Visited> nextMap, Integer weight, Integer nrBuildings) {
        if (p.x < grid.length && p.y < grid[0].length && p.x >= 0 && p.y >= 0 && grid[p.x][p.y] == 0) { //its a valid grid point
            if (
                    (!visitedMap.containsKey(p)) //no one visited it yet
                            || !visitedMap.get(p).bfsWeight.containsKey(fromBuilding) //we did not visit it yet
            ) { //in both cases - add us to the nextMap with our weight
                nextMap.putIfAbsent(p, new Visited(nrBuildings));
                Visited next = nextMap.get(p);
                next.addPoint(fromBuilding, weight);
            }
        }
    }

    void addIfValidUpgrade(Point p, Point from, int[][] grid, HashMap<Point, Visited> visitedMap, HashMap<Point, Visited> nextMap, Integer nrBuildings) {
        if (p.x < grid.length && p.y < grid[0].length && p.x >= 0 && p.y >= 0 && grid[p.x][p.y] == 0 && visitedMap.containsKey(p) && !visitedMap.get(p).isFull()) { //its a valid grid point and not done yet
            Visited done = visitedMap.get(from);
            Visited toupdate = visitedMap.get(p);
            done.bfsWeight
                    .entrySet()
                    .stream()
                    .forEach(entry -> {
                        if (!toupdate.bfsWeight.containsKey(entry.getKey())) {
                            toupdate.addPoint(entry.getKey(), entry.getValue() + 1);
                        }
                    });
            if (toupdate.sum <= done.sum)
                nextMap.put(p, toupdate);
        }
    }


    int getMin(HashMap<Point, Visited> next) {
        Optional<Visited> reduce = next.values().stream().reduce((a, b) -> {
            if (a.sum < b.sum)
                return a;
            else
                return b;
        });
        Integer integer = reduce.map(x -> x.sum).orElse(Integer.MAX_VALUE);
        return integer;
    }


}