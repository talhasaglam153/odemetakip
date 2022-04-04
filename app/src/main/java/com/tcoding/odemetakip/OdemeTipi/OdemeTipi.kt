package com.tcoding.odemetakip.OdemeTipi

import java.io.Serializable

class OdemeTipi : Serializable {

    var Id : Int = 0
    lateinit var Baslik : String
    lateinit var Periyod : String
    lateinit  var PeriyodGunu : String
}