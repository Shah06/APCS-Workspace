import processing.core.PApplet;

public class Line {
	
	private double line[] = new double[4];
	private double intersection[] = new double[2];
	
	public double[] getIntersection() {
		return intersection;
	}

	public Line(double x1, double y1, double x2, double y2) {
		line[0] = x1;
		line[1] = y1;
		line[2] = x2;
		line[3] = y2;
	}
	
	public void setPoint2(double x2, double y2) {
		line[2] = x2;
		line[3] = y2;
	}
	
	public void draw (PApplet window) {
		window.line((float)line[0], (float)line[1], (float)line[2], (float)line[3]);
	}
	
	public boolean intersects(Line other) {
		// points for the two lines
		double x1 = line[0];
		double y1 = line[1];
		double x2 = line[2];
		double y2 = line[3];
		double x3 = other.line[0];
		double y3 = other.line[1];
		double x4 = other.line[2];
		double y4 = other.line[3];
		
		// minimum values
		double min_y1, max_y1;
		double min_y2, max_y2;
		double min_x1, max_x1;
		double min_x2, max_x2;
		
		// slopes and intercepts
		Double m1;
		Double m2;
		double b1;
		double b2;
		
		try {
			intersection[0] = ((x1*y2 - x2*y1)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4))/((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
			intersection[1] = ((x1*y2 - x2*y1)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4))/((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
			
			
			// test for lines with same equation (not parallel)
			m1 = new Double (y2-y1)/(x2-x1);
			m2 = new Double (y4-y3)/(x4-x3);
			//System.out.println("" + m1 + " " + m2);
			b1 = m1*(-x1) + y1;
			b2 = m2*(-x3) + y3;
			
			if (m1.equals(m2)) {
				// special case vertical parallel lines
				if (m1.isInfinite()) {
					if (x1 == x3) {
						return true;
					}
					return false;
				}
				if (b1 == b2) {
					// TODO check for collinear lines that don't intersect
					return true;
				}
			}
			
			
			// test for point that lies on a line
			if ((x1 == x2) && (y1 == y2)) {
				// both are points and are the same
				if ((x3 == x4) && (y3 == y4)) {
					if ((x1 == x3) && (y1 == y3)) {
						return true;
					}
				}
				
				// if point 1 lies on line 2
				if (((m2 * x1) + b2) == y1) {
					return true;
				}
				
			} else if ((x3 == x4) && (y3 == y4)) {
				if (((m1 + x3) + b1) == y3) {
					return true;
				}
				return false;
			}
			
			
			// intersection point must be in between ranges and domains of BOTH lines
			if (line[0] < line[2]) {
				min_x1 = line[0];
				max_x1 = line[2];
			} else {
				min_x1 = line[2];
				max_x1 = line[0];
			}
			
			if (line[1] < line[3]) {
				min_y1 = line[1];
				max_y1 = line[3];
			} else {
				min_y1 = line[3];
				max_y1 = line[1];
			}
			
			if (other.line[0] < other.line[2]) {
				min_x2 = other.line[0];
				max_x2 = other.line[2];
			} else {
				min_x2 = other.line[2];
				max_x2 = other.line[0];
			}
			
			if (other.line[1] < other.line[3]) {
				min_y2 = other.line[1];
				max_y2 = other.line[3];
			} else {
				min_y2 = other.line[3];
				max_y2 = other.line[1];
			}
			
			//System.out.print("intersection[0]: " + intersection[0]);
			//System.out.print("intersection[1]: " + intersection[1] + " ");
			
			// check for intersection
			if ((intersection[0] >= min_x1) && (intersection[0] <= max_x1) && (intersection[1] >= min_y1) && (intersection[1] <= max_y1)) {
				if ((intersection[0] >= min_x2) && (intersection[0] <= max_x2) && (intersection[1] >= min_y2) && (intersection[1] <= max_y2)) {
					//System.out.println("TRUE");
					return true;
				}
			}
			
			//System.out.println("FALSE");
			return false;
			
		} catch (Exception e) {
			System.out.println("the use case wasn't accounted for :(");
			return false;
		}
		
	}
	
}
