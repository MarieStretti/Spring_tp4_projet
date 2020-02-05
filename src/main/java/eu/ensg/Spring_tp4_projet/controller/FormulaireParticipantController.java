package eu.ensg.Spring_tp4_projet.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.ensg.Spring_tp4_projet.model.Participant;
import eu.ensg.Spring_tp4_projet.model.ParticipantRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/participant")// This means URL's start with /participant (after Application path)
public class FormulaireParticipantController {

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
    
//    @GetMapping("/add")
//    public String addNewParticipant(Model model) {
//        model.addAttribute("participantForm", new Participant());
//        return "addNewParticipant";
//    }
//    
//    @PostMapping("/add")
//    public String submitParticipant(@ModelAttribute("participantForm") Participant p) {
//        participantRepository.save(p);
//        return "result";
//    }

//    @GetMapping("/")
//    public String greetingForm(Model model) {
//      model.addAttribute("", new Participant());
//      return "index";
//    }
//
//    @PostMapping("/")
//    public String greetingSubmit(@ModelAttribute Participant p) {
//      return "result";
//    }
    
    
}
