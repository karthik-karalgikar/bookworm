package com.example.bookworm.model

import com.example.bookworm.ImageLinks
import com.example.bookworm.IndustryIdentifiersItem
import com.example.bookworm.PanelizationSummary
import com.example.bookworm.ReadingModes

data class VolumeInfo(val industryIdentifiers: List<IndustryIdentifiersItem>?,
                      val pageCount: Int = 0,
                      val printType: String = "",
                      val readingModes: ReadingModes,
                      val previewLink: String = "",
                      val canonicalVolumeLink: String = "",
                      val description: String = "",
                      val language: String = "",
                      val title: String = "",
                      val imageLinks: ImageLinks,
                      val averageRating: Int = 0,
                      val panelizationSummary: PanelizationSummary,
                      val publisher: String = "",
                      val ratingsCount: Int = 0,
                      val publishedDate: String = "",
                      val categories: List<String>?,
                      val maturityRating: String = "",
                      val allowAnonLogging: Boolean = false,
                      val contentVersion: String = "",
                      val authors: List<String>?,
                      val infoLink: String = "")