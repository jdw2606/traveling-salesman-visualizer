
public class City {
	int x;
	int y;
	public City(int x, int y) {
		this.x = (int) (Math.random() * (x - 100)) + 50 ;
		this.y = (int) (Math.random() * (y - 100)) + 50 ;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
