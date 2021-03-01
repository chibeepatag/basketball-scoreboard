package com.chibee.scoreboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        binding.start.setOnClickListener {
            val teamAName = binding.editTextTeamAName.text.toString()
            val teamBName = binding.editTextTeamBName.text.toString()
            val toScoreBoard = SetupFragmentDirections.actionSetupFragmentToScoreboardFragment()
            toScoreBoard.teamAName = teamAName
            toScoreBoard.teamBName = teamBName
            findNavController().navigate(toScoreBoard)
        }
        return binding.root
    }

}