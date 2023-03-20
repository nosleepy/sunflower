package com.grandstream.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.grandstream.sunflower.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {
    private val map = mapOf(
        "My Garden" to R.drawable.garden,
        "Plant List" to R.drawable.plant,
        "User List" to R.drawable.user,
        "My Test" to R.drawable.test,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val viewPager2 = binding.viewPager2
        val tabLayout = binding.tabLayout

        val fragmentList = listOf(GardenFragment(), PlantListFragment(), UserListFragment(), TestFragment())

        viewPager2.adapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount(): Int {
                return map.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }
        })
        map.forEach {
            val tab = binding.tabLayout.newTab()
            tab.text = it.key
            tab.setIcon(it.value)
            binding.tabLayout.addTab(tab)
        }
        tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return binding.root
    }
}
