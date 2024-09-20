package com.square.repository.domain.model

data class RepositoryItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val description: String,
    val url: String
) {
    companion object {

        val EMPTY = RepositoryItem(0, "", "", "", "", "")
    }
}
