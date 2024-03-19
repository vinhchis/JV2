package Review01.works;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    void exportData() {
        System.out.println("Retangle: " + width + " _ " + height);
    }

    @Override
    public String toString() {
        return String.format("Retangle: {width = %.2f, height = %.2f}", this.width, this.height);
    }
}
