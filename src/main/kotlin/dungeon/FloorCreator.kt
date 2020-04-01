package dungeon

import Vector2
import kotlin.math.abs

class FloorCreator(val floor: Floor, val start: Vector2) {

    val startTile = floor.getTile(start)!!

    val visited = mutableListOf(startTile)

    //val additionalLinks = sqrt(floor.size.area.toDouble()).toInt() * 2
    val additionalLinks = floor.size.area / 4

    // get all tiles with 2+ closed links
    // pick random weighted based on max distance from an adjacent tile

    private fun visit(fromTile: FloorTile, toTile: FloorTile, distance: Int) {
        
        visited += toTile
        toTile.dist = distance

        for (neighbor in toTile.neighbors.shuffled()) {
            // Skip neighbors with rooms
            if (neighbor.room != null) {
                continue
            }

            if (neighbor !in visited) {
                visit(toTile, neighbor, distance + 1)
            }
        }

    }

    private fun defaultLink() {
        for (tile in visited) {
            for (neighbor in tile.neighbors) {
                // Get direct dist neighbors
                if (abs(neighbor.dist - tile.dist) <= 1) {
                    tile.link(neighbor, Passage.OPEN)
                } else {
                    tile.link(neighbor, Passage.CLOSED)
                }
            }
        }
    }

    fun toStringBy(func: FloorTile.() -> Any): String {
        var box = ""
        for (z in 0 until floor.size.z) {
            var line = ""
            for (x in 0 until floor.size.x) {
                val entry = visited.find { it.position == Vector2(x, z) }
                line += "${if (entry != null) func(entry) else null}\t"

            }
            box += line + "\n"
        }
        return box
    }

    override fun toString() = toStringBy { dist }

    fun start() {
        visit(startTile, startTile, 0)
        defaultLink()

        println(this)
        println(toStringBy { furthestDist })


        println(visited.sortedBy { it.position.x })
    }

}