
public class Coordenada {
    int x, y, intColor;
	public Coordenada(int x, int y, int intColor) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.intColor=intColor;
	}
	@Override
	public String toString() {
		return new String("x: "+x+" y: "+y+" col: "+intColor);
	}
}
