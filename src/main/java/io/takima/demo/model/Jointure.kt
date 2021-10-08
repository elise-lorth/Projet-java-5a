package io.takima.demo.model

import javax.persistence.*

/**
 *
 */
@Entity(name = "jointures")
data class Jointure(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var id: Long?,
    @Column(name = "id_user") var id_user: Long?,
    @Column(name = "id_reservation") var id_reservation: Long?
) {
    constructor() : this(null, null, null)

}
