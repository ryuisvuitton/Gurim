package kr.co.ryuboutin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.ryuboutin.R
import kr.co.ryuboutin.adapter.BannerItemAdapter
import kr.co.ryuboutin.data.BannerItem
import kr.co.ryuboutin.databinding.FragmentHomeBinding
import kr.co.ryuboutin.viewmodel.BannerItemViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() {
            return _binding!!
        }
    private lateinit var adapter: BannerItemAdapter
    private val viewModel: BannerItemViewModel by viewModels()
    private var isRunning = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerItemSetting()
        initObservable()
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE,
                Lifecycle.Event.ON_START,
                Lifecycle.Event.ON_PAUSE -> {
                    isRunning = false
                }
                Lifecycle.Event.ON_STOP,
                Lifecycle.Event.ON_ANY -> {

                }
                Lifecycle.Event.ON_RESUME -> {
                    isRunning = true
                    lifecycleScope.launch {
                        while (isRunning) {
                            delay(5000)
                            viewModel.getcurrentPosition()?.let {
                                viewModel.setCurrentPosition((it.plus(1)) % (viewModel.getBannerSize()))
                            }
                        }
                    }
                }
                Lifecycle.Event.ON_DESTROY -> {

                }

            }

        })
    }

    private fun recyclerViewSetting(bannerItem: List<BannerItem>) {
        adapter = BannerItemAdapter(bannerItem)
        adapter.submitList(bannerItem)
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isRunning = true

                viewModel.setCurrentPosition(position)

            }
        })
    }

    private fun bannerItemSetting() {
        viewModel.setBannerItems(
            listOf(
                BannerItem(R.color.black),
                BannerItem(R.color.gray),
                BannerItem(R.color.purple_700)
            )
        )
    }

    private fun initObservable() {

        viewModel.bannerItemList.observe(viewLifecycleOwner, Observer {
            recyclerViewSetting(it)
        })

        viewModel.currentPosition.observe(viewLifecycleOwner, Observer {
            binding.viewPager.currentItem = it
        })
    }

}