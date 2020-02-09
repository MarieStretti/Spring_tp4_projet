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

@Controller // This means that this class is a Controller
@RequestMapping(path="/participant")// This means URL's start with /participant (after Application path)
public class ParticipantController {

    @Autowired // This means to get the bean called participantRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ParticipantRepository participantRepository;
    @Autowired
    private EvenementRepository eventRepository;
    
    @RequestMapping(value = {"/addParticipant"}, method=RequestMethod.GET)
    public String addNewParticipant(Model model, @RequestParam("id") Optional<String> eventId) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Participant p = new Participant();
        
        if (eventId.isPresent()) {
        	String id = eventId.get();
        	model.addAttribute("participantForm", p);
        	model.addAttribute("eventId", id);
            return "addParticipant";
        }
        return "Error";
    }
    
    @RequestMapping(value = { "/addParticipant"}, method = RequestMethod.POST)
    public String saveParticipant(Model model, @ModelAttribute("participantForm")Participant p, @RequestParam("id") Optional<String> eventId) {
        String nom = p.getNom();
        String prenom = p.getPrenom();
        String email = p.getEmail();
        if (nom != null && nom.length() > 0 && prenom != null && prenom.length() > 0 && email != null && email.length() > 0 && eventId.isPresent()) {
            Participant pa = new Participant();
            pa.setNom(nom);
            pa.setPrenom(prenom);
            pa.setEmail(email);
            String id = eventId.get();
            Optional<Evenement> event = eventRepository.findById(Integer.parseInt(id));
            if (event.isPresent()) {
	            //pa.setEvenement(event.get());
            	pa.setIntitule(event.get().getIntitule());
	            participantRepository.save(pa);
	            model.addAttribute("event", event.get().getIntitule());
	            return "redirect:/participant/all";
            }
            model.addAttribute("errorMessage", "Les champs Nom, Prénom et Email sont requis");
            return "redirect:/addParticipant/";
        }
        
        return "addParticipant";
    }

    @GetMapping(path="/all")
    public String getAllParticipants(Model model) {
        model.addAttribute("allParticipants", participantRepository.findAll());
        return "allParticipants";
    }
}