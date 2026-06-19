package com.example.kindercare_app.View.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.Model.NotificationModel
import com.example.kindercare_app.R

class NotificationAdapter(
    private val items: List<NotificationModel>,
    private val onActionClicked: (NotificationModel) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layoutContainer: LinearLayout = view.findViewById(R.id.layoutContainer)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val viewUnreadDot: View = view.findViewById(R.id.viewUnreadDot)
        val btnAction: Button = view.findViewById(R.id.btnAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.title
        holder.tvContent.text = item.content
        holder.tvTime.text = item.time

        // Thay đổi background cho thông báo chưa đọc
        if (item.isUnread) {
            holder.layoutContainer.setBackgroundColor(Color.parseColor("#EDF4FE"))
            holder.viewUnreadDot.visibility = View.VISIBLE
        } else {
            holder.layoutContainer.setBackgroundColor(Color.WHITE)
            holder.viewUnreadDot.visibility = View.GONE
        }

        // Định dạng chữ đỏ cho trường hợp Khẩn cấp
        if (item.isUrgent) {
            holder.tvTitle.setTextColor(Color.parseColor("#D32F2F"))
            holder.tvTime.setTextColor(Color.parseColor("#D32F2F"))
        } else {
            holder.tvTitle.setTextColor(Color.parseColor("#2D3142"))
            holder.tvTime.setTextColor(Color.parseColor("#99A4B8"))
        }

        // Hiển thị nút "Thanh toán ngay" nếu có
        if (item.hasAction) {
            holder.btnAction.visibility = View.VISIBLE
            holder.btnAction.setOnClickListener { onActionClicked(item) }
        } else {
            holder.btnAction.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}