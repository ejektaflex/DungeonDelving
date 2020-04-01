package dungeon

import Vector2

class Floor(val building: Building, var size: Vector2 = Vector2(0, 0)) {

    val tiles = mutableMapOf<Vector2, FloorTile>()

    val name: String by lazy {
        building.floors.entries.first { it.value == this }.key
    }

    init {
        for (x in 0 until size.x) {
            for (z in 0 until size.z) {
                addTile(Vector2(x, z))
            }
        }
    }

    fun getTile(vec: Vector2): FloorTile? {
        return tiles[vec]
    }

    private fun addTile(pos: Vector2, func: FloorTile.() -> Unit = {}): FloorTile {
        val tile = FloorTile(this)
        tiles[pos] = tile
        func(tile)
        return tile
    }


}