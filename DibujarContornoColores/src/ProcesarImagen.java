import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JPanel;

public class ProcesarImagen {
    Recursos recRecursos;
    int intNo=-1, x, y,  DesX=30,  DesY=30, intTole=5;
    Lienzo lie;
    Imagen imaOr;
    BufferedImage biIma, biIma2, biImaOr;
    Hashtable<String, ArrayList<Coordenada>> hasPos;
    ArrayList<Punto> alVisibles=new ArrayList<Punto>();
    ArrayList<Integer> alColores=new ArrayList<Integer>();
    ArrayList<Coordenada> alCoor;
    Hashtable<String, Punto> hasVisibles=new Hashtable<String, Punto>();
    Hashtable<String, Punto> hasTodos=new Hashtable<String, Punto>();
    Hashtable<String, Punto> hasTodosBordes=new Hashtable<String, Punto>();
    Hashtable<String, Punto> hasBordes=new Hashtable<String, Punto>(),
    		hasEzquina=new Hashtable<String, Punto>(),
    	    hasPosibleBor=new Hashtable<String, Punto>();
    ArrayList<Point> alBorde=new ArrayList<Point>();
    ArrayList<Hashtable<String, Punto>> alHasBordes=new ArrayList<Hashtable<String, Punto>>(), alHasBordesOr=new ArrayList<Hashtable<String, Punto>>();
    ProcesarImagen piYo=this;
    Punto punInicio;
    Ventana venTodo;
    int intColor;
    public ProcesarImagen(Recursos recRecursos, Lienzo lie, Imagen imaIma, Ventana venTodo, int intColor) {
    	this.intColor=intColor;
		// TODO Auto-generated constructor stub
		this.recRecursos=recRecursos;
		this.lie=lie;
		this.imaOr=imaIma;
		this.venTodo=venTodo;
		biIma=imaIma.biImagen;
		biIma2=imaIma.biImagen2;
		biImaOr=imaIma.biImaOr;
		alBorde.add(new Point(-1,1));
		alBorde.add(new Point(-1,0));
		alBorde.add(new Point(-1,-1));
		alBorde.add(new Point(0,-1));
		alBorde.add(new Point(1,-1));
		alBorde.add(new Point(1,0));
		alBorde.add(new Point(1,1));
		alBorde.add(new Point(0,1));
		lie.repaint();
		/*System.out.println("Inicial"+alBorde.get(7));
		System.out.println("2.-"+alBorde.get(2)+ " "+"2.-"+alBorde.get(3)+ " "+"4.-"+alBorde.get(4)+ " ");
		System.out.println("1.-"+alBorde.get(1)+ " "+"1.-"+new Point(0,0)+ " "+"5.-"+alBorde.get(5)+ " ");
		System.out.println("0.-"+alBorde.get(0)+ " "+"7.-"+alBorde.get(7)+ " "+"6.-"+alBorde.get(6)+ " ");*/
		
		
		//mvCambio();
		/*new Thread() {
			@Override
			public void run() {
				//mvContorno();
				
				//mvPintarBorde();
				//mvRecta(50);
				//Dibujar dib=new Dibujar(hasEzquina);
				mvVisibles();
				Dibujar dib=new Dibujar(hasVisibles);
				dib.mvIniciarEn(40, 70);
				dib.mvRallitas(0, 5);
				//mvPasar(piYo.venTodo.jpPuntos);
				//piYo.lie.repaint();
				//piYo.venTodo.jpPuntos.setVisible(false);
				//piYo.venTodo.jpPuntos.updateUI();
				//piYo.venTodo.jpPuntos.repaint();
			}
		}.start();*/
		//mvRefresco();
		
	}
    public ProcesarImagen(Recursos recRecursos, Lienzo lie, Imagen imaIma, Ventana venTodo) {
		// TODO Auto-generated constructor stub
		this.recRecursos=recRecursos;
		this.lie=lie;
		this.imaOr=imaIma;
		this.venTodo=venTodo;
		biIma=imaIma.biImagen;
		biIma2=imaIma.biImagen2;
		biImaOr=imaIma.biImaOr;
		alBorde.add(new Point(-1,1));
		alBorde.add(new Point(-1,0));
		alBorde.add(new Point(-1,-1));
		alBorde.add(new Point(0,-1));
		alBorde.add(new Point(1,-1));
		alBorde.add(new Point(1,0));
		alBorde.add(new Point(1,1));
		alBorde.add(new Point(0,1));
		lie.repaint();
		/*System.out.println("Inicial"+alBorde.get(7));
		System.out.println("2.-"+alBorde.get(2)+ " "+"2.-"+alBorde.get(3)+ " "+"4.-"+alBorde.get(4)+ " ");
		System.out.println("1.-"+alBorde.get(1)+ " "+"1.-"+new Point(0,0)+ " "+"5.-"+alBorde.get(5)+ " ");
		System.out.println("0.-"+alBorde.get(0)+ " "+"7.-"+alBorde.get(7)+ " "+"6.-"+alBorde.get(6)+ " ");*/
		
		
		//mvCambio();
		/*new Thread() {
			@Override
			public void run() {
				//mvContorno();
				
				//mvPintarBorde();
				//mvRecta(50);
				//Dibujar dib=new Dibujar(hasEzquina);
				mvVisibles();
				Dibujar dib=new Dibujar(hasVisibles);
				dib.mvIniciarEn(40, 70);
				dib.mvRallitas(0, 5);
				//mvPasar(piYo.venTodo.jpPuntos);
				//piYo.lie.repaint();
				//piYo.venTodo.jpPuntos.setVisible(false);
				//piYo.venTodo.jpPuntos.updateUI();
				//piYo.venTodo.jpPuntos.repaint();
			}
		}.start();*/
		//mvRefresco();
		
	}
    protected void mvColoresDif() {
    	//alColores.add(biIma.getRGB(0, 0));
    	int intCol;
    	boolean booCom=false;
    	Color colOb, colOr;
    	for(int intX=0; intX<biIma.getWidth(); intX++) {
    		for(int intY=0; intY<biIma.getHeight(); intY++) {
    			intCol=biIma.getRGB(intX, intY);
    			colOb=new Color(intCol);
    			if(intCol!=-1) {
    				booCom=true;
        			for(int intP=0; intP<alColores.size(); intP++) {
        				colOr=new Color(alColores.get(intP));
        				//System.out.println(colOb+":"+colOb.getR);
        				if(    colOb.getRed()<=colOr.getRed()+intTole && colOb.getRed()>=colOr.getRed()-intTole
            					&&
            					colOb.getGreen()<=colOr.getGreen()+intTole && colOb.getGreen()>=colOr.getGreen()-intTole
            					&&
            					colOb.getBlue()<=colOr.getBlue()+intTole && colOb.getBlue()>=colOr.getBlue()-intTole)  {
        					
        					booCom=false;
        					break;
        				}
        				
        			}
        			if(booCom==true) {
        				System.out.println("Diferente");
        				alColores.add(intCol);
        			}
    			}
    		}
    	}

    	for(Integer intCo:alColores) {
    		System.out.println(intCo);
    	}
    	System.out.println(alColores.size());
    }
    protected void mvLimpiar(int intTol, String strCol, String strRem, int intR, int intG, int intB) {
    	int intCol=Integer.parseInt(strCol), intRem=Integer.parseInt(strRem, 16), intRGB;
    	System.out.println("Aplicando cambios");
    	Color colOb, colOr=new Color(intCol);
  
    	for(int intX=0; intX<biIma.getWidth(); intX++) {
    		for(int intY=0; intY<biIma.getHeight(); intY++) {
    			intRGB=biIma.getRGB(intX, intY);
    			colOb=new Color(intRGB);
    			if(     colOb.getRed()<=colOr.getRed()+intR && colOr.getRed()>=colOr.getRed()-intR
    					&&
    					colOb.getGreen()<=colOr.getGreen()+intG && colOr.getGreen()>=colOr.getGreen()-intG
    					&&
    					colOb.getBlue()<=colOr.getBlue()+intB && colOr.getBlue()>=colOr.getBlue()-intB) {
    				biIma2.setRGB(intX, intY, intRem);
    			}else {
    				biIma2.setRGB(intX, intY, biIma.getRGB(intX, intY));
    			}
    			
    		}
    	}
    	System.out.println("terminaron cambios");
    }
    protected void mvPosibleBor() {
    	Enumeration<Punto> enu=hasVisibles.elements();
    	boolean boo1=false, boo2=false;
    	int intL=0;
    	while(enu.hasMoreElements()) {
    		Punto pun=enu.nextElement();
    		boo1=false; boo2=false;
    		intL=0;
    		for(int intFor=7; intFor>=0; intFor--) {   		
     		   x=pun.intX+alBorde.get(intFor).x; y=pun.intY+alBorde.get(intFor).y;  
     		   if(x>0 && x<biIma.getWidth() && y>0 && y<biIma.getHeight()) {
     			  if(biIma.getRGB(x, y)==intNo) {
     				  intL++;
             		  boo2=true;
             	   }
     		   }else {
     			   boo1=true;
     		   }
         	  // lie.repaint();
     	   }
    	   if(boo1==false && boo2==true && intL!=8) {
    		  pun.mvCambiarColor(Color.YELLOW.getRGB());
      		  hasPosibleBor.put(pun.toString(), pun);
    	   }
    	}
    }//sliknop snuff
    class Limites{
    	Point poiInicio, poiFinal;
    	int intPos;
    	Limites(Point poiInicio, Point poiFinal, int intPos){
    		this.poiInicio=poiInicio;
    		this.poiFinal=poiFinal;
    		this.intPos=intPos;
    	}
    	@Override
    	public String toString() {
    		return poiInicio.toString()+" "+poiFinal.toString();
    	}
    }
    protected void mvSercaEn() {
    	
    }
    protected void mvSerca() {
    	Hashtable<Integer, Limites> hasLimites=new Hashtable<Integer, Limites>(),
    				hasLimites2=new Hashtable<Integer, Limites>();
    	ArrayList<Limites> alPos=new ArrayList<Limites>();
    	int con=0;
    	for(int intP=0; intP<alHasBordes.size(); intP++) {
    		//if(alHasBordes.get(intP).size()>2) {
    			hasLimites.put(intP, new Limites(alHasBordes.get(intP).get(String.valueOf(0)).poiPos,
        				alHasBordes.get(intP).get(String.valueOf(alHasBordes.get(intP).size()-1)).poiPos, intP));
    			hasLimites2.put(intP, new Limites(alHasBordes.get(intP).get(String.valueOf(0)).poiPos,
        				alHasBordes.get(intP).get(String.valueOf(alHasBordes.get(intP).size()-1)).poiPos, intP));
    		//}
    	}
    	System.out.println(con);
    	for(int intP=0; intP<alHasBordes.size(); intP++) {
    		if(hasLimites.get(intP)!=null) {
    			System.out.println(hasLimites.get(intP).intPos);
    		}
    		
    	}
    	alPos.add(hasLimites.get(0));
    	Limites limF;
    	double douDis, douDes;
    	while(hasLimites.size()>1) {
    		Limites lim=alPos.get(alPos.size()-1);
    		hasLimites.remove(lim.intPos);
    		System.out.println(hasLimites.size());
    		Limites limC=hasLimites.elements().nextElement();   
    		limF=limC;
    		Point poiF=lim.poiFinal;
    		Point poiI=limC.poiInicio;
    		douDis=Math.hypot(poiI.x-poiF.x, poiI.y-poiF.y);
    		for(int intP=0; intP<hasLimites2.size(); intP++) {
    			limC=hasLimites.get(intP);
    			if(limC!=null) {
    				poiI=limC.poiInicio;
    				douDes=Math.hypot(poiI.x-poiF.x, poiI.y-poiF.y);
    				//System.out.println("entro: "+douDes+ " "+douDis);
    				if(douDes<douDis) {
    					//System.out.println("entro: "+douDes);
    					douDis=douDes;
    					limF=limC;
    					if(douDis==1.0) {
    						break;
    					}
    				}
    			}
    		}
    		alPos.add(limF);
    	}
    	alPos.add(hasLimites.elements().nextElement());
    	for(int intP=0; intP<alPos.size(); intP++) {
    		alHasBordesOr.add(alHasBordes.get(alPos.get(intP).intPos));
    		//System.out.println(alPos.get(intP).intPos);
    	}
    	
    	/*Hashtable<String, Punto> hasCo, hasSerca, hasNex, has;
    	double douDis, douDisAn;
    	int intX, intY, intQuitar=0;
    	alHasBordesOr.add(alHasBordes.get(0));
    	while(alHasBordes.size()>1) {
    		System.out.println("tamaño: "+alHasBordes.size());
    		System.out.println("quitar: "+intQuitar);
    		hasCo=alHasBordesOr.get(alHasBordesOr.size()-1);
    		//alHasBordes.remove(intQuitar);
    		if(hasCo.size()>2) {
    			//alHasBordesOr.add(hasCo);
    			intX=hasCo.get(String.valueOf(hasCo.size()-1)).intX;
        		intY=hasCo.get(String.valueOf(hasCo.size()-1)).intY;
        		douDisAn=Math.hypot(intX-alHasBordes.get(0).get(String.valueOf(0)).intX,
        				intY-alHasBordes.get(0).get(String.valueOf(0)).intY);
        		hasSerca=alHasBordes.get(0);
        		intQuitar=0;
        		System.out.println("tam: "+alHasBordes.size());
        		for(int intP=0; intP<alHasBordes.size(); intP++) {
        			System.out.println("intP: "+intP);
        			has=alHasBordes.get(intP);
        			douDis=Math.hypot(intX-has.get(String.valueOf(0)).intX,
            				intY-has.get(String.valueOf(0)).intY);
        			if(douDis<=douDisAn) {
        				intQuitar=intP;
        				douDisAn=douDis;
        				hasSerca=has;
        			}
        		}
        		alHasBordesOr.add(hasSerca);
    		}
    	}
    	hasCo=alHasBordes.get(0);
    	if(hasCo.size()>2) {
    		alHasBordesOr.add(hasCo);
    	}
    	System.out.println("Termino de Acomodar");*/
    	/*
    	double douDis, douDisD;
    	if(alHasBordes.size()>0) {
    		alHasBordes.remove(hasD);
    	}else {
    		return;
    	}
    	
    	System.out.println(hasD.size());
    	if(hasD.size()>2) {
    		System.out.println("entro");
    		alHasBordesOr.add(hasD);
    		if(alHasBordes.size()>0) {
    	     	Enumeration<Punto> enu=hasD.elements();
    	     	Punto pun=null;
    	     	pun=hasD.get(String.valueOf(hasD.size()-1));
    	     	pun.mvCambiarColor(Color.BLUE.getRGB());
    	     	//System.out.println(pun);
    	     	hasCo=alHasBordes.get(0);
    	     	hasSerca=hasCo;
    	     	Point poi=hasCo.elements().nextElement().poiPos;
    	     	douDis=Math.hypot(poi.x-pun.poiPos.x, poi.y-pun.poiPos.y);
    	     	for(int intP=1; intP<alHasBordes.size(); intP++) {
    	     		hasCo=alHasBordes.get(intP);
    	     		pun=hasCo.elements().nextElement();
    	     		douDisD=Math.hypot(poi.x-pun.poiPos.x, poi.y-pun.poiPos.y);
    	     		if(douDisD<douDis && douDisD!=0) {
    	     			hasSerca=hasCo;
    	     			douDis=douDisD;
    	     		}
    	     	}
    	     	//pun.mvCambiarColor(Color.BLUE.getRGB());
    	     	mvSerca(hasSerca);
    	     }	else {
    	    	// alHasBordesOr.add(hasD);
    	    	 return;
    	     }
    	}else {
    		hasCo=alHasBordes.get(0);
            try {
            	mvSerca(hasCo);
            }catch(java.lang.IndexOutOfBoundsException e) {
            	return;
            }
    		
    		return;
    	}
    	System.out.println(alHasBordes.size());
     */
    	
    }
    protected void mvContinuos() {
    	Enumeration<Punto> enu=hasPosibleBor.elements();
    	while(enu.hasMoreElements()) {
    		try {
    			mvContorno(enu.nextElement().poiPos);
    		}catch(java.lang.NullPointerException e) {
    			
    		}
    		
    	}
    }
    protected void mvDibujar(int intEspera) {
    	for(Hashtable<String, Punto> has:alHasBordes) {
    		Dibujar dib=new Dibujar(has);
        	dib.intEspera=intEspera;
    		dib.mvIniciarEn(DesX, DesY);
    		dib.mvRallitas(DesX, DesY);
    	}
    }
    public void mvPuntear(int intEspera) {
    	for(Hashtable<String, Punto> has:alHasBordes) {
    		Dibujar dib=new Dibujar(has);
        	dib.intEspera=intEspera;
    		dib.mvIniciarEn(DesX, DesY);
    		dib.mvPuntear();
    	}
    }
    protected void mvDibPorCol(int intEspera) {
    	Dibujar dib=new Dibujar(alCoor);
    	dib.mvIniciarEn(DesX, DesY);
    	dib.intEspera=intEspera;
    	dib.mvSolCuand();
    }
    protected void mvClicks(int intEspera) {
    	Dibujar dib=new Dibujar(hasVisibles);
    	dib.intEspera=intEspera;
		dib.mvIniciarEn(DesX, DesY);
		dib.mvClick();
    }
    protected void mvPintarBorde() {
    	Enumeration<Punto> enu=hasBordes.elements();
    	while(enu.hasMoreElements()) {
    		enu.nextElement().mvCambiarColor(Color.RED.getRGB());
    	}
    	
    }
    protected void mvSobreSale() {
    	
    }
    protected void mvRecta(int intDis) {
    	int intI=0, intAu=20, intLe=200, intRe=1, intAdd=1;
    	double douAn, douAnN, douDifP=.5, douDif, douDifA=douDifP, douMargen=2;
    	Punto punA, punB, punC;
    	hasEzquina.put("0", hasBordes.get(String.valueOf(0)));
    	mvPintarCuadrado(hasBordes.get(String.valueOf(0)), 3, 3);
        do{
    		punA=hasBordes.get(String.valueOf(intI));
    		
    		punB=hasBordes.get(String.valueOf(intI+intAu));
    		douAn=mvAngulo(punA, punB);
    		//System.out.println(punB.poiPos);
    		Point poiAn=new Point(punA.poiPos.x, punA.poiPos.y);
    		//System.out.println("base1: "+punA.poiPos+" "+punB.poiPos);
    		if(punA.poiPos.x<=punB.poiPos.x && punA.poiPos.y>=punB.poiPos.y) {
    			punA.poiPos=new Point(poiAn.x+(int)(Math.sin(Math.toRadians(180-douAn))*-intLe), 
    					poiAn.y+(int)(Math.cos(Math.toRadians(180-douAn))*-intLe));
    		}else if(punA.poiPos.x>=punB.poiPos.x && punA.poiPos.y<=punB.poiPos.y) {
    			punA.poiPos=new Point(poiAn.x+(int)(Math.sin(Math.toRadians(360-douAn))*-intLe), 
    					poiAn.y+(int)(Math.cos(Math.toRadians(360-douAn))*-intLe));
    		}
    		
    		else {
    			punA.poiPos=new Point(poiAn.x+(int)(Math.sin(Math.toRadians(douAn))*-intLe), 
    					poiAn.y+(int)(Math.cos(Math.toRadians(douAn))*-intLe));
    		}
    		//System.out.println("base: "+punA.poiPos+" "+punB.poiPos);
    		//punA.poiPos=new Point(poiAn.x+(int)(Math.sin(Math.toRadians(douA-douAn))*-20), poiAn.y+(int)(Math.cos(Math.toRadians(douA-douAn))*-20));
    		//mvPintarCuadrado(punB, 3, 3);
    	    if(punB!=null && punA!=null) {
    	    	while(intI<hasBordes.size()) {
    	    		intI++;
    	    		punB=hasBordes.get(String.valueOf(intI));
    	    		if(punB!=null && punA!=null) {
    	    			douAnN=mvAngulo(punA, punB);
        	    		if(Math.abs(douAnN-douAn)>douDifP) {
        	    			//System.out.println(/*douAnN+" "+douAn+" "+*/punA.poiPos+" "+punB.poiPos+" or: "+poiAn );
        	    			
        	    			//System.out.println(punA.poiPos+" "+punB.poiPos+" "+douAn+" "+douAnN);
        	    			intRe=1;
        	    			douDifA=douDifP;
        	    			/*do {
        	    				punC=hasBordes.get(String.valueOf(intI-intRe));
        	    				douAnN=mvAngulo(punA, punC);
        	    				douDif=Math.abs(douAnN-douAn);
        	    				System.out.println(intRe+" "+douDif);
        	    				if(douDif==0) {
        	    					if(punC.poiPos.x!=punB.poiPos.x && punC.poiPos.y!=punB.poiPos.y) {
        	    						intI=intI-intRe;
        	    						punB=punC;
        	    					}
        	    					break;
        	    				}
        	    				intRe++;
        	    			}while(intRe<4);*/
        	    			hasEzquina.put(String.valueOf(intAdd), punB);
        	    			intAdd++;
        	    			mvPintarCuadrado(punB, 3, 3);	
        	    				//System.out.println(+" "+punA.poiPos+" "+punB.toString());
        	    			punA.poiPos=poiAn;
        	    			
        	    			//mvPintarCuadrado(punA, 3, 3, Color.RED, Color.RED);
        	    			break;
        	    		}
    	    		}else {
    	    			break;
    	    		}
    	    	}
    	    }else {
    	    	return;
    	    }
    	} while(punB!=null && punA!=null);
      
    } 
    protected double mvAngulo(Punto a, Punto b) {
		int intLa, intAc=90; double intLc;
		intLa=Math.abs(a.poiPos.x-b.poiPos.x);
		intLc=Math.hypot(a.poiPos.x-b.poiPos.x, a.poiPos.y-b.poiPos.y);
		double op1=(int)(intLa*Math.sin(Math.toRadians(intAc))), douRe;
		douRe=Math.toDegrees(Math.asin(op1/intLc));
		/*a.douIz=douRe;
    	b.douDer=douRe;*/
	//	System.out.println("den"+a.douIz+" "+a.douDer+" "+douRe);
		return douRe;
    }
    protected void mvPintarCuadrado(Punto punAqui, int intAn, int intAl) {
    	Point poiAqui=punAqui.poiPos;
    	Point poiInicio=new Point(poiAqui.x-(intAn/2), poiAqui.y-(intAl/2)) ;
    	for(int intX=0; intX<intAn; intX++) {
    	  for(int intY=0; intY<intAl; intY++) {
    		  
    		  biIma2.setRGB(poiInicio.x+intX, poiInicio.y+intY, Color.BLUE.getRGB());
    	  }
    	}
    	hasVisibles.get(String.valueOf(poiAqui.x+":"+poiAqui.y)).mvCambiarColor(Color.YELLOW.getRGB());
    //	biIma2.setRGB(poiAqui.x, poiAqui.y, Color.YELLOW.getRGB());
    }
    
