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
import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        // 1. Tạo ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        ArrayList<String> moreFruits = new ArrayList<>();

        // ========== CÁC PHƯƠNG THỨC CƠ BẢN ==========

        // Thêm phần tử
        fruits.add("Apple");        // thêm vào cuối danh sách
        fruits.add("Banana");
        fruits.add(1, "Orange");    // thêm tại vị trí chỉ định
        fruits.addAll(Arrays.asList("Mango", "Grapes")); // thêm collection
        fruits.addAll(2, Arrays.asList("Pineapple", "Kiwi")); // thêm tại vị trí

        System.out.println("Sau khi thêm: " + fruits);

        // Truy cập phần tử
        String first = fruits.get(0);        // lấy phần tử tại index
        System.out.println("Phần tử đầu tiên: " + first);

        // Cập nhật phần tử
        fruits.set(0, "Green Apple");        // thay thế phần tử tại index
        System.out.println("Sau khi cập nhật: " + fruits);

        // Xóa phần tử
        fruits.remove(0);                    // xóa tại index
        fruits.remove("Banana");             // xóa object đầu tiên tìm thấy
        fruits.removeAll(Arrays.asList("Kiwi")); // xóa tất cả phần tử khớp
        fruits.retainAll(Arrays.asList("Orange", "Mango", "Grapes")); // giữ lại các phần tử chỉ định

        System.out.println("Sau khi xóa: " + fruits);

        // Kiểm tra
        boolean hasMango = fruits.contains("Mango");  // kiểm tra tồn tại
        boolean isEmpty = fruits.isEmpty();           // kiểm tra rỗng
        int size = fruits.size();                     // lấy kích thước

        System.out.println("Có Mango không? " + hasMango);
        System.out.println("Có rỗng không? " + isEmpty);
        System.out.println("Kích thước: " + size);

        // ========== CÁC PHƯƠNG THỨC DÙNG INDEX ==========

        fruits.add("Orange");
        fruits.add("Mango");

        int firstOrange = fruits.indexOf("Orange");   // index đầu tiên
        int lastOrange = fruits.lastIndexOf("Orange"); // index cuối cùng

        System.out.println("Index đầu tiên của Orange: " + firstOrange);
        System.out.println("Index cuối cùng của Orange: " + lastOrange);

        // ========== PHƯƠNG THỨC CONVERT ==========

        // Chuyển sang mảng
        Object[] array = fruits.toArray();            // Object[]
        String[] stringArray = fruits.toArray(new String[0]); // String[]

        System.out.println("Mảng: " + Arrays.toString(stringArray));

        // ========== PHƯƠNG THỨC SUBLIST ==========

        List<String> subList = fruits.subList(1, 3);  // tạo sublist
        System.out.println("Sublist: " + subList);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt bằng for-each:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        System.out.println("\nDuyệt bằng Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nDuyệt bằng ListIterator (có thể duyệt ngược):");
        ListIterator<String> listIterator = fruits.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // Duyệt ngược
        System.out.println("\nDuyệt ngược:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // ========== PHƯƠNG THỨC XÓA TẤT CẢ ==========

        fruits.clear();  // xóa tất cả phần tử
        System.out.println("\nSau khi clear: " + fruits);
        System.out.println("Size: " + fruits.size());
    }
}
```

### LinkedList

```java
import java.util.*;

public class LinkedListExample {
    public static void main(String[] args) {
        // Tạo LinkedList
        LinkedList<String> students = new LinkedList<>();

        // ========== CÁC PHƯƠNG THỨC ĐẶC BIỆT CỦA LINKEDLIST ==========

        // Thêm phần tử ở đầu và cuối
        students.addFirst("Alice");          // thêm vào đầu
        students.addLast("Bob");             // thêm vào cuối
        students.offerFirst("Charlie");      // thêm vào đầu (queue operation)
        students.offerLast("David");         // thêm vào cuối (queue operation)
        students.push("Eve");                // thêm vào đầu (stack operation)

        System.out.println("Sau khi thêm: " + students);

        // Truy cập phần tử đầu và cuối
        String first = students.getFirst();      // lấy phần tử đầu
        String last = students.getLast();        // lấy phần tử cuối
        String peekFirst = students.peekFirst(); // xem phần tử đầu (không xóa)
        String peekLast = students.peekLast();   // xem phần tử cuối (không xóa)

        System.out.println("First: " + first);
        System.out.println("Last: " + last);
        System.out.println("Peek First: " + peekFirst);
        System.out.println("Peek Last: " + peekLast);

        // Xóa phần tử đầu và cuối
        String removedFirst = students.removeFirst();    // xóa và trả về phần tử đầu
        String removedLast = students.removeLast();      // xóa và trả về phần tử cuối
        String pollFirst = students.pollFirst();         // xóa và trả về phần tử đầu (null nếu rỗng)
        String pollLast = students.pollLast();           // xóa và trả về phần tử cuối (null nếu rỗng)
        String pop = students.pop();                     // xóa và trả về phần tử đầu (stack operation)

        System.out.println("Đã xóa First: " + removedFirst);
        System.out.println("Đã xóa Last: " + removedLast);
        System.out.println("Sau khi xóa: " + students);

        // ========== PHƯƠNG THỨC CỦA LIST INTERFACE ==========

        students.add("Frank");
        students.add(0, "Grace");        // thêm tại index
        students.set(1, "Henry");        // cập nhật tại index

        System.out.println("Sau khi thêm và cập nhật: " + students);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt bằng descendingIterator (từ cuối lên đầu):");
        Iterator<String> descIterator = students.descendingIterator();
        while (descIterator.hasNext()) {
            System.out.println(descIterator.next());
        }

        // ========== KIỂM TRA PHẦN TỬ ==========

        boolean containsHenry = students.contains("Henry");
        int index = students.indexOf("Henry");
        int lastIndex = students.lastIndexOf("Henry");

        System.out.println("\nCó Henry không? " + containsHenry);
        System.out.println("Index của Henry: " + index);

        // ========== CHUYỂN ĐỔI SANG MẢNG ==========

        Object[] array = students.toArray();
        System.out.println("Mảng: " + Arrays.toString(array));
    }
}
```

### Vector

```java
import java.util.*;

