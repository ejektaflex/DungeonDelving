class Vector3(var x: Int, var y: Int, var z: Int) {

    val sideNeighbors: List<Vector2>
        get() = listOf(
            Vector2(x - 1, z),
            Vector2(x + 1, z),
            Vector2(x, z - 1),
            Vector2(x, z + 1)
        )

}