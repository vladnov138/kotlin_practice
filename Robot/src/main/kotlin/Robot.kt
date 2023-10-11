class Robot(x: Int, y: Int, direction: Direction) {
    var x = x
    private set
    var y = y
    private set
    var direction = direction
    private set

    fun stepForward() {
        when (direction) {
            Direction.RIGHT -> x++
            Direction.LEFT -> x--
            Direction.UP -> y++
            Direction.DOWN -> y--
        }
    }

    fun turnRight() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
            Direction.UP -> Direction.RIGHT
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.UP
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.DOWN
            Direction.UP -> Direction.LEFT
        }
    }

    override fun toString(): String {
        return "x: ${x}, y: ${y}, dir: ${direction}"
    }
}