package amso.projects.mso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import amso.projects.mso.model.Domaine;


@Repository
public interface DomaineRepo extends MongoRepository<Domaine, Long> {

}
