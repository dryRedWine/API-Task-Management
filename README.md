# Task Management API

## Процесс авторизации и регистрации
На диаграмме показан процесс того, как я реализую регистрацию пользователя и авторизацию.
![spring-boot-jwt-authentication-spring-security-flow](pictures/spring-boot-jwt-authentication-spring-security-flow.png)
## Jwt token
Также была реализована аутентификация с помощью jwt token
![img.png](pictures/img.png)
## Тестирование api
Первоначально api было протестировано в postman, ниже представлены основные методы и их вывод
![img_2.png](pictures/img_2.png)
Регистрация пользователя
![img_3.png](pictures/img_3.png)
Авторизация пользователя
![img_4.png](pictures/img_4.png)
Данные в бд
![img_5.png](pictures/img_5.png)
Создание задачи
![img_6.png](pictures/img_6.png)
Все ошибки были представлены в виде ApiError, а exceptions описаны в ErrorHandler)
<pre>
  <code>
@ToString
@Getter
@Builder
public class ApiError {

    // Класс для понятного вывода ошибок

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private String reason;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private StackTraceElement[] errors;
}

  </code>
</pre>
![img_7.png](pictures/img_7.png)
Обновление задачи
![img_8.png](pictures/img_8.png)
Создание комментария
