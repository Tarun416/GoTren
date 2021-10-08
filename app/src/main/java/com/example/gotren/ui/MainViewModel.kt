package com.example.gotren.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotren.data.TrendingRepository
import com.example.gotren.data.local.TrendingPreference
import com.example.gotren.data.local.entitiy.TrendingData
import com.example.gotren.utils.Resource
import com.example.trending.domain.ResourceState
import com.example.trendinglib.model.TrendingListResponseItem
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

     private val repo = TrendingRepository()
     private val _trending = MutableLiveData<Resource<List<TrendingListResponseItem>>>()

     val trending :  MutableLiveData<Resource<List<TrendingListResponseItem>>> = _trending


    fun getTrendingList(fromdb: Boolean) {
        _trending.postValue(
            Resource(
                ResourceState.LOADING,
                null,
                null
            )
        )
        if (fromdb)
            repo.getTrendingListFromDb().subscribe(GetLocalObserver())
        else
            repo.getTrendingList()
                .subscribe({
                    TrendingPreference().lastdatafetched = System.currentTimeMillis().toString()
                    _trending.postValue(Resource(ResourceState.SUCCESS, it, null))
                },{
                    _trending.postValue(
                        Resource(
                            ResourceState.ERROR,
                            null,
                            "Some problem occured"
                        )
                    )
                })
    }



    // todo trying using coroutine.
    /* fun getTrendingList()
     {
         _trending.postValue(
             Resource(
                 ResourceState.LOADING,
                 null,
                 null
             )
         )

         viewModelScope.launch(Dispatchers.IO) {
             _trending.postValue(repo.getTrendingList())
         }
     }*/

    private inner class GetLocalObserver :
        DisposableSingleObserver<TrendingData>() {
        override fun onSuccess(t: TrendingData) {
            _trending.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.list,
                    null
                )
            )
        }

        override fun onError(e: Throwable) {
           _trending.postValue(
                Resource(
                    ResourceState.ERROR,
                    null,
                    "Some problem occured"
                )
            )
        }

    }


    override fun onCleared() {
        super.onCleared()

    }

}