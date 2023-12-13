# API Task Management System

Изначально я составил план для проектирования api на scrum доске
![img_1.png](img_1.png)
## Процесс авторизации и регистрации
На диаграмме показан процесс того, как я реализую регистрацию пользователя и авторизацию.
![spring-boot-jwt-authentication-spring-security-flow](spring-boot-jwt-authentication-spring-security-flow.png)
## Jwt token
Также была реализована аутентификация с помощью jwt token
![img.png](img.png)
## Тестирование api
Первоначально api было протестировано в postman, ниже представлены основные методы и их вывод
![img_2.png](img_2.png)
Регистрация пользователя
![img_3.png](img_3.png)
Авторизация пользователя
![img_4.png](img_4.png)
Данные в бд
![img_5.png](img_5.png)
Создание задачи
![img_6.png](img_6.png)
Ошибка (все ошибки были представлены в виде ApiError, а exceptions описаны в ErrorHandler)
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
![img_7.png](img_7.png)
Обновление задачи
![img_8.png](img_8.png)
Создание комментария
## Документирование
Api было задокументировано с помощью spring boot 3 swagger
## Планы для развития api
В дальнейшем можно добавить новые возможности админу приложения, для удаления пользователей и установления роли пользователю "только для чтения"
Также можно добавить рейтинг активности пользователей, чтобы просматривать кто и сколько создает/реализовывает задачи
## Что я не успел сделать
К сожалению, я не успел написать тесты для базовой реализации api.
Поэтому решил оставить ссылку на свой проект java-shareit https://github.com/DDDimbo/java-shareit, где показаны мои навыки в написании тестов.
