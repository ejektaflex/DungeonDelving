data class Vector2(var x: Int, var z: Int) {

    val directNeighbors: List<Vector2>
        get() = listOf(
            Vector2(x - 1, z),
            Vector2(x + 1, z),
            Vector2(x, z - 1),
            Vector2(x, z + 1)
        )

    fun toVector3(height: Int) = Vector3(x, height, z)

    val area: Int
        get() = x * z




    override fun toString(): String {
        return "[$x,$z]"
    }

}