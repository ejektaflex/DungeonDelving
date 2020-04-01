package dungeon

import Vector2
import dungeon.room.Room
import kotlin.math.abs

class FloorTile(val floor: Floor) {

    var room: Room? = null

    var dist = 0

    val links = mutableMapOf<FloorTile, Passage>()

    val position: Vector2
        get() = floor.tiles.entries.first { it.value == this }.key

    fun link(other: FloorTile, passage: Passage) {
        links[other] = passage
        other.links[this] = passage
    }

    fun isLinked(other: FloorTile): Boolean {
        return other in links
    }

    val furthestDist: Int
        get() {
            return abs(links.maxBy { abs(it.key.dist - dist) }!!.key.dist - dist)
        }

    fun unlink(other: FloorTile) {
        if (other in links) {
            links.remove(other)
        }
        if (this in other.links) {
            other.links.remove(this)
        }
    }

    override fun toString(): String {
        return "FT$position"
    }

    val neighbors: List<FloorTile>
        get() {
            return position.directNeighbors.mapNotNull {
                floor.tiles[it]
            }
        }

}