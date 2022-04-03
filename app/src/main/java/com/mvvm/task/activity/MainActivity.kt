package com.mvvm.task.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mvvm.task.R
import com.mvvm.task.adapter.TabAdapter
import com.mvvm.task.databinding.ActivityMainBinding
import com.mvvm.task.fragment.Feed
import com.mvvm.task.fragment.Video
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        intView()
    }

    private fun intView() {
        binding.tab.addTab(binding.tab.newTab().setText(resources.getText(R.string.Feed)))
        binding.tab.addTab(binding.tab.newTab().setText(resources.getText(R.string.Video)))
        setupViewPager(binding.viewpager)
        binding.tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewpager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.viewpager.addOnPageChangeListener (
            object : SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    binding.tab.getTabAt(position)?.select()
                }
            })
        binding.viewpager.offscreenPageLimit = 2
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TabAdapter(
            supportFragmentManager
        )
        adapter.addFragment(Feed(), resources.getText(R.string.Feed) as String)
        adapter.addFragment(Video(), resources.getText(R.string.Video) as String)

        viewPager.adapter = adapter
    }



}