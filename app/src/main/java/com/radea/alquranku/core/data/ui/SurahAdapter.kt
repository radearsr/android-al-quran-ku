package com.radea.alquranku.core.data.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radea.alquranku.R
import com.radea.alquranku.core.domain.model.Surah
import com.radea.alquranku.databinding.ItemCardSurahBinding

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.ListViewHolder>() {

    private var listData = ArrayList<Surah>()
    var onItemClick: ((Surah) -> Unit)? = null

    fun setData(newListData: List<Surah>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_surah, parent, false)
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardSurahBinding.bind(itemView)
        fun bind(data: Surah) {
            with(binding) {
                tvNumbering.text = data.surahNum.toString()
                tvTitle.text = data.latinName
                tvArabicText.text = data.name
                tvTotalAyat.text = binding.root.resources.getString(R.string.jumlah_ayat, data.place, data.totalAyat).uppercase()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}