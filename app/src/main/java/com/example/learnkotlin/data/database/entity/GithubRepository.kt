package com.example.learnkotlin.data.database.entity

import com.google.gson.annotations.Expose

data class GithubRepository(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    val forksCount: Int,
)