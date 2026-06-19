package com.example.kindercare_app.View.PhuHuynh

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R
import com.example.kindercare_app.adapter.CalendarAdapter
import com.example.kindercare_app.adapter.EventAdapter
import com.example.kindercare_app.Model.AttendanceDay
import com.example.kindercare_app.Model.UpcomingEvent

class AttendanceCalendarActivity : AppCompatActivity() {

    private lateinit var rvCalendar: RecyclerView
    private lateinit var rvEvents: RecyclerView
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_attendance_calendar)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initViews()


        btnBack.setOnClickListener {
            finish()
        }

        // 3. Cấu hình hiển thị và nạp dữ liệu cho Lịch điểm danh (Lưới 7 cột)
        setupCalendarRecyclerView()
        // 4. Cấu hình hiển thị và nạp dữ liệu cho Danh sách sự kiện sắp tới (Hàng dọc)
        setupEventsRecyclerView()
    }

    private fun initViews() {
        rvCalendar = findViewById(R.id.rvCalendar)
        rvEvents = findViewById(R.id.rvEvents)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupCalendarRecyclerView() {
        // Sử dụng GridLayoutManager với 7 cột tương ứng với các thứ từ T2 -> CN
        rvCalendar.layoutManager = GridLayoutManager(this, 7)

        // Đổ dữ liệu mẫu cho Lịch
        val daysData = generateDummyDays()
        rvCalendar.adapter = CalendarAdapter(daysData)
    }

    private fun setupEventsRecyclerView() {
        // Sử dụng LinearLayoutManager để hiển thị danh sách dạng danh sách cuộn dọc
        rvEvents.layoutManager = LinearLayoutManager(this)

        // Đổ dữ liệu mẫu cho Sự kiện
        val eventsData = generateDummyEvents()
        rvEvents.adapter = EventAdapter(eventsData)
    }


    private fun generateDummyDays(): List<AttendanceDay> {
        val list = mutableListOf<AttendanceDay>()

        // Giả lập ngày 01 đi học (Xanh lục)
        list.add(AttendanceDay("01", 1))

        for (i in 2..11) {
            list.add(AttendanceDay(String.format("%02d", i), 0))
        }
        list.add(AttendanceDay("12", 2))

        for (i in 13..31) {
            list.add(AttendanceDay(String.format("%02d", i), 0))
        }
        for (i in 1..5) {
            list.add(AttendanceDay(String.format("%02d", i), 0))
        }

        return list
    }


    private fun generateDummyEvents(): List<UpcomingEvent> {
        return listOf(
            UpcomingEvent("15", "5", "Họp phụ huynh đầu năm", "09:00 AM", "Phòng đa năng"),
            UpcomingEvent("20", "5", "Lễ hội bé khỏe bé ngoan", "08:30 AM", "Sân trường chính"),
            UpcomingEvent("28", "5", "Dã ngoại sở thú", "07:00 AM", "Toàn khối Mầm Non")
        )
    }
}