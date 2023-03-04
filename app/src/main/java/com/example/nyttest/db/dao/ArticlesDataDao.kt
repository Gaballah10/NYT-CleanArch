package com.example.nyttest.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nyttest.db.entity.ArticlesTable
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface  ArticlesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(data: ArticlesTable): Completable

    @Query("DELETE FROM articlesdatatable")
    fun deleteAll(): Completable

    @Query("SELECT * FROM articlesdatatable")
    fun getAll(): Single<MutableList<ArticlesTable>>

}