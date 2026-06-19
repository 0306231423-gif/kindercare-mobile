package com.example.kindercare_app.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R
import com.example.kindercare_app.Model.FeatureModel

class FeatureAdapter(
    private val features: List<FeatureModel>,
    private val onFeatureClick: (FeatureModel) -> Unit
) : RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature_single, parent, false) // Đảm bảo bạn có file layout này cho từng nút bấm
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = features[position]

        // Đổ dữ liệu vào từng nút bấm
        holder.tvFeatureName.text = feature.title
        holder.imgFeatureIcon.setImageResource(feature.iconRes ?: R.drawable.ic_health) // Thay bằng thuộc tính icon chính xác trong FeatureModel của bạn

        // Sự kiện click vào nút chức năng
        holder.itemView.setOnClickListener {
            onFeatureClick(feature)
        }
    }

    override fun getItemCount(): Int = features.size

    class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFeatureIcon: ImageView = view.findViewById(R.id.imgFeatureIcon) // ID của ImageView trong item_feature_single.xml
        val tvFeatureName: TextView = view.findViewById(R.id.tvFeatureName)   // ID của TextView trong item_feature_single.xml
    }
}