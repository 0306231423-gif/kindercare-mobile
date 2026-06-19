package com.example.kindercare_app.View.Adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.Model.ChatItem

import com.example.kindercare_app.databinding.ItemChatBinding // Thêm dòng này

class ChatAdapter(private val chatList: List<ChatItem>, private val onItemClick: (ChatItem) -> Unit ) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = chatList[position]
        with(holder.binding) {
            txtSenderName.text = item.senderName
            txtTime.text = item.time

            // Xử lý trạng thái đang soạn tin nhắn hoặc tin nhắn thường
            if (item.isTyping) {
                txtLastMessage.text = "Đang soạn tin nhắn..."
                txtLastMessage.setTextColor(Color.parseColor("#0F5132"))
                txtLastMessage.setTypeface(null, Typeface.ITALIC)
            } else {
                txtLastMessage.text = item.lastMessage
                txtLastMessage.setTextColor(Color.parseColor("#8C8C8C"))
                txtLastMessage.setTypeface(null, Typeface.NORMAL)
            }

            // Hiển thị thanh dọc màu xanh nếu là tin nhắn chưa đọc
            if (item.isUnread || item.isTyping) {
                viewUnreadIndicator.visibility = View.VISIBLE
            } else {
                viewUnreadIndicator.visibility = View.GONE
            }
            holder.itemView.setOnClickListener {
                onItemClick(item)
            }

            // Bạn có thể xử lý load ảnh vào `imgAvatar` ở đây bằng thư viện Glide hoặc Picasso sau này
        }
    }

    override fun getItemCount(): Int = chatList.size
}