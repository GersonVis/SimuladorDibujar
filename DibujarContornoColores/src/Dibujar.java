import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Dibujar {
	Hashtable<String, Punto> hasPuntos=new Hashtable<String, Punto>();
	Robot robot;
	int intEspera=30;
	int intX=0, intY=0;
	ArrayList<Coordenada> alCoor;
	public Dibujar(Hashtable<String, Punto> hasBordes) {
		// TODO Auto-generated constructor stub
		this.hasPuntos=hasBordes;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Dibujar(ArrayList<Coordenada> alCoor) {
		this.alCoor=alCoor;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mvSolCuand() {
		int y=alCoor.get(0).y, x=alCoor.get(0).x; 
		System.out.println("pos: "+intX+" "+intY);
		robot.mouseMove(x+intX, alCoor.get(0).y+intY);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Coordenada coor;
		for(int intP=0; intP<alCoor.size(); intP++) {
			coor=alCoor.get(intP);
			if(x!=coor.x) {
				y=alCoor.get(intP).y;
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.mouseMove(coor.x+intX, coor.y+intY);
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}else if((y+1)!=coor.y){
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.mouseMove(coor.x+intX, coor.y+intY);
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}else {
				robot.mouseMove(coor.x+intX, coor.y+intY);
			}
			y=coor.y;
			try {
				Thread.sleep(intEspera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void mvIniciarEn(int intX, int intY) {
		this.intX=intX; this.intY=intY;
	}
	public void mvClick() {
		Enumeration<Punto>enu=hasPuntos.elements();
    	while(enu.hasMoreElements()) {
    		Punto pun=enu.nextElement();
    		robot.mouseMove(pun.intX+intX, pun.intY+intY);
    		robot.mousePress(InputEvent.BUTTON1_MASK);
    		//System.out.println(pun.intX+" "+pun.intY);
    		try {
				Thread.sleep(intEspera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	}
	}
    public void mvMoverNoSoltar() {
    	robot.mouseMove(hasPuntos.get(String.valueOf(0)).intX+intX, hasPuntos.get(String.valueOf(0)).intY+intY);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	System.out.println(hasPuntos.size());
    	for(int intP=1; intP<hasPuntos.size(); intP++) {
    		Punto pun=hasPuntos.get(String.valueOf(intP));
    		robot.mouseMove(pun.intX+intX, pun.intY+intY);
    		//System.out.println(pun.intX+" "+pun.intY);
    		try {
				Thread.sleep(intEspera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void mvHacer(Punto pun, int intDesX, int intDesY) {
		try {
			Thread.sleep(intEspera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.mouseMove(pun.intX+intX, pun.intY+intY);
    }
    public void mvPuntear() {
    	Enumeration<Punto>enu=hasPuntos.elements();
    	Punto punP;
    	while(enu.hasMoreElements()) {
    		punP=enu.nextElement();
    		robot.mouseMove(punP.intX+intX, punP.intY+intY);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	try {
    			Thread.sleep(intEspera);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	robot.mouseRelease(InputEvent.BUTTON1_MASK);
        	
    	}
    }
    public void mvRallitas(int intDesX, int intDesY) {
    	System.out.println("pos: "+intX+" "+intY);
    	Enumeration<Punto>enu=hasPuntos.elements();
    	Punto punP=null;
    	for(int intT=0; intT<hasPuntos.size(); intT++) {
    		punP=hasPuntos.get(String.valueOf(intT));
    		if(punP!=null) {
    			break;
    		}
    	}
    	if(punP==null) {
    		return;
    	}
    	//robot.keyPress(KeyEvent.VK_ESCAPE);
    	robot.mouseMove(punP.intX+intX, punP.intY+intY);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	int intNu=0;
    	for(int intT=1; intT<hasPuntos.size(); intT++) {
    		Punto pun=hasPuntos.get(String.valueOf(intT));
    		if(pun!=null) {
    			mvHacer(pun, 0, 0);
    		}
    		/*if(pun==null) {
    			robot.mouseRelease(InputEvent.BUTTON1_MASK);
    			intNu=1;
    			continue;
    			//mvHacer(pun, intDesX, intDesX);
    		}else if(intNu==1) {
    				robot.mouseMove(punP.intX+intX, punP.intY+intY);
    		    	robot.mousePress(InputEvent.BUTTON1_MASK);
    		    	intNu=0;
    		}else {
    			robot.mouseMove(punP.intX+intX, punP.intY+intY);
    		}
    		try {
    			Thread.sleep(intEspera);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}*/
    	}
    	
    	//robot.mouseMove(punP.intX+intX+intDesX, punP.intY+intY+intDesY);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	//robot.keyRelease(KeyEvent.VK_ESCAPE);
    	try {
			Thread.sleep(intEspera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
/*    public void mvRallitas(int intDesX, int intDesY) {
    	System.out.println("pos: "+intX+" "+intY);
    	Enumeration<Punto>enu=hasPuntos.elements();
    	Punto punP=hasPuntos.get("0");
    	//robot.keyPress(KeyEvent.VK_ESCAPE);
    	robot.mouseMove(punP.intX+intX, punP.intY+intY);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	int intNu=0;
    	for(int intT=1; intT<hasPuntos.size(); intT++) {
    		Punto pun=hasPuntos.get(String.valueOf(intT));
    		if(pun==null) {
    			robot.mouseRelease(InputEvent.BUTTON1_MASK);
    			intNu=1;
    			//mvHacer(pun, intDesX, intDesX);
    		}else 
    			if(intNu==1) {
    				robot.mouseMove(punP.intX+intX, punP.intY+intY);
    		    	robot.mousePress(InputEvent.BUTTON1_MASK);
    		    	intNu=0;
    			}else {
    				robot.mousePress(InputEvent.BUTTON1_MASK);
    			}
    		
    	}
    	//robot.mouseMove(punP.intX+intX+intDesX, punP.intY+intY+intDesY);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	//robot.keyRelease(KeyEvent.VK_ESCAPE);
    	try {
			Thread.sleep(intEspera);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }*/
    public void mvClicks() {
    	robot.mouseMove(hasPuntos.get(String.valueOf(0)).intX+intX, hasPuntos.get(String.valueOf(0)).intY+intY);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	System.out.println(hasPuntos.size());
    	for(int intP=1; intP<hasPuntos.size(); intP++) {
    		Punto pun=hasPuntos.get(String.valueOf(intP));
    		robot.mouseMove(pun.intX+intX, pun.intY+intY);
    		robot.mousePress(InputEvent.BUTTON1_MASK);
    		//System.out.println(pun.intX+" "+pun.intY);
    		try {
				Thread.sleep(intEspera);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	}
    	robot.mouseMove(hasPuntos.get(String.valueOf(0)).intX+intX, hasPuntos.get(String.valueOf(0)).intY+intY);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
