# Laboratory Work 2: Java Reflection

This lab focuses on exploring the capabilities provided by the Java Reflection API for analyzing class structure, object states and behaviors, as well as creating various types of objects and managing them.

## Main Tasks

### Task 1

Write a method that, given the full type name as a string or a Class object, returns a string with its full description, including the package name, class modifiers, class name, base class, implemented interfaces, and a list of all fields, constructors, and methods declared in the class, along with their characteristics.

### Task 2

Create a program that allows converting an object to a string with all the necessary information for its restoration, and also restoring an object from such a string.

### Task 3

Write a method that takes an object, a method name as a string, and a list of parameters required to call the method. If the method can be called on the given object, invoke it; otherwise, throw a FunctionNotFoundException.

### Task 4

Develop a program that enables the creation of one-dimensional arrays and matrices of primitive and reference types during runtime. The program should allow resizing arrays while preserving values and converting arrays and matrices into strings.

### Task 5

Create a program that demonstrates the use of dynamic proxy objects for profiling a method (displaying the method execution time) and for tracing a method (showing the method name, parameters, and computed value).

## Additional Exercises

### Exercise 1

For convenience in solving the tasks, you can create custom auxiliary classes, the objects of which will be created using reflection. The solution can be based on a recursive procedure for creating objects using reflection. The recursive call will end when an object is encountered for which data can be entered directly from the keyboard.

### Exercise 2

Develop a program to explore the constructors and methods of a class at runtime, and another program to convert an object to a string and restore the object from the string.

### Exercise 3

Create a program to work with dynamic proxy objects, implementing the InvocationHandler interface, and using them instead of real objects to solve a specific task.

### Exercise 4

Write a program that allows creating one-dimensional arrays and matrices of known types during program execution, changing their sizes while preserving content, and converting arrays and matrices into strings.

### Exercise 5

Develop a program that demonstrates the use of dynamic proxy objects for profiling and tracing a method, displaying the method execution time, name, parameters, and computed value.

## Some results

![image](https://github.com/MaksymAndreiev/CrossPlatformProgramming/assets/29687267/223c5b12-982c-4742-9898-0792a2198d38)

# Лабораторна робота 2: Відображення в Java

Ця лабораторна робота присвячена вивченню можливостей, що надаються Java Reflection API для аналізу структури класів, станів та поведінки об'єктів, а також створенню різних типів об'єктів та управлінню ними.

## Основні завдання

### Завдання 1

Напишіть метод, який за повним ім'ям типу у вигляді рядка або об'єкту Class повертає рядок з його повним описом, включаючи ім'я пакету, модифікатори класу, ім'я класу, базовий клас, реалізовані інтерфейси, а також список всіх полів, конструкторів та методів, оголошених у класі, разом з їх характеристиками.

### Завдання 2

Створити програму, яка дозволяє перетворити об'єкт у рядок з усією необхідною інформацією для його відновлення, а також відновити об'єкт з такого рядка.

### Завдання 3

Напишіть метод, який отримує об'єкт, ім'я методу у вигляді рядка та список параметрів, необхідних для виклику методу. Якщо метод можна викликати на заданому об'єкті, то викликати його, інакше згенерувати виключення FunctionNotFoundException.

### Завдання 4

Розробити програму, яка дозволяє створювати одновимірні масиви та матриці примітивних та посилальних типів під час виконання програми. Програма повинна дозволяти змінювати розміри масивів зі збереженням значень та перетворювати масиви та матриці у рядки.

### Завдання 5

Напишіть програму, яка демонструє використання динамічних проксі-об'єктів для профілювання методу (відображення часу виконання методу) та для трасування методу (відображення імені методу, параметрів та обчисленого значення).

## Додаткові вправи

### Вправа 1

Для зручності розв'язування задач можна створити власні допоміжні класи, об'єкти яких будуть створюватись з використанням рефлексії. Розв'язок може бути побудований на основі рекурсивної процедури створення об'єктів з використанням рефлексії. Рекурсивний виклик завершиться, коли зустрінеться об'єкт, для якого дані можна буде ввести безпосередньо з клавіатури.

### Вправа 2

Розробити програму для дослідження конструкторів та методів класу під час виконання, а також програму для перетворення об'єкту в рядок та відновлення об'єкту з рядка.

### Вправа 3

Розробити програму для роботи з динамічними проксі-об'єктами, реалізувавши інтерфейс InvocationHandler, і використовуючи їх замість реальних об'єктів для розв'язку конкретної задачі.

### Вправа 4

Напишіть програму, яка дозволяє створювати одновимірні масиви та матриці відомих типів під час виконання програми, змінювати їх розміри зі збереженням вмісту, а також перетворювати масиви та матриці в рядки.

### Вправа 5

Розробити програму, яка демонструє використання динамічних проксі-об'єктів для профілювання та трасування методу, виводячи на екран час виконання методу, його ім'я, параметри та обчислене значення.

## Деякі результати

![image](https://github.com/MaksymAndreiev/CrossPlatformProgramming/assets/29687267/223c5b12-982c-4742-9898-0792a2198d38)