public class VectorExample {
    public static void main(String[] args) {
        // Tạo Vector với capacity ban đầu
        Vector<Integer> numbers = new Vector<>(5);  // capacity = 5
        Vector<Integer> numbers2 = new Vector<>(3, 2); // capacity=3, increment=2

        // ========== CÁC PHƯƠNG THỨC ĐẶC BIỆT CỦA VECTOR ==========

        // Thêm phần tử
        numbers.add(10);
        numbers.addElement(20);      // phương thức cổ điển của Vector
        numbers.add(1, 15);
        numbers.addAll(Arrays.asList(25, 30, 35));

        System.out.println("Vector: " + numbers);
        System.out.println("Capacity: " + numbers.capacity());  // lấy capacity hiện tại
        System.out.println("Size: " + numbers.size());         // lấy số phần tử

        // Truy cập phần tử
        int first = numbers.firstElement();    // phần tử đầu tiên
        int last = numbers.lastElement();      // phần tử cuối cùng
        int atIndex2 = numbers.elementAt(2);   // phần tử tại index

        System.out.println("First: " + first);
        System.out.println("Last: " + last);
        System.out.println("Element at 2: " + atIndex2);

        // Xóa phần tử
        numbers.removeElement(20);             // xóa phần tử đầu tiên bằng 20
        numbers.removeElementAt(0);            // xóa phần tử tại index 0

        System.out.println("Sau khi xóa: " + numbers);

        // ========== THAY ĐỔI CAPACITY ==========

        numbers.ensureCapacity(20);            // đảm bảo capacity ít nhất là 20
        System.out.println("Capacity sau ensureCapacity: " + numbers.capacity());

        numbers.trimToSize();                  // giảm capacity về bằng size
        System.out.println("Capacity sau trimToSize: " + numbers.capacity());

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt bằng Enumeration (cổ điển):");
        Enumeration<Integer> enumeration = numbers.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        // ========== SAO CHÉP VECTOR ==========

        Object[] copy = numbers.toArray();
        System.out.println("\nMảng sao chép: " + Arrays.toString(copy));

        // ========== XÓA TẤT CẢ ==========

        numbers.removeAllElements();          // xóa tất cả phần tử
        System.out.println("\nSau khi removeAllElements: " + numbers);
        System.out.println("Size: " + numbers.size());
        System.out.println("Is empty: " + numbers.isEmpty());
    }
}
```

### Stack

```java
import java.util.*;

public class StackExample {
    public static void main(String[] args) {
        // Tạo Stack
        Stack<String> stack = new Stack<>();

        // ========== CÁC PHƯƠNG THỨC STACK ==========

        // Thêm phần tử (push)
        stack.push("First");      // thêm vào đỉnh stack
        stack.push("Second");
        stack.push("Third");
        stack.add("Fourth");      // phương thức kế thừa từ Vector

        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());

        // Truy cập phần tử
        String top = stack.peek();    // xem phần tử đỉnh (không xóa)
        System.out.println("Phần tử đỉnh (peek): " + top);

        // Tìm kiếm phần tử
        int position = stack.search("Second");  // vị trí từ đỉnh (1-based)
        System.out.println("Vị trí của 'Second' từ đỉnh: " + position);

        // Xóa phần tử (pop)
        String popped = stack.pop();   // xóa và trả về phần tử đỉnh
        System.out.println("Đã pop: " + popped);
        System.out.println("Stack sau pop: " + stack);

        // Kiểm tra stack rỗng
        boolean isEmpty = stack.empty();
        System.out.println("Stack có rỗng không? " + isEmpty);

        // ========== PHƯƠNG THỨC KẾ THỪA TỪ VECTOR ==========

        // Truy cập bằng index
        String elementAt1 = stack.get(1);
        System.out.println("Element at index 1: " + elementAt1);

        // Thêm tại index
        stack.add(1, "Inserted");
        System.out.println("Sau khi insert tại index 1: " + stack);

