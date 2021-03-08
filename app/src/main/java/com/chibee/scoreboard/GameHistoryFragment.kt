package com.chibee.scoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chibee.scoreboard.databinding.FragmentGameHistoryBinding
import com.google.android.material.snackbar.Snackbar
import database.GameDatabase

/**
 * A simple [Fragment] subclass.
 * Use the [GameHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameHistoryFragment : Fragment() {

    private lateinit var viewModel: GameHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameHistoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_history,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao
        val gameHistoryViewModelFactory = GameHistoryViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, gameHistoryViewModelFactory).get(GameHistoryViewModel::class.java)
        binding.lifecycleOwner = this
        binding.gameHistoryViewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "All games are cleared",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })
        val adapter = GameHistoryAdapter()
        binding.gameHistList.adapter = adapter
        viewModel.games.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return binding.root
    }

}