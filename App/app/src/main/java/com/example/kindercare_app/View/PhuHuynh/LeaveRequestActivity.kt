package com.example.kindercare_app.View.PhuHuynh

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kindercare_app.Model.LeaveRequestModel
import com.example.kindercare_app.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class LeaveRequestActivity : AppCompatActivity() {

    private lateinit var tvFromDate: TextView
    private lateinit var tvToDate: TextView
    private lateinit var cbMorning: CheckBox
    private lateinit var cbAfternoon: CheckBox
    private lateinit var spReasonCategory: Spinner
    private lateinit var edtReasonDetail: EditText
    private lateinit var btnUploadImage: LinearLayout
    private lateinit var btnUploadFile: LinearLayout
    private lateinit var btnSubmit: Button
    private lateinit var btnBack: ImageView

    // Quản lý ngày tháng năm bằng Calendar và định dạng chuẩn dd/MM/yyyy
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_leave_request)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvFromDate = findViewById(R.id.tvFromDate)
        tvToDate = findViewById(R.id.tvToDate)
        cbMorning = findViewById(R.id.cbMorning)
        cbAfternoon = findViewById(R.id.cbAfternoon)
        spReasonCategory = findViewById(R.id.spReasonCategory)
        edtReasonDetail = findViewById(R.id.edtReasonDetail)
        btnUploadImage = findViewById(R.id.btnUploadImage)
        btnUploadFile = findViewById(R.id.btnUploadFile)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        // Cấu hình danh mục lý do nghỉ học cho Spinner
        val reasons = listOf("Chọn lý do xin nghỉ", "Bị ốm/Sốt", "Đi du lịch cùng gia đình", "Có việc gia đình riêng", "Khác")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, reasons)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spReasonCategory.adapter = adapter

        // Bắt sự kiện chọn Ngày bằng DatePickerDialog
        tvFromDate.setOnClickListener {
            showDatePickerDialog(tvFromDate, "Từ ngày")
        }
        tvToDate.setOnClickListener {
            showDatePickerDialog(tvToDate, "Đến ngày")
        }

        // Bắt sự kiện tải phương tiện đính kèm
        btnUploadImage.setOnClickListener {
            Toast.makeText(this, "Mở Bộ sưu tập ảnh", Toast.LENGTH_SHORT).show()
        }
        btnUploadFile.setOnClickListener {
            Toast.makeText(this, "Mở trình quản lý Tệp", Toast.LENGTH_SHORT).show()
        }

        // Xử lý khi nhấn "Gửi đơn xin nghỉ"
        btnSubmit.setOnClickListener {
            val selectedReason = spReasonCategory.selectedItem.toString()
            if (selectedReason == "Chọn lý do xin nghỉ") {
                Toast.makeText(this, "Vui lòng chọn lý do nghỉ học!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Thu thập dữ liệu vào Model
            val requestData = LeaveRequestModel(
                parentName = findViewById<TextView>(R.id.tvParentName).text.toString(),
                studentName = findViewById<TextView>(R.id.tvStudentName).text.toString(),
                className = findViewById<TextView>(R.id.tvClassName).text.toString(),
                fromDate = tvFromDate.text.toString(),
                toDate = tvToDate.text.toString(),
                isMorningOff = cbMorning.isChecked,
                isAfternoonOff = cbAfternoon.isChecked,
                reasonCategory = selectedReason,
                reasonDetail = edtReasonDetail.text.toString()
            )

            Toast.makeText(this, "Gửi đơn thành công cho bé: ${requestData.studentName}", Toast.LENGTH_LONG).show()
            finish() // Đóng màn hình sau khi gửi đơn thành công
        }
    }

    /**
     * Hàm dùng chung để hiển thị Hộp thoại chọn Ngày - Tháng - Năm
     */
    private fun showDatePickerDialog(textView: TextView, titlePrefix: String) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Tạo một instance thời gian mới dựa trên ngày người dùng chọn
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                // Chuyển ngày sang định dạng chuỗi dd/MM/yyyy
                val formattedDate = dateFormat.format(selectedCalendar.time)

                // Cập nhật lại giao diện text (Giữ đúng cấu trúc xuống dòng "\n" như file XML của bạn)
                textView.text = "$titlePrefix\n$formattedDate"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}