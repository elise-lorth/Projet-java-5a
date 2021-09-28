package io.takima.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 *
 */
@RequestMapping("/")
@Controller
public class LibraryController {

    private final UserDAO userDAO;

    public LibraryController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String homePage(Model m) {
        m.addAttribute("users", userDAO.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String addUserPage(Model m) {
        m.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public RedirectView createNewUser(@ModelAttribute User user, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès");
        userDAO.save(user);
        return new RedirectView("/");
    }

    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable("id") long id, Model m) {
            Optional<User> user = userDAO.findById(id);


        m.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, @ModelAttribute User user,BindingResult result, Model m) {
            if (result.hasErrors()) {
                user.setId(id);
                return "update-user";
            }
        userDAO.save(user);
        return ("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userDAO.delete(user);
        return "redirect:/";
    }

}
