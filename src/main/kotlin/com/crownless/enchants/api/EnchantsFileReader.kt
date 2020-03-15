package com.crownless.enchants.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.util.StringUtils
import java.io.File
import java.lang.StringBuilder

@Component
@PropertySource("classpath:application.properties")
class EnchantsFileReader {

    @Value("\${rom.enchant.files}")
    lateinit var pathToDir: String

    fun readEuroEnchants(): String {
        return readFilesInDir("euro")
    }

    fun readGlobalEnchants(): String {
        return readFilesInDir("global")
    }

    fun readSeaMpEnchants(): String {
        return readFilesInDir("sea-mp")
    }

    fun readSeaElEnchants(): String {
        return readFilesInDir("sea-el")
    }

    private fun readFilesInDir(server: String): String {
        val data = StringBuilder()
        if(!::pathToDir.isInitialized) {
            println("Not initialized")
            return ""
        }
        File("$pathToDir/$server/cached").walk().forEach { file ->
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