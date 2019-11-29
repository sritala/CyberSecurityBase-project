package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.repository.SignupRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Controller
public class ListAndDebugController {

    @Autowired
    private SignupRepository signupRepository;

    private EntityManager entityManager;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listMapping(Model model) {
        model.addAttribute("date", LocalDateTime.now());
        model.addAttribute("subscribers", signupRepository.findAll());
        return "list";
    }

    @RequestMapping(value = "/list/{subscriberId}", method = RequestMethod.GET)
    public String subscriberMapping(Model model, @PathVariable String subscriberId) {
        model.addAttribute("date", LocalDateTime.now());
        model.addAttribute("subscriber", signupRepository.findWithSubscriberId(subscriberId));
        return "list-subscriber";
    }

    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    @ResponseBody
    java.util.Properties debug() {
        return System.getProperties();
    }
}
