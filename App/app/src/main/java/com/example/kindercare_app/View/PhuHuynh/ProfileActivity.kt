package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kindercare_app.R
import com.example.kindercare_app.View.LoginActivity


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack = findViewById<ImageView>(R.id.btnBack_profile)
        val btnUpdateInfo = findViewById<Button>(R.id.btnUpdateInfo)
        val btnChangePassword = findViewById<Button>(R.id.btnChangePassword)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Sử dụng API mới thay thế cho finish() hoặc onBackPressed() cũ
        }

        // Sự kiện Cập nhật thông tin
        btnUpdateInfo.setOnClickListener {
            Toast.makeText(this, "Tính năng: Cập nhật thông tin cá nhân", Toast.LENGTH_SHORT).show()

             val intent = Intent(this, UpdateInfoActivity::class.java)
             startActivity(intent)
        }

        // Sự kiện Đổi mật khẩu
        btnChangePassword.setOnClickListener {
            Toast.makeText(this, "Tính năng: Đổi mật khẩu", Toast.LENGTH_SHORT).show()
             val intent = Intent(this, ChangePasswordActivity::class.java)
             startActivity(intent)
        }

        // Sự kiện Đăng xuất khỏi hệ thống
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Đang xử lý đăng xuất...", Toast.LENGTH_SHORT).show()

            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}