        // Xóa tại index
        stack.remove(0);
        System.out.println("Sau khi remove index 0: " + stack);

        // ========== DUYỆT STACK ==========

        System.out.println("\nDuyệt từ đỉnh xuống đáy:");
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
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
import java.util.*;

public class HashSetExample {
    public static void main(String[] args) {
        // Tạo HashSet
        HashSet<String> set = new HashSet<>();

        // ========== THÊM PHẦN TỬ ==========

        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Apple");  // phần tử trùng sẽ không được thêm

        System.out.println("HashSet: " + set);

        // Thêm collection
        set.addAll(Arrays.asList("Mango", "Grapes", "Kiwi"));
        System.out.println("Sau khi addAll: " + set);

        // ========== KIỂM TRA ==========

        boolean hasApple = set.contains("Apple");
        boolean isEmpty = set.isEmpty();
        int size = set.size();

        System.out.println("Có 'Apple' không? " + hasApple);
        System.out.println("Có rỗng không? " + isEmpty);
        System.out.println("Size: " + size);

        // ========== XÓA PHẦN TỬ ==========

        set.remove("Banana");
        boolean removed = set.remove("Watermelon");  // không tồn tại
        System.out.println("Có xóa 'Watermelon' không? " + removed);

        set.removeAll(Arrays.asList("Kiwi", "Mango"));
        System.out.println("Sau khi removeAll: " + set);

        // Giữ lại chỉ những phần tử chỉ định
        set.retainAll(Arrays.asList("Apple", "Orange"));
        System.out.println("Sau khi retainAll: " + set);

        // ========== DUYỆT PHẦN TỬ ==========

        set.addAll(Arrays.asList("Banana", "Mango", "Grapes"));

        System.out.println("\nDuyệt bằng for-each:");
        for (String fruit : set) {
            System.out.println(fruit);
        }

        System.out.println("\nDuyệt bằng Iterator:");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // ========== CHUYỂN ĐỔI SANG MẢNG ==========

        Object[] array = set.toArray();
        String[] stringArray = set.toArray(new String[0]);

        System.out.println("\nMảng: " + Arrays.toString(stringArray));

        // ========== SO SÁNH HAI SET ==========

        HashSet<String> set2 = new HashSet<>(Arrays.asList("Apple", "Orange", "Pineapple"));

        // Kiểm tra set có chứa tất cả phần tử của set2 không
        boolean containsAll = set.containsAll(set2);
        System.out.println("set có chứa tất cả phần tử của set2 không? " + containsAll);

        // ========== XÓA TẤT CẢ ==========

        set.clear();
        System.out.println("\nSau khi clear: " + set);
        System.out.println("Size: " + set.size());
    }
}
```

### LinkedHashSet

```java
import java.util.*;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        // Tạo LinkedHashSet (giữ thứ tự thêm vào)
        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();

        // ========== THÊM PHẦN TỬ ==========

        linkedSet.add("Zebra");
        linkedSet.add("Apple");
        linkedSet.add("Monkey");
        linkedSet.add("Banana");
        linkedSet.add("Apple");  // trùng lặp, không được thêm

        System.out.println("LinkedHashSet: " + linkedSet);  // Giữ đúng thứ tự thêm vào

        // ========== CÁC PHƯƠNG THỨC TƯƠNG TỰ HASHSET ==========

        // Kiểm tra
        boolean hasMonkey = linkedSet.contains("Monkey");
        int size = linkedSet.size();

        System.out.println("Có 'Monkey' không? " + hasMonkey);
        System.out.println("Size: " + size);

        // Xóa phần tử
        linkedSet.remove("Zebra");
        System.out.println("Sau khi remove 'Zebra': " + linkedSet);

        // ========== DUYỆT PHẦN TỬ (THEO THỨ TỰ THÊM VÀO) ==========

        System.out.println("\nDuyệt theo thứ tự thêm vào:");
        Iterator<String> iterator = linkedSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // ========== CHUYỂN ĐỔI SANG MẢNG ==========

        String[] array = linkedSet.toArray(new String[0]);
        System.out.println("\nMảng: " + Arrays.toString(array));

        // ========== SO SÁNH VỚI HASHSET ==========

        HashSet<String> hashSet = new HashSet<>(Arrays.asList("Zebra", "Apple", "Monkey", "Banana"));
        System.out.println("\nHashSet (không giữ thứ tự): " + hashSet);
        System.out.println("LinkedHashSet (giữ thứ tự): " + linkedSet);

        // ========== XÓA TẤT CẢ ==========

        linkedSet.clear();
        System.out.println("\nSau khi clear: " + linkedSet);
    }
}
```

### TreeSet

```java
import java.util.*;

