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

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public static class Dates{ // Classe pour récuperer les dates au format String dans le html
        Long id;
        String date_d;
        String hours;
        String minutes;
        String preference;

        public Dates() {}

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

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }

        public String getMinutes() {
            return minutes;
        }

        public void setMinutes(String minutes) {
            this.minutes = minutes;
        }

        public String getPreference() {
            return preference;
        }

        public void setPreference(String preference) {
            this.preference = preference;
        }
    }


    @GetMapping()
    public String showRooms(Model m) {
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("dates", new Dates());
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("rooms", roomDAO.findAll());
        m.addAttribute("dates", new Dates());
        return "reservation";
    }

    @PostMapping(params = "action=find")
    public String  findRoom( Model m,
                            String capacity,
                            String board,
                            String screen,
                            String tablet,
                             String name,
                             @ModelAttribute("dates") Dates dates) throws ParseException {
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("rooms", roomDAO.findAll());
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
            foundRooms.retainAll(roomDAO.findByBoardGreaterThanEqual(
                    boardi
            ));
        }

        if(!tablet.equals("0"))
        {
            int tableti = Integer.parseInt(tablet);

            foundRooms.retainAll(roomDAO.findByTabletGreaterThanEqual(
                    tableti
            ));
        }

        if(!screen.equals("0"))
        {
            int screeni = Integer.parseInt(screen);
            foundRooms.retainAll(roomDAO.findByScreenGreaterThanEqual(
                    screeni
            ));
        }
        if(!name.equals(""))
        {
            foundRooms.retainAll(roomDAO.findByName(name));
        }
        Timestamp TStart = ParseTimestamp(dates.getDate_d());
        long hours = Long.parseLong(dates.getHours());
        long minutes = Long.parseLong(dates.getMinutes());
        Timestamp TEnd = new Timestamp(TStart.getTime() + (hours*60 + minutes)*1000);
        List<Reservation> reservations = (List<Reservation>) reservationDAO.findAll();
        List<Room> finalFoundRooms = foundRooms;
        reservations.forEach((reservation) ->
                {
                    if(!(TStart.after(reservation.getEnd_date()) || TEnd.before(reservation.getStart_date()) ) && roomDAO.findById(Objects.requireNonNull(reservation.getRoom()).longValue()).isPresent())
                    {
                        finalFoundRooms.remove(roomDAO.findById(reservation.getRoom().longValue()).get());
                       }
                }
                );


        m.addAttribute("search", finalFoundRooms );
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("datesF", dates);
        return "reservation";
    }

    @PostMapping(params = "action=add")
    public RedirectView addReservation(@ModelAttribute("dates") Dates dates, @RequestParam(value = "listuser" , required = false) int[] listuser , Reservation reservation) throws ParseException {

        Timestamp TStart = ParseTimestamp(dates.getDate_d());
        long hours = Long.parseLong(dates.getHours());
        long minutes = Long.parseLong(dates.getMinutes());
        Timestamp TEnd = new Timestamp(TStart.getTime() + (hours*60 + minutes)*1000*60);

        reservation.setStart_date(TStart);
        reservation.setEnd_date(TEnd);
        reservationDAO.save(reservation);
        long user;
        if(listuser!=null) {
            for (int j : listuser) {
                user = j;
                Jointure jointure = new Jointure(user);
                jointure.setReservation(reservation.getReservation_id());
                jointureDAO.save(jointure);
            }
        }


        return new RedirectView("/accueilAdmin");
    }

    public Timestamp ParseTimestamp(String date) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = input.parse(date);
        String formattedTime = output.format(d);
        Timestamp T = Timestamp.valueOf(formattedTime);
        return T;
    }



}