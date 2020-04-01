package dungeon

import Vector2
import dungeon.room.Room

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