public class TreeSetExample {
    public static void main(String[] args) {
        // Tạo TreeSet (tự động sắp xếp)
        TreeSet<Integer> numbers = new TreeSet<>();

        // ========== THÊM PHẦN TỬ ==========

        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(30);
        numbers.add(50);  // trùng lặp, không được thêm

        System.out.println("TreeSet (tự động sắp xếp): " + numbers);

        // ========== CÁC PHƯƠNG THỨC ĐẶC BIỆT CỦA TREESET ==========

        // Truy cập phần tử đầu và cuối
        Integer first = numbers.first();      // phần tử nhỏ nhất
        Integer last = numbers.last();        // phần tử lớn nhất

        System.out.println("First (nhỏ nhất): " + first);
        System.out.println("Last (lớn nhất): " + last);

        // Truy cập phần tử nhỏ hơn/ lớn hơn một giá trị
        Integer lower = numbers.lower(25);    // phần tử lớn nhất nhỏ hơn 25
        Integer higher = numbers.higher(25);  // phần tử nhỏ nhất lớn hơn 25
        Integer floor = numbers.floor(25);    // phần tử lớn nhất nhỏ hơn hoặc bằng 25
        Integer ceiling = numbers.ceiling(25); // phần tử nhỏ nhất lớn hơn hoặc bằng 25

        System.out.println("Lower than 25: " + lower);
        System.out.println("Higher than 25: " + higher);
        System.out.println("Floor of 25: " + floor);
        System.out.println("Ceiling of 25: " + ceiling);

        // ========== XÓA PHẦN TỬ ==========

        Integer polledFirst = numbers.pollFirst();  // xóa và trả về phần tử đầu
        Integer polledLast = numbers.pollLast();    // xóa và trả về phần tử cuối

        System.out.println("Đã pollFirst: " + polledFirst);
        System.out.println("Đã pollLast: " + polledLast);
        System.out.println("Sau khi poll: " + numbers);

        // ========== CÁC PHƯƠNG THỨC CON SET ==========

        // HeadSet: tất cả phần tử nhỏ hơn giá trị chỉ định
        Set<Integer> headSet = numbers.headSet(40);
        System.out.println("HeadSet (< 40): " + headSet);

        // TailSet: tất cả phần tử lớn hơn hoặc bằng giá trị chỉ định
        Set<Integer> tailSet = numbers.tailSet(30);
        System.out.println("TailSet (>= 30): " + tailSet);

        // SubSet: phần tử trong khoảng [from, to)
        Set<Integer> subSet = numbers.subSet(20, 60);
        System.out.println("SubSet [20, 60): " + subSet);

        // SubSet với biên bao gồm/không bao gồm
        Set<Integer> subSetInclusive = numbers.subSet(20, true, 60, true);
        System.out.println("SubSet [20, 60] (inclusive): " + subSetInclusive);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt theo thứ tự tăng dần:");
        for (Integer num : numbers) {
            System.out.println(num);
        }

        System.out.println("\nDuyệt theo thứ tự giảm dần:");
        Iterator<Integer> descIterator = numbers.descendingIterator();
        while (descIterator.hasNext()) {
            System.out.println(descIterator.next());
        }

        System.out.println("\nDuyệt bằng descendingSet:");
        NavigableSet<Integer> descSet = numbers.descendingSet();
        for (Integer num : descSet) {
            System.out.println(num);
        }

        // ========== KIỂM TRA ==========

        boolean contains30 = numbers.contains(30);
        boolean isEmpty = numbers.isEmpty();
        int size = numbers.size();

        System.out.println("\nCó chứa 30? " + contains30);
        System.out.println("Có rỗng không? " + isEmpty);
        System.out.println("Size: " + size);

        // ========== XÓA TẤT CẢ ==========

        numbers.clear();
        System.out.println("\nSau khi clear: " + numbers);
    }
}
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
import java.util.*;

