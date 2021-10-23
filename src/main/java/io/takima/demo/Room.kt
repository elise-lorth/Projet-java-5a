package io.takima.demo;

import javax.persistence.*;

@Entity(name = "rooms")
data class Room (

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id var room_id: Long?,
        @Column(name = "name") var name: String?,
        @Column(name = "capacity") var capacity: Int?,
        @Column(name = "screen") var screen: Boolean?,
        @Column(name = "tablet") var tablet: Boolean?,
        @Column(name = "board") var board: Boolean?){
    constructor() :this(null, null, null, false, false, false)
}