package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kindercare_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChildProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_child_profile)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnNotification = findViewById<View>(R.id.btnNotification)
        val imgAvatarHeader = findViewById<View>(R.id.imgAvatarHeader)
        btnNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        imgAvatarHeader.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Đổ dữ liệu trực tiếp vào các ô thông tin tĩnh qua ID của thẻ <include>
        bindStaticData()

        // Xử lý sự kiện click cho nút Cập nhật thông tin
        findViewById<View>(R.id.btnRequestUpdate).setOnClickListener {
            val intent = Intent(this, RequestChangeActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Gửi yêu cầu cập nhật lên Nhà trường thành công!", Toast.LENGTH_SHORT).show()
        }


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_profile // Đặt Tổng quan làm mặc định

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. Tab Tổng quan (Trang chủ hiện tại)
                R.id.nav_profile -> {
                    true
                }

                // 2. Tab Trang chủ
                R.id.nav_overview -> {
                    val intent = Intent(this, HomePHActivity::class.java)
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

    private fun bindStaticData() {
        // Ô Ngày sinh
        val boxBirth = findViewById<View>(R.id.boxNgaySinh)
        boxBirth.findViewById<TextView>(R.id.tvBoxLabel).text = "NGÀY SINH"
        boxBirth.findViewById<TextView>(R.id.tvBoxValue).text = "15/08/2021"

        // Ô  Giới tính
        val boxGender = findViewById<View>(R.id.boxGioiTinh)
        boxGender.findViewById<TextView>(R.id.tvBoxLabel).text = "GIỚI TÍNH"
        boxGender.findViewById<TextView>(R.id.tvBoxValue).text = "Nam"

        // Ô  Mã học sinh
        val boxId = findViewById<View>(R.id.boxMaHocSinh)
        boxId.findViewById<TextView>(R.id.tvBoxLabel).text = "MÃ HỌC SINH"
        boxId.findViewById<TextView>(R.id.tvBoxValue).text = "KC-2024-102"

        // Ô  Ngày nhập học
        val boxAdmission = findViewById<View>(R.id.boxNgayNhapHoc)
        boxAdmission.findViewById<TextView>(R.id.tvBoxLabel).text = "NGÀY NHẬP HỌC"
        boxAdmission.findViewById<TextView>(R.id.tvBoxValue).text = "05/09/2023"
    }
}