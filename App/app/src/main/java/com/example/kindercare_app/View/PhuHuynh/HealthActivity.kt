package com.example.kindercare_app.View.PhuHuynh

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R
 import com.example.kindercare_app.View.Adapter.VaccineAdapter
 import com.example.kindercare_app.Model.VaccineModel

class HealthActivity : AppCompatActivity() {

    private lateinit var rvVaccine: RecyclerView
    private lateinit var vaccineAdapter: VaccineAdapter
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        rvVaccine = findViewById(R.id.rvVaccine)
        btnBack=findViewById(R.id.btnBack)


        rvVaccine.layoutManager = LinearLayoutManager(this)


        val vaccineList = listOf(
            VaccineModel("Sởi – Quai bị – Rubella (MMR)", "Đã tiêm", true),
            VaccineModel("Cúm mùa (Influenza)", "Sắp tới hẹn", false),
            VaccineModel("Viêm gan B (HepB)", "Sắp tới hẹn", false),
            VaccineModel("Thủy đậu (Varicella)", "Sắp tới hẹn", false)
        )


        vaccineAdapter = VaccineAdapter(vaccineList)
        rvVaccine.adapter = vaccineAdapter
        btnBack.setOnClickListener {
            finish()
        }
    }
}