
public class Point {
	public int i;
	public int j;
	public String direction;
	public String word;
	public Point(int i, int j, int direction, String word) {
		this.i = i;
		this.j = j;
		this.word = word;
		switch (direction) {
		case 1:	
			this.direction = "North"; break;
		case 2:
			this.direction = "NorthEast"; break;
		case 3:
			this.direction = "East"; break;
		case 4:
			this.direction = "SouthEast"; break;
		case 5:
			this.direction = "South"; break;
		case 6:
			this.direction = "SouthWest"; break;
		case 7:
			this.direction = "West"; break;
		case 8: 
			this.direction = "NorthWest"; break;
		default:
			this.direction = "Not Determined"; break;
		}
	}
}
