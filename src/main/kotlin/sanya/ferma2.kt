package dev.gaodi.fileserver.sanya

import kotlin.system.measureNanoTime

fun main() { measureNanoTime {
    val MAX = 150
    val numbers = (0..MAX).map { it.toBigInteger().pow(5) }
    val maxPow = numbers[MAX]
    for (a in 2..MAX) {
        println(a)
        val sumA = numbers[a]
        for (b in a..MAX) {
            val sumB = sumA + numbers[b]
            if(sumB > maxPow)
                break
            for (c in b..MAX) {
                val sumC = sumB + numbers[c]
                if(sumC > maxPow)
                    break
                for (d in c..MAX) {
                    val sumD = sumC + numbers[d]
                    if(sumD > maxPow)
                        break
                    for (e in (maxOf(a, b, c, d)+1)..MAX) {
                        val ePow = numbers[e]
                        if (sumD == ePow) {
                            println("$a $b $c $d $e")
                            println("${numbers[a]} ${numbers[b]} ${numbers[c]} ${numbers[d]} ${numbers[e]}")
                            println("=========")
                            return@measureNanoTime
                        }

//                        if( (sumD - ePow).absoluteValue < 100)
//                            println("$a $b $c $d $e, miss by ${(sumD - ePow).absoluteValue}")
                        if(sumD < ePow)
                            break
                    }
                }
            }
        }
    }
}.also { println("done in ${it/1_000_000_000.0}") } }