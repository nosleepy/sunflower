package com.grandstream.sunflower.state

import com.grandstream.sunflower.data.UserSearchResponse

sealed class SearchState {
    object SearchStart: SearchState()
    data class SearchStop(val searchResponse: UserSearchResponse): SearchState()
}