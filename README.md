# DEMO REST API

Điều kiện:
- Spring-tool-suite-4-4.16.1 (nếu cài phiên bản khác sẽ có thể xung đột khi import project)
- Tool cần cài đặt lombok

Cách cài đặt:
- Import project bằng tool trên và đổi cấu hình sql trong file application.properties
- Ví dụ
spring.datasource.url=jdbc:mysql://localhost:3306/db_employee
spring.datasource.username=root
spring.datasource.password=
- Tạo một database mới và đổi đường dẫn đến database mong muốn, đổi cả username và password

Nếu tool không có vấn đề về lombok thì sẽ run thành công
-- Trong trường hợp tool không nhận lombok có thể sử dụng tool khác như intellij

- Các hình ảnh demo có trong file img