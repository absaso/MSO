package amso.projects.mso.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import amso.projects.mso.model.Dossier;
import amso.projects.mso.service.SequenceGeneratorService;

@Component
public class DossierModelListener extends AbstractMongoEventListener<Dossier>{


	    private SequenceGeneratorService sequenceGenerator;

	    @Autowired
	    public DossierModelListener(SequenceGeneratorService sequenceGenerator) {
	        this.sequenceGenerator = sequenceGenerator;
	    }

	    @Override
	    public void onBeforeConvert(BeforeConvertEvent<Dossier> event) {
	        if (event.getSource().getID() < 1) {
	            event.getSource().setID(sequenceGenerator.generateSequence(Dossier.SEQUENCE_NAME));
	        }
	    }


	}
