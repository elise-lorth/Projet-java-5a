package io.takima.demo.controller;

import io.takima.demo.DAO.UserDAO;
import io.takima.demo.model.User;
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
@Controller
public class LibraryController {

    private final UserDAO userDAO;

    public LibraryController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String homePage(Model m) {
        m.addAttribute("users", userDAO.findAll());
        return "accueilPublic";
    }

    @GetMapping("/accueilPublic")
    public String PublicPage(Model m) {
        m.addAttribute("users", userDAO.findAll());
        return "accueilPublic";
    }

    @GetMapping("/accueilAdmin")
    public String AdminPage(Model m) {
        m.addAttribute("users", userDAO.findAll());
        return "accueilAdmin";
    }

    @GetMapping("/newMember")
    public String addUserPage(Model m) {
        m.addAttribute("user", new User());
        return "newMember";
    }

    @GetMapping("/newRoom")
    public String addRoomPage(Model m) {
        m.addAttribute("user", new User());
        return "newRoom";
    }

    @GetMapping("/reservation")
    public String addReservationPage(Model m) {
        m.addAttribute("user", new User());
        return "reservation";
    }

    @PostMapping("/newMember")
    public RedirectView createNewUser(@ModelAttribute User user, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès");
        userDAO.save(user);
        return new RedirectView("/accueilAdmin");
    }


    @GetMapping("/deleteMember/{id}")
    public RedirectView deleteUser(@PathVariable("id") long id, Model model) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userDAO.delete(user);
        return new RedirectView("/accueilAdmin");
    }
}