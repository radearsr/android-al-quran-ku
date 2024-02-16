package com.radea.alquranku.core.data.ui

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radea.alquranku.R
import com.radea.alquranku.core.domain.model.Ayat
import com.radea.alquranku.databinding.ItemCardAyatBinding

class AyatAdapter : RecyclerView.Adapter<AyatAdapter.ListViewHolder>() {

    private var listData = ArrayList<Ayat>()
    val onItemClick: ((Ayat) -> Unit)? = null

    fun setData(newListData: List<Ayat>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_ayat, parent, false)
        )


    override fun onBindViewHolder(holder: AyatAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardAyatBinding.bind(itemView)
        fun bind(data: Ayat) {
            with(binding) {
                tvNumberAyat.text = data.ayatNum.toString()
                tvArabicText.text = data.arabic
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvTranslate.text = Html.fromHtml(data.latin, Html.FROM_HTML_MODE_LEGACY)
                }
                tvAyatDescription.text = data.arti
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}