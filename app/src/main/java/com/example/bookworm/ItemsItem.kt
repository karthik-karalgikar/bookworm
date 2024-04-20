package com.example.bookworm

import com.example.bookworm.model.VolumeInfo

data class ItemsItem(val saleInfo: SaleInfo,
                     val searchInfo: SearchInfo,
                     val kind: String = "",
                     val volumeInfo: VolumeInfo,
                     val etag: String = "",
                     val id: String = "",
                     val accessInfo: AccessInfo,
                     val selfLink: String = "")