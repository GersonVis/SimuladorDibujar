import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ventana extends JFrame{
    Container jpPrincipal=new Container();
    JPanel	  jpConLienzo=new JPanel(), jpBotones=new JPanel(), jpVer=new JPanel();
    Lienzo liePri=new Lienzo();
    Recursos recRecursos;
    JPanel jpPuntos;
    Imagen ima1;
    JButton jbDibujar, jbDibujarTodo, jbImagen, jbVisibles, jbClicks, jbBorde;
    Ventana venYo=this;
    ProcesarImagen piProcesar;
    Point poiInicio;
    Diferentes dif;
    Hashtable<String, Contenedor> hasCon=new Hashtable<String, Contenedor>();
	public Ventana(Recursos recRecursos) {
		this.recRecursos=recRecursos;
		// TODO Auto-generated constructor stub
		vmIniciar();
		vmInterfaz();
		this.setVisible(true);
		//vmProcesar();
	}
	protected void vmIniciar() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 700);
		this.setContentPane(jpPrincipal);
		jpPrincipal.setBackground(Color.RED);
		jpPrincipal.setLayout(new BorderLayout());
	
	}
	protected void vmInterfaz() {
		vmLienzo();
		vmBotones();
	}
	protected void vmBotones() {
		jpBotones.setPreferredSize(new Dimension(300,200));
		jpBotones.setLayout(new BorderLayout());
		jpPrincipal.add(jpBotones, BorderLayout.EAST);
		jpBotones.setBackground(Color.RED);
		vmAddAplicar(jpBotones);
		//vmPuntos();
	}
	protected void vmPuntos() {
		jpPuntos=new JPanel();
		jpPuntos.setLayout(new GridLayout(0,1, 10, 10));
		jpPuntos.setBackground(Color.BLUE);
		JScrollPane jspPuntos=new JScrollPane();
		jspPuntos.setViewportView(jpPuntos);
		jpBotones.add(jspPuntos, BorderLayout.CENTER);
	}
    protected void vmLienzo() {
    	jpConLienzo.setBackground(Color.BLUE);
    	jpConLienzo.setLayout(new BorderLayout());
    	JScrollPane jspLienzo=new JScrollPane();
    
    	jspLienzo.setViewportView(liePri);
    	jpConLienzo.add(jspLienzo, BorderLayout.CENTER);
    	jpConLienzo.setSize(100,100);
    	jpPrincipal.add(jpConLienzo, BorderLayout.CENTER);
    }
    protected void vmProcesar() {
    	//C:\\Users\\Golfa\\Pictures\\Top\\paty/paty1200.png
    	//"C:/Users/Golfa/Pictures/Top/paty/paty1200.png"
    	ima1=new Imagen("C:/Users/Golfa/Downloads/itachiBlancoNegro.png", liePri);
    	liePri.hasDibujos.put("1", ima1);
    	piProcesar=new ProcesarImagen(recRecursos, liePri, ima1, this);
    	//piProcesar.mvPasar(jpPuntos);
    	
    }
    protected void mvEnlazar() {
    	
    }
    protected void vmAddAplicar(JPanel jpPadre) {
    	JPanel jpBotones=new JPanel();
    	jpBotones.setLayout(new GridLayout(0,1));
    	jbVisibles=new JButton("Visibles"); jbImagen=new JButton("Agregar"); jbDibujar=new JButton("Dibujar");
    	jbBorde=new JButton("Limpiar");
    	jbClicks=new JButton("Por color");
    	jbDibujarTodo=new JButton("Dibujar todo");
    	//jbDibujar.setVisible(false);
    	//jbClicks.setVisible(false);
    	JTextArea jta, jtaPosX=new JTextArea("10"), jtaPosY=new JTextArea("150"), jtaTol=new JTextArea("30"), jtaColor=new JTextArea("000000"),
    			jtaCamPor=new JTextArea("1e00ff");
    	jta=new JTextArea("3");
    	Lienzo lieMinia=new Lienzo();
    	JPanel jpRGB=new JPanel(), jpVerRGB=new JPanel();
    	jpVerRGB.setLayout(new GridLayout(1,0, 5, 5));
    	JLabel jlR=new JLabel(), jlG=new JLabel(), jlB=new JLabel();
    	jpVerRGB.setLayout(new GridLayout(1,0, 5, 5));
    	jpVerRGB.add(jlR);
    	jpVerRGB.add(jlG);
    	jpVerRGB.add(jlB);
    	jpRGB.setLayout(new GridLayout(1,0, 5, 5));
    	JTextArea jtaR=new JTextArea("0"), jtaG=new JTextArea("0"), jtaB=new JTextArea("0");
    	jpRGB.add(jtaR);
    	jpRGB.add(jtaG);
    	jpRGB.add(jtaB);
    	JButton jbTodo=new JButton("Hacer todo");
    	jbTodo.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent mv) {
    			
    		}
    	});
    	jbImagen.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent ev) {
    			JFileChooser jfcAr=new JFileChooser("C:\\Users\\Golfa\\Downloads");
    			jfcAr.showOpenDialog(venYo);
    			ima1=new Imagen(jfcAr.getSelectedFile().getPath(), liePri, 1);
    			liePri.hasDibujos.put("1", ima1);
    			liePri.repaint();   			
    			piProcesar=new ProcesarImagen(recRecursos, liePri, ima1, venYo);
    			dif=new Diferentes(recRecursos, liePri, ima1, venYo);
    			jpPadre.updateUI();
    			venYo.setVisible(false);
    			venYo.setVisible(true);
    		}
    	});
    	jbVisibles.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent ev) {
    			System.out.println("E iniciado");
    			new Thread() {
    				@Override
    				public void run() {
    					new Thread() {
    	    				@Override
    	    				public void run() {
    	    				  try {
    	    					System.out.println("Iniciado visibles");
    	    					piProcesar=new ProcesarImagen(recRecursos, liePri, ima1, venYo);
    	    					piProcesar.mvVisibles();
    	      					piProcesar.mvPosibleBor();
    	      					piProcesar.mvContinuos();
    	    				  }catch(java.lang.ArrayIndexOutOfBoundsException e){
    	    					  
    	    				  }
    	    					piProcesar.mvSerca();
    	    					piProcesar.alHasBordes=piProcesar.alHasBordesOr;
    	    	    			jbDibujar.setVisible(true);
    	    	    			jbClicks.setVisible(true);
    	    				}
    	    			}.start();
    				}
    			}.start();
    			
    			
    		}
    	});
    	jbDibujar.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent ev) {
    			new Thread() {
    				@Override
    				public void run() {
    					for(int intT=3; intT>=0; intT--) {
    	    				try {
    	    					Thread.sleep(1000);
    	    				}catch(InterruptedException e) {}
    	    				jbDibujar.setText(String.valueOf(intT));
    	    			}
    					jbDibujar.setText("Dibujar");
    					System.out.println("termino");
    					piProcesar.DesX=Integer.parseInt(jtaPosX.getText());
    					piProcesar.DesY=Integer.parseInt(jtaPosY.getText());
    					piProcesar.mvDibujar(Integer.parseInt(jta.getText()));
    					Enumeration<Contenedor> enu=hasCon.elements();
    					while(enu.hasMoreElements()) {
    						try {
								Thread.sleep(Integer.parseInt(jtaTol.getText())*1000);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    						enu.nextElement().mvDib();
    					}
    					/*for(ProcesarImagen pi:dif.alPI) {
    						pi.DesX=Integer.parseInt(jtaPosX.getText());
        					pi.DesY=Integer.parseInt(jtaPosY.getText());
        					pi.mvDibujar(Integer.parseInt(jta.getText()));
        					try {
								Thread.sleep(Integer.parseInt(jtaR.getText())*1000);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}*/
    				}
    			}.start();
    			
    			
    		}
    		
    	});
    	jbDibujarTodo.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent ev) {
    			new Thread() {
    				@Override
    				public void run() {
    					for(int intT=3; intT>=0; intT--) {
    	    				try {
    	    					Thread.sleep(1000);
    	    				}catch(InterruptedException e) {}
    	    				jbDibujarTodo.setText(String.valueOf(intT));
    	    			}
    					jbDibujarTodo.setText("Dibujar todo");
    					/*for(ProcesarImagen pi:dif.alPI) {
    						pi.DesX=Integer.parseInt(jtaPosX.getText());
        					pi.DesY=Integer.parseInt(jtaPosY.getText());
        					pi.mvDibujar(Integer.parseInt(jta.getText()));
        					try {
								Thread.sleep(Integer.parseInt(jtaR.getText())*1000);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}*/
    					Enumeration<Contenedor> enu=hasCon.elements();
    					while(enu.hasMoreElements()) {
    						Contenedor con=enu.nextElement();
    						con.intEspera=Integer.valueOf(jta.getText());
    						//con.mvDibujar();
    						con.mvComprobar();
    						try {
								Thread.sleep(Integer.parseInt(jtaR.getText())*1000);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    					}
    				}
    			}.start();
    			
    			
    		}
    		
    	});
    	jbClicks.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent ev) {
    			/*new Thread() {
    				@Override
    				public void run() {
    					for(int intT=3; intT>=0; intT--) {
    	    				try {
    	    					Thread.sleep(1000);
    	    				}catch(InterruptedException e) {}
    	    				jbClicks.setText(String.valueOf(intT));
    	    			}
    					jbClicks.setText("Clics");
    					System.out.println("termino");
    					piProcesar.DesX=Integer.parseInt(jtaPosX.getText());
    					piProcesar.DesY=Integer.parseInt(jtaPosY.getText());
    					piProcesar.mvClicks(Integer.parseInt(jta.getText()));
    				}
    			}.start();
    			*/
    			//piProcesar.mvColoresDif();
    			System.out.println("Iniciado");
    			new Thread() {
    				@Override
    				public void run() {
    					dif.mvColoresDif();
    	    			dif.mvLimpiar();
    	    			jpVer.removeAll();
    	    			for(ProcesarImagen pi:dif.alPI) {
    	    				new Thread() {
    	    					@Override
    	    					public void run() {
    	    						Contenedor con=new Contenedor(pi, Integer.parseInt(jtaPosX.getText()), Integer.parseInt(jtaPosY.getText()), jta);
    	    					    con.hasCon=hasCon;
    	    						jpVer.add(con);
    	    					    jpVer.updateUI();
    	    					}
    	    				}.start();
    	    			}
    				}
    			}.start();
    			
    		}
    		
    	});
    	jbBorde.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent me) {
    			System.out.println("Empezo");
				new Thread() {
    				@Override
    				public void run() {    					
    					piProcesar.mvLimpiar(Integer.valueOf(jtaTol.getText()), jtaColor.getText(), jtaCamPor.getText(),
    					Integer.valueOf(jtaR.getText()), Integer.valueOf(jtaG.getText()), Integer.valueOf(jtaB.getText()));    					
    				}
    			}.start();
    			
    		}
    	});
    	System.out.println(Color.RED.getRGB()+" "+Color.BLACK.getRGB());
    	Imagen ima2=new Imagen("Recursos/cuadrado.png", lieMinia);
    	lieMinia.hasDibujos.put("1", ima2);
    	
    	liePri.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent me) {
    			jbBorde.setText(String.valueOf(me.getX()+":"+me.getY()));
    			poiInicio=new Point(me.getX(), me.getY());
    			ima1.biImagen2.setRGB(me.getX(), me.getY(), Color.RED.getRGB());
    			int intCol=0, x=me.getX()-(ima2.biImagen.getWidth()/2), y=me.getY()-(ima2.biImagen.getHeight()/2);
    			
    			for(int intX=0; intX<ima2.biImagen.getWidth(); intX++) {
    				for(int intY=0; intY<ima2.biImagen.getHeight(); intY++) {
    					int intTr=y+10;
    					//System.out.println(intY+y+":"+ima2.biImagen.getHeight());
    	        					
    	            	
    					intCol=ima1.biImagen2.getRGB(x+intX, y+intY);
        				ima2.biImagen2.setRGB(intX, intY, intCol);
        			}
    			}
    			Color colOb=new Color(ima1.biImagen.getRGB(me.getX(), me.getY()));
    			jtaColor.setText(String.valueOf(colOb.getRGB()));
    			jlR.setText(String.valueOf(colOb.getRed()));
    			jlG.setText(String.valueOf(colOb.getGreen()));
    			jlB.setText(String.valueOf(colOb.getBlue()));
    			lieMinia.repaint();
    		}
    	});
    	
    	jpBotones.add(jbImagen);
    	jpBotones.add(jbVisibles);
    	jpBotones.add(jta);
    	jpBotones.add(jtaPosX);
    	jpBotones.add(jtaPosY);
    	jpBotones.add(jbDibujar);
    	jpBotones.add(jbClicks);
    	jpBotones.add(jtaTol);
    	jpBotones.add(jtaColor);
    	jpBotones.add(jtaCamPor);
    	jpBotones.add(jpRGB);
    	jpBotones.add(jpVerRGB);
    	jpBotones.add(jbDibujarTodo);
    	jpBotones.add(jbBorde);
    	//jpBotones.add(lieMinia);
    	JPanel jpInter=new JPanel(), jpBotonesYVer=new JPanel();
    	jpVer.setLayout(new GridLayout(0,1));
    	JScrollPane jsBo=new JScrollPane();
    	jsBo.setViewportView(jpVer);
    	jsBo.setPreferredSize(new Dimension(200,300));
    	jpBotonesYVer.setLayout(new GridLayout(1,0));
    	jpBotonesYVer.add(jpBotones);
    	jpBotonesYVer.add(jsBo);
    	jpInter.setLayout(new BorderLayout());
    	jpInter.add(jpBotonesYVer, BorderLayout.NORTH);
    	jpInter.add(lieMinia, BorderLayout.SOUTH);
    	lieMinia.setBackground(Color.RED);
    	lieMinia.setPreferredSize(new Dimension(100, 300));
    	jpPadre.add(jpInter);
    	lieMinia.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent me) {
    			int x=me.getX()*(lieMinia.getWidth()/ima2.biImagen2.getWidth()), y=me.getY()*(lieMinia.getHeight()/ima2.biImagen2.getHeight());
    			ima2.biImagen2.setRGB(x, y, Color.RED.getRGB());
    			lieMinia.repaint();
    		}
    	});
    }
}
