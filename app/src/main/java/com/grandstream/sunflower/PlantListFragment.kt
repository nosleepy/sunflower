package com.grandstream.sunflower

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.grandstream.sunflower.adapters.PlantAdapter
import com.grandstream.sunflower.adapters.PlantListAdapter
import com.grandstream.sunflower.databinding.FragmentPlantListBinding
import com.grandstream.sunflower.utils.getPlantList
import com.grandstream.sunflower.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment: Fragment() {
    private lateinit var binding: FragmentPlantListBinding
    private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val plantList = binding.plantList
        val adapter = PlantListAdapter()
//        plantList.adapter = PlantAdapter(getPlantList())
        plantList.adapter = adapter
//        plantList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//        plantList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        plantList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.linear_layout -> {
                binding.plantList.layoutManager = LinearLayoutManager(context)
                true
            }
            R.id.grid_layout -> {
                binding.plantList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                true
            }
            R.id.addPlant -> {
                viewModel.insertAll(getPlantList())
                true
            }
            R.id.deletePlant -> {
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantListAdapter) {
        viewModel.plants.asLiveData().observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }
}