package android.igorkostenko.applicasterapp.view.adapter

import android.igorkostenko.applicasterapp.databinding.ItemLinkBinding
import android.igorkostenko.applicasterapp.databinding.ItemVideoBinding
import android.igorkostenko.applicasterapp.model.Entry
import android.igorkostenko.applicasterapp.view.ui.details.VideoActivity
import android.igorkostenko.applicasterapp.view.ui.details.WebViewActivity
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
            CELL_TYPE_LINK -> ViewHolder(
                ItemLinkBinding.inflate(inflater, parent, false)
            )
            CELL_TYPE_VIDEO -> ViewHolder(
                ItemVideoBinding.inflate(inflater, parent, false)
            )
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolder).bind(items[position])

    override fun getItemViewType(position: Int): Int = when {
        position == RecyclerView.NO_POSITION -> RecyclerView.INVALID_TYPE
        (EntryMediaType.from(items[position].type.value) == EntryMediaType.LINK) -> CELL_TYPE_LINK
        else -> CELL_TYPE_VIDEO
    }

    fun updateList(list: List<Entry>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Entry) {
            if (binding is ItemLinkBinding) {
                binding.apply {
                    this.entry = entry
                    imageUrl =
                        entry.media_group.firstOrNull()?.media_item?.firstOrNull()?.src.orEmpty()
                    root.setOnClickListener {
                        root.context.startActivity(
                            WebViewActivity.createIntent(
                                binding.root.context,
                                entry.link.href
                            )
                        )
                    }
                }
            }
            if (binding is ItemVideoBinding) {
                binding.apply {
                    this.entry = entry
                    imageUrl =
                        entry.media_group.firstOrNull()?.media_item?.firstOrNull()?.src.orEmpty()
                    button.setOnClickListener {
                        root.context.startActivity(
                            VideoActivity.createIntent(
                                binding.root.context,
                                entry.content.src
                            )
                        )
                    }
                }
            }
            binding.executePendingBindings()
        }
    }

    private enum class EntryMediaType(val value: String) {
        LINK("link"),
        VIDEO("video");

        companion object {
            fun from(value: String): EntryMediaType? =
                values().find { it.value == value }
        }
    }
}

