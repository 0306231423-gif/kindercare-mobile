package com.example.kindercare_app.Model

// layout homePH
data class FeatureModel(
    val title: String,
    val iconRes: Int? = null
)
//layout khoảnh khắc
data class MomentModel(
    val dateText: String,
    val isToday: Boolean,
    val imageResIds: List<Int>
)
// layout lịch phụ hunh
data class AttendanceDay(
    val dayNumber: String,
    val status: Int // 0: Bình thường/Chưa học, 1: Đi học , 2: Vắng
)
// layout lịch phụ huynh
data class UpcomingEvent(
    val day: String,
    val month: String,
    val title: String,
    val time: String,
    val location: String
)
// layout Profilechild
data class ChildInfoModel(
    val label: String,
    val value: String
)
// layout sức khoẻ và thể chất
data class VaccineModel(
    val name: String,
    val status: String,
    val isVaccinated: Boolean // Dùng để thay đổi màu sắc trạng thái (
)
// layout hoạt dộng
data class ActivityPost(
    val id: String,
    val teacherName: String,
    val role: String,
    val dateText: String,
    val contentTitle: String,
    val imageResId: Int? = null,
    val avatarResId: Int? = null
)
// loayout đoạn chat
data class ChatItem(
    val id: String,
    val avatarUrl: String?,
    val senderName: String,
    val lastMessage: String,
    val time: String,
    val isUnread: Boolean = false,
    val isTyping: Boolean = false
)
// layout thông báo
data class NotificationModel(
    val id: Int,
    val title: String,
    val content: String,
    val time: String,
    val isUnread: Boolean,
    val isUrgent: Boolean = false,
    val hasAction: Boolean = false
)
//layout xin nghỉ
data class LeaveRequestModel(
    val parentName: String,
    val studentName: String,
    val className: String,
    val fromDate: String,
    val toDate: String,
    val isMorningOff: Boolean,
    val isAfternoonOff: Boolean,
    val reasonCategory: String,
    val reasonDetail: String
)
//layout feeactivity
data class TuitionItem(
    val title: String,
    val isPaid: Boolean
)
//layout Report
data class ReportModel(
    val title: String,
    val content: String,
    val time: String
)
// Định nghĩa Sealed Class HomeItem layout homePH
sealed class HomeItem {
    //  Tiêu đề
    data class HeaderTitle(val title: String) : HomeItem()

    // layout chức năng
    data class FeaturesBlock(val list: List<FeatureModel>) : HomeItem()

    // Layout sự kiện
    data class MomentItem(val moment: MomentModel) : HomeItem()
}

