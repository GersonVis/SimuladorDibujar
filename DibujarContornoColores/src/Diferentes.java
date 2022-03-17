import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Diferentes {

    ArrayList<Integer> alColores=new ArrayList<Integer>();
    Hashtable<String, ArrayList<Coordenada>> hasPos=new Hashtable<String, ArrayList<Coordenada>>();
    int intTole=20;
    BufferedImage biIma, biIma2, biImaOr;
    Recursos recRecursos; 
    Lienzo lie; 
    Imagen imaIma; 
    Ventana venTodo;
    ArrayList<ProcesarImagen> alPI=new ArrayList<ProcesarImagen>();
	public Diferentes(Recursos recRecursos, Lienzo lie, Imagen imaIma, Ventana venTodo) {
		// TODO Auto-generated constructor stub
		biIma=imaIma.biImagen;
		biIma2=imaIma.biImagen2;
		biImaOr=imaIma.biImaOr;
		this.recRecursos=recRecursos;
		this.lie=lie;
		this.imaIma=imaIma;
		this.venTodo=venTodo;
	}
    protected void mvColoresDif() {
    	//alColores.add(biIma.getRGB(0, 0));
    	int intCol;
    	int val=0; 
    	boolean booCom=false;
    	Color colOb, colOr;
    	for(int intX=0; intX<biIma.getWidth(); intX++) {
    		for(int intY=0; intY<biIma.getHeight(); intY++) {
    			intCol=biIma.getRGB(intX, intY);
    			colOb=new Color(intCol);
    			if(intCol!=-1) {
    				booCom=true;
    				int pos=-1;
        			for(int intP=0; intP<alColores.size(); intP++) {
        				colOr=new Color(alColores.get(intP));
        				//System.out.println(colOb+":"+colOb.getR);
        				if(    colOb.getRed()<=colOr.getRed()+intTole && colOb.getRed()>=colOr.getRed()-intTole
            					&&
            					colOb.getGreen()<=colOr.getGreen()+intTole && colOb.getGreen()>=colOr.getGreen()-intTole
            					&&
            					colOb.getBlue()<=colOr.getBlue()+intTole && colOb.getBlue()>=colOr.getBlue()-intTole)  {
        					pos=intP;
        					booCom=false;
        					break;
        				}
        				
        			}
        			
        			if(booCom==true) {
        				//System.out.println("Diferente");
        				hasPos.put(String.valueOf(intCol), new ArrayList<Coordenada>());
        				hasPos.get(String.valueOf(intCol)).add(new Coordenada(intX, intY, intCol));
        				//System.out.println(intCol);
        				alColores.add(intCol);
        			}else if(hasPos.get(String.valueOf(intCol))!=null) {
    					hasPos.get(String.valueOf(intCol)).add(new Coordenada(intX, intY, intCol)); 
    				}else {
    					hasPos.get(String.valueOf(alColores.get(pos))).add(new Coordenada(intX, intY, intCol));
    				}
    			}
    		}
    	}
    	for(Integer intCo:alColores) {
    		System.out.println(intCo);
    	}
    	System.out.println(alColores.size());
    	System.out.println("termino");
       /* Enumeration<ArrayList<Coordenada>> enu=hasPos.elements();
        while(enu.hasMoreElements()) {
        	for(Coordenada coor:enu.nextElement()) {
        		System.out.print(coor.toString()+" ");
        	}
        	System.out.print("\n");
        }*/
    }
    public void mvLimpiar() {
    	Enumeration<ArrayList<Coordenada>> enu=hasPos.elements();
    	int intPos=0, intColor;
        while(enu.hasMoreElements()) {
        	for(int intX=0; intX<biIma.getWidth(); intX++) {
        		for(int intY=0; intY<biIma.getHeight(); intY++) {
        	       biIma.setRGB(intX, intY, Color.WHITE.getRGB());		
        		}
        	}
        	ArrayList<Coordenada> alCoor=enu.nextElement();
        	for(Coordenada coor:alCoor) {
        		biIma.setRGB(coor.x, coor.y, coor.intColor);		
        	}
        	ProcesarImagen piProcesar=new ProcesarImagen(recRecursos, lie, imaIma, venTodo, alCoor.get(0).intColor);
        	piProcesar.hasPos=hasPos;
        	piProcesar.alCoor=alCoor;
			try {
				    piProcesar.mvVisibles();
					piProcesar.mvPosibleBor();
					piProcesar.mvContinuos();
		    }catch(java.lang.ArrayIndexOutOfBoundsException e){}
			/*for(ProcesarImagen piProcesarD:alPI) {
	        	Enumeration<Punto> enuPu=piProcesar.hasTodosBordes.elements();
	    		while(enuPu.hasMoreElements()) {
	    			Punto pun=enuPu.nextElement();
	    			for(ProcesarImagen pi:alPI) {
	    				if(pi==piProcesar) {
	    					continue;
	    				}
	    				for(Hashtable<String, Punto> hasD:pi.alHasBordes){
	    					Enumeration<Punto> enuD=hasD.elements();
	    					while(enuD.hasMoreElements()) {
	    						Punto punD=enuD.nextElement();
	    						douDes=Math.hypot(pun.intX-punD.intX, pun.intY-punD.intY);
	    						//System.out.println(pun.toString()+" "+punD.toString()+" "+hasD.get(String.valueOf(punD.intPos)).toString());
	    						if(douDes==1) {
	    							hasD.remove(String.valueOf(punD.intPos));
	    							piProcesar.biIma2.setRGB(punD.intX, punD.intY, Color.BLUE.getRGB());
	    						}
	    					}
	    				}
	    	        }  
	    		}
	        	
	        }*/
			
			
			alPI.add(piProcesar);
			intPos++;
			System.out.println("termino: "+intPos);
        }
       /* double douDes;
        int intC=0;
        for(ProcesarImagen piProcesar:alPI) {
        	for(Hashtable<String, Punto> hasP:piProcesar.alHasBordes) {
        		Enumeration<Punto> enuPu=hasP.elements();
        		while(enuPu.hasMoreElements()) {
        			Punto pun=enuPu.nextElement();
        			for(ProcesarImagen pi:alPI) {
        				if(pi==piProcesar) {
        					continue;
        				}
        				for(Hashtable<String, Punto> hasD:pi.alHasBordes){
        					Enumeration<Punto> enuD=hasD.elements();
        					while(enuD.hasMoreElements()) {
        						Punto punD=enuD.nextElement();
        						douDes=Math.hypot(pun.intX-punD.intX, pun.intY-punD.intY);
        						//System.out.println(pun.toString()+" "+punD.toString()+" "+hasD.get(String.valueOf(punD.intPos)).toString());
        						if(douDes==1) {
        							hasD.remove(String.valueOf(punD.intPos));
        							piProcesar.biIma2.setRGB(punD.intX, punD.intY, Color.BLUE.getRGB());
        						}
        					}
        				}
        	        }  
        		}
        		System.out.println("Limpiando: "+intC);
        		intC++;
        	}
        	
        	
        }
        for(ProcesarImagen pi:alPI) {
			for(Hashtable<String, Punto> hasD:pi.alHasBordes){
				Enumeration<Punto> enuD=hasD.elements();
				while(enuD.hasMoreElements()) {
					Punto punD=enuD.nextElement();
					pi.biIma2.setRGB(punD.intX, punD.intY, Color.YELLOW.getRGB());
				}
			}
        } 
		System.out.println("Termino");*/
       /*double douDes;
        for(ProcesarImagen pi:alPI) {
        	for(Hashtable<String, Punto> has:pi.alHasBordes) {
        		Enumeration<Punto> enuPu=has.elements();
        		while(enu.hasMoreElements()) {
        			Punto pun=enuPu.nextElement();
        			
        		}
        	}
        	douDes=Math.hypot(poiI.x-poiF.x, poiI.y-poiF.y);
        }  */
    }
      
}
