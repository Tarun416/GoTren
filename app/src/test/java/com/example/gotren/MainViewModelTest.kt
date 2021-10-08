package com.example.gotren

import androidx.lifecycle.ViewModel
import com.example.gotren.data.TrendingRepository
import com.example.gotren.data.local.dao.TrendingDao
import com.example.gotren.ui.MainViewModel
import com.example.gotren.utils.Resource
import com.example.trendinglib.api.ApiService
import com.example.trendinglib.model.TrendingListResponseItem
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.willReturn
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Rule
    @JvmField
    val rx2SchedulersOverrideRule: Rx2SchedulersOverrideRule =
        Rx2SchedulersOverrideRule()
    private val dao = mock<TrendingDao>()
    private val service = mock<ApiService>()
    private lateinit var repository: TrendingRepository
    private lateinit var vm: MainViewModel

    @Before
    fun start() {
        repository = TrendingRepository(dao, service)
        vm = MainViewModel()
    }

    @After
    fun stop() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `get trending list and verify success updates UI`() {
        val single = io.reactivex.Single.just(listOf(
            TrendingListResponseItem()
        ))
        given(repository.getTrendingList()).willReturn{single}

        vm.getTrendingList(false)
        vm.trending.test()
            .assertValue{
                it is Resource
            }
    }
}