# VK Echo Bot

## Описание проекта
Этот проект реализует бота для ВКонтакте, который отвечает на полученные сообщения эхо-ответом.

## Требования
- Java 17+
- Gradle
- Учетная запись ВКонтакте
- Ngrok (по необходимости)

## Конфигурация
Перед запуском проекта необходимо получить ключи доступа и настроить конфигурацию.

### Получение ключей доступа VK API
1. **Создайте сообщество ВКонтакте**:
   - Перейдите на страницу ВКонтакте и создайте новое сообщество (например, "Сообщество").

2. **Получите ключ доступа (access_token)**:
   - Перейдите в раздел "Управление" -> "Работа с API" -> "Ключи доступа".
   - Создайте новый ключ доступа и выберите необходимые права доступа (например, отправка сообщений, чтение сообщений). Скопируйте этот ключ, он понадобится для конфигурации вашего бота.

3. **Настройте Callback API**:
   - Перейдите в раздел "Управление" -> "Callback API".
   - Нажмите "Создать сервер" и укажите URL вашего сервера (например, если вы используете ngrok, это будет ваш URL, сгенерированный в ngrok, например `http://<ngrok-url>/callback`).
   - Установите секретный ключ и скопируйте его. Этот ключ будет использоваться в качестве `vk.confirmation.token`.
   - Включите события, на которые бот будет реагировать (например, входящие сообщения).

### Конфигурация проекта
В файл `application.properties` в директории `src/main/resources` необходимо ввести необходмые ключи:

```properties
server.port=8080

# VK API tokens
vk.api.token=your_vk_api_token
vk.confirmation.token=your_confirmation_token
```

Замените `your_vk_api_token` и `your_confirmation_token` на значения, которые вы получили на предыдущих шагах.

## Запуск проекта

   ```bash
   ./gradlew bootRun
   ```
Или используйте стандартные средства IDE Idea


