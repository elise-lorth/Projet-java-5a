package io.takima.demo.model

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigInteger
import java.sql.Timestamp
import javax.persistence.*

/**
 *
 */
@Entity(name = "reservations")

data class Reservation(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var id: Long?,
    @Column(name = "room") var room: BigInteger?,
    @Column(name = "start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    var start_date: Timestamp?,
    @Column(name = "end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    var end_date: Timestamp?
) {
    constructor() : this(null, null, null, null)

}
