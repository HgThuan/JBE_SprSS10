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
