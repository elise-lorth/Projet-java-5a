package io.takima.demo.model

import javax.persistence.*

/**
 *
 */
@Entity(name = "jointures")
data class Jointure(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var jointure_id: Long?,
    @Column(name = "user") var user: Long?,
    @Column(name = "reservation") var reservation: Long?
) {
    constructor() : this(null, null, null)
    constructor(user: Long?) : this(null,user,null )

}
