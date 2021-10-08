package com.example.gotren.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gotren.R
import com.example.gotren.data.local.TrendingPreference
import com.example.gotren.databinding.ActivityMainBinding
import com.example.gotren.utils.CheckNetConnectivity
import com.example.trending.domain.ResourceState
import com.example.trendinglib.model.TrendingListResponseItem
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() , View.OnClickListener{

    private val viewModel : MainViewModel by viewModels()

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MainAdapter

    private val sdf=  SimpleDateFormat("dd MMM, h:mm a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        getList(false)
        observeLiveData()
        setListeners()

    }

    private fun setListeners() {
        binding.retry.setOnClickListener(this)
        binding.swiperefresh.setOnRefreshListener { getList(true) }
    }

    private fun observeLiveData() {
        viewModel.trending.observe(this) {
            it.let {
                when (it.status) {
                    ResourceState.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    ResourceState.SUCCESS -> {
                        binding.swiperefresh.isRefreshing = false
                        binding.progressBar.visibility = View.GONE
                        binding.errorView.visibility = View.GONE
                        binding.retry.visibility = View.GONE
                        showData(it.data)
                    }

                    ResourceState.ERROR -> {
                        list.clear()
                        adapter.notifyDataSetChanged()
                        binding.swiperefresh.isRefreshing = false
                        binding.progressBar.visibility = View.GONE
                        binding.errorView.visibility = View.VISIBLE
                        binding.retry.visibility  = View.VISIBLE
                        Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
                    }

                }
            }
        }

    }


    private fun showData(data: List<TrendingListResponseItem>?) {
        this.list.clear()
        this.list.addAll(data!!)
        adapter.notifyDataSetChanged()

    }

    private var list = ArrayList<TrendingListResponseItem>()
    private fun setRecyclerView() {
        binding.trendingrv.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this,list)
        binding.trendingrv.adapter = adapter
    }

    private var preference = TrendingPreference()
    val twohourInSeconds = 120 * 60
    private fun getList(swipeRefresh: Boolean) {

        // fetch from local
        if(!swipeRefresh && checkForLocalFetch2hrCondition())
        {
            val duration =
                (System.currentTimeMillis() - preference.lastdatafetched!!.toLong()) / (1000 * 60)
            Log.d("lastfetchdurationinmin", duration.toString())
            viewModel.getTrendingList(true)

            checkForLastUpdateTime(duration)
        }
        // fetch from remote
        else {
            if (CheckNetConnectivity.isOnline(this)) {
                list.clear()
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.VISIBLE
                binding.errorView.visibility = View.GONE
                binding.retry.visibility = View.GONE
                viewModel.getTrendingList(false)
            } else {
                list.clear()
                adapter.notifyDataSetChanged()
                binding.swiperefresh.isRefreshing = false
                binding.progressBar.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
                binding.retry.visibility = View.VISIBLE
                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkForLastUpdateTime(duration: Long) {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.add(Calendar.MINUTE,(duration*-1).toInt())

        Log.d("updatedtime",sdf.format(cal.time))
    }

    private fun checkForLocalFetch2hrCondition(): Boolean {
        return preference.lastdatafetched != null && ((System.currentTimeMillis() - (preference.lastdatafetched?.toLong()!!))/1000 <= twohourInSeconds)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retry -> {
                getList(false)
            }
        }
    }
}