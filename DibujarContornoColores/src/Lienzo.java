import java.awt.Container;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Hashtable;

public class Lienzo extends Container{
    Hashtable<String, Dibujo> hasDibujos=new Hashtable<String, Dibujo>();
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(hasDibujos!=null) {
			Enumeration<Dibujo> enuDibujos=hasDibujos.elements();
			while(enuDibujos.hasMoreElements()) {
				enuDibujos.nextElement().vmPintar(g);
			}
		}
		
		
	}
      
}
