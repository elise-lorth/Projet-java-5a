package io.takima.demo.controller;

import io.takima.demo.DAO.UserDAO;
import io.takima.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(path = "/editMember")
public class EditController {

    private final UserDAO userDAO;

    public EditController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public String editUserPage(@PathVariable("id") long id, Model m) {
        Optional<User> user = userDAO.findById(id);
        m.addAttribute("user", user);
        return "editMember";
    }

    @PostMapping("/{id}")
    public RedirectView editUser(@PathVariable("id") long id, @ModelAttribute User user, BindingResult result, Model m) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
        userDAO.save(user);
        return new RedirectView("/accueilAdmin");
    }
}
