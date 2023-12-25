# Консольное приложение для управления кинотеатром

## Описание приложения
Это консольное приложение разработано для облегчения управления данными о фильмах, сеансах и продаже билетов в кинотеатре. Приложение предоставляет ряд функциональностей, позволяющих администратору эффективно контролировать работу кинозала.

## Основные возможности
1. **Просмотр доступных мест:**
   - Администратор может просмотреть информацию о свободных и проданных местах для конкретного сеанса.

2. **Продажа билета:**
   - Продажа билета посетителям кинотеатра с возможностью выбора конкретного места на сеансе.

3. **Возврат билета:**
   - Возможность вернуть ранее проданный билет до начала сеанса.

4. **Редактирование фильма:**
   - Изменение информации о фильмах в репертуаре кинотеатра.

5. **Отметка посещения:**
   - Фиксация посещения посетителями конкретного сеанса.

6. **Добавление сеанса:**
   - Планирование нового сеанса для фильма с указанием времени и количества доступных мест.

7. **Добавление фильма:**
   - Добавление нового фильма в репертуар кинотеатра, предоставив его ID, название и длительность.
  
8. **Редактирование сеанса:**
   - Изменение времени сеанса и количества доступных мест.

9. **Выход:**
   - Сохранение всех изменений в CSV файлы и завершение работы приложения.

## Инструкция по использованию

### 1. Запуск приложения
   - Откройте файл `Main.kt` и запустите его.
   - Введите соответствующий номер опции из меню для выбора действия.

### 2. Ввод данных
   - Приложение предоставляет инструкции по вводу данных.
   - Следуйте указаниям на экране и вводите корректные значения.

### 3. Управление фильмами
   - Используйте опции 4 и 6 для редактирования фильмов и добавления новых сеансов соответственно.
   - При добавлении сеанса введите ID фильма, время начала (в формате dd-MM-yyyy HH:mm) и количество доступных мест.

### 4. Просмотр мест и продажа билетов
   - Опция 1 позволяет просматривать доступные и проданные места для конкретного сеанса.
   - Опция 2 позволяет продавать билеты, предоставив ID сеанса и выбрав номер места.

### 5. Возврат билетов
   - Для возврата билета выберите опцию 3 и введите ID сеанса и номер места.

### 6. Отметка посещения
   - Опция 5 используется для отметки посещения, используя ID сеанса и номер места.

### 7. Добавление фильмов
   - Выберите опцию 7 для добавления нового фильма, введя его ID, название и длительность.

### 8. Редактирование сеанса
   - Выберите опцию 8 для редактирования сеанса, введя его ID.

### 9. Выход из приложения
   - Опция 9 сохраняет все изменения в CSV файлы и закрывает приложение.

## Хранение данных
   - Информация о фильмах, сеансах и билетах сохраняется в файлах CSV (`movies.csv`, `sessions.csv`, `tickets.csv`).
   - Файлы CSV создаются автоматически при первом запуске приложения.

## Примечания
   - Проверьте настройки Kotlin для корректной компиляции и запуска приложения.
   - Приложение использует консольный интерфейс для простоты использования.

С этим консольным приложением вы сможете легко и удобно управлять вашим кинотеатром. Исследуйте все возможности и наслаждайтесь управлением!
