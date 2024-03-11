package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;


public class ScenarioDegrade{
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois asterix=new Gaulois("Asterix",9);
		try {
			etal.acheterProduit(-3, asterix);
			etal.libererEtal();
			System.out.println("Fin du test");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
}
