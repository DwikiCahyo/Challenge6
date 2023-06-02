package com.dwiki.movieapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dwiki.movieapplication.DataDummy
import com.dwiki.movieapplication.model.repository.MainRepository
import com.dwiki.movieapplication.model.responsemodel.ResponseUpcomingMovie
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
class UpcomingMovieViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upcomingViewModel: UpcomingViewModel
    @Mock private lateinit var repository: MainRepository
    private val dummyResult = DataDummy.dummyUpcomingResponse()

    private var token = "token"
    @Before
    fun setUp() {
        upcomingViewModel = UpcomingViewModel(repository)
    }

    @Test
    fun `when data is loaded should return success`(){
        val expectedResponse = MutableLiveData<Resources<ResponseUpcomingMovie>>()
        expectedResponse.value = Resources.success(dummyResult)

        `when`(repository.upcomingMovie(token)).thenReturn(expectedResponse)
        val actualResponse = upcomingViewModel.upcomingMovie(token).value

        Mockito.verify(repository).upcomingMovie(token)
        assertEquals(expectedResponse.value, actualResponse)

    }

    @Test
    fun `when data is error will return error`(){
        val expectedResponse = MutableLiveData<Resources<ResponseUpcomingMovie>>()
        expectedResponse.value = Resources.error("error", null)

        `when`(repository.upcomingMovie(token)).thenReturn(expectedResponse)
        val actualResponse = upcomingViewModel.upcomingMovie(token).value

        Mockito.verify(repository).upcomingMovie(token)
        assertEquals(expectedResponse.value, actualResponse)

    }


}