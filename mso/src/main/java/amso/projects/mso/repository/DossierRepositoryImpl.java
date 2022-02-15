package amso.projects.mso.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import amso.projects.mso.model.Dossier;

public class DossierRepositoryImpl implements DossierCustom{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Dossier> findDomaineByNumClasseurOrDomaineOrLibelle(Integer numClasseur, String domaine, String libelle) {
		 final Query query = new Query();
		 final List<Criteria> criteria = new ArrayList<>();
	     if (numClasseur != null && !numClasseur.equals(0))
	     criteria.add(Criteria.where("numClasseur").is(numClasseur));
	     if (domaine != null && !domaine.isEmpty())
	     criteria.add(Criteria.where("domaine").is(domaine));
	     if (libelle != null && !libelle.isEmpty())
	     criteria.add(Criteria.where("libelle").in(libelle)); 
	     
	     if (!criteria.isEmpty())
	    	 query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	    	 return mongoTemplate.find(query, Dossier.class);
	}
	
	

	

}
