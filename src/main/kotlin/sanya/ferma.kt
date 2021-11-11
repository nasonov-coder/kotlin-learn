package dev.gaodi.fileserver.sanya

import kotlin.math.pow

fun main() {
    val numbers = (0..151).map { it.toDouble().pow(5).toLong() }
    for( a in 2..151 ) {
        println(a)
        for (b in a..151)
            for (c in b..151)
                for (d in c..151)
                    for (e in maxOf(a, b, c, d)..151)
                        if (numbers[a] + numbers[b] + numbers[c] + numbers[d] == numbers[e]) {
                            println("$a $b $c $d $e")
                            println("${numbers[a]} ${numbers[b]} ${numbers[c]} ${numbers[d]} ${numbers[e]}")
                            println("=========")
                        }
    }
    println("done")
}