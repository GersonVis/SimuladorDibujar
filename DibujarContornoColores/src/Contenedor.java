import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Contenedor extends JPanel{
	ProcesarImagen pi;
	JButton jbDibujar, jbPintar, jbQuitar, jbAgregar;
	JTextArea jtaR, jtaG, jtaB;
	JPanel jpColor;
	int intX, intY, intEspera;
	JTextArea jtaEspera;
	Hashtable<String, Contenedor> hasCon;
	Contenedor con=this;
	JLabel jlDatos;
	public Contenedor(ProcesarImagen pi, int intX, int intY, JTextArea jtaEspera) {
		// TODO Auto-generated constructor stub
	    this.pi=pi;
	    this.intX=intX;
	    this.intY=intY;
	    this.jtaEspera=jtaEspera;
	    mvInterfaz();
	    mvEventos();
	    jlDatos.setText(String.valueOf(pi.alCoor.size()));
	}
	public void mvInterfaz() {
		jbDibujar=new JButton("Dibujar");
		jpColor=new JPanel();
		jbQuitar=new JButton("Quitar");
		jbPintar=new JButton("Dibujar Color");
		jbAgregar=new JButton("Agregar");
		jlDatos=new JLabel("Datos");
		Color col=new Color(pi.intColor);
		jtaR=new JTextArea(String.valueOf(col.getRed()));
		jtaG=new JTextArea(String.valueOf(col.getGreen()));
		jtaB=new JTextArea(String.valueOf(col.getBlue()));
		JPanel jpRGB=new JPanel();
		jpRGB.setLayout(new GridLayout(1, 0));
		jpRGB.add(jtaR);
		jpRGB.add(jtaG);
		jpRGB.add(jtaB);
		jpColor.setBackground(new Color(pi.intColor));
		this.setPreferredSize(new Dimension(100,150));
		this.setLayout(new GridLayout(0,1));
		this.add(jpColor);
		this.add(jpRGB);
		this.add(jbDibujar);
		this.add(jbPintar);
		this.add(jbQuitar);
		this.add(jbAgregar);
		this.add(jlDatos);
	}
	public void mvDibujar() {
				jbDibujar.setText("Dibujar");
				pi.DesX=intX;
				pi.DesY=intY;
				pi.mvDibujar(intEspera);
	}
    public void mvComprobar() {
    	pi.DesX=intX;
		pi.DesY=intY;
		pi.mvPuntear(intEspera);
 		/*	for(Hashtable<String, Punto> hasD:pi.alHasBordes){
 				Enumeration<Punto> enuD=hasD.elements();
 				while(enuD.hasMoreElements()) {
 					Punto punD=enuD.nextElement();
 					pi.biIma2.setRGB(punD.intX, punD.intY, Color.BLACK.getRGB());
 				}
 			}*/
        
    }
	public void mvEventos() {
		jbDibujar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				mvDibujar();
			}
		});
		jpColor.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent ar) {
				/*for(int intX=0; intX<pi.biIma.getWidth(); intX++) {
	        		for(int intY=0; intY<pi.biIma.getHeight(); intY++) {
	        	       pi.biIma2.setRGB(intX, intY, Color.WHITE.getRGB());		
	        		}
	        	}*/
				mvRepintar();
			}
		});
		jbPintar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
				mvDibPorCol();
			}
		});
		jbQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
				mvQuitar();
			}
		});
		jbAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
				if(jbAgregar.getText().equals("Agregar")) {
					jbAgregar.setText("Quitar");
					hasCon.put(String.valueOf(con.pi.intColor), con);
				}else {
					hasCon.remove(String.valueOf(con.pi.intColor));
					jbAgregar.setText("Agregar");
				}
				
			}
		});
	}
	public void mvRepintar() {
		Color col=new Color(Integer.parseInt(jtaR.getText()), Integer.parseInt(jtaG.getText()), Integer.parseInt(jtaB.getText()));
		for(Coordenada coor:pi.alCoor) {
    		pi.biIma2.setRGB(coor.x, coor.y, col.getRGB());		
    	}
		jpColor.setBackground(col);
		pi.lie.repaint();
	}
	public void mvQuitar() {
		Enumeration<ArrayList<Coordenada>> enu=pi.hasPos.elements();
    	int intPos=0, intColor;
        while(enu.hasMoreElements()) {
        	ArrayList<Coordenada> alCoor=enu.nextElement();
        	if(alCoor!=pi.alCoor) {
        		for(Coordenada coor:alCoor) {
            		
            		pi.biIma.setRGB(coor.x, coor.y, coor.intColor);	
            		pi.biIma2.setRGB(coor.x, coor.y, coor.intColor);
            	}
    		}
       }
		for(Coordenada coor:pi.alCoor) {
    		pi.biIma2.setRGB(coor.x, coor.y, Color.WHITE.getRGB());	
    		pi.biIma.setRGB(coor.x, coor.y, Color.WHITE.getRGB());		
		}
		pi.lie.repaint();
	}
	public void mvDibPorCol() {
		new Thread() {
			@Override
			public void run() {
				System.out.println(pi.alCoor.size());
				for(int intT=3; intT>=0; intT--) {
    				try {
    					Thread.sleep(1000);
    				}catch(InterruptedException e) {}
    				jbPintar.setText(String.valueOf(intT));
    			}
				mvDib();
			}
		}.start();
	}
	public void mvDib() {
		jbPintar.setText("Pintar");
		pi.DesX=intX;
		pi.DesY=intY;
		pi.mvDibPorCol(Integer.parseInt(jtaEspera.getText()));
	}
}
