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
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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

        m.addAttribute("errorMessage");
        m.addAttribute("errorMessage2");
        addAttributes(m);
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




        LocalDateTime now = LocalDateTime.now();
        Timestamp TStart = Timestamp.valueOf(now);
        Timestamp TEnd = new Timestamp(TStart.getTime());


        if(dates.getPreference() ==null ) {

            try {
                TStart = ParseTimestamp(dates.getDate_d());
            } catch (Exception E) {
                m.addAttribute("errorMessage", "Veuillez choisir une date");
                addAttributes(m);

                return "reservation";
            }
            long hours = Long.parseLong(dates.getHours());
            long minutes = Long.parseLong(dates.getMinutes());
            TEnd = new Timestamp(TStart.getTime() + (hours*60 + minutes)*1000 *60);

        } else {

            Calendar calendar = Calendar.getInstance();
            switch (dates.getPreference()) {

                case "matin":
                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10) {
                        calendar.set(Calendar.HOUR_OF_DAY,Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+2);

                    } else {
                        calendar.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
                        calendar.set(Calendar.HOUR_OF_DAY,9);

                    }
                    break;
                case "apresmidi":
                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 18) {
                        calendar.set(Calendar.HOUR_OF_DAY,Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+2);


                    } else {
                        calendar.set(Calendar.HOUR_OF_DAY,17);
                        calendar.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);

                    }
                    break;

                case "asap":

                    calendar.set(Calendar.HOUR_OF_DAY,Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+2);
                    if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 22)
                    {

                        calendar.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
                        calendar.set(Calendar.HOUR_OF_DAY,9);
                    }

                    break;
                case "semainepro":

                    calendar.set(Calendar.WEEK_OF_MONTH,Calendar.getInstance().get(Calendar.WEEK_OF_MONTH)+1);
                    calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
                    calendar.set(Calendar.HOUR_OF_DAY,9);


                    break;


                case "moispro":

                    calendar.set(Calendar.MONTH,Calendar.getInstance().get(Calendar.MONTH)+1);
                    calendar.set(Calendar.DAY_OF_MONTH,1);
                    calendar.set(Calendar.HOUR_OF_DAY,9);
                    break;

                case "":
                    m.addAttribute("errorMessage", "Veuillez choisir une date");
                    addAttributes(m);

                    return "reservation";




            }

            TStart = new Timestamp(calendar.getTimeInMillis());
            long hours = Long.parseLong(dates.getHours());
            long minutes = Long.parseLong(dates.getMinutes());
            TEnd = new Timestamp(TStart.getTime() + (hours*60 + minutes)*1000 *60);
            dates.setDate_d(ReverseTimestamp(TStart));
        }

            List<Reservation> reservations = (List<Reservation>) reservationDAO.findAll();

        Timestamp finalTStart = TStart;
        List<Room> finalFoundRooms = foundRooms;
        Timestamp finalTEnd = TEnd;

        reservations.forEach((reservation) ->
                {
                    if((!(finalTStart.after(reservation.getEnd_date()) || finalTEnd.before(reservation.getStart_date()) ) || (finalTStart.before(reservation.getStart_date()) && finalTEnd.after(reservation.getEnd_date())) ))
                    {
                        finalFoundRooms.remove(roomDAO.findById(reservation.getRoom().longValue()).get());
                       }
                }
                );
        if(finalFoundRooms.isEmpty()) {
            m.addAttribute("errorMessage3", "Aucune salle libre, veuillez changer les conditions");
            addAttributes(m);

            return "reservation";
        }

        m.addAttribute("search", finalFoundRooms );
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("datesF", dates);
        return "reservation";
    }

    @PostMapping(params = "action=add")
    public String addReservation(@ModelAttribute("datesF") Dates dates,Model m, @RequestParam(value = "listuser" , required = false) int[] listuser , Reservation reservation) throws ParseException {


        Timestamp TStart;
        try {
            TStart = ParseTimestamp(dates.getDate_d());
        } catch (Exception E) {
            m.addAttribute("errorMessage", "Veuillez choisir une date");
            addAttributes(m);




            return "reservation";
        }

        if(reservation.getRoom() == null) {
            m.addAttribute("errorMessage2", "Veuillez choisir une salle");
            addAttributes(m);
            return "reservation";
        }


        long hours = Long.parseLong(dates.getHours());
        long minutes = Long.parseLong(dates.getMinutes());
        Timestamp TEnd = new Timestamp(TStart.getTime() + (hours * 60 + minutes) * 1000 * 60);

        reservation.setStart_date(TStart);
        reservation.setEnd_date(TEnd);
        reservationDAO.save(reservation);
        long user;
        if (listuser != null) {
            for (int j : listuser) {
                user = j;
                Jointure jointure = new Jointure(user);
                jointure.setReservation(reservation.getReservation_id());
                jointureDAO.save(jointure);
            }
        }


        return "accueilAdmin";
    }

    public Timestamp ParseTimestamp(String date) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = input.parse(date);
        String formattedTime = output.format(d);
        Timestamp T = Timestamp.valueOf(formattedTime);
        return T;
    }

    public String ReverseTimestamp(Timestamp timestamp) throws ParseException {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String TString = timestamp.toString();
        java.util.Date d = output.parse(TString);
        return input.format(d);
    }

    public void addAttributes(Model m) {
        m.addAttribute("reservation", new Reservation());
        m.addAttribute("room", new Room() );
        m.addAttribute("users", userDAO.findAll());
        m.addAttribute("rooms", roomDAO.findAll());
        m.addAttribute("dates", new Dates());
    }



}