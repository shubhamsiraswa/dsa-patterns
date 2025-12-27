# Java Arrays - Ultimate Cheatsheet

## 📋 Table of Contents
- [Declaration & Initialization](#declaration--initialization)
- [Basic Operations](#basic-operations)
- [java.util.Arrays Methods](#javautilarrays-class-methods)
- [Stream Operations](#stream-operations-java-8)
- [Sorting with Comparators](#sorting-with-comparators)
- [Common Algorithms & Patterns](#common-algorithms--patterns)
- [Array vs ArrayList](#array-vs-arraylist)
- [Copying Arrays](#copying-arrays)
- [Default Values](#default-values)
- [Common Pitfalls](#common-pitfalls--gotchas)
- [Memory & Performance](#memory--performance)
- [Varargs](#varargs-variable-arguments)
- [Quick Reference Table](#quick-reference-table)

---

## Declaration & Initialization
```java
// Declaration
int[] arr;                    // Preferred style
int arr[];                    // Also valid (C-style)

// Initialization
int[] arr = new int[5];       // Array of 5 integers (default: 0)
int[] arr = {1, 2, 3, 4, 5}; // Direct initialization
int[] arr = new int[]{1, 2, 3}; // Explicit with new keyword

// Multi-dimensional Arrays
int[][] matrix = new int[3][4];              // 3 rows, 4 columns
int[][] matrix = {{1,2}, {3,4}, {5,6}};     // Direct initialization
int[][][] cube = new int[2][3][4];          // 3D array

// Jagged Arrays (irregular)
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[4];
jagged[2] = new int[3];
```

---

## Basic Operations
```java
// Length
int size = arr.length;
int rows = matrix.length;
int cols = matrix[0].length;

// Access elements
int first = arr[0];
int last = arr[arr.length - 1];
arr[2] = 10;

// Iterate - Standard for loop
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Iterate - Enhanced for-loop (for-each)
for (int num : arr) {
    System.out.println(num);
}

// Iterate - Using streams (Java 8+)
Arrays.stream(arr).forEach(System.out::println);

// Iterate 2D Array
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}

// Enhanced for-loop for 2D
for (int[] row : matrix) {
    for (int num : row) {
        System.out.print(num + " ");
    }
    System.out.println();
}
```

---

## java.util.Arrays Class Methods
```java
import java.util.Arrays;

// Sorting
Arrays.sort(arr);                    // Sort entire array (ascending)
Arrays.sort(arr, 0, 3);             // Sort from index 0 to 2 (exclusive end)
Arrays.sort(arr, Collections.reverseOrder()); // Descending (Integer[] only)

// Parallel Sorting (faster for large arrays)
Arrays.parallelSort(arr);

// Searching (array MUST be sorted first)
int index = Arrays.binarySearch(arr, 5);  // Returns index or negative if not found

// Filling
Arrays.fill(arr, 0);                // Fill entire array with 0
Arrays.fill(arr, 1, 4, 5);         // Fill index 1-3 with value 5

// Copying
int[] copy = Arrays.copyOf(arr, arr.length);      // Exact copy
int[] larger = Arrays.copyOf(arr, arr.length + 5); // Copy with more space
int[] partial = Arrays.copyOfRange(arr, 1, 4);    // Copy from index 1 to 3

// Comparing
boolean equal = Arrays.equals(arr1, arr2);        // Element-wise comparison
boolean deepEqual = Arrays.deepEquals(arr2D1, arr2D2); // For multi-dimensional
int comparison = Arrays.compare(arr1, arr2);      // Lexicographic comparison

// Converting to String
String str = Arrays.toString(arr);           // [1, 2, 3, 4, 5]
String str2D = Arrays.deepToString(matrix);  // For multi-dimensional

// Converting to List
List<Integer> list = Arrays.asList(1, 2, 3);
List<Integer> list2 = new ArrayList<>(Arrays.asList(arr)); // Mutable list

// Mismatch (Java 9+)
int index = Arrays.mismatch(arr1, arr2);  // Returns first index where they differ

// Hash Code
int hash = Arrays.hashCode(arr);
int deepHash = Arrays.deepHashCode(matrix);
```

---

## Stream Operations (Java 8+)
```java
import java.util.Arrays;
import java.util.stream.*;

// Create stream from array
IntStream stream = Arrays.stream(arr);
Stream<Integer> boxedStream = Arrays.stream(arr).boxed();

// Common stream operations
int sum = Arrays.stream(arr).sum();
double average = Arrays.stream(arr).average().orElse(0.0);
int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);

// Filter and collect
int[] filtered = Arrays.stream(arr)
                       .filter(x -> x > 5)
                       .toArray();

// Map and transform
int[] squared = Arrays.stream(arr)
                      .map(x -> x * x)
                      .toArray();

// Check conditions
boolean anyMatch = Arrays.stream(arr).anyMatch(x -> x > 10);
boolean allMatch = Arrays.stream(arr).allMatch(x -> x > 0);
boolean noneMatch = Arrays.stream(arr).noneMatch(x -> x < 0);

// Count elements
long count = Arrays.stream(arr).filter(x -> x % 2 == 0).count();
```

---

## Sorting with Comparators
```java
// For Integer[] (not int[])
Integer[] arr = {5, 2, 8, 1, 9};

// Descending order
Arrays.sort(arr, Collections.reverseOrder());

// Custom comparator - Lambda
Arrays.sort(arr, (a, b) -> b - a);  // Descending

// Custom comparator - By absolute value
Arrays.sort(arr, (a, b) -> Math.abs(a) - Math.abs(b));

// Sort 2D array by first column
int[][] points = {{3, 4}, {1, 2}, {5, 1}};
Arrays.sort(points, (a, b) -> a[0] - b[0]);

// Sort 2D array by second column
Arrays.sort(points, (a, b) -> a[1] - b[1]);

// Sort strings by length
String[] words = {"apple", "pie", "banana"};
Arrays.sort(words, (a, b) -> a.length() - b.length());

// Comparator.comparing (Java 8+)
Arrays.sort(words, Comparator.comparing(String::length));
```

---

## Common Algorithms & Patterns

### Find Min/Max
```java
int max = arr[0];
int min = arr[0];
for (int num : arr) {
    max = Math.max(max, num);
    min = Math.min(min, num);
}

// Or using streams
int max = Arrays.stream(arr).max().getAsInt();
int min = Arrays.stream(arr).min().getAsInt();
```

### Sum and Average
```java
// Sum of elements
int sum = 0;
for (int num : arr) sum += num;
// Or: int sum = Arrays.stream(arr).sum();

// Average
double avg = (double) sum / arr.length;
// Or: double avg = Arrays.stream(arr).average().orElse(0.0);
```

### Reverse Array
```java
// Reverse array in-place
for (int i = 0; i < arr.length / 2; i++) {
    int temp = arr[i];
    arr[i] = arr[arr.length - 1 - i];
    arr[arr.length - 1 - i] = temp;
}
```

### Search Element
```java
// Check if element exists (unsorted)
boolean found = false;
for (int num : arr) {
    if (num == target) {
        found = true;
        break;
    }
}
// Or: boolean found = Arrays.stream(arr).anyMatch(x -> x == target);

// Find index of element
int index = -1;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        index = i;
        break;
    }
}

// Count occurrences
int count = 0;
for (int num : arr) {
    if (num == target) count++;
}
```

### Remove Duplicates
```java
// Remove duplicates (return new array)
int[] unique = Arrays.stream(arr).distinct().toArray();
```

### Rotate Array
```java
// Rotate array right by k positions
void rotate(int[] arr, int k) {
    k = k % arr.length;
    reverse(arr, 0, arr.length - 1);
    reverse(arr, 0, k - 1);
    reverse(arr, k, arr.length - 1);
}
```

### Two Pointer Technique
```java
int left = 0, right = arr.length - 1;
while (left < right) {
    // Process elements
    left++;
    right--;
}
```

### Sliding Window (Fixed Size)
```java
// Sum of k consecutive elements
int windowSum = 0;
for (int i = 0; i < k; i++) {
    windowSum += arr[i];
}
int maxSum = windowSum;
for (int i = k; i < arr.length; i++) {
    windowSum = windowSum - arr[i - k] + arr[i];
    maxSum = Math.max(maxSum, windowSum);
}
```

### Prefix Sum
```java
int[] prefixSum = new int[arr.length];
prefixSum[0] = arr[0];
for (int i = 1; i < arr.length; i++) {
    prefixSum[i] = prefixSum[i - 1] + arr[i];
}

// Sum of range [left, right]
int rangeSum = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
```

### Kadane's Algorithm (Max Subarray Sum)
```java
int maxSum = arr[0], currentSum = arr[0];
for (int i = 1; i < arr.length; i++) {
    currentSum = Math.max(arr[i], currentSum + arr[i]);
    maxSum = Math.max(maxSum, currentSum);
}
```

---

## Array vs ArrayList
```java
// Array - Fixed size, can store primitives
int[] arr = new int[5];
arr[0] = 1;
int size = arr.length;  // Property, not method

// ArrayList - Dynamic size, stores objects only
ArrayList<Integer> list = new ArrayList<>();
list.add(1);              // Add element
list.remove(0);           // Remove by index
list.get(0);              // Access element
int size = list.size();   // Method call

// Convert ArrayList to Array
Integer[] arr = list.toArray(new Integer[0]);
// Or for primitives
int[] primitiveArr = list.stream().mapToInt(i -> i).toArray();

// Convert Array to ArrayList
List<Integer> list = new ArrayList<>(Arrays.asList(arr));
// Or
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// For primitive arrays
int[] primitives = {1, 2, 3};
List<Integer> list = Arrays.stream(primitives).boxed().collect(Collectors.toList());
```

---

## Copying Arrays
```java
// Method 1: Arrays.copyOf()
int[] copy1 = Arrays.copyOf(arr, arr.length);

// Method 2: clone()
int[] copy2 = arr.clone();

// Method 3: System.arraycopy()
int[] copy3 = new int[arr.length];
System.arraycopy(arr, 0, copy3, 0, arr.length);

// Method 4: Manual copy
int[] copy4 = new int[arr.length];
for (int i = 0; i < arr.length; i++) {
    copy4[i] = arr[i];
}

// Deep copy for 2D arrays
int[][] original = {{1, 2}, {3, 4}};
int[][] deepCopy = new int[original.length][];
for (int i = 0; i < original.length; i++) {
    deepCopy[i] = original[i].clone();
}

// Or using streams
int[][] deepCopy2 = Arrays.stream(original)
                          .map(int[]::clone)
                          .toArray(int[][]::new);
```

---

## Default Values
```java
// Numeric primitives: 0
int[] ints = new int[5];        // [0, 0, 0, 0, 0]
double[] doubles = new double[5]; // [0.0, 0.0, 0.0, 0.0, 0.0]

// boolean: false
boolean[] bools = new boolean[5]; // [false, false, false, false, false]

// char: '\u0000' (null character)
char[] chars = new char[5];

// Objects: null
String[] strings = new String[5]; // [null, null, null, null, null]
Integer[] integers = new Integer[5]; // [null, null, null, null, null]
```

---

## Common Pitfalls & Gotchas

### 1. Arrays Are Passed by Reference
```java
int[] a = {1, 2, 3};
int[] b = a;        // b points to same array
b[0] = 10;          // Changes a[0] too!

// Solution: Clone or copy
int[] c = a.clone();
```

### 2. ArrayIndexOutOfBoundsException
```java
arr[arr.length];    // ❌ ERROR! Valid indices: 0 to length-1
arr[arr.length - 1]; // ✅ Last element
```

### 3. NullPointerException
```java
int[] arr = null;
int len = arr.length; // ❌ NPE!

// Always check:
if (arr != null && arr.length > 0) {
    // Safe to use
}
```

### 4. Comparing Arrays with ==
```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
System.out.println(a == b);  // ❌ false (compares references)
System.out.println(Arrays.equals(a, b)); // ✅ true (compares content)
```

### 5. Modifying Array During Iteration
```java
for (int i = 0; i < arr.length; i++) {
    arr = new int[10]; // ❌ Don't do this!
}
```

### 6. Integer[] vs int[]
```java
Integer[] objects = {1, 2, 3};
int[] primitives = {1, 2, 3};
// They are NOT interchangeable
// Arrays.asList() works with Integer[], not int[]
```

### 7. Arrays.asList() Returns Fixed-Size List
```java
List<Integer> list = Arrays.asList(1, 2, 3);
list.add(4); // ❌ UnsupportedOperationException!

// Solution: Create mutable ArrayList
List<Integer> mutable = new ArrayList<>(Arrays.asList(1, 2, 3));
```

### 8. Multidimensional Array Initialization
```java
int[][] matrix = new int[3][]; // ✅ OK - rows created, columns null
int[][] matrix = new int[][3]; // ❌ ERROR - must specify first dimension
```

### 9. Array Covariance
```java
Object[] arr = new String[5];
arr[0] = new Integer(5); // Compiles but throws ArrayStoreException at runtime
```

### 10. Sorting Affects Original Array
```java
int[] original = {3, 1, 2};
Arrays.sort(original); // original is now {1, 2, 3}
// If you need original: make a copy first
int[] copy = original.clone();
Arrays.sort(copy);
```

---

## Memory & Performance
```java
// Arrays are faster than ArrayList
// - No boxing/unboxing for primitives
// - Less memory overhead
// - Better cache locality

// Space complexity
int[] arr = new int[n];        // O(n) space
int[][] matrix = new int[m][n]; // O(m*n) space

// Array allocation is O(n) time
// Accessing element is O(1) time

// Use parallelSort for large arrays (10,000+ elements)
Arrays.parallelSort(largeArray);

// Prefer primitive arrays over wrapper arrays
int[] primitives;    // ✅ Better performance
Integer[] wrappers;  // ⚠️ Boxing overhead
```

---

## Varargs (Variable Arguments)
```java
// Method with varargs
public static int sum(int... numbers) {
    int total = 0;
    for (int num : numbers) {
        total += num;
    }
    return total;
}

// Usage
sum(1, 2, 3);
sum(1, 2, 3, 4, 5);
int[] arr = {1, 2, 3};
sum(arr); // Can pass array directly

// Varargs is actually an array
public static void print(String... args) {
    System.out.println(args.length);  // Array length
    System.out.println(args[0]);      // Array access
}
```

---

## Quick Reference Table

| Operation | Time Complexity | Code |
|-----------|-----------------|------|
| Access | O(1) | `arr[i]` |
| Update | O(1) | `arr[i] = x` |
| Search (unsorted) | O(n) | Linear search |
| Search (sorted) | O(log n) | `Arrays.binarySearch()` |
| Sort | O(n log n) | `Arrays.sort()` |
| Copy | O(n) | `Arrays.copyOf()` |
| Fill | O(n) | `Arrays.fill()` |

---

## Best Practices

✅ Always check bounds before accessing: `if (i >= 0 && i < arr.length)`
✅ Use enhanced for-loop when you don't need the index
✅ Prefer Arrays utility methods over manual implementations
✅ Clone arrays when you need independent copies
✅ Use ArrayList if you need dynamic sizing
✅ Validate null before operations: `if (arr != null)`
✅ Consider streams for functional-style operations
✅ Use primitives when possible for better performance
✅ Initialize arrays at declaration when size is known
✅ Be careful with multi-dimensional arrays - they're arrays of arrays

---

## Additional Resources

- [Java Documentation: java.util.Arrays](https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html)
- Common interview problems: Two Sum, Maximum Subarray, Rotate Array
- Practice: LeetCode, HackerRank array problems

---

**Last Updated:** December 27, 2024