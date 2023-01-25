package com.example.learnkotlin.domen.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.learnkotlin.data.database.entity.RoomGithubUser

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    var userId: String
)
