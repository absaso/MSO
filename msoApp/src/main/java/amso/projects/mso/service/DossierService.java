package amso.projects.mso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amso.projects.mso.model.Dossier;
import amso.projects.mso.repository.DossierRepo;

@Service
public class DossierService {

	@Autowired
	private DossierRepo dossierRepo;
	
	public List<Dossier> getDossiers() {
		return dossierRepo.findAll();
	}
	public Dossier saveDossier(Dossier dossier) {
        return dossierRepo.save(dossier);
    }

	public List<Dossier> getSpecificDossier(String libelle) {
		
			return dossierRepo.findDossierByRegexpLibelle(libelle);
	}
	
	public List<Dossier> getSpecificDomaine(String domaine) {
		
		return dossierRepo.findDossierByRegexpDomaine(domaine);
	}
	
	public List<Dossier> getSpecificClasseur(Integer classeur) {
		
		return dossierRepo.findByNumClasseur(classeur);
	}
	
	public List<Dossier> fetchDossierByProperties (Integer numClasseur,String domaine,String libelle){
		return dossierRepo.findDomaineByNumClasseurOrDomaineOrLibelle(numClasseur, domaine, libelle);
	}
	
	public Dossier updateDossier(Dossier dossier) {
		return dossierRepo.save(dossier);
		
	}
	
	public Dossier getDossierById(long id) {
		return dossierRepo.findById(id).get();
	}
	
	public void deleteDossierById(Long id) {
		dossierRepo.deleteById(id);
	}
	
	/*public List<Dossier> getSpecificLibelle(String libelle) {
		return dossierRepo.findByLibelle(libelle);
	}*/
	
	/*public Dossier getSpecificDomaine(String domaine) {
		Optional<Dossier> dossierOpt = dossierRepo.findByDomaine(domaine);
		if (dossierOpt.isPresent()) {
			return dossierOpt.get();
		}else {
			return null;
		}
	}*/
	
	/*public Dossier findDossier(String keywordClasseur) {
		Optional<Dossier> dossierOpt = dossierRepo.findByKeywordClasseur(keywordClasseur);
		if (dossierOpt.isPresent()) {
			return dossierOpt.get();
		}else {
			return null;
		}
	}*/
	
	/*public Dossier getSpecificClasseur(int numclasseur) {
		Optional<Dossier> dossierOpt = dossierRepo.findByNumClasseur(numclasseur);
		if (dossierOpt.isPresent()) {
			return dossierOpt.get();
		}else {
			return null;
		}	}*/
	


}
