package com.example.mh_onsopt_assignment.feature.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.databinding.FragmentPortfolioBinding
import com.example.mh_onsopt_assignment.vo.DummyPortfolioList
import com.example.mh_onsopt_assignment.vo.PortfolioListData

class PortfolioFragment : Fragment() {
    lateinit var binding: FragmentPortfolioBinding
    lateinit var portfolioAdapter: PortfolioAdapter
    private var portfolioList = DummyPortfolioList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_portfolio, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRvList()
    }

    private fun initRvList(){
        portfolioAdapter = PortfolioAdapter(requireContext())

        binding.rvPortfolio.adapter = portfolioAdapter

        portfolioAdapter.data = portfolioList.getPortfolioList()

        portfolioAdapter.notifyDataSetChanged()
    }
}