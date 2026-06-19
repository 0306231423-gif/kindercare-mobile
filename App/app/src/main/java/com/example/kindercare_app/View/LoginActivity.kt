package com.example.kindercare_app.View

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kindercare_app.R
import com.example.kindercare_app.View.ForgotPasswordActivity
import com.example.kindercare_app.View.GiaoVien.ManHinhBangDieuKhien
import com.example.kindercare_app.View.PhuHuynh.HomePHActivity

class LoginActivity : AppCompatActivity() {


    private lateinit var edtPhoneNumber: EditText
    private lateinit var edtPassword: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initViews()


        setupRoleSpinner()


        setupClickListeners()
    }


    private fun initViews() {
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        edtPassword = findViewById(R.id.edtPassword)
        spinnerRole = findViewById(R.id.spinnerRole)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }


    private fun setupRoleSpinner() {

        val roles = arrayOf("Phụ huynh", "Giáo viên")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter
    }

    /**
     * Hàm quản lý tất cả sự kiện bấm nút (Click Listener)
     */
    private fun setupClickListeners() {
        // Xử lý sự kiện khi bấm nút Đăng nhập
        btnLogin.setOnClickListener {

            handleLogin()
        }

        // Xử lý sự kiện khi bấm nút Quên mật khẩu
        tvForgotPassword.setOnClickListener {
           tvForgotPassword.setOnClickListener {
               val intent = Intent(this, ForgotPasswordActivity::class.java)
               startActivity(intent)
           }
        }
    }

    /**
     * Hàm thực hiện logic kiểm tra dữ liệu đầu vào và xử lý đăng nhập
     */
    private fun handleLogin() {
        val phoneNumber = edtPhoneNumber.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val selectedRole = spinnerRole.selectedItem.toString()

        // Kiểm tra xem người dùng đã điền đủ thông tin chưa
        if (phoneNumber.isEmpty()) {
            edtPhoneNumber.error = "Số điện thoại không được để trống"
            edtPhoneNumber.requestFocus()
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = "Mật khẩu không được để trống"
            edtPassword.requestFocus()
            return
        }

        // Thông báo giả định khi dữ liệu hợp lệ (Bạn sẽ thay thế bằng logic gọi API/Firebase ở đây)
        val message = "Đăng nhập thành công với vai trò: $selectedRole"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        val intent = when (selectedRole) {
            "Phụ huynh" -> Intent(this, HomePHActivity::class.java)
            "Giáo viên" -> Intent(this, ManHinhBangDieuKhien::class.java)
            "Quản trị viên" -> {
                // Tạm thời hiển thị Toast vì chưa có màn hình Admin, hoặc bạn có thể đổi thành màn hình tùy ý
                Toast.makeText(this, "Tính năng Admin đang phát triển", Toast.LENGTH_SHORT).show()
                return
            }
            else -> return
        }
        startActivity(intent)
        finish()
    }
}