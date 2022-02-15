package amso.projects.mso.repository;

import java.util.List;

import amso.projects.mso.model.Dossier;

public interface DossierCustom {
	
	public List<Dossier> findDomaineByNumClasseurOrDomaineOrLibelle(Integer numClasseur,String domaine,String libelle);
}
