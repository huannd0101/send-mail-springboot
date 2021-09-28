#Demo send mail spring boot
##Add dependences
* Sử dụng để send mail
<pre>
    spring-boot-starter-mail
</pre>
* Sử dụng để dùng thymeleaf
<pre>
    thymeleaf-spring5
</pre>

## Config 
- Tạo bean với interface JavaMailSender
- Khai báo đối tượng JavaMailSenderImpl và add các thuộc tính sau:
1. mailSender.setHost("smtp.gmail.com");
2. mailSender.setPort(587);
3. mailSender.setUsername(MY_EMAIL);
4. mailSender.setPassword(MY_PASSWORD);
5. Khai báo đối tượng Properties properties = mailSender.getJavaMailProperties();
   
    properties.put("mail.transport.protocol", "smtp");

    properties.put("mail.smtp.auth", "true");

    properties.put("mail.smtp.starttls.enable", "true");

    properties.put("mail.debug", "false");
## Đối với password mail

- Dùng mail ứng dụng: https://support.google.com/accounts/answer/185833?hl=vi 
- Đọc từng bước trong link trên để tạo mật khẩu cho ứng dụng dùng như mật khẩu bình thường

## Đảm bảo rằng tắt Ứng dụng kém an toàn của gmail

Các bước thực hiện:

Bước 1: Đăng nhập vào tài khoản Gmail trên web gmail.com

Bước 2: Truy cập vào link https://myaccount.google.com/lesssecureapps?pli=1

Bước 3: Click chọn "Quyền truy cập của ứng dụng kém an toàn" click nút gạt màu Xanh để bật sang chế độ cho phép như hình dưới.

## Code thì tự đọc nhé :v
(Nguồn tham khảo trên mạng lâu lâu quên link :v)