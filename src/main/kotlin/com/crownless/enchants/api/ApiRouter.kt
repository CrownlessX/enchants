package com.crownless.enchants.api

import org.springframework.beans.factory.annotation.Autowired
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
class ApiController {

    @Autowired
    lateinit var enchantsReader: EnchantsFileReader

    @GetMapping("/ping")
    fun ping() = "pong"

    @GetMapping("/sea-el")
    fun getSeaElFiles() : String {
        return enchantsReader.readSeaElEnchants()
    }

    @GetMapping("/sea-mp")
    fun getSeaMpFiles() : String {
        return enchantsReader.readSeaMpEnchants()
    }

    @GetMapping("/global")
    fun getGlobalFiles() : String {
        return enchantsReader.readGlobalEnchants()
    }

    @GetMapping("/euro")
    fun getEuroFiles() : String {
        return enchantsReader.readEuroEnchants()
    }

}