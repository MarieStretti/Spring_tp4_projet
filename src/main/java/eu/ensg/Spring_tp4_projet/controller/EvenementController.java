package eu.ensg.Spring_tp4_projet.controller;

import eu.ensg.Spring_tp4_projet.model.Evenement;
import eu.ensg.Spring_tp4_projet.model.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/evenement")// This means URL's start with /participant (after Application path)
public class EvenementController {

    @Autowired // This means to get the bean called participantRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EvenementRepository eventRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewEvent(@RequestParam String intitule,
            @RequestParam String theme) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Evenement e = new Evenement();
        e.setIntitule(intitule);
        e.setTheme(theme);
        eventRepository.save(e);
        return "addEvent";
    }

    @GetMapping(path = "/all")
    public String getAllEvents(Model model) {
        model.addAttribute("allEvents", eventRepository.findAll());
        return "allEvents";
    }

}