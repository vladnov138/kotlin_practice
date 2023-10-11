import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TestMoving {
    @Test
    @DisplayName("Moving Test 1")
    fun testMovingFromStandard() {
        val robot = Robot(0, 0, Direction.UP)
        val coords: Array<IntArray> = Array(6) { IntArray(2) { 0 } }
        coords[0] = intArrayOf(10, 27)
        coords[1] = intArrayOf(10, 53)
        coords[2] = intArrayOf(2, 0)
        coords[3] = intArrayOf(-5, 0)
        coords[4] = intArrayOf(-3, 2)
        coords[5] = intArrayOf(-3, 5)
        for (coord in coords) {
            moveRobot(robot, coord[0], coord[1])
            assertEquals(coord[0], robot.x)
            assertEquals(coord[1], robot.y)
        }
    }

    @Test
    @DisplayName("Moving test 2")
    fun testMoving() {
        val robot = Robot(-5, 5, Direction.RIGHT)
        val coords: Array<IntArray> = Array(3) { IntArray(2) { 0 } }
        coords[0] = intArrayOf(-5, -5)
        coords[1] = intArrayOf(-5, -5)
        coords[2] = intArrayOf(-5, -15)
        for (coord in coords) {
            moveRobot(robot, coord[0], coord[1])
            while (robot.direction != Direction.UP) {
                robot.turnLeft()
            }
            assertEquals(coord[0], robot.x)
            assertEquals(coord[1], robot.y)
        }
    }

    @Test
    @DisplayName("Moving test 3")
    fun testMovingAndReturnDown() {
        val robot = Robot(1, 2, Direction.LEFT)
        val coords: Array<IntArray> = Array(3) { IntArray(2) { 0 } }
        coords[0] = intArrayOf(1, -4)
        coords[1] = intArrayOf(1, -5)
        coords[2] = intArrayOf(3, -15)
        for (coord in coords) {
            moveRobot(robot, coord[0], coord[1])
            while (robot.direction != Direction.LEFT) {
                robot.turnLeft()
            }
            assertEquals(coord[0], robot.x)
            assertEquals(coord[1], robot.y)
        }
    }

    @Test
    @DisplayName("Moving test 4")
    fun testMovingX() {
        val robot = Robot(1, 2, Direction.LEFT)
        val coords: Array<IntArray> = Array(3) { IntArray(2) { 0 } }
        coords[0] = intArrayOf(3, 2)
        coords[1] = intArrayOf(-1, 2)
        coords[2] = intArrayOf(0, 0)
        for (coord in coords) {
            moveRobot(robot, coord[0], coord[1])
            while (robot.direction != Direction.UP) {
                robot.turnLeft()
            }
            assertEquals(coord[0], robot.x)
            assertEquals(coord[1], robot.y)
        }
    }

    @Test
    @DisplayName("Moving test to the same direction")
    fun testMovingSameDirection() {
        val robot = Robot(0, 0, Direction.RIGHT)
        val coord = intArrayOf(2, 0)
        moveRobot(robot, coord[0], coord[1])
        assertEquals(coord[0], robot.x)
        assertEquals(coord[1], robot.y)
    }
}