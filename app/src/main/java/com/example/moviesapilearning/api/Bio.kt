package com.example.moviesapilearning.api

data class Bio(
    val type: String,
    val akas: List<String>,
    val birthDate: String,
    val birthPlace: String,
    val gender: String,
    val heightCentimeters: Double,
    val id: String,
    val image: Image,
    val legacyNameText: String,
    val miniBios: List<MiniBio>,
    val name: String,
    val nicknames: List<String>,
    val realName: String,
    val spouses: List<Spouse>,
    val trademarks: List<String>
)