package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R

data class StudentAttendance(
    val id: Int,
    val name: String,
    val avatarResId: Int,
    var status: String // "Hiện diện", "Nghỉ", "Muộn"
)

class DiemDanhAdapter(
    private var studentList: List<StudentAttendance>,
    private val onStatusChanged: () -> Unit
) : RecyclerView.Adapter<DiemDanhAdapter.DiemDanhViewHolder>() {

    private var fullStudentList: List<StudentAttendance> = studentList

    fun filter(query: String) {
        studentList = if (query.isEmpty()) {
            fullStudentList
        } else {
            fullStudentList.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiemDanhViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diem_danh, parent, false)
        return DiemDanhViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiemDanhViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvIndex.text = "${position + 1}#"
        holder.tvName.text = student.name
        holder.ivAvatar.setImageResource(student.avatarResId)

        updateStatusUI(holder.tvStatusButton, student.status)

        holder.tvStatusButton.setOnClickListener {
            // Cycle through statuses
            student.status = if (student.status == "Hiện diện") "Nghỉ" else "Hiện diện"
            updateStatusUI(holder.tvStatusButton, student.status)
            onStatusChanged()
        }
    }

    private fun updateStatusUI(tvStatus: TextView, status: String) {
        tvStatus.text = status
        when (status) {
            "Hiện diện" -> {
                tvStatus.setBackgroundResource(R.drawable.bg_status_present)
            }
            "Nghỉ" -> {
                tvStatus.setBackgroundResource(R.drawable.bg_status_leave)
            }
        }
    }

    override fun getItemCount(): Int = studentList.size

    class DiemDanhViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIndex: TextView = itemView.findViewById(R.id.tvStudentIndex)
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivStudentAvatar)
        val tvName: TextView = itemView.findViewById(R.id.tvStudentName)
        val tvStatusButton: TextView = itemView.findViewById(R.id.tvStatusButton)
    }
}
