package com.grandstream.sunflower

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grandstream.sunflower.databinding.FragmentPlantDetailBinding
import com.grandstream.sunflower.entity.Plant
import com.grandstream.sunflower.viewmodels.PlantDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment: Fragment() {
    private lateinit var binding: FragmentPlantDetailBinding
    private var plantId: Int = 0
    private val viewModel: PlantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        plantId = arguments?.getString("plantId")!!.toInt()
        viewModel.getPlant(plantId).observe(viewLifecycleOwner) {
            binding.plant = it
        }
        binding.add.setOnClickListener {
            viewModel.addPlant(plantId)
        }
        binding.delete.setOnClickListener {
            viewModel.deletePlant(plantId)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                val plant = Plant(
                    plantId = plantId,
                    name = binding.name.text.toString(),
                    description = binding.description.text.toString(),
                    price = binding.price.text.toString(),
                    amount = binding.amount.text.toString(),
                    level = binding.level.text.toString(),
                    flag = if (binding.add.visibility == View.VISIBLE) "0" else "1"
                )
                viewModel.updatePlant(plant)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}