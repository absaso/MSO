package amso.projects.mso.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import amso.projects.mso.model.Dossier;

@Repository
public interface DossierRepo extends MongoRepository<Dossier, Long> , DossierCustom{

	List<Dossier> findByLibelle(String libelle);

	Optional<Dossier> findByDomaine(String domaine);

	//Optional<Dossier> findByNumClasseur(int numclasseur);
	
	@Query("{ 'libelle' : { $regex: ?0 } }")
	List<Dossier> findDossierByRegexpLibelle(String regexp);
	
	@Query("{ 'domaine' : { $regex: ?0 } }")
	List<Dossier> findDossierByRegexpDomaine(String regexp);	
	
	List<Dossier> findByNumClasseur(Integer numClasseur);
	
}
