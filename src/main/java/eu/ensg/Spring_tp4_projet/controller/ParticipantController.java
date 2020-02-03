package eu.ensg.Spring_tp4_projet.controller;

import eu.ensg.Spring_tp4_projet.model.Participant;
import eu.ensg.Spring_tp4_projet.model.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/participant")// This means URL's start with /participant (after Application path)
public class ParticipantController {

    @Autowired // This means to get the bean called participantRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ParticipantRepository participantRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewParticipant(@RequestParam String nom,
             @RequestParam String prenom) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Participant p = new Participant();
        p.setNom(nom);
        p.setPrenom(prenom);
        participantRepository.save(p);
        return "addParticipant";
    }

    @GetMapping(path="/all")
    public String getAllParticipants(Model model) {
        model.addAttribute("allParticipants", participantRepository.findAll());
        // This returns a JSON or XML with the users
        return "allParticipants";
    }
}
