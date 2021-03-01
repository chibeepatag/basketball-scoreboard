package com.chibee.scoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.chibee.scoreboard.databinding.SetupFragmentBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SetupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SetupFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.setup_fragment, container, false)
        binding.start.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_setupFragment_to_scoreboardFragment))
        return binding.root
    }

}