    protected void mvPintarCuadrado(Punto punAqui, int intAn, int intAl, Color col, Color col2) {
    	Point poiAqui=punAqui.poiPos;
    	Point poiInicio=new Point(poiAqui.x-(intAn/2), poiAqui.y-(intAl/2)) ;
    	for(int intX=0; intX<intAn; intX++) {
    	  for(int intY=0; intY<intAl; intY++) {
    		  biIma2.setRGB(poiInicio.x+intX, poiInicio.y+intY, col.getRGB());
    	  }
    	}
    	hasVisibles.get(String.valueOf(poiAqui.x+":"+poiAqui.y)).mvCambiarColor(col2.getRGB());
    	//biIma2.setRGB(poiAqui.x, poiAqui.y, col2.getRGB());
    }
    protected void mvRefresco() {
    	//System.out.println("Iniciado");
    	new Thread(){
    		@Override
    		public void run() {
    			while(true) {
    				//System.out.println("Refresco");
    				lie.repaint();
    				try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	}.start();
    	
    }
    
    int intPos=0;
    protected void mvVisibles() {
    	Punto pun;
    	for(int intX=0; intX<biIma.getWidth(); intX++) {
    		for(int intY=0; intY<biIma.getHeight(); intY++) {
    			pun=new Punto(intX, intY, biIma, piYo);
    			pun.biIma2=biIma2;
    			if(pun.lonValor!=intNo) {
    				alVisibles.add(pun);
    				hasVisibles.put(new String(intX+":"+intY), pun);
    				//pun.mvCambiarColor(Color.BLUE.getRGB());
    			}
    		}
    	}
    	//mvCambio();
    }
    protected void mvContorno(Point poi) {
    			punInicio=hasVisibles.get(new String(poi.x+":"+poi.y));
    			hasBordes=new Hashtable<String, Punto>();
    			alHasBordes.add(hasBordes);
    			intPos=0;
    			hasBordes.put(String.valueOf(intPos), punInicio);
    			punInicio.strNombre=String.valueOf(intPos);
    			punInicio.intPos=intPos;
    			int x, y;
    			Punto[] punPuntos=mvSiguiente(punInicio);
    			while(punPuntos!=null) {
    				intPos++;
    			 	//System.out.println(punPuntos[0].strNombre+" : "+punPuntos[1].strNombre);
    				if(punPuntos[1].booSoyBorde==false) {
    					 // punPuntos[0].mvCambiarColor(Color.GREEN.getRGB());    					  
    					  punPuntos=mvSiguiente(punPuntos[1]);
    			       }else {
    			    	   if(punPuntos[0].booSoyBorde==false) {
    			    		  // punPuntos[1].mvCambiarColor(Color.GREEN.getRGB());
    			    		 //  hasBordes.put(String.valueOf(intPos), punPuntos[1]);
    			    		   punPuntos=mvSiguiente(punPuntos[0]);
    			    		   
    			    	   }else {    			    		   
    			    		   punPuntos=null;
    			    		  // System.out.println("termino");
    			    		   break;
    			    	   }
    			       }
    			   				
    			}
    			lie.repaint();	
    				
    			
    	/*	}
    	}.start();*/
    }
    protected void mvPausa(int intMili) {
    	lie.repaint();
    	try {
			Thread.sleep(intMili);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    protected void mvComprobar(Color colAnt, Color colNuevo) {
    	
    }
    protected Punto[] mvSiguiente(Punto pun) {  
    	hasBordes.put(String.valueOf(intPos), pun);
    	hasTodosBordes.put(pun.toString(), pun);
    	hasPosibleBor.remove(pun.toString());
    	pun.strNombre=String.valueOf(intPos);
    	pun.intPos=intPos;
    	pun.booSoyBorde=true;
    	pun.mvCambiarColor(Color.RED.getRGB());
    	//pun.mvCambiarColor(Color.BLUE.getRGB());
    	//System.out.println(pun.strNombre);
    	lie.repaint();
       int  intInicio=7, intPosPr=0, intPosSe=0;
       String strP="", strUl="";
       x=pun.intX+alBorde.get(0).x; y=pun.intY+alBorde.get(0).y;
       if(biIma.getRGB(x, y)==intNo) {
    	   for(int intFor=7; intFor>0; intFor--) {
    		   x=pun.intX+alBorde.get(intFor).x; y=pun.intY+alBorde.get(intFor).y;    		   
        	   if(biIma.getRGB(x, y)!=intNo) {
        		   strP=new String(x+":"+y);
        		   intPosPr=intFor;
        		  // biIma2.setRGB(x, y, Color.GREEN.getRGB());
        		   if(intFor==7) {
        			  intInicio=0;
        		   }else {
        			   intInicio=intFor+1;
        		   }
        		   break;
        	   }
        	   
        	  // lie.repaint();
    	   }
       }else {
    	   for(int intFor=1; intFor<8; intFor++) {
    		   x=pun.intX+alBorde.get(intFor).x; y=pun.intY+alBorde.get(intFor).y;
    		  // int intColA=biIma.getRGB(x, y);
    		  // biIma2.setRGB(x, y, Color.RED.getRGB());
    		   //lie.repaint();
    		  // mvPausa(50);
    		   //biIma2.setRGB(x, y, intColA);
    		   
          	   if(biIma.getRGB(x, y)==intNo) {
        		   intInicio=intFor;
        		   x=pun.intX+alBorde.get(intFor-1).x; y=pun.intY+alBorde.get(intFor-1).y;
        		   intPosPr=intFor-1;
        		  // biIma2.setRGB(x, y, Color.GREEN.getRGB());
        		   strP=x+":"+y;
        		   break;
        	   }
          	   
          	  // lie.repaint();
    	   }
       }
       int intDesplazar=0;
       Punto punP=hasVisibles.get(strP);
     //  punP.mvCambiarColor(Color.ORANGE.getRGB());
       for(int intFor=0; intFor<8; intFor++) {
    	   intDesplazar=intInicio+intFor;
    	   if(intDesplazar>7) {
    		   intDesplazar=intDesplazar%8;
    	   }
    	   x=pun.intX+alBorde.get(intDesplazar).x; y=pun.intY+alBorde.get(intDesplazar).y;
    	 //  int intColA=biIma.getRGB(x, y);
    	  // biIma2.setRGB(x, y, Color.RED.getRGB());
		  // mvPausa(50);
		  // biIma2.setRGB(x, y, intColA);
    	   if(biIma.getRGB(x, y)!=intNo) {
    		  // biIma2.setRGB(x, y, Color.YELLOW.getRGB());
    		   intPosSe=intDesplazar;
    		   strUl=new String(x+":"+y);
    		   break;
    	   }
    	   
    	   
       }
        Punto punF=null;
        try {
        	punF=hasVisibles.get(strUl);
            punP.intCuadrante=intPosPr;
            punF.intCuadrante=intPosSe;
        }catch(java.lang.NullPointerException e) {
        	System.out.println(strUl);
        }
        
    //   punF.mvCambiarColor(Color.YELLOW.getRGB());
      // mvPausa(100);
       
       return new Punto[]{punP, punF};
       
    }
    protected void mvAdelante(Punto pun) {
    	int x=pun.intX+alBorde.get(0).x, y=pun.intY+alBorde.get(0).y;
    }
    
    protected void mvCambio() {
    	for(Punto pun:alVisibles) {
    		pun.mvCambiarColor(Color.RED.getRGB());
    	}
    	lie.repaint();
    }
    public void mvPasar(JPanel jpPadre) {
    	Punto pun;
    	int intP=0;
    	pun=hasBordes.get(String.valueOf(intP));
    	while(pun!=null) {
    		pun.mvInterfaz();
    		pun.repaint();
    		jpPadre.add(pun);
    		intP++;
    		pun=hasBordes.get(String.valueOf(intP));
    	}
    		
    	
    	/*Enumeration<Punto> enu=hasBordes.elements();
    	while(enu.hasMoreElements()) {
    		Punto pun=enu.nextElement();
    		pun.mvInterfaz();
    		pun.repaint();
    		jpPadre.add(pun);
    	}*/
    }
}
