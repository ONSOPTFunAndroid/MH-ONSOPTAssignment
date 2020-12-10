package com.example.mh_onsopt_assignment.feature.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.databinding.FragmentSettingBinding
import com.example.mh_onsopt_assignment.feature.portfolio.PortfolioAdapter
import com.example.mh_onsopt_assignment.vo.DummyPortfolioList


class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    lateinit var portfolioAdapter: SettingAdapter
    private var portfolioList = DummyPortfolioList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRvList()
    }

    private fun initRvList(){
        portfolioAdapter = SettingAdapter(requireContext())

        binding.rvPortfolio2.adapter = portfolioAdapter

        portfolioAdapter.data = portfolioList.getPortfolioList()

        portfolioAdapter.notifyDataSetChanged()
    }
}