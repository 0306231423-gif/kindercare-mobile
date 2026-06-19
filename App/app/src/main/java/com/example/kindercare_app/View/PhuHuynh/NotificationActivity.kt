package com.example.kindercare_app.View.PhuHuynh

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.Model.NotificationModel
import com.example.kindercare_app.R
import com.example.kindercare_app.View.Adapter.NotificationAdapter

class NotificationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private lateinit var btnback: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnback=findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerViewNotifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnback.setOnClickListener {
            finish()
        }
        val notificationList = listOf(
            NotificationModel(
                id = 1,
                title = "[KHẨN CẤP] Tới hạn thanh toán học phí",
                content = "Hôm nay là hạn chót thanh toán học phí Quý 3 cho bé Ngọc Châu. Vui lòng thanh toán sớm để đảm bảo quá trình học tập không bị gián đoạn.",
                time = "VỪA XONG",
                isUnread = true,
                isUrgent = true,
                hasAction = true
            ),
            NotificationModel(
                id = 2,
                title = "Thông báo thu học phí",
                content = "Nhà trường đã phát hành hóa đơn học phí Quý 3. Quý phụ huynh vui lòng kiểm tra thông tin chi tiết trong mục Hóa đơn.",
                time = "10 phút trước",
                isUnread = true
            ),
            NotificationModel(
                id = 3,
                title = "Đơn xin nghỉ phép đã được duyệt",
                content = "Giáo viên chủ nhiệm lớp Mầm 1 đã phê duyệt đơn xin nghỉ phép ngày 15/10 cho bé Ngọc Châu.",
                time = "1 tiếng trước",
                isUnread = true
            ),
            NotificationModel(
                id = 4,
                title = "Hoạt động mới của lớp",
                content = "Cô giáo vừa cập nhật 15 hình ảnh mới trong giờ học về sáng nay của các bé. Xem ngay tại thư viện ảnh!",
                time = "09:30 AM",
                isUnread = true
            ),
            NotificationModel(
                id = 5,
                title = "Nhắc nhở: Lịch khám sức khỏe định kỳ",
                content = "Đừng quên lịch khám sức khỏe tổng quát cho bé vào thứ 6 tuần này tại phòng y tế trường.",
                time = "Hôm qua",
                isUnread = false
            ),
            NotificationModel(
                id = 6,
                title = "Xác nhận dặn thuốc",
                content = "Nhà trường đã tiếp nhận thông tin dặn thuốc cho bé Ngọc Châu từ phụ huynh lúc 07:45 AM.",
                time = "Hôm qua",
                isUnread = false
            ),
            NotificationModel(
                id = 7,
                title = "Thực đơn tuần mới",
                content = "Thực đơn dinh dưỡng tuần từ 14/10 - 20/10 đã có sẵn. Quý phụ huynh có thể tham khảo tại mục Menu.",
                time = "3 ngày trước",
                isUnread = false
            )
        )


        adapter = NotificationAdapter(notificationList) { selectedItem ->
            // Xử lý sự kiện khi click vào nút "Thanh toán ngay"
            Toast.makeText(this, "Thực hiện hành động cho: ${selectedItem.title}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}