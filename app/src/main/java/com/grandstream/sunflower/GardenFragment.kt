package com.grandstream.sunflower

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.grandstream.sunflower.adapters.PlantListAdapter
import com.grandstream.sunflower.databinding.FragmentGardenBinding
import com.grandstream.sunflower.utils.getPlantList
import com.grandstream.sunflower.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment: Fragment() {
    private lateinit var binding: FragmentGardenBinding
    private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        val addPlant = binding.addPlant
        addPlant.setOnClickListener {
            requireActivity().findViewById<ViewPager2>(R.id.viewPager2).currentItem = 1
        }
        val gardenList = binding.gardenList
        val adapter = PlantListAdapter()
        gardenList.adapter = adapter
        gardenList.layoutManager = GridLayoutManager(context, 2)
//        adapter.submitList(DataUtil.getPlantList())
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: PlantListAdapter) {
        viewModel.addPlants.asLiveData().observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
            binding.hasPlantings = plants.isEmpty()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_garden_list, menu)
    }
}