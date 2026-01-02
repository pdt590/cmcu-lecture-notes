# 🧠 Sơ đồ tổng quan Java Collections Framework

```java
Collection
 ├── List
 │    ├── ArrayList
 │    ├── LinkedList
 │    ├── Vector
 │    └── Stack
 │
 ├── Set
 │    ├── HashSet
 │    ├── LinkedHashSet
 │    └── TreeSet
 │
 └── Queue
      ├── PriorityQueue
      └── Deque
           └── ArrayDeque

Map
 ├── HashMap
 ├── LinkedHashMap
 ├── TreeMap
 └── Hashtable
```

# 🧵 Ví dụ: Collections

## 1️⃣ List – Danh sách (có thứ tự, cho phép trùng)

### 🔹 ArrayList

```java
ArrayList<String> arrayList = new ArrayList<>();
arrayList.add("A");
arrayList.add("B");
```

### 🔹 LinkedList

```java
LinkedList<String> linkedList = new LinkedList<>();
linkedList.add("A");
linkedList.add("B");
```

### 🔹 Vector (legacy, thread-safe)

```java
Vector<String> vector = new Vector<>();
vector.add("A");
```

### 🔹 Stack (legacy, LIFO)

```java
Stack<String> stack = new Stack<>();
stack.push("A");
stack.push("B");
```

## 2️⃣ Set – Tập hợp (không trùng)

### 🔹 HashSet (không thứ tự)

```java
HashSet<Integer> hashSet = new HashSet<>();
hashSet.add(1);
hashSet.add(2);
```

### 🔹 LinkedHashSet (giữ thứ tự chèn)

```java
LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
linkedHashSet.add(1);
linkedHashSet.add(2);
```

### 🔹 TreeSet (sắp xếp tăng dần)

```java
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
```

## 3️⃣ Queue / Deque – Hàng đợi

### 🔹 PriorityQueue (ưu tiên)

```java
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
priorityQueue.add(3);
priorityQueue.add(1);
```

### 🔹 ArrayDeque (Deque – 2 đầu)

```java
ArrayDeque<String> deque = new ArrayDeque<>();
deque.addFirst("A");
deque.addLast("B");
```

## 4️⃣ Map – Key / Value (KHÔNG phải Collection)

### 🔹 HashMap

```java
HashMap<String, Integer> hashMap = new HashMap<>();
hashMap.put("A", 1);
```

### 🔹 LinkedHashMap (giữ thứ tự chèn)

```java
LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
linkedHashMap.put("A", 1);
```

### 🔹 TreeMap (sắp xếp theo key)

```java
TreeMap<String, Integer> treeMap = new TreeMap<>();
treeMap.put("B", 2);
treeMap.put("A", 1);
```

### 🔹 Hashtable (legacy, thread-safe)

```java
Hashtable<String, Integer> hashtable = new Hashtable<>();
hashtable.put("A", 1);
```

### 5️⃣ Utility Class – Collections

```java
Collections.sort(arrayList);
Collections.reverse(arrayList);
```

### 6️⃣ Iterator – Duyệt Collection

```java
Iterator<String> it = arrayList.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```
