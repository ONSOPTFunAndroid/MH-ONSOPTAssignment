package com.example.mh_onsopt_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.mh_onsopt_assignment.feature.ViewPagerAdapter
import com.example.mh_onsopt_assignment.feature.portfolio.PortfolioFragment
import com.example.mh_onsopt_assignment.feature.profile.ProfileFragment
import com.example.mh_onsopt_assignment.feature.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.properties.Delegates

class HomeActivity : AppCompatActivity() {
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            ProfileFragment(),
            PortfolioFragment(),
            SettingFragment()
        )

        vp_home.adapter = viewpagerAdapter

        vp_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_profile -> index = 0
                R.id.menu_portfolio -> index = 1
                R.id.menu_setting -> index = 2
            }
            vp_home.currentItem = index
            true

        }
    }

    
}