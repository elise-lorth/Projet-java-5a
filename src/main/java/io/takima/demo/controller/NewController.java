package io.takima.demo.controller;

import io.takima.demo.DAO.JointureDAO;
import io.takima.demo.DAO.ReservationDAO;
import io.takima.demo.DAO.RoomDAO;
import io.takima.demo.DAO.UserDAO;
import io.takima.demo.model.Room;
import io.takima.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(path = "")
public class NewController {

    private final UserDAO userDAO;
    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final JointureDAO jointureDAO;

    public NewController(UserDAO userDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, JointureDAO jointureDAO) {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.jointureDAO = jointureDAO;
    }


    @GetMapping("/newMember")
    public String addUserPage(Model m) {
        m.addAttribute("user", new User());
        return "newMember";
    }

    @PostMapping("/newMember")
    public RedirectView createNewUser(@ModelAttribute User user, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message", "Utilisateur ajouté avec succès");
        userDAO.save(user);
        return new RedirectView("/accueilAdmin");
    }

    @GetMapping("/newRoom")
    public String addRoomPage(Model m) {
        m.addAttribute("room", new Room());
        return "newRoom";
    }

    @PostMapping("/newRoom")
    public RedirectView createNewRoom(@ModelAttribute Room room, RedirectAttributes attrs) {
        attrs.addFlashAttribute("message_room", "Salle ajoutée avec succès");
        roomDAO.save(room);
        return new RedirectView("/accueilAdmin");
    }



}
