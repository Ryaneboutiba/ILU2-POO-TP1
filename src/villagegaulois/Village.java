package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private MarcheInterne marche;

	public Village(String nom, int nbVillageoisMaximum,int nbetal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		MarcheInterne marche=new MarcheInterne(nbetal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public String installerVendeur(Gaulois vendeur,String produit, int nbProduit) {
		StringBuilder texte=new StringBuilder();
		texte.append(vendeur.getNom()+ " cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		return texte.toString();
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class MarcheInterne{
		private Etal[] etals;
		
		private MarcheInterne(int nbetal) {
			etals=new Etal[nbetal];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			Etal occupant = new Etal();
			occupant.occuperEtal(vendeur,produit,nbProduit);
			etals[indiceEtal]=occupant;
		}
		
		public int trouverEtalLibre() {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int taille=0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					taille++;
				}
			}
			Etal[] tab=new Etal[taille];
			for(int i=0;i<taille;i++) {
				if(etals[i].isEtalOccupe()) {
					tab[i]=etals[i];
				}
			}
			return tab;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		public void afficherMarche() {
			StringBuilder texte=new StringBuilder();
			Etal[] etalOcuppe=trouverEtals("produit");
			for(int i=0;i<etalOcuppe.length;i++) {
				texte.append(etalOcuppe[i].afficherEtal());
			}
			int etalVide=etals.length-etalOcuppe.length;
			if(etalVide>0) {
				texte.append("Il reste "+etalVide+" etals non utilises dans le marche\n");
			}
		}
	}
}
	
	
	
