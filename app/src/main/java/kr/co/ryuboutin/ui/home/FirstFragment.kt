package kr.co.ryuboutin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.ryuboutin.R
import kr.co.ryuboutin.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeViewpagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding!!.tabLayout, binding.viewPager) { tab: TabLayout.Tab, position: Int ->
            when(position) {
                0 ->  {
                    tab.text = resources.getString(R.string.home_tab_name_today)
                    
                }
                1 -> tab.text = resources.getString(R.string.home_tab_name_release_information)
                else -> Fragment()
            }
        }.attach()





    }

    class HomeViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> HomeFragment()
                1 -> ReleaseInformationFragment()
                else -> Fragment()
            }
        }

        override fun getItemCount(): Int = 2


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}