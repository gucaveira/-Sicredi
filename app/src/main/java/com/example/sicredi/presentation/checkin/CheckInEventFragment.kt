package com.example.sicredi.presentation.checkin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sicredi.databinding.FragmentCheckInEventBinding
import com.example.sicredi.presentation.eventslist.EventsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CheckInEventFragment : Fragment() {

    private var _binding: FragmentCheckInEventBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CheckInEventFragmentArgs>()
    private val viewModel: EventsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckInEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeCheckInEvent.TvConfirm.setOnClickListener {
            viewModel.checkInEvent(
                args.detailViewArg.eventId,
                binding.includeCheckInEvent.editTextName.text.toString(),
                binding.includeCheckInEvent.editTextEmail.text.toString()
            )
        }

        viewModel.uiStateCheckIn.observe(viewLifecycleOwner) { uiState ->
            binding.flipperCheckIn.displayedChild = when (uiState) {
                EventsViewModel.UiStateCheckIn.Loading -> {
                    FLIPPER_CHILD_POSITION_CHECK_IN_LOADING
                }
                is EventsViewModel.UiStateCheckIn.Success -> {
                    FLIPPER_CHILD_POSITION_CHECK_IN_SUCCESS
                }
                is EventsViewModel.UiStateCheckIn.Error -> {
                    FLIPPER_CHILD_POSITION_CHECK_IN_ERROR
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_CHECK_IN_LOADING = 1
        private const val FLIPPER_CHILD_POSITION_CHECK_IN_SUCCESS = 2
        private const val FLIPPER_CHILD_POSITION_CHECK_IN_ERROR = 3

    }
}