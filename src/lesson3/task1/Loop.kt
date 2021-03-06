@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        abs(n) == m -> 1
        abs(n) < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var num = n
    var digitsCount = 0

    if (n == 0)
        return 1

    while (num != 0) {
        digitsCount++
        num /= 10
    }

    return digitsCount
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 0)
        return 0

    var fib1 = 1
    var fib2 = 1

    for (i in 3..n) {
        val temp = fib1 + fib2
        fib1 = fib2
        fib2 = temp
    }

    return fib2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val largerNum = max(m, n)
    var res = largerNum

    while ((res % m != 0) or (res % n != 0))
        res += largerNum

    return res
}

/** +++++++++++++++++++
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0)
        return 2

    for (i in 3..n step 2)
        if (n % i == 0)
            return i

    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in (n / 2.0).toInt() downTo 1)
        if (n % i == 0)
            return i

    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var num1 = m
    var num2 = n

    if ((m == 1) or (n == 1))
        return true

    while(abs(num1 - num2) != 1) {
        if (num1 > num2) {
            if (num1 % num2 == 0)
                return false

            num1 -= num2
        } else {
            if (num2 % num1 == 0)
                return false

            num2 -= num1
        }
    }

    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val firstNum = sqrt(m.toDouble()).toInt()
    val lastNum = sqrt(n.toDouble()).toInt()
    for (i in firstNum..lastNum) {
        if ((sqr(i) >= m) and (sqr(i) <= n))
            return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X / 2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var stepsCount = 0
    var num = x

    while (num != 1) {
        if (num % 2 == 0) {
            num /= 2
        } else {
            num = 3 * num + 1
        }

        stepsCount++
    }

    return stepsCount
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val angle = x % (2 * PI)
    var isPositiveMember = true
    var i = 1
    var result = 0.0

    while (true) {
        val nextMember = angle.pow(i) / factorial(i)

        if (abs(nextMember) < eps)
            break

        if (isPositiveMember) {
            result += nextMember
        } else {
            result -= nextMember
        }

        i += 2
        isPositiveMember = !isPositiveMember
    }

    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val angle = x % (2 * PI)
    var isPositiveMember = true
    var degree = 0
    var result = 0.0

    while (true) {
        val nextMember = angle.pow(degree) / factorial(degree)

        if (nextMember < eps)
            return result

        if (isPositiveMember) {
            result += nextMember
        } else {
            result -= nextMember
        }

        degree += 2
        isPositiveMember = !isPositiveMember
    }
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num = n
    var result = 0

    while (num != 0) {
        val nextDigit = num % 10
        result = result * 10 + nextDigit
        num /= 10
    }

    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val revertNum = revert(n)

    return revertNum == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val lastDigit = n % 10
    var digitsToCheck = n / 10

    while (digitsToCheck != 0) {
        if (lastDigit != digitsToCheck % 10)
            return true

        digitsToCheck /= 10
    }

    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var digitsCount = 0
    var nextSqr: Int

    while(true) {
        nextSqr = sqr(i)
        digitsCount += digitNumber(nextSqr)
        if (digitsCount >= n)
            break

        i++
    }

    for (j in n until digitsCount) {
        nextSqr /= 10
    }

    return nextSqr % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var i = 1
    var digitsCount = 0
    var nextFib: Int

    while(true) {
        nextFib = fib(i)
        digitsCount += digitNumber(nextFib)
        if (digitsCount >= n)
            break

        i++
    }

    for (j in n until digitsCount) {
        nextFib /= 10
    }

    return nextFib % 10
}
