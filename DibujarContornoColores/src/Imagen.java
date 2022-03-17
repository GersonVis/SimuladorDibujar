import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Imagen extends Dibujo{
    BufferedImage biImagen=null, biImagen2, biImaOr;
    int intAl, intAlCon, intAn, intAnCon;
    Container con;
    boolean booPas=true;
    Hashtable<Point, Point> hasPos=new Hashtable<Point, Point>();
	public Imagen(String strRuta, Container con) {
		// TODO Auto-generated constructor stub
		this.con=con;
		try {
			biImagen=ImageIO.read(new File(strRuta));
			biImagen2=ImageIO.read(new File(strRuta));
			biImaOr=ImageIO.read(new File(strRuta));
			booPas=false;
			//mvActAlAn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public Imagen(String strRuta, Container con, int intC) {
		// TODO Auto-generated constructor stub
		this.con=con;
		try {
			biImagen=ImageIO.read(new File(strRuta));
			biImagen2=ImageIO.read(new File(strRuta));
			booPas=true;
			//mvMeReal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public Imagen(BufferedImage biImagen, BufferedImage biImagen2, Container con) {
		// TODO Auto-generated constructor stub
		this.con=con;
		this.biImagen=biImagen;
		this.biImagen2=biImagen2;
		mvActAlAn();
	}
	public Imagen(BufferedImage biImagen, BufferedImage biImagen2, Container con, int intAnCon, int intAlCon) {
		// TODO Auto-generated constructor stub
		this.con=con;
		this.biImagen=biImagen;
		this.biImagen2=biImagen2;
		mvActAlAn();
	}
	protected void mvActAlAn() {
		intAl=biImagen.getHeight();
		intAn=biImagen.getWidth();
		intAlCon=con.getHeight();
		intAnCon=con.getWidth();
	}
	protected void mvMeReal() {
		intAl=biImagen.getHeight();
		intAn=biImagen.getWidth();
		this.intAlCon=biImagen.getHeight();
		this.intAnCon=biImagen.getWidth();
		con.setPreferredSize(new Dimension(intAnCon, intAlCon));
		
	}
	public void mvAddPunto(Point poiPos, int intCol) {
		hasPos.put(poiPos, poiPos);
		//ima2.biImagen2.setRGB(intX, intY, intCol);
	}
	@Override
	public void vmPintar(Graphics g) {//se pinta la imagen en el padre
		if(biImagen!=null) {
			if(booPas) {
				g.drawImage(biImagen2, 0, 0, biImagen2.getWidth(), biImagen2.getHeight(), 0, 0, biImagen2.getWidth(), biImagen2.getHeight(), con);
			}else {
				g.drawImage(biImagen2, 0, 0, con.getWidth(), con.getHeight(), 0, 0, biImagen2.getWidth(), biImagen2.getHeight(), con);
			}
			
		}
		
	}
}
