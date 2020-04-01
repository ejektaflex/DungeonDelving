package dungeon

import Vector2

class FloorCreator(val floor: Floor, val start: Vector2) {

    val startTile = floor.getTile(start)!!

    val visited = mutableListOf(startTile)


    private fun visit(fromTile: FloorTile, toTile: FloorTile, distance: Int) {

        println("Visiting: ${toTile.position}")

        visited += toTile
        toTile.dist = distance

        for (neighbor in toTile.neighbors.shuffled()) {
            // Skip neighbors with rooms
            if (neighbor.room != null) {
                continue
            }

            if (neighbor !in visited) {
                fromTile.link(toTile, Passage.OPEN)
                visit(toTile, neighbor, distance + 1)
            } else {
                fromTile.link(toTile, Passage.CLOSED)
            }
        }

        val unvisitedNeighbors = (toTile.neighbors - visited).shuffled()

        for (neighbor in unvisitedNeighbors) {
            if (neighbor !in visited) {
                visit(toTile, neighbor, distance + 1)
            }
        }

    }

    override fun toString(): String {
        var box = ""
        for (z in 0 until floor.size.z) {
            var line = ""
            for (x in 0 until floor.size.x) {
                val entry = visited.find { it.position == Vector2(x, z) }
                line += "${entry?.dist}\t"

            }
            box += line + "\n"
        }
        return box
    }

    fun start() {
        visit(startTile, startTile, 0)

        println(this)


        println(visited.sortedBy { it.position.x })
    }

}