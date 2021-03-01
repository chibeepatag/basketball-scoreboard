package com.chibee.scoreboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chibee.scoreboard.databinding.ScoreboardFragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreboardFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ScoreboardFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.scoreboard_fragment,
            container,
            false
        )
        binding.setLifecycleOwner(this)
        viewModel =  ViewModelProvider(this).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        Log.i("Scoreboard Arguments", arguments.toString())
        var args = ScoreboardFragmentArgs.fromBundle(requireArguments())
        binding.teamALabel.setText(args.teamAName)
        binding.teamBLabel.setText(args.teamBName)
        return binding.root
    }

}