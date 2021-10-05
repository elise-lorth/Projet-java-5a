package io.takima.demo.model;

import javax.persistence.*;

@Entity(name = "rooms")
data class Room(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var room_id: Long?,
    @Column(name = "name") var name: String?,
    @Column(name = "capacity") var capacity: Int?,
    @Column(name = "screen") var screen: Int?,
    @Column(name = "tablet") var tablet: Int?,
    @Column(name = "board") var board: Int?,
    @Column(name = "icon") var icon: String?
) {
    constructor() : this(null, null, null, null, null,  null, null)
}