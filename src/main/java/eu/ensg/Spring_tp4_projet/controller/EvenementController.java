package eu.ensg.Spring_tp4_projet.controller;

import eu.ensg.Spring_tp4_projet.model.Evenement;
import eu.ensg.Spring_tp4_projet.model.EvenementRepository;
import eu.ensg.Spring_tp4_projet.model.Participant;
import eu.ensg.Spring_tp4_projet.model.ParticipantRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/evenement")
public class EvenementController {

    @Autowired
    private EvenementRepository eventRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    /**
     * Ajoute un nouvel événement au model
     * @param model
     * @return vue
     */
    @RequestMapping(value = {"/addEvenement"}, method=RequestMethod.GET)
    public String addNewEvenement(Model model) {
        Evenement e = new Evenement();
        
    	model.addAttribute("evenementForm", e);
        return "addEvenement";
    }
    
    /**
     * Ajoute un nouvel événement et le sauvegarde
     * @param model
     * @param e
     * @return vue
     */
    @RequestMapping(value = { "/addEvenement"}, method = RequestMethod.POST)
    public String saveEvenement(Model model, @ModelAttribute("evenementForm")Evenement e) {

        if (e.getIntitule() != null
                && e.getIntitule().length() > 0
                && e.getTheme() != null 
                && e.getTheme().length() > 0) {
        	eventRepository.save(e);
            return "redirect:/evenement/all";
        }
        model.addAttribute("errorMessage", "Les champs Intitulé et Thème sont requis");        
        
        return "addEvenement";
    }
    
    /**
     * Renvoie tous les événements
     * @param model
     * @return vue
     */
    @GetMapping(path = "/all")
    public String getAllEvents(Model model) {
        model.addAttribute("allEvents", eventRepository.findAll());
        return "allEvents";
    }
    
    /**
     * Ajoute l'événement à modifier au model
     * @param model
     * @param evenementId
     * @return vue
     */
    @RequestMapping(value = {"/modifyEvenement"}, method=RequestMethod.GET)
    public String modifyEvenement(Model model, @RequestParam("id") Optional<String> evenementId) {

    	int id = Integer.parseInt(evenementId.get());
        Optional<Evenement> evenement = eventRepository.findById(id);
        
        if (evenement.isPresent()) {
        	model.addAttribute("evenementForm", evenement.get());
        	model.addAttribute("numevent", id);
            return "modifyEvenement";
        }
        return "Error";
    }
    
    /**
     * Modifie l'événement et le sauvegarde
     * @param model
     * @param e
     * @param evenementId
     * @return vue
     */
    @RequestMapping(path = "/modifyEvenement", method = RequestMethod.POST)
    public String saveModifiedEvenement(Model model, @ModelAttribute("evenementForm")Evenement e, @RequestParam("id") Optional<String> evenementId)
    {
    	int id = Integer.parseInt(evenementId.get());
        Optional<Evenement> evenement = eventRepository.findById(id);
        if(evenement.isPresent()) 
        {
            Evenement newEvenement = evenement.get();
            newEvenement.setDatedebut(e.getDatedebut());
            newEvenement.setNbpartmax(e.getNbpartmax());
            newEvenement.setOrganisateur(e.getOrganisateur());
            newEvenement = eventRepository.save(newEvenement);
            return "redirect:/evenement/all";
        }
        return "modifyEvenement";
    }
    
    /**
     * Supprime un événement ainsi que tous les participants inscrits à cet événement
     * @param model
     * @param evenementId
     * @return vue
     */
    @GetMapping(path="/removeEvenement")
    public String removeEvenement(Model model, @RequestParam("id") Optional<String> evenementId){
        String id = evenementId.get();
        Optional<Evenement> evenement = eventRepository.findById(Integer.parseInt(id));
    	
        if(evenement.isPresent()) 
        {
        	List<Participant> participants = evenement.get().getParticipants();
        	for (int i = 0; i < participants.size(); i++) {
        		Participant participant = participants.get(i);
	            participantRepository.deleteById(participant.getNumpers());
        	}
        	eventRepository.deleteById(Integer.parseInt(id));
        } else {
            return "Il n'y a pas d'événement pour cet identifiant";
        }
        return "redirect:/evenement/all";
    }

}
