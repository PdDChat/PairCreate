package pddchat.paircreate.ui.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pddchat.paircreate.R
import pddchat.paircreate.databinding.FragmentSplashBinding
import pddchat.paircreate.ui.viewmodel.SplashViewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = SplashViewModel()
        binding.viewModel = viewModel

        findNavController().navigate(R.id.splash_to_pair_list)
    }
}