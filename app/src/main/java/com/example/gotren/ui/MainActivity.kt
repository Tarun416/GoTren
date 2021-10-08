package com.example.gotren.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gotren.databinding.ActivityMainBinding
import com.example.trendinglib.model.TrendingListResponseItem

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        viewModel.getTrendingList()
        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.trending.observe(this)
        {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }

    }

    private var list = ArrayList<TrendingListResponseItem>()
    private fun setRecyclerView() {
        binding.trendingrv.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this,list)
        binding.trendingrv.adapter = adapter
    }
}