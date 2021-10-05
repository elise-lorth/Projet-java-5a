package io.takima.demo.controller;

import io.takima.demo.DAO.JointureDAO;
import io.takima.demo.DAO.ReservationDAO;
import io.takima.demo.DAO.RoomDAO;
import io.takima.demo.DAO.UserDAO;
import io.takima.demo.model.Room;
import io.takima.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(path = "")
public class EditController {

    private final UserDAO userDAO;
    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final JointureDAO jointureDAO;

    public EditController(UserDAO userDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, JointureDAO jointureDAO)
    {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.jointureDAO = jointureDAO;
    }

    @GetMapping("/editMember/{id}")
    public String editUserPage(@PathVariable("id") long id, Model m) {
        Optional<User> user = userDAO.findById(id);
        m.addAttribute("user", user);
        return "editMember";
    }

    @PostMapping("/editMember/{id}")
    public RedirectView editUser(@PathVariable("id") long id, @ModelAttribute User user, BindingResult result, Model m) {
        userDAO.save(user);
        return new RedirectView("/accueilAdmin");
    }

    @GetMapping("/editRoom/{room_id}")
    public String editRoomPage(@PathVariable("room_id") long room_id, Model m) {
        Optional<Room> room = roomDAO.findById(room_id);
        System.out.println(room);
        m.addAttribute("room", room);
        return "/editRoom";
    }

    @PostMapping("/editRoom/{room_id}")
    public RedirectView editRoom(@PathVariable("room_id") long id, @ModelAttribute Room room, BindingResult result, Model m) {
        roomDAO.save(room);
        return new RedirectView("/accueilAdmin");
    }
}
