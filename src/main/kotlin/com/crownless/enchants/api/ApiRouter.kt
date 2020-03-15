package com.crownless.enchants.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {

    @Autowired
    lateinit var enchantsReader: EnchantsFileReader

    @GetMapping("/ping")
    fun ping() = "pong"

    @GetMapping("/sea-el")
    fun getSeaElFiles() = enchantsReader.readSeaElEnchants()

    @GetMapping("/sea-mp")
    fun getSeaMpFiles() = enchantsReader.readSeaMpEnchants()

    @GetMapping("/global")
    fun getGlobalFiles() = enchantsReader.readGlobalEnchants()

    @GetMapping("/euro")
    fun getEuroFiles() = enchantsReader.readEuroEnchants()

}