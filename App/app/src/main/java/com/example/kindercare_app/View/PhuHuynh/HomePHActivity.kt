package com.example.kindercare_app.View.PhuHuynh

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R
import com.example.kindercare_app.Model.FeatureModel
import com.example.kindercare_app.Model.MomentModel
import com.example.kindercare_app.Model.HomeItem
import com.example.kindercare_app.View.Adapter.PhuHuynhAdapter
import com.example.kindercare_app.View.Adapter.FeatureAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePHActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_phactivity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
        supportActionBar?.hide()

        val btn_profile = findViewById<View>(R.id.btn_profile)
        val btn_thongbao = findViewById<View>(R.id.btn_thongbao)
        val rvFeatures = findViewById<RecyclerView>(R.id.rvFeatures)
        val rvMoments = findViewById<RecyclerView>(R.id.rvMoments)

        btn_profile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
        btn_thongbao.setOnClickListener { startActivity(Intent(this, NotificationActivity::class.java)) }

        // 1. Khôi phục Tiện ích mở rộng (Features)
        val features = listOf(
            FeatureModel("Sức khoẻ", R.drawable.ic_health),
            FeatureModel("Xin nghỉ", R.drawable.ic_activity),
            FeatureModel("Dặn dò", R.drawable.ic_chat),
            FeatureModel("Báo cáo", R.drawable.ic_grid_overview),
            FeatureModel("Lịch học", R.drawable.ic_calendar),
            FeatureModel("Thực đơn", R.drawable.ic_cake)
        )

        rvFeatures.layoutManager = GridLayoutManager(this, 3)
        // Dùng FeatureAdapter trực tiếp để hiển thị item_feature_ph
        rvFeatures.adapter = FeatureAdapter(features) { feature ->
            handleFeatureClick(feature)
        }

        // 2. Khôi phục Khoảnh khắc (Moments)
        val mockMomentsData = listOf(
            MomentModel("Hôm nay, 13/05/2024", true, listOf(R.drawable.logo, R.drawable.logo)),
            MomentModel("Hôm qua, 12/05/2024", false, listOf(R.drawable.logo))
        )
        val momentItems = mockMomentsData.map { HomeItem.MomentItem(it) }
        rvMoments.layoutManager = LinearLayoutManager(this)
        rvMoments.adapter = PhuHuynhAdapter(momentItems) {}

        setupBottomNavigation()
    }

    private fun handleFeatureClick(feature: FeatureModel) {
        when (feature.title) {
            "Sức khoẻ" -> startActivity(Intent(this, HealthActivity::class.java))
            "Xin nghỉ" -> startActivity(Intent(this, LeaveRequestActivity::class.java))
            "Lịch học" -> startActivity(Intent(this, AttendanceCalendarActivity::class.java))
            "Dặn dò" -> startActivity(Intent(this, PlayActivity::class.java))
            "Báo cáo" -> startActivity(Intent(this, ReportActivity::class.java))
            "Thực đơn" -> startActivity(Intent(this, MealDetailActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.nav_overview
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_overview -> true
                R.id.nav_profile -> { startActivity(Intent(this, ChildProfileActivity::class.java)); overridePendingTransition(0,0); true }
                R.id.nav_fee -> { startActivity(Intent(this, FeeActivity::class.java)); overridePendingTransition(0,0); true }
                R.id.nav_activity -> { startActivity(Intent(this, ActivityActivity::class.java)); overridePendingTransition(0,0); true }
                R.id.nav_contact -> { startActivity(Intent(this, ChatActivity::class.java)); overridePendingTransition(0,0); true }
                else -> false
            }
        }
    }
}