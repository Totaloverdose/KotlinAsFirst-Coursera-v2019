@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import kotlin.math.sqrt

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
    val lowerCase = str.toLowerCase().filter { it != ' ' }
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
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val sumOfSquares: Double = v.fold(0.0) { res, elem ->
        res + elem * elem
    }

    return sqrt(sumOfSquares)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.size == 0)
        return 0.0

    val sum = list.fold(0.0) {res, elem ->
        res + elem
    }

    return sum / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)

    list.replaceAll{ it - mean }

    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0

    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }

    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = p.foldIndexed(0) { i, res, elem ->
    res + elem * pow(x, i)
}

fun pow(x: Int, deg: Int): Int {
    var res = 1

    for (i in 1..deg)
        res *= x

    return res
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0

    list.replaceAll {
        sum += it
        sum
    }

    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    val factors: MutableList<Int> = mutableListOf()

    while(!isPrime(number)) {
        val nextDivisor = minDivisor(number)
        factors.add(nextDivisor)
        number /= nextDivisor
    }
    factors.add(number)

    return factors
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val result: MutableList<Int> = mutableListOf()

    while (number != 0) {
        result.add(0, number % base)
        number /= base
    }

    if (result.size == 0)
        result.add(0)

    return result
}

/**
 * Сложная
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
    val resList: List<Int> = convert(n, base)

    return resList.joinToString(
        separator = "",
        transform = { it.toString(base) }
    )
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    return digits.foldIndexed(0) {i, res, digit ->
        res + digit * pow(base, digits.size - i - 1)
    }
}

/**
 * Сложная
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
    var result = 0

    str.forEachIndexed { i, c ->
        val equalizer: Int
        if ((c >= 'a') and (c <= 'z')) {
            equalizer = 87
        } else {
            equalizer = 48
        }
        result += (c.toInt() - equalizer) * pow(base, str.length - i - 1)
    }

    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var resultString = ""
    var number = n

    val thousands = number / 1000
    number %= 1000
    for (i in 1..thousands) {
        resultString += 'M'
    }

    var hundreds = number / 100
    number %= 100
    when {
        hundreds == 9 -> {
            resultString += "CM"
            hundreds -= 9
        }
        hundreds >= 5 -> {
            resultString += 'D'
            hundreds -= 5
        }
        hundreds == 4 -> {
            resultString += "CD"
            hundreds -= 4
        }
    }
    for (i in 1..hundreds) {
        resultString += 'C'
    }

    var dozens = number / 10
    number %= 10
    when {
        dozens == 9 -> {
            resultString += "XC"
            dozens -= 9
        }
        dozens >= 5 -> {
            resultString += "L"
            dozens -= 5
        }
        dozens == 4 -> {
            resultString += "XL"
            dozens -= 4
        }
    }
    for (i in 1..dozens) {
        resultString += 'X'
    }

    when {
        number == 9 -> {
            resultString += "IX"
            number -= 9
        }
        number >= 5 -> {
            resultString += 'V'
            number -= 5
        }
        number == 4 -> {
            resultString += "IV"
            number -= 4
        }
    }
    for (i in 1..number) {
        resultString += 'I'
    }

    return resultString
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var resultString = ""
    val listNumber: MutableList<Int> = convert(n, 10).toMutableList()

    val hundredsOfThousands = getAndRemoveFirstIfSizeNotLess(listNumber, 6)
    val dozensOfThousands = getAndRemoveFirstIfSizeNotLess(listNumber, 5)
    val thousands = getAndRemoveFirstIfSizeNotLess(listNumber, 4)
    val hundreds = getAndRemoveFirstIfSizeNotLess(listNumber, 3)
    val dozens = getAndRemoveFirstIfSizeNotLess(listNumber, 2)
    val units = getAndRemoveFirstIfSizeNotLess(listNumber, 1)

    val resThousands = numToRussianWords(hundredsOfThousands, dozensOfThousands, thousands, true)
    val resUnits = numToRussianWords(hundreds, dozens, units)
    resultString += resThousands
    if (!resThousands.equals("") && !resUnits.equals(""))
        resultString += ' '
    resultString += resUnits

    return resultString
}

/*
    type 1: hundreds
    type 2: dozens
    type 3: units
    type 4: *teens
 */
fun numToRussianWord(digit: Int, type: Int, isThousands: Boolean = false): String {
    return when (type) {
        1 -> {
            when (digit) {
                1 -> "сто"
                2 -> "двести"
                3 -> "триста"
                4 -> "четыреста"
                5 -> "пятьсот"
                6 -> "шестьсот"
                7 -> "семьсот"
                8 -> "осемьсот"
                9 -> "девятьсот"
                else -> ""
            }
        }
        2 -> {
            when (digit) {
                1 -> "десять"
                2 -> "двадцать"
                3 -> "тридцать"
                4 -> "сорок"
                5 -> "пятьдесят"
                6 -> "шестьдесят"
                7 -> "семьдесят"
                8 -> "восемьдесят"
                9 -> "девяносто"
                else -> ""
            }
        }
        3 -> {
            when (digit) {
                1 -> if (isThousands) "одна" else "один"
                2 -> if (isThousands) "две" else "два"
                3 -> "три"
                4 -> "четыре"
                5 -> "пять"
                6 -> "шесть"
                7 -> "семь"
                8 -> "восемь"
                9 -> "девять"
                else -> ""
            }
        }
        4 -> {
            when (digit) {
                10 -> "десять"
                11 -> "одиннадцать"
                12 -> "двенадцать"
                13 -> "тринадцать"
                14 -> "четырнадцать"
                15 -> "пятнадцать"
                16 -> "шестнадцать"
                17 -> "семнадцать"
                18 -> "восемнадцать"
                19 -> "девятнадцать"
                else -> ""
            }
        }
        else -> ""
    }
}

fun numToRussianWords(hundreds: Int, dozens: Int, units: Int, isThousands: Boolean = false): String {
    var resultString = ""

    if (hundreds > 0) {
        resultString += numToRussianWord(hundreds, 1)
        if (dozens != 0 || units != 0) {
            resultString += ' '
        }
    }
    when {
        dozens == 1 -> {
            resultString += numToRussianWord(dozens * 10 + units, 4)
        }
        dozens > 0 -> {
            resultString += numToRussianWord(dozens, 2)
            if (units != 0) resultString += ' '
            resultString += numToRussianWord(units, 3, isThousands)
        }
        else -> {
            if (units > 0) {
                resultString += numToRussianWord(units, 3, isThousands)
            }
        }
    }

    if (isThousands && (hundreds != 0 || dozens != 0 || units != 0)) {
        if (dozens == 1 || units == 0 || units >= 5) {
            resultString += " тысяч"
        } else if (units == 1){
            resultString += " тысяча"
        } else {
            resultString += " тысячи"
        }
    }

    return resultString
}

fun getAndRemoveFirstIfSizeNotLess(mList: MutableList<Int>, size: Int): Int {
    var result = 0

    if (mList.size >= size) {
        result = mList.first()
        mList.removeAt(0)
        return result
    }

    return result
}