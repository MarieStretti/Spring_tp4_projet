package eu.ensg.Spring_tp4_projet.controller;

import eu.ensg.Spring_tp4_projet.model.Evenement;
import eu.ensg.Spring_tp4_projet.model.EvenementRepository;
import eu.ensg.Spring_tp4_projet.model.Participant;
import eu.ensg.Spring_tp4_projet.model.ParticipantRepository;
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
@RequestMapping(path="/participant")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EvenementRepository eventRepository;
    

    /**
     * Ajoute au model un nouveau participant pour un événement
     * @param model
     * @param eventId
     * @return vue
     */
    @RequestMapping(value = {"/addParticipant"}, method=RequestMethod.GET)
    public String addNewParticipant(Model model, @RequestParam("id") Optional<String> eventId) {

        Participant p = new Participant();
        
        if (eventId.isPresent()) {
        	String id = eventId.get();
        	model.addAttribute("participantForm", p);
        	model.addAttribute("eventId", id);
            return "addParticipant";
        }
        return "Error";
    }
    
    /**
     * Ajoute un participant et le sauvegarde
     * @param model
     * @param p
     * @param eventId
     * @return vue
     */
    @RequestMapping(value = { "/addParticipant"}, method = RequestMethod.POST)
    public String saveParticipant(Model model, @ModelAttribute("participantForm")Participant p, @RequestParam("id") Optional<String> eventId) {

        if (p.getNom() != null
                && p.getNom().length() > 0
                && p.getPrenom() != null 
                && p.getPrenom().length() > 0 
                && p.getEmail() != null 
                && p.getEmail().length() > 0 
                && eventId.isPresent()) {
            String id = eventId.get();
            Optional<Evenement> event = eventRepository.findById(Integer.parseInt(id));
            if (event.isPresent()) {
            	p.setEvent(event.get());
                participantRepository.save(p);
                model.addAttribute("event", event.get());
                return "redirect:/participant/all";
            }
            model.addAttribute("errorMessage", "Les champs Nom, Prénom et Email sont requis");
            return "redirect:/addParticipant/";
        }
        
        return "addParticipant";
    }

    /**
     * Renvoie tous les participants
     * @param model
     * @return vue
     */
    @GetMapping(path="/all")
    public String getAllParticipants(Model model) {
        model.addAttribute("allParticipants", participantRepository.findAll());
        return "allParticipants";
    }
    
    /**
     * Supprime un participant
     * @param participantId
     * @return vue
     */
    @GetMapping(path="/removeParticipant")
    public String removeParticipant(@RequestParam("id") Optional<String> participantId){
        String id = participantId.get();
        Optional<Participant> participant = participantRepository.findById(Integer.parseInt(id));
         
        if(participant.isPresent()) 
        {
            participantRepository.deleteById(Integer.parseInt(id));
        } else {
            return "Il n'y a pas de participant pour cet identifiant";
        }
        return "redirect:/participant/all";
    }
    
    /**
     * Ajoute au model le participant à modifier
     * @param model
     * @param participantId
     * @return vue
     */
    @RequestMapping(value = {"/modifyParticipant"}, method=RequestMethod.GET)
    public String modifyParticipant(Model model, @RequestParam("id") Optional<String> participantId) {

    	int id = Integer.parseInt(participantId.get());
        Optional<Participant> participant = participantRepository.findById(id);
        
        if (participant.isPresent()) {
        	model.addAttribute("participantForm", participant.get());
        	model.addAttribute("numpers", id);
            return "modifyParticipant";
        }
        return "Error";
    }
    
    /**
     * Modifie le participant et le sauvegarde
     * @param model
     * @param p
     * @param participantId
     * @return string vue
     */
    @RequestMapping(path = "/modifyParticipant", method = RequestMethod.POST)
    public String saveModifiedParticipant(Model model, @ModelAttribute("participantForm")Participant p, @RequestParam("id") Optional<String> participantId)
    {
    	int id = Integer.parseInt(participantId.get());
        Optional<Participant> participant = participantRepository.findById(id);
        if(participant.isPresent()) 
        {
            Participant newParticipant = participant.get();
            newParticipant.setEmail(p.getEmail());
            newParticipant.setOrganisation(p.getOrganisation());
            newParticipant.setObservations(p.getObservations());
            newParticipant = participantRepository.save(newParticipant);
            return "redirect:/participant/all";
        }
        return "modifyParticipant";
    }
}
