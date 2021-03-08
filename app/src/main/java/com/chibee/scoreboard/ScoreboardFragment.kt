package com.chibee.scoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.chibee.scoreboard.databinding.ScoreboardFragmentBinding
import com.chibee.scoreboard.database.GameDatabase

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
        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao
        var args = ScoreboardFragmentArgs.fromBundle(requireArguments())
        binding.teamALabel.setText(args.teamAName)
        binding.teamBLabel.setText(args.teamBName)
        val scoreViewModelFactory = ScoreViewModelFactory(dataSource, application, args.teamAName, args.teamBName)
        viewModel =  ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.scoreViewModel = viewModel

        viewModel.navigateToGameHistory.observe(viewLifecycleOwner, Observer {navigate ->
            if(navigate){
                requireView().findNavController().navigate(R.id.action_scoreboardFragment_to_gameHistoryFragment)
                viewModel.doneNavigating()
            }
        })
        return binding.root
    }

}