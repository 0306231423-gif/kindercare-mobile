package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kindercare_app.Model.ChatItem
import com.example.kindercare_app.R
import com.example.kindercare_app.View.Adapter.ChatAdapter
import com.example.kindercare_app.databinding.ActivityChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var chatAdapter: ChatAdapter
    private var chatList: MutableList<ChatItem> = mutableListOf()
    private var filteredList: MutableList<ChatItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setupRecyclerView()
        loadDummyData()
        setupSearch()
        setupFilterButtons()
        val img_notification = binding.imgNotification
        val img_top_avatar = binding.imgUserAvatar
        img_notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        img_top_avatar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_contact // Đặt Tổng quan làm mặc định

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. Tab Tổng quan (Trang chủ hiện tại)
                R.id.nav_contact -> {
                    true
                }

                // 2. Tab Hồ sơ bé
                R.id.nav_profile -> {
                    val intent = Intent(this, ChildProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 3. Tab Học phí
                R.id.nav_fee -> {
                    // Thay thế FeeActivity bằng tên Activity quản lý Học phí thực tế trong dự án của bạn nếu có khác biệt
                    val intent = Intent(this, FeeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 4. Tab Hoạt động
                R.id.nav_activity -> {
                    val intent = Intent(this, ActivityActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 5. Tab trang chủ
                R.id.nav_overview -> {
                    // Thay thế ContactActivity bằng tên Activity chat/liên lạc của bạn nếu có khác biệt
                    val intent = Intent(this, HomePHActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupRecyclerView() {

        chatAdapter = ChatAdapter(filteredList) { item ->

            Toast.makeText(this, "Mở cuộc trò chuyện với: ${item.senderName}", Toast.LENGTH_SHORT).show()
        }

        binding.rvChatList.layoutManager = LinearLayoutManager(this)
        binding.rvChatList.adapter = chatAdapter
    }

    private fun setupSearch() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterChats(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupFilterButtons() {
        binding.btnFilterAll.setOnClickListener {
            filterChats(binding.edtSearch.text.toString(), showUnreadOnly = false)
        }
        binding.btnFilterUnread.setOnClickListener {
            filterChats(binding.edtSearch.text.toString(), showUnreadOnly = true)
        }
    }

    private fun filterChats(query: String, showUnreadOnly: Boolean = false) {
        filteredList.clear()
        val lowerQuery = query.lowercase()

        filteredList.addAll(
            chatList.filter {
                val matchesQuery = it.senderName.lowercase().contains(lowerQuery) ||
                        it.lastMessage.lowercase().contains(lowerQuery)
                val matchesUnread = if (showUnreadOnly) it.isUnread else true
                matchesQuery && matchesUnread
            }
        )
        // Thông báo cho Adapter biết dữ liệu filteredList đã thay đổi để cập nhật lên màn hình
        chatAdapter.notifyDataSetChanged()
    }

    private fun loadDummyData() {
        chatList.clear()
        chatList.addAll(
            listOf(
                ChatItem("1", null, "Cô Lan Anh - Lớp Mầm 1", "Ngày mai bé nhớ mang áo mưa nhé", "10:45", isUnread = true),
                ChatItem("2", null, "Cô Mai - Lớp Lá 2", "Bé hôm nay ăn rất ngoan", "09:30"),
                ChatItem("3", null, "Cô Hạnh - Lớp Chồi 3", "Đang soạn tin nhắn...", "08:15", isTyping = true)
            )
        )
        // Đưa dữ liệu từ chatList sang filteredList lần đầu tiên
        filterChats("")
    }
}