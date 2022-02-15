package amso.projects.mso.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


//@Entity(name = "DOMAINE")
@Document(collection = "DOMAINE")
public class Domaine {
	
	/*@Id
	@SequenceGenerator
	(name = "sequence_id_domaine",
	sequenceName = "sequence_id_domaine",
	allocationSize = 1)*/
	
	@Id
	private long ID;
	@Indexed(unique = true)
	private String nom;
	private String description;
	

	public Domaine(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}

	public Domaine() {
		super();
	}

	public long getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
