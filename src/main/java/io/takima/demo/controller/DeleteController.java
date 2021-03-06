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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "")
public class DeleteController {

    private final UserDAO userDAO;
    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final JointureDAO jointureDAO;

    public DeleteController(UserDAO userDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, JointureDAO jointureDAO) {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.jointureDAO = jointureDAO;
    }

    @GetMapping("/deleteMember/{id}")
    public RedirectView deleteUser(@PathVariable("id") long id, Model model, RedirectAttributes attrs) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        attrs.addFlashAttribute("message", "Utilisateur supprimé avec succès");
        userDAO.delete(user);
        return new RedirectView("/accueilAdmin");
    }

    @GetMapping("/deleteRoom/{id}")
    public RedirectView deleteRoom(@PathVariable("id") long id, Model model, RedirectAttributes attrs) {
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        attrs.addFlashAttribute("message_room", "Salle supprimée avec succès");
        roomDAO.delete(room);
        return new RedirectView("/accueilAdmin");
    }


}
