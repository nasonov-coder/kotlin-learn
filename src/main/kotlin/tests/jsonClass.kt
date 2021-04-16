package dev.gaodi.fileserver.tests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

fun main() {
    val json = """{
  "apps": [
    {
      "dev.equal.service": {
        "version": 2,
        "link": "http://google.com"
      },
      "com.example.tstapp": {
        "version": 2,
        "link": "/sdcard/Downloads/"
      }
    }
  ]
}
"""
    println(jacksonObjectMapper().readValue<Settings>(json))
}

data class Settings(
    val apps: List<Map<String, Setting>>
)
data class Setting(
    val version: Number,
    val link: String
)