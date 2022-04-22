package amso.projects.mso.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity (name = "DOSSIER")
@Document(collection = "DOSSIER")
public class Dossier {
	
	@Transient
    public static final String SEQUENCE_NAME = "dossiers _sequence";

	
	@org.springframework.data.annotation.Id
	/*@SequenceGenerator
	(name = "sequence_id_dossier",
	sequenceName = "sequence_id_dossier",
	allocationSize = 1)*/
	private long ID;
	private Integer numClasseur;
	@Indexed(unique = true)
	private String libelle;
	private String photo; /*path de l'image */
	private String domaine;
	
	public Dossier(int numClasseur, String libelle, String photo, String domaine) {
		super();
		this.numClasseur = numClasseur;
		this.libelle = libelle;
		this.photo = photo;
		this.domaine = domaine;
	}

	public Dossier() {
		super();
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Integer getNumClasseur() {
		return numClasseur;
	}

	public void setNumClasseur(Integer numClasseur) {
		this.numClasseur = numClasseur;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	
	

}
