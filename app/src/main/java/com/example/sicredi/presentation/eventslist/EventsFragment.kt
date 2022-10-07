package com.example.sicredi.presentation.eventslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.Events
import com.example.sicredi.databinding.FragmentListEventsBinding
import com.example.sicredi.framework.imageLoader.ImageLoader
import com.example.sicredi.presentation.detail.DetailViewArg
import com.example.sicredi.presentation.eventslist.adapter.EventsAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    private var _binding: FragmentListEventsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EventsViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentListEventsBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.flipperEvents.displayedChild = when (uiState) {
                EventsViewModel.UiState.Loading -> {
                    FLIPPER_CHILD_POSITION_LIST_EVENTS_LOADING
                }

                is EventsViewModel.UiState.Success -> {
                    initCharactersAdapter(uiState.detailParentList)
                    FLIPPER_CHILD_POSITION_LIST_EVENTS_SUCCESS
                }
                is EventsViewModel.UiState.Error -> {
                    FLIPPER_CHILD_POSITION_LIST_EVENTS_ERROR
                }

                EventsViewModel.UiState.Empty -> {
                    FLIPPER_CHILD_POSITION_LIST_EVENTS_EMPTY
                }
            }
        }

        viewModel.fetchEvent()
    }

    private fun initCharactersAdapter(detailParentList: Events) {
        binding.recyclerEvent.run {
            setHasFixedSize(true)
            adapter = EventsAdapter(detailParentList, imageLoader) { event ->

                val directions = EventsFragmentDirections
                    .actionEventsFragmentToDetailFragment(
                        event.title,
                        DetailViewArg(event.id, event.title, event.description, event.image)
                    )

                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_LIST_EVENTS_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_LIST_EVENTS_SUCCESS = 1
        private const val FLIPPER_CHILD_POSITION_LIST_EVENTS_ERROR = 2
        private const val FLIPPER_CHILD_POSITION_LIST_EVENTS_EMPTY = 3
    }
}