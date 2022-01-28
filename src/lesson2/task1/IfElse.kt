@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {                                      //перебор идет от 0 наверх с шагом 1? смена свича рушит логику
        age % 100 in 11..14 -> "$age лет"
        age % 10 == 1 -> "$age год"
        age % 10 in 2..4 -> "$age года"
        else -> "$age лет"
    }


/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double,
): Double {

    var sHalf: Double = (t1 * v1 + t2 * v2 + t3 * v3) / 2

    var s1: Double = t1 * v1
    var s2: Double = s1 + t2 * v2

    var v1Debug: Double = v1
    var v2Debug: Double = v2
    var v3Debug: Double = v3

    if (v1 == 0.0) {
        v1Debug = 1.0
    }

    if (v2 == 0.0) {
        v2Debug = 1.0
    }

    if (v3 == 0.0) {
        v3Debug = 1.0
    }

    return if (sHalf <= s1) {
        sHalf / v1Debug
    } else if (sHalf <= s2) {
        (s1 / v1Debug) + (sHalf - s1) / v2Debug
    } else s1 / v1Debug + s2 / v2Debug + (sHalf - s2 - s1) / v3Debug

}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int,
): Int {

    return if (((kingX == rookX1) and (kingY == rookY2)) or ((kingX == rookX2) and (kingY == rookY1))) {
        3
    } else if (((kingX == rookX1) and (kingY != rookY2)) or ((kingX != rookX2) and (kingY == rookY1))) {
        1
    } else if (((kingX != rookX1) and (kingY == rookY2)) or ((kingX == rookX2) and (kingY != rookY1))) {
        2
    } else
        0

}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int,
): Int {

    var kingBishopX: Int = abs(kingX - bishopX)
    var kingBishopY: Int = abs(kingY - bishopY)

    return if (((kingX == rookX) or (kingY == rookY)) and (kingBishopX == kingBishopY)) {
        3
    } else if (((kingX == rookX) or (kingY == rookY)) and (kingBishopX != kingBishopY)) {
        1
    } else if (((kingX != rookX) or (kingY != rookY)) and (kingBishopX == kingBishopY)) {
        2
    } else 0
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {

    var i: Int = 1

    var maxSide = max(max(a,b),c)
    var lessSide1 = min(min(a,b),c)
    var lessSide2 = a + b + c - maxSide - lessSide1


    if (maxSide < lessSide1 + lessSide2) {
        if (sqr(maxSide) == sqr(lessSide1) + sqr(lessSide2)) {
            i = 1
        } else if (sqr(maxSide) < sqr(lessSide1) + sqr(lessSide2)) {
            i = 0
        } else if (sqr(maxSide) > sqr(lessSide1) + sqr(lessSide2)) {
            i = 2
        }
    } else i = -1


    return when (i) {
        0 -> 0
        1 -> 1
        2 -> 2
        else -> -1
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {

    return if ((c in a..b) and (d !in a..b)) {
        abs(b - c)
    } else if ((c in a..b) and (d in a..b)) {
        abs(c - d)
    } else if ((a in c..d) and (b !in c..d)) {
        abs(d - a)
    } else if ((a in c..d) and (b in c..d)) {
        abs(b - a)
    } else -1

}

