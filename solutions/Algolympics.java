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

    3. Get each point of rectangle into an array.
    4. Get the maximum/minimum of the array.
    5. Output will be the points of the intersecting triangle.
    // It is assumed that all triangles will be intersecting
*/

import java.util.*;
import java.util.stream.Collectors;

class Rectangle {
    private int top;
    private int bot;
    private int left;
    private int right;

    public Rectangle(String input) {
        List<Integer> dimensions = Arrays.stream(input.split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList()); //Split by space
        this.left = dimensions.get(0); //X
        this.bot = dimensions.get(1); //Y
        this.right = this.left + dimensions.get(2); //X + W
        this.top = this.bot + dimensions.get(3); //Y + H
    }

    public Rectangle(int left, int bot, int right, int top) {
        this.left = left; //X
        this.bot = bot; //Y
        this.right = right; //X + W
        this.top = top; //Y + H
    }

    // Getters/Setters
    public int getBot() { return this.bot; }
    public int getLeft() { return this.left; }
    public int getRight() { return this.right; }
    public int getTop() { return this.top; }

    public String print() { return this.left + " " + this.bot + " " + (this.right-this.left) + " " + (this.top-this.bot) + " "; }
}

class RectIntersection {

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


    List<Integer> getBot(List<Rectangle> rectangles) { 
        return rectangles.stream()
            .map(Rectangle::getBot)
            .collect(Collectors.toList()); 
        }
    List<Integer> getLeft(List<Rectangle> rectangles) { 
        return rectangles.stream()
            .map(Rectangle::getLeft)
            .collect(Collectors.toList()); 
        }
    List<Integer> getRight(List<Rectangle> rectangles) { 
        return rectangles.stream()
        .map(Rectangle::getRight)
        .collect(Collectors.toList()); 
    }
    List<Integer> getTop(List<Rectangle> rectangles) { 
        return rectangles.stream()
        .map(Rectangle::getTop)
        .collect(Collectors.toList()); 
    }

    Rectangle findIntersection(List<Rectangle> rectangles) {

        //Get lists
        List<Integer> bot = getBot(rectangles);
        List<Integer> left = getLeft(rectangles);
        List<Integer> right = getRight(rectangles);
        List<Integer> top = getTop(rectangles);

        //Process lists
        int intBot = findBot(bot);
        int intLeft = findLeft(left);
        int intRight = findRight(right);
        int intTop = findTop(top);

        //Create new Rectangle from intersection
        return new Rectangle(intLeft, intBot, intRight, intTop);
    }
    
    public static void main(String[] args) {
        RectIntersection algo = new RectIntersection(); //Initialize class methods
        //List<Rectangle> rectangles = new ArrayList(Rectangle);
        
        //Input
        Rectangle intersection = algo.findIntersection(
            new ArrayList<Rectangle>(){{
                add(new Rectangle("0 0 3 3"));
                add(new Rectangle("1 -1 5 5"));
            }}
        );

        System.out.println("Intersection Rectangle is: " + intersection.print());
    }
}


