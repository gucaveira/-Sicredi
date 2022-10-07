package com.example.sicredi.presentation.eventslist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.EventsItem
import com.example.sicredi.databinding.ItemListEventBinding
import com.example.sicredi.framework.imageLoader.ImageLoader
import com.example.sicredi.util.OnEventItemClick

class EventsAdapter(
    private val eventsList: List<EventsItem> = mutableListOf(),
    private val imageLoader: ImageLoader,
    private val onItemClickListener: OnEventItemClick
) : RecyclerView.Adapter<EventsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.create(parent, imageLoader, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        return holder.bind(eventsList[position])
    }

    override fun getItemCount() = eventsList.size

    class CustomViewHolder private constructor(
        itemEventBinding: ItemListEventBinding,
        private val imageLoader: ImageLoader,
        private val onItemClickListener: OnEventItemClick
    ) : RecyclerView.ViewHolder(itemEventBinding.root) {

        private val textName = itemEventBinding.textView
        private val imageView = itemEventBinding.imageView

        fun bind(event: EventsItem) {
            textName.text = event.title
            imageLoader.load(imageView, event.image)

            itemView.setOnClickListener {
                onItemClickListener.invoke(event)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader,
                onItemClickListener: OnEventItemClick
            ): CustomViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemListEventBinding.inflate(inflater, parent, false)
                return CustomViewHolder(itemBinding, imageLoader, onItemClickListener)
            }
        }
    }
}