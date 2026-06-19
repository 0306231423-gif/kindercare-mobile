package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// Định nghĩa Data Class trực tiếp hoặc tách file riêng tùy bạn
data class TuitionItem(
    val title: String,
    val isPaid: Boolean
)

class FeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fee)

        // Giữ nguyên đoạn xử lý tràn viền hệ thống của bạn
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- BẮT ĐẦU CODE LOGIC XỬ LÝ RECYCLERVIEW ---
        val rvTuitionList = findViewById<RecyclerView>(R.id.rvTuitionList)
        rvTuitionList.layoutManager = LinearLayoutManager(this)

        // Dữ liệu mẫu hiển thị "học phí định kỳ" (Chưa thanh toán) giống trong ảnh
        val tuitionData = listOf(
            TuitionItem("học phí định kỳ", isPaid = false)
        )

        // Thiết lập Adapter
        val adapter = TuitionAdapter(tuitionData)
        rvTuitionList.adapter = adapter
        val img_notification = findViewById<View>(R.id.ivNotification)
        val img_top_avatar = findViewById<View>(R.id.ivAvatar)
        img_notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        img_top_avatar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_fee // Đặt Tổng quan làm mặc định

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. Tab Tổng quan (Trang chủ hiện tại)
                R.id.nav_fee -> {
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

                // 3. Tab Trang chủ
                R.id.nav_overview -> {
                    val intent = Intent(this, HomePHActivity::class.java)
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

                // 5. Tab Liên lạc phụ huynh
                R.id.nav_contact -> {
                    // Thay thế ContactActivity bằng tên Activity chat/liên lạc của bạn nếu có khác biệt
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }
    }
}