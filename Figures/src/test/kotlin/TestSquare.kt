import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TestSquare {
    @Test
    @DisplayName("Area test")
    fun testArea() {
        val side = 5
        val square = Square(0, 1, side)
        val area = (side * side).toFloat()
        Assertions.assertEquals(area, square.area())
    }

    @Test
    @DisplayName("Resize test")
    fun testResize() {
        val side = 5
        val square = Square(10, 2, side)
        val zoom = 5
        square.resize(zoom)
        Assertions.assertEquals(side * zoom, square.side)
    }

    @Test
    @DisplayName("Clockwise rotate test")
    fun testRotateClockwise() {
        val x = 2
        val y = 2
        val square = Square(x, y, 4)
        square.rotate(RotateDirection.Clockwise, 3, -3)
        Assertions.assertEquals(8, square.x)
        Assertions.assertEquals(-2, square.y)
    }

    @Test
    @DisplayName("CounterClockwise rotate test")
    fun testRotateCounterClockwise() {
        val x = 2
        val y = 2
        val square = Square(x, y, 4)
        square.rotate(RotateDirection.CounterClockwise, 3, -3)
        Assertions.assertEquals(-2, square.x)
        Assertions.assertEquals(-4, square.y)
    }

    @Test
    @DisplayName("Rotate test")
    fun testRotate() {
        val x = 2
        val y = 2
        val square = Square(x, y, 4)
        for (i in 0..3) {
            square.rotate(RotateDirection.CounterClockwise, 3, -3)
        }
        Assertions.assertEquals(x, square.x)
        Assertions.assertEquals(y, square.y)
    }

    @Test
    @DisplayName("Setters test")
    fun testSetters() {
        val square = Square(2, 5, 2)
        val newX = 3
        val newY = 10
        val newSide = 50
        square.x = newX
        square.y = newY
        square.side = newSide
        Assertions.assertEquals(newX, square.x)
        Assertions.assertEquals(newY, square.y)
        Assertions.assertEquals(newSide, square.side)
    }

    @Test
    @DisplayName("ToString test")
    fun testToString() {
        val text = Square(1, 1, 1).toString()
        Assertions.assertEquals("Square (1, 1), side: 1", text)
    }
}