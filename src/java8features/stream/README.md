# 🚀 Java 8 Streams Guide

Java 8 Streams provide a **functional approach** to processing sequences of elements (collections, arrays, etc.) in a concise, readable, and efficient way. Streams allow **pipeline operations** consisting of **intermediate** and **terminal operations**, and support **lazy evaluation**.

---

## 📌 Table of Contents

1. [Introduction](#introduction)
2. [Intermediate Operations](#intermediate-operations)
3. [Terminal Operations](#terminal-operations)
4. [Short-Circuit Operations](#short-circuit-operations)
5. [Additional Stream Functions](#additional-stream-functions)
6. [Examples](#examples)
7. [References](#references)

---

## 🧩 Introduction

A **Stream** is a sequence of elements supporting **functional-style operations** to perform computations. Streams **do not store data**; they operate on data from a **source**, such as a `Collection` or an array. Streams support **parallel processing** to enhance performance.

Key points:

- Streams can be **sequential** or **parallel**.
- They **do not modify the underlying data source**.
- Intermediate operations are **lazy**; terminal operations are **eager**.

---

## ⚙️ Intermediate Operations

Intermediate operations return **another Stream** and allow chaining. They are **lazy**, meaning they are only executed when a terminal operation is called.

| Operation | Description |
|-----------|-------------|
| `map(Function<T,R>)` | Transforms each element into another form. |
| `filter(Predicate<T>)` | Filters elements based on a condition. |
| `flatMap(Function<T, Stream<R>>)` | Flattens nested structures into a single Stream. |
| `distinct()` | Removes duplicate elements. |
| `sorted()` | Sorts elements (natural or custom comparator). |
| `limit(long n)` | Truncates the Stream to `n` elements. |
| `skip(long n)` | Skips first `n` elements. |
| `peek(Consumer<T>)` | Performs an action on elements without consuming them. |

### Additional Intermediate Functions

- `mapToInt()`, `mapToDouble()`, `mapToLong()` → Converts elements to primitive streams.
- `boxed()` → Converts primitive streams back to object streams.
- `sorted(Comparator)` → Sort using a custom comparator.

---

## ⚡ Terminal Operations

Terminal operations **consume the Stream** and produce a **result**. Once invoked, the Stream **cannot be reused**.

| Operation | Description |
|-----------|-------------|
| `forEach(Consumer<T>)` | Performs an action for each element. |
| `collect(Collector<T,A,R>)` | Reduces elements into a collection or other data structure. |
| `reduce(BinaryOperator<T>)` | Reduces elements to a single value using an accumulation function. |
| `count()` | Returns the number of elements. |
| `anyMatch(Predicate<T>)` | Returns true if any element matches a predicate. |
| `allMatch(Predicate<T>)` | Returns true if all elements match a predicate. |
| `noneMatch(Predicate<T>)` | Returns true if no elements match a predicate. |
| `findFirst()` | Returns the first element as an `Optional`. |
| `findAny()` | Returns any element as an `Optional`. |

### Additional Terminal Functions

- `toArray()` → Converts the Stream to an array.
- `min(Comparator)` → Returns the minimum element.
- `max(Comparator)` → Returns the maximum element.
- `sum()` → Works on primitive streams to sum values.
- `average()` → Computes average of primitive stream elements.

---

## ⏱ Short-Circuit Operations

Short-circuit operations **may stop processing early** when a result is already determined.

| Operation | Description |
|-----------|-------------|
| `anyMatch(Predicate)` | Stops and returns `true` if any element matches. |
| `allMatch(Predicate)` | Stops and returns `false` if any element does not match. |
| `noneMatch(Predicate)` | Stops and returns `true` if no element matches. |
| `findFirst()` | Returns the first element and stops further processing. |
| `findAny()` | Returns any element and stops further processing. |
| `limit(long n)` | Stops after `n` elements are processed. |

---

## 🔹 Additional Stream Functions

| Operation | Description |
|-----------|-------------|
| `concat(Stream, Stream)` | Concatenates two streams into one. |
| `iterate(seed, UnaryOperator)` | Creates an infinite stream based on a seed and function. |
| `generate(Supplier)` | Generates an infinite stream of elements. |
| `takeWhile(Predicate)` | Takes elements until predicate fails (Java 9+). |
| `dropWhile(Predicate)` | Skips elements until predicate fails (Java 9+). |
| `peek()` | Useful for debugging intermediate steps. |

---

## 📝 Examples

```java
import java.util.*;
import java.util.stream.*;

public class StreamExamples {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,2,3);

        // Intermediate operations: filter + map
        List<Integer> squaredEvens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Squared evens: " + squaredEvens);

        // Terminal operations: count
        long count = numbers.stream().filter(n -> n > 3).count();
        System.out.println("Numbers > 3: " + count);

        // Short-circuit: findFirst
        Optional<Integer> firstEven = numbers.stream().filter(n -> n % 2 == 0).findFirst();
        firstEven.ifPresent(n -> System.out.println("First even number: " + n));

        // Using reduce
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum of numbers: " + sum);

        // Stream generate
        Stream<Integer> randomNumbers = Stream.generate(() -> new Random().nextInt(100)).limit(5);
        randomNumbers.forEach(System.out::println);
    }
}
