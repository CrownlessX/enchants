package com.crownless.enchants.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.lang.StringBuilder

@RestController
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
class ApiController {

    @Value("\${rom.enchant.files}")
    lateinit var pathToDir: String

    @GetMapping("/ping")
    fun ping() = "pong"

    @GetMapping("/sea-el")
    fun getSeaElFiles() : String {
        return readFilesInDir("sea-el")
    }

    @GetMapping("/sea-mp")
    fun getSeaMpFiles() : String {
        return readFilesInDir("sea-mp")
    }

    @GetMapping("/global")
    fun getGlobalFiles() : String {
        return readFilesInDir("global")
    }

    @GetMapping("/euro")
    fun getEuroFiles() : String {
        return readFilesInDir("euro")
    }

    private fun readFilesInDir(dir: String): String {
        val data = StringBuilder()
        File("$pathToDir/$dir/cached").walk().forEach { file ->
            if (file.isFile) {
                if (data.isNotEmpty()) {
                    data.append(",")
                } else {
                    data.append("[")
                }
                data.append(file.readText())
            }
        }
        if (data.isNotEmpty()) {
            data.append("]")
        }
        return data.toString()
    }

}