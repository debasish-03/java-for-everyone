# JVM Internals — Deep Dive for Senior Java Developers

This README provides a **comprehensive deep dive into JVM internals** — covering **Class Loading, JIT Compilation, Garbage Collection, Memory Model, and Performance Tuning** with advanced JVM configuration flags and examples.

---

## 1. JVM Architecture Overview

```
      +---------------------------------------------------+
      |                 Java Virtual Machine              |
      +---------------------------------------------------+
      | Class Loader Subsystem | Memory Areas | Execution |
      +---------------------------------------------------+
             |                        |               |
      [Loads .class files]    [Heap, Stack, Metaspace]  [JIT + GC + Threads]
```

---

## 2️. Class Loading Mechanism

The **ClassLoader** subsystem loads `.class` files into memory during runtime.

###  Phases

1. **Loading:** Reads `.class` bytecode.
2. **Linking:** Verifies and prepares classes.
3. **Initialization:** Executes static initializers.

###  Types of ClassLoaders

| Loader | Description |
|---------|--------------|
| **Bootstrap ClassLoader** | Loads Java core classes |
| **Extension ClassLoader** | Loads JARs from `lib/ext` |
| **Application ClassLoader** | Loads classes from classpath |
| **Custom ClassLoader** | Used in frameworks (Tomcat, Spring Boot) |

### Example: Custom ClassLoader

```java
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Loading: " + name);
        return super.findClass(name);
    }
}
```

---

## 3. JIT Compilation (Just-In-Time)

JIT converts **frequently executed bytecode** into **native code** for performance.

### Compilation Flow

```
Source → Bytecode → Interpreter → JIT → Native Machine Code
```

###  Example

```java
public class JITExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) compute();
    }
    static int compute() { return 42 * 42; }
}
```

**Command:**
```bash
java -XX:+PrintCompilation JITExample
```

---

## 4. JVM Memory Model (Heap, Stack, Metaspace)

### Memory Areas

| Area | Description |
|------|--------------|
| **Heap** | Objects & instances |
| **Stack** | Local variables per thread |
| **Metaspace** | Class metadata |
| **PC Register** | Current executing instruction |
| **Native Stack** | JNI calls |

### Memory Example

```java
public class MemoryExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            String obj = new String("Data" + i);
        }
    }
}
```

Run with:
```bash
java -Xms128m -Xmx128m -XX:+PrintGCDetails MemoryExample
```

---

## 5. Garbage Collection (GC)

### GC Process

1. Mark live objects  
2. Sweep unreachable ones  
3. Compact memory

### GC Collectors

| GC Type | Description | Ideal Use |
|----------|--------------|-----------|
| **Serial GC** | Single-threaded | Small apps |
| **Parallel GC** | Multi-threaded | Batch jobs |
| **G1 GC** | Region-based, low pause | Default Java 11+ |
| **ZGC** | Ultra-low pause | Large heaps |
| **Shenandoah** | Concurrent GC | Real-time systems |

### Commands

```bash
# Use G1
-XX:+UseG1GC

# Use ZGC
-XX:+UseZGC

# GC logging
-Xlog:gc*,gc+heap=debug:file=gc.log:time,uptime,level,tags
```

---

## 6. JVM Performance Tuning Flags

### 🔹 Memory Management

| Flag | Description |
|------|--------------|
| `-Xms<size>` | Minimum heap size |
| `-Xmx<size>` | Maximum heap size |
| `-XX:NewSize=<size>` | Young gen size |
| `-XX:MaxNewSize=<size>` | Max young gen size |
| `-XX:SurvivorRatio=<ratio>` | Ratio between Eden/Survivor spaces |
| `-XX:MetaspaceSize=<size>` | Initial metaspace size |
| `-XX:MaxMetaspaceSize=<size>` | Max metaspace size |

###  GC & Heap

| Flag | Description |
|------|--------------|
| `-XX:+UseG1GC` | Enable G1 GC |
| `-XX:+UseZGC` | Enable ZGC |
| `-XX:MaxGCPauseMillis=<ms>` | Target max pause time |
| `-XX:+HeapDumpOnOutOfMemoryError` | Generate heap dump on OOM |
| `-XX:HeapDumpPath=/logs/heapdump.hprof` | Heap dump path |

### JIT & Compilation

| Flag | Description |
|------|--------------|
| `-XX:+PrintCompilation` | Show JIT compiled methods |
| `-XX:+TieredCompilation` | Use both C1 and C2 JIT |
| `-XX:+UnlockDiagnosticVMOptions` | Unlock diagnostic options |
| `-XX:+PrintInlining` | Print method inlining info |

### Logging & Diagnostics

| Flag | Description |
|------|--------------|
| `-Xlog:gc*` | GC logging |
| `-Xlog:heap+stats` | Heap statistics |
| `-XX:+PrintFlagsFinal` | Print all JVM flags |
| `-XX:+PrintCommandLineFlags` | Show flags in use |
| `-XX:+UnlockExperimentalVMOptions` | Unlock experimental flags |

### Sample Production JVM Command

```bash
java -Xms1G -Xmx2G      -XX:+UseG1GC      -XX:MaxGCPauseMillis=200      -XX:+HeapDumpOnOutOfMemoryError      -XX:HeapDumpPath=/var/logs/heapdump.hprof      -XX:+PrintGCDetails      -Xlog:gc*:file=/var/logs/gc.log:time,uptime,level,tags      -XX:+PrintCommandLineFlags      -XX:+PrintCompilation      -jar myapp.jar
```

---

## 7. Performance Debugging Workflow

1. Enable GC logging
2. Capture heap dump: `jmap -dump:live,format=b,file=heap.hprof <pid>`
3. Analyze with VisualVM/JProfiler
4. Tune heap size & GC algorithm
5. Reduce object churn

---

## 8. JVM Analysis Tools

| Tool | Use |
|------|-----|
| **VisualVM** | Monitor heap, threads, GC |
| **JConsole** | Live monitoring |
| **JProfiler / YourKit** | Advanced profiling |
| **jmap / jstack** | Dump heap and thread info |
| **gcviewer** | Visualize GC logs |
| **Java Flight Recorder (JFR)** | Low-overhead profiling |

---

## Key Takeaways

- Understand your **heap, stack, and metaspace**.
- Use **G1 or ZGC** for low-latency services.
- **Profile, measure, and tune** JVM flags.
- **Monitor in production** using JFR, VisualVM, or GC logs.

---

> "A senior Java developer doesn’t just write code — they understand how their code executes inside the JVM."

---

**Author:** Senior Java Developer Reference Guide  
**Topics Covered:** JVM Internals, GC, JIT, Memory Model, Performance Tuning  
**Java Versions:** 8 → 21
