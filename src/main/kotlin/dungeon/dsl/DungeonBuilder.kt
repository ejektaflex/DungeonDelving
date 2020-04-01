package dungeon.dsl

import Vector2
import dungeon.Building

class DungeonBuilder(func: Building.() -> Unit) {

    val building = Building()


    init {
        func(building)
    }

}