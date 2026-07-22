# Thiết kế RESTful API cho Bác sĩ (Doctors)

Dưới đây là danh sách 5 Endpoint chuẩn RESTful cho các thao tác quản lý tài nguyên "Bác sĩ" trong hệ thống quản lý bệnh viện:

| Thao tác | HTTP Method | Endpoint | Mô tả |
| :--- | :--- | :--- | :--- |
| **Lấy danh sách toàn bộ bác sĩ** | `GET` | `/api/v1/doctors` | Trả về danh sách tất cả các bác sĩ hiện có trong hệ thống. |
| **Lấy thông tin chi tiết một bác sĩ** | `GET` | `/api/v1/doctors/{id}` | Lấy thông tin chi tiết của một bác sĩ cụ thể dựa vào `id`. |
| **Thêm mới một bác sĩ** | `POST` | `/api/v1/doctors` | Tạo một bản ghi bác sĩ mới trong hệ thống. |
| **Cập nhật thông tin bác sĩ** | `PUT` | `/api/v1/doctors/{id}` | Cập nhật toàn bộ thông tin của một bác sĩ đã tồn tại dựa theo `id`. |
| **Xóa bác sĩ khỏi hệ thống** | `DELETE` | `/api/v1/doctors/{id}` | Xóa một bác sĩ khỏi hệ thống dựa vào `id`. |

### 📌 Lưu ý về chuẩn RESTful đã áp dụng:
- Sử dụng danh từ số nhiều `doctors` để đại diện cho tài nguyên.
- Không sử dụng động từ trên URL (như `/getAllDoctors`, `/createDoctor`, `/deleteDoctor`...).
- Phân biệt các hành động (Lấy, Thêm, Sửa, Xóa) thông qua các **HTTP Method** tương ứng (`GET`, `POST`, `PUT`, `DELETE`).
- Khuyến nghị sử dụng versioning (`/v1/`) trong thiết kế API để dễ dàng mở rộng và bảo trì sau này.

---

# Phân biệt các mã lỗi HTTP (400, 404, 500)

Dưới đây là phân tích và lựa chọn HTTP Status Code phù hợp cho các tình huống thực tế trong hệ thống y tế:

### TH1: Người dùng gửi yêu cầu đặt lịch khám nhưng bỏ trống tên bệnh nhân.
- **Mã lỗi:** `400 Bad Request`
- **Giải thích:** Lỗi này xảy ra ở phía client (người dùng) do gửi thiếu trường dữ liệu bắt buộc (tên bệnh nhân). Yêu cầu (Request) không hợp lệ nên server từ chối xử lý.

### TH2: Tìm kiếm hồ sơ bệnh án với ID là 999 nhưng trong Database không tồn tại.
- **Mã lỗi:** `404 Not Found`
- **Giải thích:** Server đã tiếp nhận và hiểu yêu cầu từ client, nhưng tài nguyên mà client muốn truy xuất (hồ sơ bệnh án có ID 999) lại không tồn tại trong hệ thống (database).

### TH3: Hệ thống đang chạy thì Database MySQL bị sập, không thể truy vấn dữ liệu.
- **Mã lỗi:** `500 Internal Server Error`
- **Giải thích:** Lỗi này hoàn toàn xuất phát từ phía server (máy chủ). Client đã gửi một Request hợp lệ, nhưng server không thể trả về kết quả do gặp sự cố hệ thống nội bộ (không kết nối được với cơ sở dữ liệu).

### TH4: Người dùng nhập tuổi bệnh nhân là -5 (Dữ liệu không hợp lệ về mặt logic).
- **Mã lỗi:** `400 Bad Request` (hoặc `422 Unprocessable Entity`)
- **Giải thích:** Đây là lỗi Validation do phía client gửi dữ liệu sai logic nghiệp vụ (tuổi không thể là số âm). Dựa trên phân loại chính (400, 404, 500) thì `400 Bad Request` là đáp án phù hợp nhất để chỉ ra yêu cầu có dữ liệu đầu vào không hợp lệ.
