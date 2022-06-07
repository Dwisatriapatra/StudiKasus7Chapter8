package com.example.studikasus7chapter8.model

data class GetAllUserResponseItem(
    val address: String,
    val id: String,
    val image: String,
    val name: String,
    val pass: String,
    val password: String,
    val umur: Int,
    val username: String
)