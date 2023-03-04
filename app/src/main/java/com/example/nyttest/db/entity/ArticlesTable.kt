package com.example.nyttest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.nyttest.network.model.Results

@Entity(tableName = "articlesdatatable", primaryKeys = ["title"])
data class ArticlesTable(
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "published_date") var published_date: String = "",
    @ColumnInfo(name = "imageUrl") var imageUrl: String = "",

    ) {
    companion object {
        fun Parse(data: Results): ArticlesTable {
            return ArticlesTable(
                data.title ?: "",
                data.published_date ?: "",
                data.media[0].`media-metadata`[0].url ?: "",
                )
        }
    }
}