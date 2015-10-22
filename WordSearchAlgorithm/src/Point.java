
public class Point {
	public int i;
	public int j; // j
	public String direction;
	public String word;
	public Point(int i, int j, int direction, String word) {
		int strlen = word.length() - 1;
		int xchange = 0;
		int ychange = 0;
		this.word = word;
		switch (direction) {
		case 1:	
			ychange += strlen;
			this.direction = "North"; break;
		case 2:
			ychange += strlen;
			xchange -= strlen;
			this.direction = "NorthEast"; break;
		case 3:
			xchange -= strlen;
			this.direction = "East"; break;
		case 4:
			xchange -= strlen;
			ychange -= strlen;
			this.direction = "SouthEast"; break;
		case 5:
			ychange -= strlen;
			this.direction = "South"; break;
		case 6:
			xchange += strlen;
			ychange -= strlen;
			this.direction = "SouthWest"; break;
		case 7:
			xchange += strlen;
			this.direction = "West"; break;
		case 8: 
			xchange += strlen;
			ychange += strlen;
			this.direction = "NorthWest"; break;
		default:
			this.direction = "Not Determined"; break;
		}
		this.i = i + ychange;
		this.j = j + xchange;
	}
}
