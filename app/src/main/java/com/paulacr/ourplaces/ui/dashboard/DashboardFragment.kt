package com.paulacr.ourplaces.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.ourplaces.databinding.FragmentDashboardBinding
import com.paulacr.sectionedrecyclerview.BaseAdapter
import com.paulacr.sectionedrecyclerview.common.MultiHeadersDivider

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvDashboard: RecyclerView = binding.rvDashboard
        dashboardViewModel.items.observe(
            viewLifecycleOwner,
            Observer {
                rvDashboard.addItemDecoration(MultiHeadersDivider(rvDashboard.context, items = it))
                rvDashboard.layoutManager = LinearLayoutManager(context)
                rvDashboard.adapter = BaseAdapter(items = it) {}
            }
        )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
