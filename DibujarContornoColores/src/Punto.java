
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Punto extends JPanel{
    int intX, intY, intPos, intCuadrante;
    long lonValor;
    BufferedImage biIma, biIma2;
    Boolean booSoyBorde=false;
    String strNombre, strPos;
    Point poiPos;
    double douDer=-1234, douIz=-1234, douAn, douDif;
    Color colOr;
    ProcesarImagen piYo;
    JPanel jpYo=this;
    JLabel jlAngulo=new JLabel("");
	public Punto(int x, int y, BufferedImage biIma, ProcesarImagen pi) {
		// TODO Auto-generated constructor stub
		intX=x;
		this.piYo=pi;
		intY=y;
		poiPos=new Point(x, y);
		strNombre=new String(x+":"+y);
		lonValor=biIma.getRGB(x, y);
		this.biIma=biIma;
		mvEvento();
	}
	protected void mvInterfaz() {
		this.setPreferredSize(new Dimension(200,90));
		this.setLayout(new GridLayout(0,1));
		JLabel jlInfo=new JLabel(String.valueOf("Pos: x:"+poiPos.x+" y:"+poiPos.y+" \nNombre: "+strNombre+" Cuadrante: "+intCuadrante));
		jlInfo.setVerticalAlignment(SwingConstants.CENTER);
		jlInfo.setHorizontalAlignment(SwingConstants.CENTER);
		jlInfo.setFont(new Font("Calibri", 0, 12));
		//jlAngulo=new JLabel();
		jlAngulo.setFont(new Font("Calibri", 0, 12));
		this.add(jlInfo);
		this.add(jlAngulo);
	}
	public void mvCambiarColor(String strCol) {
		colOr=new Color(Integer.parseInt(strCol, 16));
		biIma2.setRGB(intX, intY, colOr.getRGB());
		jpYo.setBackground(new Color(Integer.parseInt(strCol, 16)));
	}
	public void mvCambiarColor(int intCol) {
		colOr=new Color(intCol);
		biIma2.setRGB(intX, intY, intCol);
		jpYo.setBackground(new Color(intCol));
	}
	int intOn=0;
    public void mvEvento() {
    	this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(intOn==0) {
					biIma2.setRGB(intX, intY, Color.GREEN.getRGB());
					jpYo.setBackground(Color.GREEN);
					intOn=1;
				}else {
					biIma2.setRGB(intX, intY, colOr.getRGB());	
					jpYo.setBackground(colOr);
					intOn=0;
				}
				piYo.lie.repaint();
			}
    		
    	});
    }
	@Override
	public String toString() {
		return intX+":"+intY;
	}
}
