package dungeon

import Vector2

class Building {

    val floors = mutableMapOf<String, Floor>()

    fun addFloor(name: String, size: Vector2, func: Floor.() -> Unit): Floor {
        val floor = Floor(this, size)
        floors[name] = floor
        func(floor)
        return floor
    }

}