package com.example.bookworm.model

data class BookDataResponse(
    val title: String,
    val authors: List<String>,
    val categories: List<String>
)
