# Two Pointers Pattern

## 📖 What is Two Pointers?

A technique where we use two pointers (indices) to traverse an array/string, typically from:
- **Both ends moving inward** (most common)
- **Same direction at different speeds**
- **One stationary, one moving**

## 🎯 When to Use This Pattern?

Look for these keywords in problem descriptions:

✅ **Sorted array** (biggest clue)
✅ Finding **pairs, triplets, or subarrays**
✅ **In-place modifications** (remove duplicates, move elements)
✅ **Palindrome** checking
✅ **Partitioning** arrays
✅ Comparing elements from **both ends**
✅ **Opposite directions** (start and end)

### Problem Phrases That Scream Two Pointers:
- "Find pair that sums to target"
- "Remove duplicates in-place"
- "Is this string a palindrome?"
- "Find three numbers that sum to..."
- "Container with most water"
- "Sorted array" + any search/find operation

## 🔧 Common Templates

### Template 1: Opposite Direction (Most Common)
```java
// Use when: sorted array, finding pairs, palindrome
int left = 0;
int right = array.length - 1;

while (left < right) {
    // Calculate something with array[left] and array[right]
    
    if (condition_met) {
        // Found answer or record result
        left++;
        right--;
    } else if (need_larger_sum) {
        left++;  // Move left pointer right
    } else {
        right--; // Move right pointer left
    }
}
```

**Example Problems:**
- Two Sum II (sorted array)
- Valid Palindrome
- Container With Most Water

---

### Template 2: Same Direction (Slow-Fast)
```java
// Use when: in-place modifications, removing elements
int slow = 0;

for (int fast = 0; fast < array.length; fast++) {
    if (array[fast] meets condition) {
        array[slow] = array[fast];
        slow++;
    }
}

return slow; // Usually returns new length
```

**Example Problems:**
- Remove Duplicates from Sorted Array
- Remove Element
- Move Zeroes

---

### Template 3: Three Pointers (For Triplets)
```java
// Use when: finding triplets, 3Sum problems
Arrays.sort(array); // Usually need sorted array

for (int i = 0; i < array.length - 2; i++) {
    // Skip duplicates
    if (i > 0 && array[i] == array[i-1]) continue;
    
    int left = i + 1;
    int right = array.length - 1;
    
    while (left < right) {
        int sum = array[i] + array[left] + array[right];
        
        if (sum == target) {
            // Found triplet
            left++;
            right--;
            
            // Skip duplicates
            while (left < right && array[left] == array[left-1]) left++;
            while (left < right && array[right] == array[right+1]) right--;
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
}
```

**Example Problems:**
- 3Sum
- 3Sum Closest
- Triplet Sum Smaller Than

---

## 🧠 Key Concepts & Tricks

### 1. **When to Move Which Pointer?**
- If current sum/value is **too small** → move **left** pointer right (increase)
- If current sum/value is **too large** → move **right** pointer left (decrease)
- This ONLY works with **sorted arrays**

### 2. **Handling Duplicates**
```java
// Skip duplicates after finding a solution
while (left < right && array[left] == array[left+1]) left++;
while (left < right && array[right] == array[right-1]) right--;
```

### 3. **Sorting First**
- Many two-pointer problems require **sorting first**
- If not explicitly sorted, consider: `Arrays.sort(array);`

### 4. **In-Place Modifications**
- Use slow-fast pointers
- Slow pointer: where to write next valid element
- Fast pointer: scanning all elements

### 5. **Time Complexity**
- Two pointers: **O(n)** for single pass
- With sorting: **O(n log n)** due to sort
- Better than brute force: **O(n²)**

## ❌ Common Mistakes I Made

### Mistake 1: Not sorting first
```java
// ❌ WRONG: Trying two pointers on unsorted array
int[] nums = {3, 1, 4, 2, 5};
// Two pointers won't work correctly!

// ✅ CORRECT: Sort first
Arrays.sort(nums); // {1, 2, 3, 4, 5}
// Now two pointers will work
```

