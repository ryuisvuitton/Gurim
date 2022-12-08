package kr.co.ryuboutin.viewholder

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kr.co.ryuboutin.R
import kr.co.ryuboutin.data.BannerItem

class BannerItemViewHolder(itemView: View) : ViewHolder(itemView) {
    private var view: View = itemView
    fun bind(listener: View.OnClickListener, item: BannerItem) {
        val imageBanner = view.findViewById<ImageView>(R.id.banner_image)
        imageBanner.setBackgroundColor(ContextCompat.getColor(imageBanner.context, item.imageUrl))
        view.setOnClickListener(listener)
    }

}