public class ArrayDequeExample {
    public static void main(String[] args) {
        // Tạo ArrayDeque
        ArrayDeque<String> deque = new ArrayDeque<>();

        // ========== THÊM PHẦN TỬ ==========

        // Thêm ở cuối
        deque.add("First");          // thêm vào cuối
        deque.addLast("Last");       // thêm vào cuối
        deque.offer("Offer");        // thêm vào cuối (queue operation)
        deque.offerLast("OfferLast");// thêm vào cuối

        // Thêm ở đầu
        deque.addFirst("FirstAdd");  // thêm vào đầu
        deque.offerFirst("OfferFirst"); // thêm vào đầu

        System.out.println("ArrayDeque: " + deque);

        // ========== TRUY CẬP PHẦN TỬ ==========

        String first = deque.getFirst();    // lấy phần tử đầu
        String last = deque.getLast();      // lấy phần tử cuối
        String peekFirst = deque.peekFirst(); // xem phần tử đầu
        String peekLast = deque.peekLast();   // xem phần tử cuối

        System.out.println("First: " + first);
        System.out.println("Last: " + last);
        System.out.println("Peek First: " + peekFirst);
        System.out.println("Peek Last: " + peekLast);

        // ========== XÓA PHẦN TỬ ==========

        String removedFirst = deque.removeFirst();  // xóa và trả về phần tử đầu
        String removedLast = deque.removeLast();    // xóa và trả về phần tử cuối
        String polledFirst = deque.pollFirst();     // xóa và trả về phần tử đầu (null nếu rỗng)
        String polledLast = deque.pollLast();       // xóa và trả về phần tử cuối (null nếu rỗng)

        System.out.println("Đã removeFirst: " + removedFirst);
        System.out.println("Đã removeLast: " + removedLast);
        System.out.println("Sau khi xóa: " + deque);

        // ========== KIỂM TRA ==========

        boolean containsOffer = deque.contains("Offer");
        int size = deque.size();
        boolean isEmpty = deque.isEmpty();

        System.out.println("Có chứa 'Offer'? " + containsOffer);
        System.out.println("Size: " + size);
        System.out.println("Có rỗng không? " + isEmpty);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt từ đầu đến cuối:");
        for (String element : deque) {
            System.out.println(element);
        }

        System.out.println("\nDuyệt bằng descendingIterator (từ cuối lên đầu):");
        Iterator<String> descIterator = deque.descendingIterator();
        while (descIterator.hasNext()) {
            System.out.println(descIterator.next());
        }

        // ========== XÓA TẤT CẢ ==========

        deque.clear();
        System.out.println("\nSau khi clear: " + deque);
    }
}
```

### PriorityQueue

```java
import java.util.*;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Tạo PriorityQueue (mặc định min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Tạo PriorityQueue với Comparator (max-heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // ========== THÊM PHẦN TỬ ==========

        pq.add(30);      // thêm phần tử
        pq.offer(10);    // thêm phần tử (queue operation)
        pq.add(20);
        pq.add(5);
        pq.add(15);

        System.out.println("PriorityQueue: " + pq);

        // ========== TRUY CẬP PHẦN TỬ ==========

        Integer head = pq.peek();    // xem phần tử đầu (phần tử nhỏ nhất)
        System.out.println("Phần tử đầu (peek): " + head);

        // ========== XÓA PHẦN TỬ ==========

        Integer removed = pq.poll();  // xóa và trả về phần tử đầu
        System.out.println("Đã poll: " + removed);
        System.out.println("Sau khi poll: " + pq);

        boolean removed5 = pq.remove(5);  // xóa phần tử cụ thể
        System.out.println("Đã remove 5? " + removed5);
        System.out.println("Sau khi remove 5: " + pq);

        // ========== KIỂM TRA ==========

        boolean contains20 = pq.contains(20);
        int size = pq.size();
        boolean isEmpty = pq.isEmpty();

        System.out.println("Có chứa 20? " + contains20);
        System.out.println("Size: " + size);
        System.out.println("Có rỗng không? " + isEmpty);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt bằng Iterator (không theo thứ tự ưu tiên):");
        Iterator<Integer> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nDuyệt theo thứ tự ưu tiên (dùng poll):");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        // ========== CHUYỂN ĐỔI SANG MẢNG ==========

        pq.add(100);
        pq.add(200);
        Object[] array = pq.toArray();
        System.out.println("\nMảng: " + Arrays.toString(array));

        // ========== XÓA TẤT CẢ ==========

        pq.clear();
        System.out.println("\nSau khi clear: " + pq);
        System.out.println("Size: " + pq.size());
    }
}
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
import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        // Tạo HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // ========== THÊM CẶP KEY-VALUE ==========

        map.put("Alice", 25);      // thêm key-value
        map.put("Bob", 30);
        map.put("Charlie", 35);
        map.put("Alice", 26);      // cập nhật giá trị cho key đã tồn tại

        System.out.println("HashMap: " + map);

        // Thêm tất cả từ map khác
        HashMap<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("David", 40);
        anotherMap.put("Eve", 28);
        map.putAll(anotherMap);

        System.out.println("Sau khi putAll: " + map);

        // Thêm nếu key chưa tồn tại
        map.putIfAbsent("Bob", 32);    // không thay đổi vì Bob đã tồn tại
        map.putIfAbsent("Frank", 45);  // thêm mới vì Frank chưa tồn tại

        System.out.println("Sau khi putIfAbsent: " + map);

        // ========== TRUY CẬP GIÁ TRỊ ==========

        Integer age = map.get("Alice");           // lấy giá trị theo key
        Integer ageOrDefault = map.getOrDefault("George", 0); // lấy giá trị hoặc giá trị mặc định

        System.out.println("Tuổi của Alice: " + age);
        System.out.println("Tuổi của George (mặc định 0): " + ageOrDefault);

        // ========== KIỂM TRA ==========

        boolean hasAlice = map.containsKey("Alice");      // kiểm tra key
        boolean hasAge25 = map.containsValue(25);         // kiểm tra value
        boolean isEmpty = map.isEmpty();
        int size = map.size();

        System.out.println("Có key 'Alice'? " + hasAlice);
        System.out.println("Có value 25? " + hasAge25);
        System.out.println("Có rỗng không? " + isEmpty);
        System.out.println("Size: " + size);

        // ========== XÓA PHẦN TỬ ==========

        map.remove("Bob");                    // xóa theo key
        map.remove("Alice", 25);              // chỉ xóa nếu key-value khớp
        System.out.println("Sau khi remove: " + map);

        // ========== DUYỆT MAP ==========

        System.out.println("\nDuyệt keys:");
        for (String key : map.keySet()) {
            System.out.println("Key: " + key);
        }

        System.out.println("\nDuyệt values:");
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }

        System.out.println("\nDuyệt entries (key-value pairs):");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nDuyệt bằng Iterator:");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ========== THAO TÁC TRÊN VALUE ==========

        // Tính toán giá trị mới nếu key tồn tại
        map.computeIfPresent("Charlie", (key, val) -> val + 5);
        System.out.println("\nSau khi computeIfPresent (Charlie + 5): " + map);

        // Tính toán giá trị mới nếu key không tồn tại
        map.computeIfAbsent("Grace", key -> 50);
        System.out.println("Sau khi computeIfAbsent (Grace): " + map);

        // Merge value
        map.merge("Charlie", 10, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("Sau khi merge (Charlie + 10): " + map);

        // ========== THAY THẾ GIÁ TRỊ ==========

        map.replace("David", 42);                    // thay thế value
        map.replace("Eve", 28, 29);                  // chỉ thay thế nếu oldValue khớp
        map.replaceAll((key, value) -> value + 1);   // thay thế tất cả values

        System.out.println("Sau khi replaceAll (+1 cho tất cả): " + map);

        // ========== XÓA TẤT CẢ ==========

        map.clear();
        System.out.println("\nSau khi clear: " + map);
        System.out.println("Size: " + map.size());
    }
}
```

### LinkedHashMap

```java
import java.util.*;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // Tạo LinkedHashMap (giữ thứ tự chèn)
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();

        // ========== THÊM CẶP KEY-VALUE ==========

        linkedMap.put("Zebra", 100);
        linkedMap.put("Apple", 200);
        linkedMap.put("Monkey", 150);
        linkedMap.put("Banana", 250);

        System.out.println("LinkedHashMap (giữ thứ tự chèn): " + linkedMap);

        // ========== CÁC PHƯƠNG THỨC TƯƠNG TỰ HASHMAP ==========

        // Truy cập
        Integer value = linkedMap.get("Apple");
        System.out.println("Giá trị của 'Apple': " + value);

        // Kiểm tra
        boolean hasKey = linkedMap.containsKey("Monkey");
        boolean hasValue = linkedMap.containsValue(150);

        System.out.println("Có key 'Monkey'? " + hasKey);
        System.out.println("Có value 150? " + hasValue);

        // Xóa
        linkedMap.remove("Zebra");
        System.out.println("Sau khi remove 'Zebra': " + linkedMap);

        // ========== DUYỆT THEO THỨ TỰ CHÈN ==========

        System.out.println("\nDuyệt theo thứ tự chèn:");
        for (Map.Entry<String, Integer> entry : linkedMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ========== LINKEDHASHMAP VỚI ACCESS ORDER ==========

        // Tạo LinkedHashMap với access-order (truy cập gần nhất sẽ ở cuối)
        LinkedHashMap<String, Integer> accessOrderMap = new LinkedHashMap<>(
            16, 0.75f, true  // truy cập gần nhất ở cuối
        );

        accessOrderMap.put("One", 1);
        accessOrderMap.put("Two", 2);
        accessOrderMap.put("Three", 3);

        System.out.println("\nAccess-order LinkedHashMap ban đầu: " + accessOrderMap);

        // Truy cập phần tử sẽ thay đổi thứ tự
        accessOrderMap.get("One");
        System.out.println("Sau khi get('One'): " + accessOrderMap);

        accessOrderMap.get("Two");
        System.out.println("Sau khi get('Two'): " + accessOrderMap);

        // ========== XÓA TẤT CẢ ==========

        linkedMap.clear();
        System.out.println("\nSau khi clear: " + linkedMap);
    }
}
```

### TreeMap

```java
import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        // Tạo TreeMap (tự động sắp xếp theo key)
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // ========== THÊM CẶP KEY-VALUE ==========

        treeMap.put("Orange", 300);
        treeMap.put("Apple", 200);
        treeMap.put("Banana", 150);
        treeMap.put("Mango", 400);
        treeMap.put("Apple", 250);  // cập nhật giá trị

        System.out.println("TreeMap (sắp xếp theo key): " + treeMap);

        // ========== CÁC PHƯƠNG THỨC ĐẶC BIỆT CỦA TREEMAP ==========

        // Truy cập phần tử đầu và cuối
        Map.Entry<String, Integer> firstEntry = treeMap.firstEntry();
        Map.Entry<String, Integer> lastEntry = treeMap.lastEntry();
        String firstKey = treeMap.firstKey();
        String lastKey = treeMap.lastKey();

        System.out.println("First Entry: " + firstEntry);
        System.out.println("Last Entry: " + lastEntry);
        System.out.println("First Key: " + firstKey);
        System.out.println("Last Key: " + lastKey);

        // Truy cập phần tử nhỏ hơn/lớn hơn
        Map.Entry<String, Integer> lowerEntry = treeMap.lowerEntry("C");  // key lớn nhất < "C"
        Map.Entry<String, Integer> higherEntry = treeMap.higherEntry("B"); // key nhỏ nhất > "B"
        String floorKey = treeMap.floorKey("Banana");    // key lớn nhất <= "Banana"
        String ceilingKey = treeMap.ceilingKey("C");     // key nhỏ nhất >= "C"

        System.out.println("Lower than 'C': " + lowerEntry);
        System.out.println("Higher than 'B': " + higherEntry);
        System.out.println("Floor key of 'Banana': " + floorKey);
        System.out.println("Ceiling key of 'C': " + ceilingKey);

        // ========== XÓA PHẦN TỬ ==========

        Map.Entry<String, Integer> polledFirst = treeMap.pollFirstEntry();  // xóa và trả về entry đầu
        Map.Entry<String, Integer> polledLast = treeMap.pollLastEntry();    // xóa và trả về entry cuối

        System.out.println("Đã pollFirstEntry: " + polledFirst);
        System.out.println("Đã pollLastEntry: " + polledLast);
        System.out.println("Sau khi poll: " + treeMap);

        // ========== CÁC PHƯƠNG THỨC CON MAP ==========

        // HeadMap: tất cả entry với key nhỏ hơn giá trị chỉ định
        SortedMap<String, Integer> headMap = treeMap.headMap("Mango");
        System.out.println("HeadMap (< 'Mango'): " + headMap);

        // TailMap: tất cả entry với key lớn hơn hoặc bằng giá trị chỉ định
        SortedMap<String, Integer> tailMap = treeMap.tailMap("Banana");
        System.out.println("TailMap (>= 'Banana'): " + tailMap);

        // SubMap: entry với key trong khoảng [from, to)
        SortedMap<String, Integer> subMap = treeMap.subMap("Apple", "Orange");
        System.out.println("SubMap ['Apple', 'Orange'): " + subMap);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt theo thứ tự tăng dần của key:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nDuyệt theo thứ tự giảm dần của key:");
        NavigableMap<String, Integer> descMap = treeMap.descendingMap();
        for (Map.Entry<String, Integer> entry : descMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ========== KIỂM TRA ==========

        boolean containsKey = treeMap.containsKey("Apple");
        boolean containsValue = treeMap.containsValue(300);

        System.out.println("\nCó key 'Apple'? " + containsKey);
        System.out.println("Có value 300? " + containsValue);

        // ========== XÓA TẤT CẢ ==========

        treeMap.clear();
        System.out.println("\nSau khi clear: " + treeMap);
    }
}
```

### Hashtable

```java
import java.util.*;

