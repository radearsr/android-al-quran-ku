package com.radea.alquranku.core.data.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.radea.alquranku.R
import com.radea.alquranku.core.domain.model.DateItem
import com.radea.alquranku.databinding.ItemDateScheduleBinding

class DateScheduleAdapter() :
    RecyclerView.Adapter<DateScheduleAdapter.ListViewHolder>() {
    private var listData = ArrayList<DateItem>()
    var onItemClick: ((DateItem) -> Unit)? = null

    fun setData(newListData: List<DateItem>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_date_schedule, parent, false)
        )


    override fun onBindViewHolder(holder: DateScheduleAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDateScheduleBinding.bind(itemView)
        fun bind(data: DateItem) {
            with(binding) {
                tvDateNum.text = data.date.toString()
                tvDateOfWeek.text = data.day
                clDate.background = if (data.isSelected) {
                    ContextCompat.getDrawable(root.context, R.drawable.border_rounded_selected)
                } else {
                    ContextCompat.getDrawable(root.context, R.drawable.border_rounded)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}