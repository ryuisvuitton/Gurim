package kr.co.ryuboutin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.co.ryuboutin.R
import kr.co.ryuboutin.data.BannerItem
import kr.co.ryuboutin.viewholder.BannerItemViewHolder

class BannerItemAdapter(private var item: List<BannerItem>):
    RecyclerView.Adapter<BannerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_banner_item, parent, false)
        return BannerItemViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: BannerItemViewHolder, position: Int) {
        var item = item[position]
        val listener = View.OnClickListener {
            Toast.makeText(it.context, "Clicked + $position", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
        }
    }

    fun submitList(list: List<BannerItem>) {
        item = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = item.size
}