package dungeon

import Vector2

class FloorCreator(val floor: Floor, val start: Vector2) {

    val startTile = floor.getTile(start)!!

    val visited = mutableMapOf(startTile to 0)


    fun visit(fromTile: FloorTile, toTile: FloorTile, distance: Int) {

        println("Visiting: ${toTile.position}")

        visited[toTile] = distance

        val unvisitedNeighbors = (toTile.neighbors - visited.keys).shuffled()

        for (neighbor in unvisitedNeighbors) {
            if (neighbor !in visited) {
                visit(toTile, neighbor, distance + 1)
            }
        }

    }

    fun start() {
        visit(startTile, startTile, 0)

        for (z in 0 until floor.size.z) {
            var line = ""
            for (x in 0 until floor.size.x) {
                val entry = visited.entries.first { it.key.position == Vector2(x, z) }
                line += "${entry.value}\t"

            }
            println(line)
        }


        println(visited.entries.sortedBy { it.key.position.x })
    }

}