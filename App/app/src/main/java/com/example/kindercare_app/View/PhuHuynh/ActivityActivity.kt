package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kindercare_app.View.Adapter.ActivityPostAdapter
import com.example.kindercare_app.Model.ActivityPost
import com.example.kindercare_app.R
import com.example.kindercare_app.databinding.ActivityActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityActivity : AppCompatActivity() {


    private lateinit var binding: ActivityActivityBinding
    private lateinit var activityAdapter: ActivityPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
       // setupBottomNavigation()
        val btn_notification = binding.btnNotification
        val img_top_avatar = binding.imgTopAvatar
        btn_notification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        img_top_avatar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_activity // Đặt Tổng quan làm mặc định

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. Tab Tổng quan (Trang chủ hiện tại)
                R.id.nav_activity -> {
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

                    val intent = Intent(this, FeeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 4. Tab trang chủ
                R.id.nav_overview    -> {
                    val intent = Intent(this, HomePHActivity::class.java)
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

    private fun setupRecyclerView() {

        // Tạo dữ liệu ảo hiển thị
        val mockData = listOf(
            ActivityPost("1", "Nguyen Thi Lan", "Homeroom Teacher", "May 26", "Meal plan on 26/05/2026"),
            ActivityPost("2", "Nguyen Thi Lan", "Homeroom Teacher", "June 24", "Meal plan on 26/05/2026"),
            ActivityPost("3", "Nguyen Thi Lan", "Homeroom Teacher", "May 23", "Meal plan on 26/05/2026")
        )


        activityAdapter = ActivityPostAdapter(
            mockData,
            onImageClick = { post ->
                // Click vào hình ảnh chuyển sang màn hình MealDetailActivity
                val intent = Intent(this, MealDetailActivity::class.java)
                startActivity(intent)
            },
            onCommentClick = { post ->
                // Click vào comment chuyển sang màn hình CommentActivity
                val intent = Intent(this, CommentActivity::class.java)
                startActivity(intent)
            }
        )


        binding.rvActivityPosts.apply {
            layoutManager = LinearLayoutManager(this@ActivityActivity)
            adapter = activityAdapter
        }

    }

   /* private fun setupBottomNavigation() {
        // Đánh dấu chọn sẵn tab Activity (Hoạt động) ở thanh menu dưới cùng
        binding.bottomNavigation.selectedItemId = R.id.nav_activity

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> { /* Điều hướng qua màn hình Hồ sơ bé */ true }
                R.id.nav_fee -> { /* Điều hướng qua màn hình Học phí */ true }
                R.id.nav_overview -> { /* Điều hướng qua màn hình Tổng quan */ true }
                R.id.nav_activity -> true // Đang ở chính màn hình Activity này
                R.id.nav_contact -> { /* Điều hướng qua màn hình Liên lạc phụ huynh */ true }
                else -> false
            }
        }
    }*/
}