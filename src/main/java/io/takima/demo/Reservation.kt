package io.takima.demo

import java.math.BigInteger
import java.util.*
import javax.persistence.*

/**
 *
 */
@Entity(name = "reservations")
data class Reservation(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id var id: Long?,
        @Column(name = "room") var room: BigInteger?,
        @Column(name = "start_date") var start_date: Date?,
        @Column(name = "end_date") var end_date: Date?) {
    constructor() : this(null, null, null, null)

}
