package com.grandstream.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import com.grandstream.sunflower.repository.UserRepository
import com.grandstream.sunflower.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    val res = flow<SearchState> {
        emit(SearchState.SearchStart)
        delay(3000)
        emit(SearchState.SearchStop(userRepository.getUserSearchResult()))
    }
}