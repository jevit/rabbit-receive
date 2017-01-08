package org.jive.bean;

/**
 * Configuration du serveur
 * 
 * @author jerem
 *
 */
public class ConfServeur {
	private String serveurAdresse;
	private String serveurUtilisateur;
	private String serveurPassword;

	public String getServeurAdresse() {
		return serveurAdresse;
	}

	public void setServeurAdresse(String serveurAdresse) {
		this.serveurAdresse = serveurAdresse;
	}

	public String getServeurUtilisateur() {
		return serveurUtilisateur;
	}

	public void setServeurUtilisateur(String serveurUtilisateur) {
		this.serveurUtilisateur = serveurUtilisateur;
	}

	public String getServeurPassword() {
		return serveurPassword;
	}

	public void setServeurPassword(String serveurPassword) {
		this.serveurPassword = serveurPassword;
	}

	/**
	 * Constructeur
	 * 
	 * @param serveurAdresse
	 *            Adresse du serveur
	 * @param serveurUtilisateur
	 *            nom de l'utilisateur
	 * @param serveurPassword
	 *            password de l'utilisateur
	 */
	public ConfServeur(String serveurAdresse, String serveurUtilisateur, String serveurPassword) {
		super();
		this.serveurAdresse = serveurAdresse;
		this.serveurUtilisateur = serveurUtilisateur;
		this.serveurPassword = serveurPassword;
	}

	public ConfServeur() {
		super();
		// TODO Auto-generated constructor stub
	}

}
