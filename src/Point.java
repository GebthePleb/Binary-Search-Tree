/**
 * 
 * @author Gabriel O'Donnell
 * This class exists to test our binary search tree class with a 
 * user defined object. 
 */
public class Point implements Comparable<Point> {
	public int x, y;
	
	Point(int a, int b){
		this.x = a;
		this.y =b;
	}
	Point(final Point obj){
		this.x = obj.x;
		this.y = obj.y;

	}
	
	@Override
	public String toString() {
		return  "("+ x + "," + y +")";
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
            return false;
        }
		
		Point other = (Point) obj;
		if(this.x == other.x && this.y == other.y) {
			return true;
		}
		
		
		return false;
		
	}
	
	@Override
	public int compareTo(Point other) {
		if(this.x > other.x) {
			return 1;
		}
		if(this.x == other.x) {
			if(this.y > other.y) {
				return 1;
			}
			else if(this.y == other.y) {
				return 0;
			}
		}
		else {
			return -1;
		}
		return 0;
	}
}
