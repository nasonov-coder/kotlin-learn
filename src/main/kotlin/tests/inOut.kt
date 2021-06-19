package dev.gaodi.fileserver.tests


fun toKek(): KKClass<Gen> {
    return KKClass<Gen2>()
}

open class Gen
class Gen2 : Gen()

class KKClass<out T>