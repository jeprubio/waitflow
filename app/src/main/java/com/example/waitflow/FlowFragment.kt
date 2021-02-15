package com.example.waitflow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waitflow.databinding.FlowFragmentBinding

class FlowFragment : Fragment(R.layout.flow_fragment) {

    private var binding: FlowFragmentBinding? = null

    companion object {
        fun newInstance() = FlowFragment()
    }

    private lateinit var viewModel: FlowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CountingIdlingResourceSingleton.increment()
        binding = FlowFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlowViewModel::class.java)
        viewModel.namesAsLiveData.observe(viewLifecycleOwner, {
            CountingIdlingResourceSingleton.decrement()
            binding?.loading?.visibility = View.GONE
            binding?.flowValue?.text = it
        })
    }

}