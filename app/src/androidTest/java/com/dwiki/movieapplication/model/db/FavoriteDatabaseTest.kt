package com.dwiki.movieapplication.model.db

import org.junit.Assert.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dwiki.movieapplication.DataDummy
import com.dwiki.movieapplication.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.*

class FavoriteDatabaseTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: FavoriteDatabase
    private lateinit var dao: FavoriteDAO
    private val sampleFavorite = DataDummy.generateDummyFavourite()[0]

    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoriteDatabase::class.java
        ).build()
        dao = database.getFavoriteDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun saveFavouriteSuccess() = runTest {
        dao.addFavorite(sampleFavorite)
        val actualFavorite = dao.getFavorite().getOrAwaitValue()
        Assert.assertEquals(sampleFavorite.title, actualFavorite[0].title)
        println(sampleFavorite.title)
        println(actualFavorite[0].title)
    }

}
