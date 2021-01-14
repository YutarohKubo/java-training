package ch09.ex08;

public class Point implements Comparable<Point>{

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        int diff = (x < o.x) ? -1 : ((x == o.x) ? 0 : 1);
        if (diff != 0) {
            return diff;
        }
        return (y < o.y) ? -1 : ((y == o.y) ? 0 : 1);
    }
}
