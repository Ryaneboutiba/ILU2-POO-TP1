package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import personnages.VillageSansChefException;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private MarcheInterne marche;

	public Village(String nom, int nbVillageoisMaximum,int nbetal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche =new MarcheInterne(nbetal);
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
		int indice=marche.trouverEtalLibre();
		if(indice!=-1) {
			marche.utiliserEtal(indice, vendeur, produit, nbProduit);
			texte.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (indice+1)   + ".\n");
	        }else {
	            return "Il n'y a pas d'étal disponible pour le vendeur " + vendeur.getNom() + ".\n";
	    }
		return texte.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etals = marche.trouverEtals(produit);
	    if (etals.length == 0) {
	        return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n";
	    } else {
	        StringBuilder resultat = new StringBuilder();
	        resultat.append("Les vendeurs qui proposent des "+produit+" sont :\n");
	        for (int i = 0; i < etals.length; i++) {
	            resultat.append("- "+etals[i].getVendeur().getNom()+"\n");
	        }
	        return resultat.toString();
	    }
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String afficherMarche() {
		StringBuilder chaine=new StringBuilder();
		chaine.append("Le marvhe du village "+nom+" possede pkusieurs etals : \n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal=marche.trouverVendeur(vendeur);
		String texte=etal.libererEtal();
		return texte;
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

	public String afficherVillageois() throws VillageSansChefException {
		if(chef==null) {
			throw new VillageSansChefException("Le village n'a pas dechef et ne peut donc exister");
		}
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
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		    Etal occupant = new Etal();
			occupant.occuperEtal(vendeur,produit,nbProduit);
			etals[indiceEtal]=occupant;
		}
		
		private int trouverEtalLibre() {
			if(etals.length==0) {
				return 0;
			}
			for(int i=0;i<etals.length;i++) {
				if(etals[i]==null || !(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbResultats = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i] != null && etals[i].contientProduit(produit)) {
		            nbResultats++;
		        }
		    }

		    Etal[] resultats = new Etal[nbResultats];
		    int index = 0;
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i] != null && etals[i].contientProduit(produit)) {
		            resultats[index++] = etals[i];
		        }
		    }
		    return resultats;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
            int nbEtalVide = 0;
            for (int i=0;i<etals.length;i++) {
                if (etals[i]!=null && etals[i].isEtalOccupe() ) {
                    chaine.append(etals[i].afficherEtal()+"\n");
                } else {
                    nbEtalVide++;
                }
            }
            chaine.append("Il reste ").append(nbEtalVide).append(" étals non utilisés dans le marché.\n");
            return chaine.toString();
		}
	}
}
	
	
	
