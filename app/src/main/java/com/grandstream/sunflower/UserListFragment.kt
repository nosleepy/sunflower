package com.grandstream.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.grandstream.sunflower.adapters.UserListAdapter
import com.grandstream.sunflower.databinding.FragmentUserListBinding
import com.grandstream.sunflower.state.SearchState
import com.grandstream.sunflower.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        val userList = binding.userList
        val adapter = UserListAdapter()
        userList.adapter = adapter
        userList.layoutManager = LinearLayoutManager(context)
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: UserListAdapter) {
        lifecycleScope.launchWhenResumed {
            viewModel.res.collect {
                when (it) {
                    is SearchState.SearchStart -> binding.bar.visibility = View.VISIBLE
                    is SearchState.SearchStop -> {
                        binding.bar.visibility = View.GONE
                        adapter.submitList(it.searchResponse.results)
                    }
                }
            }
        }
    }
}