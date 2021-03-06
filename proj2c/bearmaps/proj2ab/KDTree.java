package bearmaps.proj2ab;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{

    public Node root;
    public class Node {
        public int flag;
        public Point point;
        public Node part1;
        public Node part2;
        /**
         *  if flag is 0, it's a Node that we look at its left and right part;
         *  if flag is 1, it's a Node that we look at its up and down part.
         */
        public Node(int flag, Point point) {
            this.flag = flag;
            this.point = point;
            this.part1 = null;
            this.part2 = null;
        }
    }

    public KDTree(List<Point> points) {
        root = insertPoints(root, points);
    }

    public Node insertPoints(Node root, List<Point> points) {
        for (Point point: points) {
            root = insertPoint(root, point, 0);
        }
        return root;
    }

    public Node insertPoint(Node node, Point point, int flag) {
        if (node == null) {
            return new Node(flag, point);
        }

        if (node.flag == 0) {
            if (point.getX() < node.point.getX()) {
                node.part1 = insertPoint(node.part1, point, 1-flag);
            } else {
                node.part2 = insertPoint(node.part2, point, 1-flag);
            }
        } else {
            if (point.getY() < node.point.getY()) {
                node.part1 = insertPoint(node.part1, point, 1-flag);
            } else {
                node.part2 = insertPoint(node.part2, point, 1-flag);
            }
        }
        return node;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(root, target, root).point;
    }

    public Node nearest(Node node, Point target, Node best) {
        if (node == null) return best;
        if (Point.distance(node.point, target) < Point.distance(best.point, target)) best = node;
        Node goodSide, badSide;

        double diff = nodeComparator(target, node);
        if (diff < 0) {
            goodSide = node.part1;
            badSide = node.part2;
        } else {
            goodSide = node.part2;
            badSide = node.part1;
        }
        best = nearest(goodSide, target, best);
        //prune
        if (Math.abs(diff) < Math.sqrt(Point.distance(best.point, target))) {
            best = nearest(badSide, target, best);
        }
        return best;
    }

    private double nodeComparator(Point target, Node node) {
        if (node.flag == 0) {
            return target.getX() - node.point.getX();
        } else {
            return target.getY() - node.point.getY();
        }
    }

}
