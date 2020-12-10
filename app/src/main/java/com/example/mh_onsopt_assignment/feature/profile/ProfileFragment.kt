package com.example.mh_onsopt_assignment.feature.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.feature.ViewPagerAdapter
import com.example.mh_onsopt_assignment.feature.portfolio.PortfolioFragment
import com.example.mh_onsopt_assignment.feature.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        viewpagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments = listOf(
            PortfolioFragment(),
            SettingFragment()
        )

        rootView.vp_profile.adapter = viewpagerAdapter

        rootView.tl_profile.setupWithViewPager(rootView.vp_profile)
        rootView.tl_profile.apply {
            getTabAt(0)?.text = "첫 번째"
            getTabAt(1)?.text = "두 번째"
        }

        return rootView
    }


}