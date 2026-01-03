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

## Collection (Interface gốc)

```java
Collection<String> c = new ArrayList<>();

c.add("A");                // Thêm phần tử
c.add("B");

c.remove("A");             // Xóa phần tử
c.contains("B");           // Kiểm tra tồn tại
c.size();                  // Số phần tử
c.isEmpty();               // Kiểm tra rỗng

c.clear();                 // Xóa toàn bộ
```

## List – Danh sách (có thứ tự, cho phép trùng)

### List (Interface)

```java
List<String> list = new ArrayList<>();

list.add("A");             // Thêm cuối
list.add(1, "B");          // Thêm tại index

list.get(0);               // Lấy phần tử
list.set(0, "Z");          // Cập nhật phần tử
list.remove(1);            // Xóa theo index

list.indexOf("Z");         // Vị trí đầu tiên
list.lastIndexOf("Z");     // Vị trí cuối
```

### ArrayList

```java
ArrayList<String> list = new ArrayList<>();

list.add("A");
list.add("B");

list.ensureCapacity(10);   // Đảm bảo capacity (tối ưu)
list.trimToSize();         // Giảm capacity dư thừa

list.clone();              // Clone shallow copy
```

### LinkedList

```java
LinkedList<String> list = new LinkedList<>();

list.addFirst("A");        // Thêm đầu
list.addLast("B");         // Thêm cuối

list.getFirst();           // Lấy đầu
list.getLast();            // Lấy cuối

list.removeFirst();        // Xóa đầu
list.removeLast();         // Xóa cuối
```

### Vector

```java
Vector<Integer> v = new Vector<>();

v.add(1);
v.addElement(2);           // Legacy method

v.firstElement();          // Phần tử đầu
v.lastElement();           // Phần tử cuối

v.removeElement(1);        // Xóa phần tử
```

### Stack

```java
Stack<Integer> stack = new Stack<>();

stack.push(1);             // Push vào stack
stack.push(2);

stack.peek();              // Xem đỉnh
stack.pop();               // Lấy + xóa đỉnh
stack.empty();             // Kiểm tra rỗng
```

## Set – Tập hợp (có thứ tự, không cho trùng)

### Set (Interface)

```java
Set<String> set = new HashSet<>();

set.add("A");
set.add("A");              // Bị bỏ qua (không trùng)

set.contains("A");
set.remove("A");
```

### HashSet

```java
HashSet<String> set = new HashSet<>();

set.add("A");
set.add("B");

set.iterator();            // Duyệt phần tử
```

### LinkedHashSet

```java
LinkedHashSet<String> set = new LinkedHashSet<>();

set.add("A");
set.add("B");              // Giữ thứ tự insert
```

### TreeSet

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(3);
set.add(1);

set.first();               // Nhỏ nhất
set.last();                // Lớn nhất

set.higher(1);             // Lớn hơn 1
set.lower(3);              // Nhỏ hơn 3

set.subSet(1, 3);          // Tập con
```

## Queue / Deque – Hàng đợi

### Queue (Interface)

```java
Queue<Integer> q = new LinkedList<>();

q.offer(1);                // Thêm (không exception)
q.peek();                  // Xem đầu
q.poll();                  // Lấy + xóa đầu
```

### Deque (Interface)

```java
Deque<Integer> d = new ArrayDeque<>();

d.addFirst(1);              // Thêm đầu
d.addLast(2);               // Thêm cuối

d.peekFirst();              // Xem đầu
d.peekLast();               // Xem cuối

d.pollFirst();              // Lấy + xóa đầu
d.pollLast();               // Lấy + xóa cuối
```

### ArrayDeque

```java
ArrayDeque<Integer> d = new ArrayDeque<>();

d.addFirst(1);              // Thêm đầu
d.addLast(2);               // Thêm cuối

d.peekFirst();              // Xem đầu
d.peekLast();               // Xem cuối

d.pollFirst();              // Lấy + xóa đầu
d.pollLast();               // Lấy + xóa cuối
```

### PriorityQueue

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(5);
pq.offer(1);
pq.offer(3);

pq.peek();                  // Phần tử ưu tiên nhất
pq.poll();                  // Lấy + xóa phần tử ưu tiên
```

## Map – Key / Value

### Map (Interface)

```java
Map<String, Integer> map = new HashMap<>();

map.put("A", 1);            // Thêm key-value
map.get("A");               // Lấy value

map.containsKey("A");
map.containsValue(1);

map.remove("A");

map.keySet();               // Tập key
map.values();               // Tập value
map.entrySet();             // Tập entry
```

### HashMap

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("A", 1);
map.putIfAbsent("B", 2);    // Thêm nếu chưa tồn tại

map.computeIfPresent("A", (k,v) -> v + 1);
map.replace("A", 10);
```

### LinkedHashMap

```java
LinkedHashMap<String, Integer> map =
        new LinkedHashMap<>();

map.put("A", 1);
map.put("B", 2);            // Giữ thứ tự insert
```

### TreeMap

```java
TreeMap<String, Integer> map = new TreeMap<>();

map.put("B", 2);
map.put("A", 1);

map.firstKey();             // Key nhỏ nhất
map.lastKey();              // Key lớn nhất

map.headMap("B");           // < B
map.tailMap("B");           // >= B
```

### Hashtable

```java
Hashtable<String, Integer> table =
        new Hashtable<>();

table.put("A", 1);
table.get("A");             // Thread-safe (legacy)
```

## Các Utility Class khác

### Collections

```java
List<Integer> list = Arrays.asList(3,1,2);

Collections.sort(list);           // Sắp xếp
Collections.reverse(list);        // Đảo ngược
Collections.shuffle(list);        // Trộn
Collections.min(list);            // Nhỏ nhất
Collections.max(list);            // Lớn nhất
```

### Arrays

```java
int[] arr = {3,1,2};

Arrays.sort(arr);                 // Sắp xếp
Arrays.binarySearch(arr, 2);      // Tìm nhị phân
Arrays.toString(arr);             // In mảng
Arrays.asList(1,2,3);             // Chuyển sang List
```

### Immutable Collections (Java 9+)

```java
List<String> list = List.of("A", "B");
Set<String> set = Set.of("A", "B");
Map<String,Integer> map = Map.of("A",1);

// list.add("C"); ❌ UnsupportedOperationException
```
