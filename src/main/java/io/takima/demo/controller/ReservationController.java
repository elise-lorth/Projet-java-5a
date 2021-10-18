package io.takima.demo.controller;

import io.takima.demo.DAO.JointureDAO;
import io.takima.demo.DAO.ReservationDAO;
import io.takima.demo.DAO.RoomDAO;
import io.takima.demo.DAO.UserDAO;
import io.takima.demo.model.Jointure;
import io.takima.demo.model.Reservation;
import io.takima.demo.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {

    private final UserDAO userDAO;
    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final JointureDAO jointureDAO;

    public ReservationController(UserDAO userDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, JointureDAO jointureDAO) {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.jointureDAO = jointureDAO;
    }

    public static class Dates{
        Long id;
        String date_d;
        String date_f;

        public Dates() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDate_d() {
            return date_d;
        }

        public void setDate_d(String date_d) {
            this.date_d = date_d;
        }

        public String getDate_f() {
            return date_f;
        }

        public void setDate_f(String date_f) {
            this.date_f = date_f;
        }
    }


    @GetMapping()
    public String showRooms(Model m) {
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("dates", new Dates());
        m.addAttribute("users", userDAO.findAll());

        return "reservation";
    }

    @PostMapping(params = "action=find")
    public String  findRoom( Model m,
                            String capacity,
                            String board,
                            String screen,
                            String tablet)
    {
        List<Room> foundRooms = (List<Room>) roomDAO.findAll();
        if(!capacity.equals("0"))
        {
            int capacityi = Integer.parseInt(capacity);
            foundRooms = roomDAO.findByCapacityGreaterThanEqual(
                    capacityi
            );
        }

        if(!board.equals("0"))
        {
            int boardi = Integer.parseInt(board);
            List<Room> foundRoomsB = roomDAO.findByBoardGreaterThanEqual(
                    boardi
            );
            foundRooms.retainAll(foundRoomsB);
        }

        if(!tablet.equals("0"))
        {
            int tableti = Integer.parseInt(tablet);
            List<Room> foundRoomsT = roomDAO.findByTabletGreaterThanEqual(
                    tableti
            );
            foundRooms.retainAll(foundRoomsT);
        }

        if(!screen.equals("0"))
        {
            int screeni = Integer.parseInt(screen);
            List<Room> foundRoomsS = roomDAO.findByScreenGreaterThanEqual(
                    screeni
            );
            foundRooms.retainAll(foundRoomsS);
        }


        m.addAttribute("search", foundRooms );
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("dates", new Dates());
        return "reservation";
    }



    @PostMapping(params = "action=add")
    public RedirectView addReservation(@ModelAttribute("Date") Dates dates, @RequestParam(value = "listuser" , required = false) int[] listuser , Reservation reservation, RedirectAttributes attrs) throws ParseException {
        attrs.addFlashAttribute("message", "Salle ajoutée avec succès");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = sdf.parse(dates.getDate_d());
        java.util.Date d2 = sdf.parse(dates.getDate_f());
        String formattedTime = output.format(d);
        String formattedTime2 = output.format(d2);

        Timestamp T = Timestamp.valueOf(formattedTime);
        Timestamp T2 = Timestamp.valueOf(formattedTime2);


        reservation.setStart_date(T);
        reservation.setEnd_date(T2);
        reservationDAO.save(reservation);
        long user;

        for (int j : listuser) {
            user = j;
            Jointure jointure = new Jointure(user);
            jointure.setReservation(reservation.getId());
            jointureDAO.save(jointure);

        }

        return new RedirectView("/accueilAdmin");
    }








}