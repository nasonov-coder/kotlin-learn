package dev.gaodi.fileserver.tests

import tests.sout

fun main() {
    "\\d{15}".toRegex().matches("123456789012345").sout()
}