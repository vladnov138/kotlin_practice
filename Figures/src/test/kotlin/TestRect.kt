import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TestRect {
    @Test
    @DisplayName("Copy constructor test")
    fun testCopyConstructor() {
        val rect1 = Rect(1, 2, 10, 20)
        val rect2 = Rect(rect1)
        assertEquals(rect1.x, rect2.x)
        assertEquals(rect1.y, rect2.y)
        assertEquals(rect1.width, rect2.width)
        assertEquals(rect1.height, rect2.height)
        assertNotEquals(rect1, rect2)
    }

    @Test
    @DisplayName("Moving test")
    fun testMove() {
        val x = 0
        val y = 1
        val rect = Rect(x, y, 10, 15)
        val newX = 10
        val newY = -1
        rect.move(newX, newY)
        assertEquals(x + newX, rect.x)
        assertEquals(y + newY, rect.y)
    }

    @Test
    @DisplayName("Area test")
    fun testArea() {
        val width = 5
        val height = 4
        val rect = Rect(0, 1, width, height)
        val area = (width * height).toFloat()
        assertEquals(area, rect.area())
    }

    @Test
    @DisplayName("Resize test")
    fun testResize() {
        val width = 5
        val height = 4
        val rect = Rect(10, 2, width, height)
        val zoom = 5
        rect.resize(zoom)
        assertEquals(width * zoom, rect.width)
        assertEquals(height * zoom, rect.height)
    }

    @Test
    @DisplayName("Clockwise rotate test")
    fun testRotateClockwise() {
        val x = 2
        val y = 2
        val rect = Rect(x, y, 4, 2)
        rect.rotate(RotateDirection.Clockwise, 3, -3)
        assertEquals(8, rect.x)
        assertEquals(-2, rect.y)
    }

    @Test
    @DisplayName("CounterClockwise rotate test")
    fun testRotateCounterClockwise() {
        val x = 2
        val y = 2
        val rect = Rect(x, y, 4, 2)
        rect.rotate(RotateDirection.CounterClockwise, 3, -3)
        assertEquals(-2, rect.x)
        assertEquals(-4, rect.y)
    }

    @Test
    @DisplayName("Rotate test")
    fun testRotate() {
        val x = 2
        val y = 2
        val rect = Rect(x, y, 4, 2)
        for (i in 0..3) {
            rect.rotate(RotateDirection.CounterClockwise, 3, -3)
        }
        assertEquals(x, rect.x)
        assertEquals(y, rect.y)
    }

    @Test
    @DisplayName("Setters test")
    fun testSetters() {
        val rect = Rect(2, 5, 2, 5)
        val newX = 3
        val newY = 10
        val newWidth = 20
        val newHeight = 50
        rect.x = newX
        rect.y = newY
        rect.width = newWidth
        rect.height = newHeight
        assertEquals(newX, rect.x)
        assertEquals(newY, rect.y)
        assertEquals(newWidth, rect.width)
        assertEquals(newHeight, rect.height)
    }

    @Test
    @DisplayName("ToString test")
    fun testToString() {
        val text = Rect(1, 1, 1, 2).toString()
        assertEquals("Rectangle (1, 1), width: 1, height: 2", text)
    }
}