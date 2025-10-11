# Java for Everyone

# 🧠 Object-Oriented Programming (OOP) in Java

Object-Oriented Programming (OOP) is a programming paradigm based on the concept of **objects**, which can contain data and code that manipulates that data.  
Java is a **pure object-oriented language** (except for primitive types), and understanding OOP is crucial for writing scalable, maintainable, and reusable code.

---

## 🚀 Table of Contents
1. [Introduction](#introduction)
2. [Four Pillars of OOP](#four-pillars-of-oop)
    - [Encapsulation](#1-encapsulation)
    - [Inheritance](#2-inheritance)
    - [Polymorphism](#3-polymorphism)
    - [Abstraction](#4-abstraction)
3. [Class vs Object](#class-vs-object)
4. [Constructor](#constructor)
5. [Access Modifiers](#access-modifiers)
6. [Interfaces and Abstract Classes](#interfaces-and-abstract-classes)
7. [OOP Best Practices](#oop-best-practices)
8. [UML Diagram Example](#uml-diagram-example)
9. [Real-World Example](#real-world-example)
10. [References](#references)

---

## 🧩 Introduction

In **Object-Oriented Programming**, everything revolves around **objects** — real-world entities that have:
- **State (fields or attributes)**
- **Behavior (methods or functions)**

For example:
```java
class Car {
    String color;
    String model;

    void drive() {
        System.out.println("Car is driving...");
    }
}
```

Here,
- `Car` is a **class** (a blueprint),
- and any instance created from it is an **object**.

---

## ⚙️ Four Pillars of OOP

### 1. 🛡 Encapsulation
Encapsulation is the concept of **binding data and methods together** into a single unit (class) and **restricting direct access** to some components.

#### Example:
```java
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0)
            balance += amount;
    }
}
```

✅ **Key Points:**
- Keeps data **safe from unauthorized access**.
- Accessed only through **getters and setters**.
- Promotes **data hiding** and **security**.

---

### 2. 🧬 Inheritance
Inheritance allows one class to **acquire the properties and behaviors** of another class.

#### Example:
```java
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // inherited method
        dog.bark();
    }
}
```

✅ **Key Points:**
- Promotes **code reuse**.
- Supports **hierarchical classification**.
- The `extends` keyword is used for inheritance.

🧱 **Types of Inheritance in Java**
- Single Inheritance
- Multilevel Inheritance
- Hierarchical Inheritance  
  *(Java does not support multiple inheritance with classes, but can achieve it with interfaces.)*

---

### 3. 🔄 Polymorphism
Polymorphism means **"many forms"** — the ability of an object to behave differently based on context.

#### 3.1 Compile-time Polymorphism (Method Overloading)
```java
class MathUtils {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```

#### 3.2 Runtime Polymorphism (Method Overriding)
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Upcasting
        a.sound(); // Bark (runtime decision)
    }
}
```

✅ **Key Points:**
- Overloading → Same method name, different parameters.
- Overriding → Same method signature, different behavior.
- Enables **dynamic method dispatch**.

---

### 4. 🧰 Abstraction
Abstraction focuses on **hiding implementation details** and showing only the **essential features** of an object.

#### Example using Abstract Class:
```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}
```

#### Example using Interface:
```java
interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Paid via Credit Card");
    }
}
```

✅ **Key Points:**
- Abstract class → partial abstraction.
- Interface → complete abstraction.
- Reduces **complexity** and improves **flexibility**.

---

## 🏗 Class vs Object

| Concept | Description | Example |
|----------|--------------|---------|
| **Class** | Blueprint or template for creating objects | `class Car {}` |
| **Object** | Instance of a class | `Car car1 = new Car();` |
| **Instance Variables** | Define the state of an object | `car1.color = "Red";` |
| **Methods** | Define the behavior of an object | `car1.drive();` |

---

## 🧱 Constructor

A **constructor** is a special method used to **initialize objects**.

#### Example:
```java
class Student {
    String name;
    int age;

    // Constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

✅ **Key Points:**
- Name is same as class name.
- Has **no return type**.
- Java provides a **default constructor** if none is defined.

---

## 🔒 Access Modifiers

| Modifier | Scope | Accessible In Subclass | Accessible Outside Package |
|-----------|--------|------------------------|-----------------------------|
| **public** | Everywhere | ✅ | ✅ |
| **protected** | Within package + subclasses | ✅ | ❌ |
| **default** | Within same package | ✅ | ❌ |
| **private** | Within same class only | ❌ | ❌ |

---

## 🧩 Interfaces and Abstract Classes

| Feature | Interface | Abstract Class |
|----------|------------|----------------|
| Keyword | `interface` | `abstract class` |
| Inheritance | Multiple | Single |
| Methods | Abstract (default) | Can be abstract or concrete |
| Variables | `public static final` | Instance or static |
| Use Case | Define a contract | Provide partial implementation |

---

## 🧠 OOP Best Practices

1. Use **encapsulation** to protect internal states.
2. Apply **inheritance** carefully (prefer composition over inheritance when possible).
3. Use **interfaces** to define contracts.
4. Follow **SOLID Principles**:
    - **S**: Single Responsibility Principle
    - **O**: Open/Closed Principle
    - **L**: Liskov Substitution Principle
    - **I**: Interface Segregation Principle
    - **D**: Dependency Inversion Principle
5. Promote **loose coupling** and **high cohesion**.
6. Always use **meaningful class and method names**.

---

## 🧮 UML Diagram Example

Below is a simple UML representation of **Inheritance and Polymorphism**:

```
        +-----------+
        |   Animal  |
        +-----------+
        | - name    |
        +-----------+
        | + eat()   |
        +-----------+
             ▲
             |
     -----------------
     |               |
+----------+    +----------+
|   Dog    |    |   Cat    |
+----------+    +----------+
| + bark() |    | + meow() |
+----------+    +----------+
```

---

## 🌍 Real-World Example: Vehicle System

```java
abstract class Vehicle {
    abstract void start();
    abstract void stop();
}

class Car extends Vehicle {
    void start() { System.out.println("Car started"); }
    void stop() { System.out.println("Car stopped"); }
}

class Bike extends Vehicle {
    void start() { System.out.println("Bike started"); }
    void stop() { System.out.println("Bike stopped"); }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Car();
        Vehicle v2 = new Bike();
        v1.start();
        v2.start();
    }
}
```

🧩 **Explanation:**
- `Vehicle` defines a general contract.
- `Car` and `Bike` provide concrete implementations.
- Demonstrates **Abstraction**, **Inheritance**, and **Polymorphism**.

---

## 📚 References

- [Official Java Documentation](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [Java OOP Guide - GeeksforGeeks](https://www.geeksforgeeks.org/object-oriented-programming-oops-concept-in-java/)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/)

---

> ✨ *"Object-Oriented Programming is not just about objects — it's about thinking in terms of responsibilities, relationships, and reusability."*
