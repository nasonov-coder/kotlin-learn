package dev.gaodi.fileserver.tests

import org.intellij.lang.annotations.Language

fun yamlToNode(@Language("YAML") yaml: String) {}

fun main() {
    yamlToNode("""
        type: object
        properties:
          name:
            type: string
          group:
            type: string
          handover:
            type: boolean
          required:
            - name
            - group
            - handover

    """.trimIndent())
}





