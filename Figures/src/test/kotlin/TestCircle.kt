import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.math.PI

class TestCircle {
    @Test
    @DisplayName("Area test")
    fun testArea() {
        val r = 5
        val circle = Circle(0, 1, r)
        val area = (PI * r * r).toFloat()
        Assertions.assertEquals(area, circle.area())
    }

    @Test
    @DisplayName("Resize test")
    fun testResize() {
        val r = 5
        val circle = Circle(10, 2, r)
        val zoom = 5
        circle.resize(zoom)
        Assertions.assertEquals(r * zoom, circle.r)
    }

    @Test
    @DisplayName("Clockwise rotate test")
    fun testRotateClockwise() {
        val x = 2
        val y = 2
        val circle = Circle(x, y, 4)
        circle.rotate(RotateDirection.Clockwise, 3, -3)
        Assertions.assertEquals(8, circle.x)
        Assertions.assertEquals(-2, circle.y)
    }

    @Test
    @DisplayName("CounterClockwise rotate test")
    fun testRotateCounterClockwise() {
        val x = 2
        val y = 2
        val circle = Circle(x, y, 2)
        circle.rotate(RotateDirection.CounterClockwise, 3, -3)
        Assertions.assertEquals(-2, circle.x)
        Assertions.assertEquals(-4, circle.y)
    }

    @Test
    @DisplayName("Rotate test")
    fun testRotate() {
        val x = 2
        val y = 2
        val circle = Circle(x, y, 2)
        for (i in 0..3) {
            circle.rotate(RotateDirection.CounterClockwise, 3, -3)
        }
        Assertions.assertEquals(x, circle.x)
        Assertions.assertEquals(y, circle.y)
    }

    @Test
    @DisplayName("Setters test")
    fun testSetters() {
        val circle = Circle(2, 5, 3)
        val newX = 3
        val newY = 10
        val newR = 20
        circle.x = newX
        circle.y = newY
        circle.r = newR
        Assertions.assertEquals(newX, circle.x)
        Assertions.assertEquals(newY, circle.y)
        Assertions.assertEquals(newR, circle.r)
    }

    @Test
    @DisplayName("ToString test")
    fun testToString() {
        val text = Circle(1, 1, 1).toString()
        assertEquals("Circle (1, 1), radius: 1", text)
    }
}