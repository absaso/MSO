package amso.projects.mso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amso.projects.mso.model.Dossier;
import amso.projects.mso.service.DossierService;


@Controller
public class DossierCtr {

	@Autowired
	private DossierService dossierService;
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	private List<Dossier> listDossiers;
	
	//visualisation de tous les dossiers
	@RequestMapping("/AllDocuments") 
	public List<Dossier> getAllDossiers() {
		  return dossierService.getDossiers();
		  }
	
	//visualisation de tous les dossiers via le template
	@RequestMapping("/mainPage") 
	public String showMain(Model model) {
		listDossiers = dossierService.getDossiers();
		model.addAttribute("dossiers", listDossiers);
	    return "main";
		  }
	
	//Redirection vers la page de création d'un nouveau document
	@RequestMapping(value="/AddNewDossier", method=RequestMethod.GET)
	public String newDossierForm(Model model) {
		
		//creating an empty object de type type qui va hold les infos saisies dans le form de creation
		Dossier dossier = new Dossier();
		model.addAttribute("dossier",dossier);
	    return "AddNewDossier";
		  }
	
	//Enregistrement d'un dossier
	@PostMapping("/SaveDossier")
    public String createDossier(@ModelAttribute Dossier dossier) {
        dossierService.saveDossier(dossier);
		return "redirect:/mainPage";
    }
	
	@RequestMapping(value="/SearchDossier", method=RequestMethod.GET)
	public String searchDossier(@RequestParam (value = "keywordClasseur", required = false) Integer keywordClasseur, @RequestParam(value = "keywordLibelle",required = false) String keywordLibelle, @RequestParam(value = "keywordDomaine",required = false) String keywordDomaine,Model model) {
		listDossiers = dossierService.fetchDossierByProperties(keywordClasseur,keywordDomaine,keywordLibelle);
		model.addAttribute("dossiers", listDossiers);
		return "main";
	}
	
	@GetMapping("/EditDossier/{id}")
	public String editDossier (@PathVariable Long id, Model model ) {
		model.addAttribute("dossier",dossierService.getDossierById(id));
		return "EditDossier";
	}
	
	@RequestMapping(value="/Dossier/{id}", method=RequestMethod.POST, params="action=edit")
	public String updateDossier (@PathVariable Long id, @ModelAttribute("dossier") Dossier dossier, Model model) {
		Dossier existingDossier = dossierService.getDossierById(id);
		existingDossier.setID(id);
		existingDossier.setLibelle(dossier.getLibelle());
		existingDossier.setNumClasseur(dossier.getNumClasseur());
		existingDossier.setDomaine(dossier.getDomaine());
		existingDossier.setPhoto(dossier.getPhoto());
		
		dossierService.updateDossier(existingDossier);
		return "redirect:/mainPage";
	}
	
	@RequestMapping(value="/Dossier/{id}", method=RequestMethod.POST, params="action=delete")
	public String deleteDossier(@PathVariable Long id) {
		dossierService.deleteDossierById(id);
		return "redirect:/mainPage";
	}
	/*
	//Méthode chargée de faire la recherche selon le mot-clé
	@RequestMapping(value="/SearchDossier", method=RequestMethod.GET)
	public String searchDossier(@RequestParam (value = "keywordClasseur", required = false) Integer keywordClasseur, @RequestParam(defaultValue = "empty",value = "keywordLibelle") String keywordLibelle, @RequestParam(defaultValue = "empty",value = "keywordDomaine") String keywordDomaine,Model model) {
		if (keywordClasseur.intValue() != 0) {
			listDossiers = dossierService.getSpecificClasseur(keywordClasseur);
			model.addAttribute("dossiers", listDossiers);	
		    return "main";
		}
		if (keywordLibelle != "empty")
		{
			listDossiers = dossierService.getSpecificDossier(keywordLibelle);
			model.addAttribute("dossiers", listDossiers);	
		    return "main";
		}
		if (keywordDomaine != "empty")
		{
			listDossiers = dossierService.getSpecificDomaine(keywordDomaine);
			model.addAttribute("dossiers", listDossiers);	
		    return "main";
		}
		listDossiers = dossierService.getDossiers();
		model.addAttribute("dossiers", listDossiers);	
	    return "main";
		  }
	*/

	/*// recherche d'un dossier spécifique à partir du libelle
	@RequestMapping("/SearchLibelle/{libelle}") 
	public Dossier getDossier(@PathVariable String libelle) {
		Dossier doc = dossierService.getSpecificDossier(libelle);
		return doc;
	}*/
	/*// recherche dossiers à partir du domaine
	@RequestMapping("/SearchDomaineDossier/{domaine}") 
	public Dossier getDossierByDomaine(@PathVariable String domaine) {
		Dossier doc = dossierService.getSpecificDomaine(domaine);
		return doc;
	}*/
	
	/*
	//recherche dossiers à partir du numero de classeur
	@RequestMapping("/SearchClasseur/{numclasseur}") 
	public Dossier getDossierByDomaine(@PathVariable int numclasseur) {
		Dossier doc = dossierService.getSpecificClasseur(numclasseur);
		return doc;
	}*/
	
	
}
