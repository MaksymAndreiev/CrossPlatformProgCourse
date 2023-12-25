# Laboratory Work 3: Serialization

In this lab, we will explore the capabilities provided by the Java API for organizing object serialization and
deserialization.

## Main Tasks

### Task 1: Library Management Program

Develop a program that manages readers and books in a library. This involves creating a set of classes representing the
library (Author, Book, Bookshelf, Reader, Rental, etc.). Each class should have a default constructor, getters and
setters, an overridden toString() method, and methods implementing basic functionality. Use the serialization mechanism
to save the current state of the system to a file and then restore the system from this file. Implement three versions
of the program that use different serialization strategies:

1. All classes in the system are serializable (implement the java.io.Serializable interface).
2. Not all classes in the system are serializable; for example, the class "Educational Subject" is not serializable, and
   the field defining the number of points earned by the student is a transient field (implement a simple encryption
   mechanism during saving).
3. Classes in the system implement the java.io.Externalizable interface.

The first version of the program should be implemented as a console application, where the main function demonstrates
the system's operation: a "library" is created with books and a list of readers, books are issued, information about the
current state of the library is displayed, and the state of the deserialized system is shown. Serialization and
deserialization should also be performed.

## Additional Tasks

### Task 1: Educational Process Modeling Program

- Create a program to model the educational process in a higher education institution.
- Develop classes representing academic subjects, study plans, teachers, students, etc.
- Implement serialization to save the system's state to a file and restore the system from this file.

## Some results

# Лабораторна робота 3: Серіалізація

У цій лабораторній роботі ми розглянемо можливості, які надає Java API для організації серіалізації та
десеріалізації.

## Основні завдання

### Завдання 1: Програма управління бібліотекою

Розробіть програму, яка керує читачами та книгами в бібліотеці. Для цього необхідно створити набір класів, що представляють бібліотеку (Автор, Книга, Поличка, Читач і т.д.).
бібліотеку (Автор, Книга, Книжкова полиця, Читач, Прокат і т.д.). Кожен клас повинен мати конструктор за замовчуванням, геттери та
та встановлювачі, перевизначений метод toString(), а також методи, що реалізують базову функціональність. Використовуйте механізм серіалізації
для збереження поточного стану системи у файл і подальшого відновлення системи з цього файлу. Реалізувати три версії
програми, що використовують різні стратегії серіалізації:

1. Усі класи в системі є серіалізованими (реалізувати інтерфейс java.io.Serializable).
2. Не всі класи в системі є серіалізованими, наприклад, клас "Навчальний предмет" не є серіалізованим, а
   поле, що визначає кількість балів, набраних студентом, є перехідним (реалізувати простий механізм шифрування при збереженні).
   механізм шифрування при збереженні).
3. Класи в системі реалізують інтерфейс java.io.Externalizable.

Перша версія програми повинна бути реалізована у вигляді консольного додатку, де основна функція демонструє
роботу системи: створюється "бібліотека" з книгами та списком читачів, видаються книги, виводиться інформація про
поточний стан бібліотеки, виводиться інформація про поточний стан бібліотеки, а також показується стан десеріалізованої системи. Серіалізація та
десеріалізація також повинні бути виконані.

## Додаткові завдання

### Завдання 1: Програма моделювання навчального процесу

- Створіть програму для моделювання навчального процесу у вищому навчальному закладі.
- Розробити класи, що представляють навчальні предмети, навчальні плани, викладачів, студентів тощо.
- Реалізувати серіалізацію для збереження стану системи у файл та відновлення системи з цього файлу.

## Деякі результати