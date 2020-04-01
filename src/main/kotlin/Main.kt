import dungeon.Building
import dungeon.Floor
import dungeon.FloorCreator
import dungeon.dsl.DungeonBuilder

fun main() {

    infix fun Int.x(y: Int) = Vector2(this, y)

    /*


     */

    DungeonBuilder {

        val floor1 = addFloor("main", 30 x 30) {


        }

        println(floor1.name)

        println(floor1.tiles)

        val creator = FloorCreator(floor1, 0 x 0)

        creator.start()

        println(floor1.getTile(Vector2(1, 1))!!.links)




    }

}