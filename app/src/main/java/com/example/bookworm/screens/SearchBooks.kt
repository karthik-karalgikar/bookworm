package com.example.bookworm.screens

import android.util.Log
import java.io.File

class SearchBooks {

    fun searchForBooks() {
        val csvFile = File("/Users/karthikkaralgikar/Downloads/books.csv")
        val lines = csvFile.readLines()
        val searchQuery = "Harry Potter"
        val matchingBooks = mutableListOf<String>()

        // Assuming your CSV file has a header row
        val header = lines.first()
        val data = lines.drop(1) // Skip the header row

        for (line in data) {
            val columns = line.split(",") 
            val bookTitle = columns[1]
            Log.d("Books", "searchForBooks: $bookTitle")
            val author = columns[2]
            Log.d("Books", "searchForBooks: $author")

            if (bookTitle.equals(searchQuery, ignoreCase = true) || author.equals(
                    searchQuery,
                    ignoreCase = true
                )
            ) {
                matchingBooks.add("$bookTitle by $author")
            }
        }
        if (matchingBooks.isNotEmpty()) {
            println("Matching books found:")
            matchingBooks.forEach { println(it) }
        } else {
            println("No matching books found.")
        }
    }
}

