# Yêu cầu hệ thống để chạy Spring Boot và React

## 1. Yêu cầu phần cứng
- **RAM**: Ít nhất 8 GB
- **Bộ vi xử lý**: Intel Core i5 hoặc tương đương (hoặc mạnh hơn)
- **Dung lượng ổ cứng**: Ít nhất 10 GB trống
- **Mạng**: Kết nối internet ổn định để tải các phụ thuộc và chạy các dịch vụ online

## 2. Cài đặt phần mềm cần thiết

### a. **Cài đặt Spring Boot**
1. **Java JDK**:
   - Cài đặt **JDK 11** hoặc **JDK 17**.
   - Tải và cài đặt từ [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) hoặc [OpenJDK](https://openjdk.java.net/install/).
   
2. **Maven** (hoặc **Gradle**):
   - Spring Boot yêu cầu một công cụ build như Maven hoặc Gradle.
   - Cài đặt Maven từ [Apache Maven](https://maven.apache.org/download.cgi) hoặc Gradle từ [Gradle](https://gradle.org/install/).

3. **IDE**: Cài đặt một môi trường phát triển như:
   - **IntelliJ IDEA** (hoặc phiên bản cộng đồng).
   - **Eclipse** hoặc **VSCode** với plugin hỗ trợ Spring Boot.

### b. **Cài đặt React**
1. **Node.js và npm**:
   - Cài đặt **Node.js** (bao gồm cả npm - Node Package Manager) từ [Node.js](https://nodejs.org/).
   - Sau khi cài đặt, bạn có thể kiểm tra phiên bản với các lệnh:
     ```bash
     node -v
     npm -v
     ```

2. **Yarn (tùy chọn)**:
   - Bạn có thể cài đặt Yarn như một công cụ quản lý phụ thuộc thay thế npm từ [Yarn](https://yarnpkg.com/getting-started/install).

3. **IDE**: Cài đặt **VSCode** hoặc **WebStorm** để phát triển ứng dụng React.

## 3. Cấu hình Spring Boot

1. **Tạo dự án Spring Boot**:
   - Sử dụng [Spring Initializr](https://start.spring.io/) để tạo một dự án Spring Boot với các phụ thuộc sau:
     - Spring Web
     - Spring Data JPA
     - H2 (hoặc MySQL nếu bạn sử dụng cơ sở dữ liệu bên ngoài)
     - Spring Boot DevTools (tùy chọn, giúp tái tải lại ứng dụng khi thay đổi mã nguồn)

2. **Cấu hình `application.properties`**:
   - Cấu hình kết nối cơ sở dữ liệu (ví dụ: MySQL):
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Khởi động Spring Boot**:
   - Trong thư mục dự án Spring Boot, mở terminal và chạy lệnh:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Hoặc nếu sử dụng Gradle:
     ```bash
     ./gradlew bootRun
     ```

## 4. Cấu hình React

1. **Tạo dự án React**:
   - Mở terminal và chạy lệnh sau để tạo một ứng dụng React mới:
     ```bash
     npx create-react-app your-react-app
     ```

2. **Chạy ứng dụng React**:
   - Vào thư mục ứng dụng React:
     ```bash
     cd your-react-app
     ```
   - Khởi động ứng dụng React:
     ```bash
     npm start
     ```
   - Ứng dụng React sẽ chạy trên [http://localhost:3000](http://localhost:3000).

## 5. Kết nối Spring Boot và React

1. **Cấu hình API trong Spring Boot**:
   - Tạo các controller để xử lý các yêu cầu API.
   - Ví dụ, một API đơn giản trong `UserController.java`:
     ```java
     @RestController
     @RequestMapping("/api/users")
     public class UserController {
         @GetMapping
         public List<User> getAllUsers() {
             return userService.getAllUsers();
         }
     }
     ```

2. **Gửi yêu cầu từ React**:
   - Cài đặt **axios** hoặc sử dụng **fetch** trong React để gửi yêu cầu HTTP đến Spring Boot.
   - Cài đặt axios:
     ```bash
     npm install axios
     ```
   - Ví dụ gửi yêu cầu từ React:
     ```javascript
     import axios from 'axios';

     const getUsers = async () => {
         const response = await axios.get('http://localhost:8080/api/users');
         console.log(response.data);
     }

     useEffect(() => {
         getUsers();
     }, []);
     ```

## 6. Triển khai ứng dụng

- **Triển khai Spring Boot**:
  - Đóng gói ứng dụng Spring Boot thành file `.jar`:
    ```bash
    ./mvnw clean package
    ```
  - Chạy ứng dụng Spring Boot từ file `.jar`:
    ```bash
    java -jar target/your-application.jar
    ```

- **Triển khai React**:
  - Xây dựng ứng dụng React để triển khai lên môi trường sản xuất:
    ```bash
    npm run build
    ```

## Tóm tắt

Để chạy ứng dụng **Spring Boot** và **React** trên hệ thống của bạn, cần chuẩn bị môi trường phát triển với Java, Maven/Gradle, Node.js, và React. Sau đó, cấu hình API trong Spring Boot và giao tiếp với ứng dụng React qua HTTP. Khi phát triển xong, có thể triển khai ứng dụng lên các máy chủ sản xuất.
