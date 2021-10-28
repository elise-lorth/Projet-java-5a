package io.takima.demo.controller;

import io.takima.demo.DAO.JointureDAO;
import io.takima.demo.DAO.ReservationDAO;
import io.takima.demo.DAO.RoomDAO;
import io.takima.demo.DAO.UserDAO;

import io.takima.demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.takima.demo.model.Room;
import io.takima.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

import java.math.BigInteger;

/**
 *
 */
@Controller
public class LibraryController {

    private final UserDAO userDAO;
    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final JointureDAO jointureDAO;

    public LibraryController(UserDAO userDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, JointureDAO jointureDAO) {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.jointureDAO = jointureDAO;
    }



    @GetMapping
    public String homePage(Model m) {
        Iterable<Jointure> listJointures = jointureDAO.findAll();
        List<User> membresReu = new ArrayList<>();
        listJointures.forEach((jointure) ->  {
            membresReu.add(userDAO.findById(jointure.getUser()).get());
        });

        m.addAttribute("jointures", listJointures);
        m.addAttribute("rooms", roomDAO.findAll());
        m.addAttribute("meets", reservationDAO.findAll());
        m.addAttribute("detailsMeet", membresReu);
        return "accueilPublic";
    }

    @GetMapping("/accueilPublic")
    public String PublicPage(Model m) {

        List<Jointure> listJointures = jointureDAO.findByReservation(3);
        List<User> membresReu = new ArrayList<>();
        listJointures.forEach((jointure) ->  {
            membresReu.add(userDAO.findById(jointure.getUser()).get());
        });
        m.addAttribute("jointures", listJointures);
        m.addAttribute("rooms", roomDAO.findAll());
        m.addAttribute("meets", reservationDAO.findAll());
        m.addAttribute("detailsMeet", membresReu);
        System.out.println(m.getAttribute("meets"));
        return "accueilPublic";
    }

    @GetMapping("/accueilAdmin")
    public String AdminPage(Model m) {
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("rooms", roomDAO.findAll());
        return "accueilAdmin";
    }




}