import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

/* 
    Input:
    2  <-- Number of rectangles
    3 4 5 6  <-- X, Y, W, H of each rectangle
    1 2 3 5

    1. Convert input into points
        * X and Y describe where each point will lie on Cartesian plane
          W and H describe the size
        * Ex. 0 0 3 3 -> It means that the initial coordinate lies on
                         the origin (0, 0). Adding the W gives (3, 0), 
                         and adding H gives (0, 3). Converting it would
                         give a rectangle whose points are:
                            top: 3
                            bot: 0
                            left: 0
                            right: 3
        * To reduce this, we can say that the initial coordinate describes
          the left and bottom, respectively, and the width and height
          describe the top and right as Y+H and X+W, respectively.
    2. Lmao idk
    3. Get each point of rectangle into an array.
    4. Get the maximum/minimum of the array.
    5. Output will be the points of the intersecting triangle.
    // It is assumed that all triangles will be intersecting
*/

public class Main {

    public static void main(String[] args) {
        RectIntersection algo = new RectIntersection(); //Initialize class methods
        
        //Input
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        rectangles.add(new Rectangle("0 0 3 3"));
        rectangles.add(new Rectangle("1 -1 5 5"));

        Rectangle intersection = algo.findIntersection(rectangles);
        
        // Output
        System.out.println(
            "Intersection Rectangle is: " + intersection.getDimensions() + " // " + intersection.getPoints() + 
            "\nArea of Intersection is: " + intersection.getArea() +
            "\nTotal Area - Area of Intersection is: " + ( (int) algo.findArea(rectangles) - intersection.getArea() )
            );
    }
}
