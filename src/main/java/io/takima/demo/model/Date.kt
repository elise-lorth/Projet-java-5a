package io.takima.demo.model

import javax.persistence.*

@Entity(name = "dates")
data class Date(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id var id: Long?,
        @Column(name = "date_d") var date_d: String?,
        @Column(name = "date_f") var date_f: String?){
        constructor() : this(null, null, null)

}