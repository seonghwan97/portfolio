public class Circle {
	// Instance variable for the radius of the circle
	protected double radius;
	// Constant for PI
	final double PI = 3.141592;
	
	// Getter method
	public double getRadius() {
		return radius;
	}
	
	// Setter method
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	// Calculate method to get the area of the circle
	public double getArea() {
		return PI * radius * radius;
	}
	
	// toString method to return the area of the circle
	@Override
	public String toString() {
		return "Area of the cirle is: " + getArea() + " square units";
	}
}