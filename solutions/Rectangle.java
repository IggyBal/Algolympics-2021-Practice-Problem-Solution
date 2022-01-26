import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class Rectangle {
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
