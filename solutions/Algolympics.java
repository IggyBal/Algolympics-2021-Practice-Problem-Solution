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

import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

class Rectangle {
    private int top;
    private int bot;
    private int left;
    private int right;

    private int width;
    private int length;
    private int area;

    public Rectangle(String input) {
        List<Integer> dimensions = Arrays.stream(input.split(" ")) //Split by space
            .map(Integer::parseInt)
            .collect(Collectors.toList()); 
        this.left = dimensions.get(0); //X
        this.bot = dimensions.get(1); //Y
        this.width = dimensions.get(2);
        this.length = dimensions.get(3);
        this.right = this.left + this.width; //X + W
        this.top = this.bot + this.length; //Y + H
        this.area = this.length * this.width;

        //Check
        if (this.area < 0) { throw new NegativeAreaException(this.area + " is negative!"); }
    }

    public Rectangle(int left, int bot, int right, int top) {
        this.left = left; //X
        this.bot = bot; //Y
        this.right = right; //X + W
        this.top = top; //Y + H
        this.width = this.right-this.left;
        this.length = this.top-this.bot;
        this.area = this.length * this.width;

        //Check
        if (this.area < 0) { throw new NegativeAreaException(this.area + " is negative!"); }
    }

    // Getters/Setters
    public int getBot() { return this.bot; }
    public int getLeft() { return this.left; }
    public int getRight() { return this.right; }
    public int getTop() { return this.top; }
    public int getWidth() { return this.width; }
    public int getLength() { return this.length; }
    public int getArea() { return this.area; }

    public String getDimensions() { return this.left + " " + this.bot + " " + this.width + " " + this.length + " "; }
    public String getPoints() { return "(" + this.left + ", " + this.bot + "), (" + this.right + ", " + this.top + ")"; }
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

