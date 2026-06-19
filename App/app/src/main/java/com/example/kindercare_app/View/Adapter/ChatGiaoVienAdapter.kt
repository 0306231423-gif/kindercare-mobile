package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R

data class ChatItem(
    val name: String,
    val lastMessage: String,
    val time: String,
    val avatarResId: Int,
    val isOnline: Boolean = false,
    val hasNewMessage: Boolean = false
)

class ChatGiaoVienAdapter(private var chatList: List<ChatItem>) :
    RecyclerView.Adapter<ChatGiaoVienAdapter.ChatViewHolder>() {

    private var fullChatList: List<ChatItem> = chatList

    fun filter(query: String) {
        chatList = if (query.isEmpty()) {
            fullChatList
        } else {
            fullChatList.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.tvName.text = chat.name
        holder.tvMessage.text = chat.lastMessage
        holder.tvTime.text = chat.time
        holder.ivAvatar.setImageResource(chat.avatarResId)

        holder.viewOnlineBadge.visibility = if (chat.isOnline) View.VISIBLE else View.GONE
        holder.viewIndicator.visibility = if (chat.hasNewMessage) View.VISIBLE else View.INVISIBLE

        // Thay đổi màu sắc tin nhắn nếu là tin nhắn mới
        if (chat.hasNewMessage) {
            holder.tvMessage.setTextColor(holder.itemView.context.getColor(R.color.secondary_green))
        } else {
            holder.tvMessage.setTextColor(holder.itemView.context.getColor(R.color.text_muted))
        }
    }

    override fun getItemCount(): Int = chatList.size

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val viewOnlineBadge: View = itemView.findViewById(R.id.viewOnlineBadge)
        val viewIndicator: View = itemView.findViewById(R.id.viewIndicator)
    }
}
