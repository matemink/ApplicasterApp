package android.igorkostenko.applicasterapp.view.adapter

import android.igorkostenko.applicasterapp.databinding.ItemLinkBinding
import android.igorkostenko.applicasterapp.databinding.ItemVideoBinding
import android.igorkostenko.applicasterapp.model.Entry
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class EmployeeEntriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Entry>()

    companion object {
        const val CELL_TYPE_LINK: Int = 0
        const val CELL_TYPE_VIDEO: Int = 1
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CELL_TYPE_LINK -> LinkViewHolder(
                ItemLinkBinding.inflate(inflater, parent, false)
            )
            CELL_TYPE_VIDEO -> VideoViewHolder(
                ItemVideoBinding.inflate(inflater, parent, false)
            )
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            CELL_TYPE_LINK -> (holder as LinkViewHolder).bind(items[position])
            CELL_TYPE_VIDEO -> (holder as VideoViewHolder).bind(items[position])
            else -> throw IllegalArgumentException("invalid viewType: ${holder.itemViewType}")
        }

    override fun getItemViewType(position: Int): Int = when {
        position == RecyclerView.NO_POSITION -> RecyclerView.INVALID_TYPE
        // TODO
        (items[position].type.value == "link") -> CELL_TYPE_LINK
        else -> CELL_TYPE_VIDEO
    }

    fun updateList(list: List<Entry>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    class LinkViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Entry) {
            if (binding is ItemLinkBinding) {
                binding.entry = entry
            }
            binding.executePendingBindings()
        }
    }

    class VideoViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Entry) {
            if (binding is ItemVideoBinding) {
                binding.entry = entry
            }
            binding.executePendingBindings()
        }
    }
}

