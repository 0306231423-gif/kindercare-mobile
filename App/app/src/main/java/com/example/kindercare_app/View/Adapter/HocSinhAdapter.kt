package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kindercare_app.R

data class HocSinh(
    val ten: String,
    val thongTinPhu: String,
    val ngaySinh: String,
    val tenPhuHuynh: String,
    val avatarResId: Int,
    val diUng: String? = null
)

class HocSinhAdapter(private var listHocSinh: List<HocSinh>) :
    RecyclerView.Adapter<HocSinhAdapter.HocSinhViewHolder>() {

    private var fullListHocSinh: List<HocSinh> = listHocSinh

    fun filter(query: String) {
        listHocSinh = if (query.isEmpty()) {
            fullListHocSinh
        } else {
            fullListHocSinh.filter { it.ten.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HocSinhViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hocsinh, parent, false)
        return HocSinhViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HocSinhViewHolder,
        position: Int
    ) {
        val hs = listHocSinh[position]

        holder.tvTen.text = hs.ten
        holder.tvThongTinPhu.text = hs.thongTinPhu
        holder.tvNgaySinh.text = hs.ngaySinh
        holder.tvTenPhuHuynh.text = "Mẹ: ${hs.tenPhuHuynh}"
        holder.imgAvatar.setImageResource(hs.avatarResId)

        if (!hs.diUng.isNullOrEmpty()) {
            holder.layoutDiUng.visibility = View.VISIBLE
            holder.tvGhiChuDiUng.text = hs.diUng
        } else {
            holder.layoutDiUng.visibility = View.GONE
        }

        holder.btnCall.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Gọi cho mẹ của ${hs.ten}", Toast.LENGTH_SHORT).show()
        }

        holder.btnMessage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Nhắn tin cho mẹ của ${hs.ten}", Toast.LENGTH_SHORT).show()
        }

        holder.btnMoreOptions.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Tùy chọn cho ${hs.ten}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return listHocSinh.size
    }

    class HocSinhViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatarHocSinh)
        val tvTen: TextView = itemView.findViewById(R.id.tvTenHocSinh)
        val tvThongTinPhu: TextView = itemView.findViewById(R.id.tvThongTinPhu)
        val tvNgaySinh: TextView = itemView.findViewById(R.id.tvNgaySinh)
        val tvTenPhuHuynh: TextView = itemView.findViewById(R.id.tvTenPhuHuynh)
        val layoutDiUng: LinearLayout = itemView.findViewById(R.id.layoutDiUng)
        val tvGhiChuDiUng: TextView = itemView.findViewById(R.id.tvGhiChuDiUng)
        val btnMoreOptions: ImageView = itemView.findViewById(R.id.btnMoreOptions)
        val btnCall: ImageView = itemView.findViewById(R.id.btnCall)
        val btnMessage: ImageView = itemView.findViewById(R.id.btnMessage)
    }
}
