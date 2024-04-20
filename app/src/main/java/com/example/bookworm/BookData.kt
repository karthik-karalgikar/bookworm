package com.example.bookworm

data class BookData(val totalItems: Int = 0,
                    val kind: String = "",
                    val items: List<ItemsItem>?)