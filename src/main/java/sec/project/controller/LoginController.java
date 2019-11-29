package sec.project.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Login;
import sec.project.repository.Database;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;


@Controller
public class LoginController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadForm(Model model, HttpSession httpSession) {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String password) throws SQLException {
        Database db = new Database ();
        try {
            Login login = db.getAccount(username, password);
            if (login != null) {
                return "/list/{subscriberId}";
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}