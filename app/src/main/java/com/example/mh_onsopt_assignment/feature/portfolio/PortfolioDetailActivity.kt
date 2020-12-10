package com.example.mh_onsopt_assignment.feature.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.databinding.ActivityPortfolioDetailBinding

class PortfolioDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPortfolioDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_portfolio_detail)
        binding.lifecycleOwner = this

        initInfo()
    }

    private fun initInfo(){
        binding.txtPortfolioDetailTitle.text = intent.getStringExtra("title").toString()
        binding.txtPortfolioDetailSub.text = intent.getStringExtra("sub").toString()
        binding.txtPortfolioDetailDate.text = intent.getStringExtra("date").toString()
        binding.txtPortfolioDetailContents.text = intent.getStringExtra("contents").toString()
    }

}