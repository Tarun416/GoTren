package com.example.gotren.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotren.data.TrendingRepository
import com.example.trendinglib.model.TrendingListResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

     private val repo = TrendingRepository()
     private val _trending = MutableLiveData<List<TrendingListResponseItem>>()

     val trending :  MutableLiveData<List<TrendingListResponseItem>> = _trending

     fun getTrendingList()
     {
         viewModelScope.launch(Dispatchers.IO) {
             _trending.postValue(repo.getTrendingList())
         }
     }

}