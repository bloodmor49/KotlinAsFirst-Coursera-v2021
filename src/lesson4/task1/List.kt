@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.pow
//import ru.spbstu.wheels.NullableMonad.map
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    return when {
        v.isEmpty() -> 0.0
        else -> sqrt(v.sumOf { it * it })
    }
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return when {
        list.isEmpty() -> 0.0
        else -> list.sum() / list.size
    }
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    return if (list.isEmpty()) list
    else {
        val mean = mean(list)
        for (i in list.indices) {
            list[i] -= mean
        }
        list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    return if (a.isEmpty() || b.isEmpty()) 0
    else {
        for (i in a.indices) {
            c += a[i] * b[i]
        }
        c
    }
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {

    var c = 0
    return if (p.isEmpty()) 0
    else {
        for (i in p.indices) {
            c += p[i] * x.toDouble().pow(i).toInt()
        }
        c
    }

}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {

    return if (list.isEmpty()) list
    else {
        for (i in 1 until list.size) {
            list[i] += list[i - 1]
        }
        list
    }

}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {

    val list = mutableListOf<Int>()
    var i = 2
    var nLast = n

    return if (nLast == 0) list + 0
    else {
        while (nLast > 1) {
            if (nLast % i == 0) {
                list += i
                nLast /= i
            } else i++

        }
        list
    }

}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */

fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {

    val list = mutableListOf<Int>()
    var nLast = n

    return if (nLast == 0) list + 0
    else {
        while (nLast > 0) {
            list += (nLast % base)
            nLast /= base
        }
        list.reversed()
    }
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {

    return if (base !in 1 until 37) "Вне заданного диапазон"
    else {

        val baseList = convert(n, base)
        val wordsList = "0123456789abcdefghijklmnopqrstuvwxyz"
        var str = ""

        for (i in baseList.indices) str += wordsList[baseList[i]]
        return str
    }
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {

    var result = 0
    val list = digits.reversed()

    for (i in list.indices) result += list[i] * base.toDouble().pow(i).toInt()
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {

    val baseList = mutableListOf<Int>()
    val wordsList = "0123456789abcdefghijklmnopqrstuvwxyz"

    for (i in str.indices) {
        for (j in wordsList.indices) {
            if (wordsList[j] == str[i]) baseList += j
        }
    }
    return decimal(baseList, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {

    var str = ""
    val romNumb = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val romAlph = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    var number = n
    var i = 0

    while (number > 0) {
        while (number - romNumb[i] >= 0) {
            str += romAlph[i]
            number -= romNumb[i]
        }
        i += 1
    }
    return str
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {

    var nList = mutableListOf<Int>()
    var nString = n.toString()
    var finalList = mutableListOf<String>()

    for (element in nString) nList.add(element.digitToInt())

    if (nList.size > 3) {
        finalList = secondThreeDots(nList.subList(0, nList.lastIndex - 2)) as MutableList<String>
        finalList += firstThreeDots(nList)
    } else finalList = firstThreeDots(nList) as MutableList<String>

    return finalList.joinToString(separator = " ")
}

fun firstThreeDots(list: List<Int>): List<String> {

    val numbers100 =
        listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val numbers20To90 =
        listOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val numbers10To19 = listOf("десять",
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать")
    val numbers1To9 = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")

    var finalList = mutableListOf<String>()

    for (i in list.indices) {
        if (list[i] != 0) {
            when (i) {
                list.size - 3 -> finalList.add(numbers100[list[i] - 1])
                list.size - 2 -> if (list[i] == 1) {
                    finalList.add(numbers10To19[list[i + 1]])
                    break
                } else finalList.add(numbers20To90[list[i] - 2])
                list.size - 1 -> finalList.add(numbers1To9[list[i] - 1])
            }
        }
    }

    return finalList
}

fun secondThreeDots(list: List<Int>): List<String> {

    val numbers100 =
        listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val numbers20To90 =
        listOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val numbers10To19 = listOf("десять",
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать")
    val endNumbers1To9 = listOf("одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")

    var finalList = mutableListOf<String>()

    for (i in list.indices) {
        if (list[i] != 0) {
            when (i) {
                list.size - 3 -> finalList.add(numbers100[list[i] - 1])
                list.size - 2 -> if (list[i] == 1) {
                    finalList.add(numbers10To19[list[i + 1]])
                    finalList.add("тысяч")
                    break
                } else finalList.add(numbers20To90[list[i] - 2])
                list.size - 1 -> {
                    finalList.add(endNumbers1To9[list[i] - 1])
                    if (list[i] == 1) finalList.add("тысяча")
                    else if (list[i] >= 5) finalList.add("тысяч")
                    else finalList.add("тысячи")
                }
            }
        }
    }

    if (list[list.lastIndex] == 0) finalList.add("тысяч")

    return finalList
}

fun main() {
    println(russian(725356))
}