public class HashtableExample {
    public static void main(String[] args) {
        // Tạo Hashtable
        Hashtable<String, Integer> table = new Hashtable<>();

        // ========== THÊM CẶP KEY-VALUE ==========

        table.put("John", 25);
        table.put("Jane", 30);
        table.put("Bob", 35);
        // table.put(null, 40);  // LỖI: Hashtable không cho phép null key
        // table.put("Alice", null); // LỖI: Hashtable không cho phép null value

        System.out.println("Hashtable: " + table);

        // ========== CÁC PHƯƠNG THỨC TƯƠNG TỰ HASHMAP ==========

        // Truy cập
        Integer age = table.get("John");
        System.out.println("Tuổi của John: " + age);

        // Kiểm tra
        boolean hasKey = table.containsKey("Jane");
        boolean hasValue = table.containsValue(30);
        boolean isEmpty = table.isEmpty();
        int size = table.size();

        System.out.println("Có key 'Jane'? " + hasKey);
        System.out.println("Có value 30? " + hasValue);
        System.out.println("Có rỗng không? " + isEmpty);
        System.out.println("Size: " + size);

        // ========== CÁC PHƯƠNG THỨC ĐẶC BIỆT ==========

        // Kiểm tra bằng key hoặc value
        boolean contains = table.contains(30);  // tương đương containsValue
        System.out.println("Có value 30 (dùng contains)? " + contains);

        // ========== DUYỆT PHẦN TỬ ==========

        System.out.println("\nDuyệt keys:");
        Enumeration<String> keys = table.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println("Key: " + key);
        }

