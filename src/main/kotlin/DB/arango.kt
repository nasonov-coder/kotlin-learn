package dev.gaodi.fileserver.DB

import org.intellij.lang.annotations.Language

@Language("AQL")
val q1 = """
    FOR M IN Test
    LET doc = M.kek
    RETURN doc
""".trimIndent()