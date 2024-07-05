import java.util.Scanner;

class Point { 
    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double distanceTo(Point otherPoint) {
        return Math.sqrt(Math.pow(this.x - otherPoint.x, 2) + Math.pow(this.y - otherPoint.y, 2));
    }
}

class PointDistanceCalculator {
    private Point point1;
    private Point point2;
    
    public void readPointsFromTerminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter coordinates for point 1 (format: x-y): ");
        String[] point1Input = scanner.nextLine().split("-");
        int x1 = Integer.parseInt(point1Input[0]);
        int y1 = Integer.parseInt(point1Input[1]);
        this.point1 = new Point(x1, y1);
        
        System.out.print("Enter coordinates for point 2 (format: x-y): ");
        String[] point2Input = scanner.nextLine().split("-");
        int x2 = Integer.parseInt(point2Input[0]);
        int y2 = Integer.parseInt(point2Input[1]);
        this.point2 = new Point(x2, y2);
        
        scanner.close();
    }
    
    public double computeDistance() {
        if (point1 == null || point2 == null) {
            System.out.println("Please input both points before computing distance.");
            return -1;
        }
        
        return point1.distanceTo(point2);
    }
}

public class Main {
    public static void main(String[] args) {
        PointDistanceCalculator calculator = new PointDistanceCalculator();
        calculator.readPointsFromTerminal();
        double distance = calculator.computeDistance();
        if (distance != -1) {
            System.out.println("Distance between the points: " + distance);
        }
    }
}