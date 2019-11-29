package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.repository.SignupRepository;

@Controller
public class ListController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listMapping(Model model) {
        model.addAttribute("subscribers", signupRepository.findAll());
        return "list";
    }
    @RequestMapping(value = "/list/{subscriberId}", method = RequestMethod.GET)
    public String subscriberMapping(Model model, @PathVariable String subscriberId) {
        model.addAttribute("subscriber", signupRepository.findWithSubscriberId(subscriberId));
        return "list-subscriber";
    }
}