        System.out.println("\nDuyệt values:");
        Enumeration<Integer> values = table.elements();
        while (values.hasMoreElements()) {
            Integer value = values.nextElement();
            System.out.println("Value: " + value);
        }

        System.out.println("\nDuyệt bằng entrySet:");
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ========== XÓA PHẦN TỬ ==========

        table.remove("Bob");
        System.out.println("\nSau khi remove 'Bob': " + table);

        // ========== SAO CHÉP ==========

        // Sao chép vào mảng Object
        Collection<Integer> tableValues = table.values();
        System.out.println("Values collection: " + tableValues);

        // ========== XÓA TẤT CẢ ==========

        table.clear();
        System.out.println("\nSau khi clear: " + table);
    }
}
```

## Các Utility Class khác

### Collections

```java
import java.util.*;

public class CollectionsUtilityExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("Banana", "Apple", "Orange", "Mango"));

        // ========== SẮP XẾP ==========

        System.out.println("List ban đầu: " + list);

        // Sắp xếp tăng dần
        Collections.sort(list);
        System.out.println("Sau khi sort: " + list);

        // Sắp xếp giảm dần
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Sau khi sort reverse: " + list);

        // Sắp xếp với Comparator
        Collections.sort(list, (a, b) -> a.length() - b.length());
        System.out.println("Sắp xếp theo độ dài: " + list);

        // ========== TÌM KIẾM NHỊ PHÂN ==========

        // Phải sắp xếp trước khi dùng binarySearch
        Collections.sort(list);
        int index = Collections.binarySearch(list, "Orange");
        System.out.println("Index của 'Orange': " + index);

        // ========== ĐẢO NGƯỢC ==========

        Collections.reverse(list);
        System.out.println("Sau khi reverse: " + list);

        // ========== XÁO TRỘN ==========

        Collections.shuffle(list);
        System.out.println("Sau khi shuffle: " + list);

        // ========== HOÁN ĐỔI ==========

        Collections.swap(list, 0, list.size() - 1);
        System.out.println("Sau khi swap đầu và cuối: " + list);

        // ========== ĐIỀN GIÁ TRỊ ==========

        Collections.fill(list, "Fruit");
        System.out.println("Sau khi fill 'Fruit': " + list);

        // ========== SAO CHÉP ==========

        List<String> dest = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.copy(dest, list);  // copy từ list sang dest
        System.out.println("Sau khi copy: " + dest);

        // ========== TÌM MIN/MAX ==========

        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        Integer min = Collections.min(numbers);
        Integer max = Collections.max(numbers);

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

        // Min/Max với Comparator
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "orange");
        String shortest = Collections.min(words, Comparator.comparingInt(String::length));
        String longest = Collections.max(words, Comparator.comparingInt(String::length));

        System.out.println("Từ ngắn nhất: " + shortest);
        System.out.println("Từ dài nhất: " + longest);

        // ========== TẦN SUẤT ==========

        List<String> freqList = Arrays.asList("A", "B", "A", "C", "A", "B");
        int frequency = Collections.frequency(freqList, "A");
        System.out.println("Tần suất của 'A': " + frequency);

        // ========== COLLECTIONS DISJOINT ==========

        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("D", "E", "F");
        List<String> list3 = Arrays.asList("A", "E", "F");

        boolean disjoint1 = Collections.disjoint(list1, list2);  // true: không có phần tử chung
        boolean disjoint2 = Collections.disjoint(list1, list3);  // false: có phần tử chung 'A'

        System.out.println("list1 và list2 disjoint? " + disjoint1);
        System.out.println("list1 và list3 disjoint? " + disjoint2);

        // ========== THÊM TẤT CẢ ==========

        List<String> source = Arrays.asList("X", "Y", "Z");
        Collections.addAll(list1, "D", "E", "F");
        System.out.println("Sau khi addAll: " + list1);

        // ========== UNMODIFIABLE COLLECTIONS ==========

        List<String> unmodifiableList = Collections.unmodifiableList(list1);
        // unmodifiableList.add("G");  // LỖI: UnsupportedOperationException

        Set<String> unmodifiableSet = Collections.unmodifiableSet(new HashSet<>(list1));
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(new HashMap<>());

        // ========== SYNCHRONIZED COLLECTIONS ==========

        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        // ========== SINGLETON ==========

        Set<String> singletonSet = Collections.singleton("Single");
        List<String> singletonList = Collections.singletonList("Single");
        Map<String, String> singletonMap = Collections.singletonMap("key", "value");

        System.out.println("Singleton Set: " + singletonSet);
        System.out.println("Singleton List: " + singletonList);
        System.out.println("Singleton Map: " + singletonMap);

        // ========== EMPTY COLLECTIONS ==========

        List<String> emptyList = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, Integer> emptyMap = Collections.emptyMap();

        System.out.println("Empty List: " + emptyList);
        System.out.println("Empty Set: " + emptySet);
        System.out.println("Empty Map: " + emptyMap);

        // ========== ROTATE ==========

        List<Integer> rotateList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.rotate(rotateList, 2);  // xoay sang phải 2 vị trí
        System.out.println("Sau khi rotate 2: " + rotateList);

        Collections.rotate(rotateList, -1); // xoay sang trái 1 vị trí
        System.out.println("Sau khi rotate -1: " + rotateList);

        // ========== REPLACE ALL ==========

        List<String> replaceList = new ArrayList<>(Arrays.asList("A", "B", "A", "C", "A"));
        Collections.replaceAll(replaceList, "A", "Z");
        System.out.println("Sau khi replaceAll A->Z: " + replaceList);

        // ========== INDEX OF SUBLIST ==========

        List<String> sourceList = Arrays.asList("A", "B", "C", "D", "E", "F");
        List<String> targetList = Arrays.asList("C", "D", "E");
        int firstIndex = Collections.indexOfSubList(sourceList, targetList);
        int lastIndex = Collections.lastIndexOfSubList(sourceList, targetList);

        System.out.println("First index of sublist: " + firstIndex);
        System.out.println("Last index of sublist: " + lastIndex);
    }
}
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
