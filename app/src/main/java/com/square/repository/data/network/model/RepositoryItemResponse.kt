package com.square.repository.data.network.model

import com.squareup.moshi.Json

data class RepositoryItemResponse(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "full_name") val fullName: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String?
)

