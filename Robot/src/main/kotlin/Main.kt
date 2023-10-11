fun turnRobot(r: Robot, direction: Direction, toCoord: Int, axis: Axis) {
    if (axis == Axis.Y && r.y != toCoord) {
        if (r.direction == Direction.LEFT && direction == Direction.UP || r.direction == Direction.RIGHT &&
            direction == Direction.DOWN) {
            r.turnRight()
        } else if (r.direction != Direction.UP && r.direction != Direction.DOWN) {
            r.turnLeft()
        } else {
            while (r.direction != direction) {
                r.turnLeft()
            }
        }
        while (r.direction == direction && r.y != toCoord) {
            r.stepForward()
        }
    } else if (axis == Axis.X && r.x != toCoord){
        if (r.direction == Direction.UP && direction == Direction.RIGHT || r.direction == Direction.DOWN &&
            direction == Direction.LEFT) {
            r.turnRight()
        } else if (r.direction != Direction.LEFT && r.direction != Direction.RIGHT) {
            r.turnLeft()
        } else {
            while (r.direction != direction) {
                r.turnLeft()
            }
        }
        while (r.direction == direction && r.x != toCoord) {
            r.stepForward()
        }
    }
}

fun moveRobot(r: Robot, toX: Int, toY: Int) {
    val directionX = if (toX < r.x) Direction.LEFT else Direction.RIGHT
    val directionY = if (toY < r.y) Direction.DOWN else Direction.UP

    // Если направление совпало с нужным, то идем вперед сразу
    while ((r.direction == directionX && r.x != toX) || (r.direction == directionY && r.y != toY)) {
        r.stepForward()
    }
    // Если мы смотрим в параллельно оси Х, то нам выгоднее повернуться вправо или влево по оси Y
    // Если направление совпало - мы прошли к нужной координате в прошлом цикле
    // Иначе нам надо повернуться на 180 градусов (два поворота). Выгоднее сначала сделать один поворот
    // к оси Y, пройти и затем еще один поворот и снова пройти.
    if (r.direction == Direction.LEFT || r.direction == Direction.RIGHT) {
        turnRobot(r, directionY, toY, Axis.Y)
        turnRobot(r, directionX, toX, Axis.X)
    } else {
        turnRobot(r, directionX, toX, Axis.X)
        turnRobot(r, directionY, toY, Axis.Y)
    }
}

fun main() {
    println("Генерация робота")
    val robot = Robot(3, -5, Direction.DOWN)
    println("Робот после генерации: " + robot)
    moveRobot(robot, 10, 53)
    println("Робот идет к позиции 10,53: " + robot)
    moveRobot(robot, 2, 0)
    println("Робот идет к позиции 2,0: " + robot)
}