### Mistake 2: Infinite loops
```java
// ❌ WRONG: Forgetting to move pointers
while (left < right) {
    if (array[left] + array[right] == target) {
        return true; // ✅ Good, exits
    }
    // ❌ Forgot to move pointers! Infinite loop!
}

// ✅ CORRECT: Always move at least one pointer
while (left < right) {
    if (array[left] + array[right] == target) {
        return true;
    } else if (array[left] + array[right] < target) {
        left++; // ✅ Move pointer
    } else {
        right--; // ✅ Move pointer
    }
}
```

### Mistake 3: Off-by-one errors
```java
// ❌ WRONG: Using <= when should use 
while (left <= right) {  // ❌ Will check middle element twice
    // ...
}

// ✅ CORRECT: Use < for most cases
while (left < right) {  // ✅ Stops when pointers meet
    // ...
}
```

### Mistake 4: Not handling duplicates in 3Sum
```java
// ❌ WRONG: Will have duplicate triplets
for (int i = 0; i < nums.length; i++) {
    // No duplicate checking
}

// ✅ CORRECT: Skip duplicate 'i' values
for (int i = 0; i < nums.length; i++) {
    if (i > 0 && nums[i] == nums[i-1]) continue; // ✅ Skip duplicates
}
```

## 📝 Problems Solved

### Easy (6/6)
<!-- - [x] Valid Palindrome - 15 min - First try ✅
- [x] Two Sum II - 12 min - Used template 1 ✅
- [x] Remove Duplicates - 18 min - Template 2, struggled with slow pointer initially ⚠️
- [x] Remove Element - 10 min - Similar to above ✅
- [x] Move Zeroes - 20 min - Tricky edge cases ⚠️
- [x] Squares of Sorted Array - 15 min - First try ✅ -->

### Medium (6/6)
<!-- - [x] 3Sum - 45 min - Hard! Struggled with duplicates ❌➡️✅
- [x] 3Sum Closest - 30 min - Similar to 3Sum ✅
- [x] Container With Most Water - 25 min - Understood why greedy works ✅
- [x] Triplet Sum Smaller - 35 min - Had to look at hint ⚠️
- [x] Subarray Product Less Than Target - 40 min - Tricky sliding window + two pointers ⚠️
- [x] Sort Colors (Dutch Flag) - 50 min - Complex three-pointer logic ❌➡️✅ -->

### Status Legend:
- ✅ = Solved first try
- ⚠️ = Solved with hints/struggle
- ❌➡️✅ = Couldn't solve initially, understood solution, coded myself

---

## 🎓 Key Learnings

1. **Always check if array is sorted** - that's your biggest clue
2. **Sorting first is often okay** - O(n log n) still better than O(n²)
3. **Slow-fast pattern** is different from opposite-direction pattern
4. **Handling duplicates** is the hardest part of 3Sum type problems
5. **Drawing it out helps** - visualize pointers moving on paper

---

## 🔄 Pattern Variations I've Seen

| Variation | Example Problem | Key Insight |
|-----------|----------------|-------------|
| Opposite direction | Two Sum II | Sorted array, find pairs |
| Same direction | Remove Duplicates | In-place modification |
| Three pointers | 3Sum | One fixed, two moving |
| With sliding window | Subarray Product < K | Hybrid pattern |
| Partitioning | Sort Colors | Three-way partitioning |

---

## 💡 When NOT to Use Two Pointers

❌ **Unsorted array** and you can't sort (need original order)
❌ **Need to track multiple elements** across array (use HashMap)
❌ **Complex conditions** that can't be reduced to pointer movement
❌ **Tree/Graph problems** (use DFS/BFS instead)

---

## 🎯 Next Steps

<!-- - [ ] Revisit 3Sum in 3 days (struggled with duplicates)
- [ ] Practice Dutch Flag problem again (complex logic)
- [ ] Try Trapping Rain Water (advanced two pointers)
- [ ] Move to Sliding Window pattern (related concept) -->

---

## 📊 Confidence Level: 2/10

**Strong in:**
<!-- - Basic opposite-direction problems
- In-place modifications -->

**Need work on:**
<!-- - Triplet problems with duplicates
- Three-way partitioning
- Combining with other patterns -->

---

<!-- **Pattern Mastered:** ✅ December 29, 2024
**Total Time Spent:** ~6 hours
**Problems Solved:** 1/12
**Ready for interviews:** NO (for easy/medium two-pointer questions) -->



