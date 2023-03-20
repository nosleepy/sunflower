package com.grandstream.sunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grandstream.sunflower.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.*
import java.io.IOException

@AndroidEntryPoint
class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = OkHttpClient()
        val request = Request.Builder().url("https://getman.cn/mock/test").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure,thread = ${Thread.currentThread().name}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onResponse,response = ${response.body()?.string()}")
            }

        })
//        val response = client.newCall(request).execute()
//        binding.tv.text = arguments?.getString("info")
    }

    companion object {
        private const val TAG = "TestFragment"

        fun newInstance(info: String): TestFragment {
            val args = Bundle().apply {
                putString("info", info)
            }
            val testFragment = TestFragment()
            testFragment.arguments = args
            return testFragment
        }
    }
}