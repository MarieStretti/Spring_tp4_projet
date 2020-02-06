package eu.ensg.Spring_tp4_projet.controller;

import eu.ensg.Spring_tp4_projet.model.Participant;
import eu.ensg.Spring_tp4_projet.model.ParticipantRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static sun.security.krb5.KrbException.errorMessage;

@Controller // This means that this class is a Controller
@RequestMapping(path="/participant")// This means URL's start with /participant (after Application path)
public class ParticipantController {

    @Autowired // This means to get the bean called participantRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ParticipantRepository participantRepository;
    private static List<Participant> participants = new ArrayList<Participant>();
    
//    @PostMapping(value = "/add") // Map ONLY POST Requests
    @RequestMapping(value = {"/addParticipant"}, method=RequestMethod.GET)
    public String addNewParticipant(Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Participant p = new Participant();
        model.addAttribute("participantForm", p);
        return "addParticipant";
        //participantRepository.save(p);
    }
    
    @RequestMapping(value = { "/addParticipant"}, method = RequestMethod.POST)
    public String saveParticipant(Model model, @ModelAttribute("participantForm")Participant p) {
        
        String nom = p.getNom();
        String prenom = p.getPrenom();
        
        //model.addAttribute("errorMessage", errorMessage);
        if (nom != null && nom.length() > 0 //
                && prenom != null && prenom.length() > 0) {
            Participant pa = new Participant();
            pa.setNom(nom);
            pa.setPrenom(prenom);
            participantRepository.save(pa);
            return "redirect:/participant/all";
        }
        return "addParticipant";
    }

    @GetMapping(path="/all")
    public String getAllParticipants(Model model) {
        model.addAttribute("allParticipants", participantRepository.findAll());
        // This returns a JSON or XML with the users
        return "allParticipants";
    }
}
