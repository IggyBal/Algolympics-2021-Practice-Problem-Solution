import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class RectIntersection {
    int findMax(List<Integer> dimensions) {
        int max = dimensions.get(0);
        for (int x : dimensions) if (max < x) max = x;
        return max;
    }

    int findMin(List<Integer> dimensions) {
        int min = dimensions.get(0);
        for (int x : dimensions) if (min > x) min = x;
        return min;
    }

    int findBot(List<Integer> bot) { return findMax(bot); } 
    int findLeft(List<Integer> left) { return findMax(left); }
    int findRight(List<Integer> right) { return findMin(right); }
    int findTop(List<Integer> top) { return findMin(top); }

    List<Integer> getBot(List<Rectangle> rectangles) { return rectangles.stream().map(Rectangle::getBot).collect(Collectors.toList()); }
    List<Integer> getLeft(List<Rectangle> rectangles) { return rectangles.stream().map(Rectangle::getLeft).collect(Collectors.toList()); }
    List<Integer> getRight(List<Rectangle> rectangles) { return rectangles.stream().map(Rectangle::getRight).collect(Collectors.toList()); }
    List<Integer> getTop(List<Rectangle> rectangles) { return rectangles.stream().map(Rectangle::getTop).collect(Collectors.toList()); }

    Rectangle findIntersection(List<Rectangle> rectangles) {
        int left = findLeft(getLeft(rectangles));
        int bot = findBot(getBot(rectangles));
        int right = findRight(getRight(rectangles));
        int top = findTop(getTop(rectangles));

        return new Rectangle(left, bot, right, top);
    }

    int findArea(List<Rectangle> rectangles) { return rectangles.stream().map(Rectangle::getArea).reduce(Integer::sum).get(); }
}
