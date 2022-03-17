import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Recursos {
	Hashtable<String, BufferedImage> hasImagenes=new Hashtable<String, BufferedImage>();
	public Recursos() {
		// TODO Auto-generated constructor stub
		mvImagenes();
	}
    public void mvImagenes() {
    	File filCarpeta=new File("Recursos");
    	for(File filIma:filCarpeta.listFiles()) {
    		try {
				hasImagenes.put(filIma.getName(), ImageIO.read(filIma));
				System.out.println(filIma.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
