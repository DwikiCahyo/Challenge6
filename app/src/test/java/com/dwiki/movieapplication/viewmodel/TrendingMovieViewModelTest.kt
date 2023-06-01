package com.dwiki.movieapplication.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dwiki.movieapplication.DataDummy
import com.dwiki.movieapplication.model.repository.MainRepository
import com.dwiki.movieapplication.model.responsemodel.ResponseTrendingMovieWeek
import com.dwiki.movieapplication.util.Resources
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingMovieViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var trendingMovieViewModel: TrendingMovieViewModel
    @Mock private lateinit var repository: MainRepository
    private val dummyResult = DataDummy.dummyTrendingResponse()

    private var token = "tokeen"

    @Before
    fun setUp(){
        trendingMovieViewModel = TrendingMovieViewModel(repository)
    }

    @Test
    fun `when get trending movie should not null`(){
        val expectedResponse = MutableLiveData<Resources<ResponseTrendingMovieWeek>>()
        expectedResponse.value = Resources.success(dummyResult)

        `when`(repository.trendingMovieWeek(token)).thenReturn(expectedResponse)
        val actualResponse = trendingMovieViewModel.getTrendingMovie(token).value

        Mockito.verify(repository).trendingMovieWeek(token)
        assertEquals(expectedResponse.value,actualResponse)
    }

    @Test
    fun `when error get data will return error`(){
        val expectedResponse = MutableLiveData<Resources<ResponseTrendingMovieWeek>>()
        expectedResponse.value = Resources.error("error",null)

        `when`(repository.trendingMovieWeek(token)).thenReturn(expectedResponse)
        val actualResponse = trendingMovieViewModel.getTrendingMovie(token).value
        println(actualResponse!!.message)
        println((expectedResponse.value as Resources<ResponseTrendingMovieWeek>).message)

        Mockito.verify(repository).trendingMovieWeek(token)
        assertEquals(expectedResponse.value,actualResponse)



    }

}