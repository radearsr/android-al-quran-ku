package com.radea.alquranku.core.data.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radea.alquranku.R
import com.radea.alquranku.core.domain.model.ScheduleSholat
import com.radea.alquranku.databinding.ItemTimeSholatBinding

class ScheduleSholatAdapter() : RecyclerView.Adapter<ScheduleSholatAdapter.ListViewHolder>() {
    private var listData = ArrayList<ScheduleSholat>()

    fun setData(newListData: List<ScheduleSholat>) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleSholatAdapter.ListViewHolder = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_time_sholat, parent, false)
    )

    override fun onBindViewHolder(holder: ScheduleSholatAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTimeSholatBinding.bind(itemView)
        fun bind(data: ScheduleSholat) {
            with(binding) {
                tvTitle.text = data.key.uppercase()
                tvTime.text = data.value
            }
        }
    }

}