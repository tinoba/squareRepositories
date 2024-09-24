package com.square.repository.domain.model

data class RepositoryItem(
    val id: Int,
    val fullName: String,
    val htmlUrl: String,
    val description: String
) {
    companion object {

        val EMPTY = RepositoryItem(0, "", "", "")
    }
}
