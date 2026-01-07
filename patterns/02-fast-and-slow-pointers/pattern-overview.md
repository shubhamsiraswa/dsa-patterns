# Fast and Slow Pointers Pattern

## 📖 What is Fast and Slow Pointers?

A technique where we use **two pointers moving at different speeds** through a data structure:
- **Slow pointer**: moves 1 step at a time 🐢
- **Fast pointer**: moves 2 steps at a time 🐰

Also called **"Tortoise and Hare" algorithm** (Floyd's Cycle Detection).

## 🎯 When to Use This Pattern?

Look for these keywords in problem descriptions:

✅ **Linked List** problems (biggest clue!)
✅ **Cycle detection** (does a loop exist?)
✅ Finding **middle element**
✅ **Palindrome** in linked list
✅ **K-th element from end**
✅ **Happy Number** type problems
✅ Problems with **implicit linked lists** (array indices)

### Problem Phrases That Scream Fast & Slow:
- "Does the linked list have a cycle?"
- "Find the middle of the linked list"
- "Is the linked list a palindrome?"
- "Find where the cycle starts"
- "Happy number"
- "Find the duplicate number" (array as linked list)

## 🔧 Common Templates

### Template 1: Cycle Detection (Most Common)
```java
// Use when: detecting if cycle exists
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;           // Move 1 step 🐢
    fast = fast.next.next;      // Move 2 steps 🐰
    
    if (slow == fast) {
        return true;  // Cycle found!
    }
}

return false;  // No cycle
```

**Why it works:** If there's a cycle, fast will eventually lap slow and they'll meet!

**Example Problems:**
- Linked List Cycle (LC 141)
- Happy Number (LC 202)

---

### Template 2: Finding Middle Element
```java
// Use when: need to find middle of linked list
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

return slow;  // This is the middle!
```

**Why it works:** When fast reaches end, slow is at middle!

**Example Problems:**
- Middle of Linked List (LC 876)
- Palindrome Linked List (LC 234)
- Reorder List (LC 143)

---

### Template 3: Finding Cycle Start (Two Phases)
```java
// Phase 1: Detect cycle
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    
    if (slow == fast) {
        // Cycle found! Now find start...
        
        // Phase 2: Find cycle start
        slow = head;  // Reset slow to head
        while (slow != fast) {
            slow = slow.next;      // Both move 1 step now
            fast = fast.next;
        }
        return slow;  // This is cycle start!
    }
}

return null;  // No cycle
```

**Math behind it:** Distance from head to cycle start = Distance from meeting point to cycle start!

**Example Problems:**
- Linked List Cycle II (LC 142)
- Find Duplicate Number (LC 287)

---

### Template 4: Palindrome Check (Three Steps)
```java
// Step 1: Find middle
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

// Step 2: Reverse second half
ListNode secondHalf = reverse(slow);

// Step 3: Compare both halves
ListNode firstHalf = head;
while (secondHalf != null) {
    if (firstHalf.val != secondHalf.val) {
        return false;
    }
    firstHalf = firstHalf.next;
    secondHalf = secondHalf.next;
}

return true;
```

**Example Problems:**
- Palindrome Linked List (LC 234)

---

## 🧠 Key Concepts & Tricks

### 1. **Why Fast Catches Slow in a Cycle?**
```
Imagine a circular track:
- Slow runs at 1 mph
- Fast runs at 2 mph
- Fast gains 1 mph on slow each round
- Eventually fast will lap slow!
```

### 2. **The Magic of Meeting Point Math**
```
When they meet:
- Distance traveled by slow = d
- Distance traveled by fast = 2d

For cycle start:
- Distance from head to start = k
- Distance from meeting point to start = k (same!)
```

### 3. **Null Pointer Checks (Critical!)**
```java
// ✅ CORRECT: Check both fast and fast.next
while (fast != null && fast.next != null) {
    fast = fast.next.next;  // Safe!
}

// ❌ WRONG: Only checking fast
while (fast != null) {
    fast = fast.next.next;  // NPE if fast.next is null!
}
```

### 4. **When to Use Do-While vs While**
```java
// Use WHILE when checking condition BEFORE moving
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

// Use DO-WHILE when moving BEFORE checking (Happy Number)
do {
    slow = getNext(slow);
    fast = getNext(getNext(fast));
} while (slow != fast);
```

### 5. **Different Starting Positions**
```java
// Both start at head (most common)
ListNode slow = head, fast = head;

// Fast starts one ahead (for first middle in even list)
ListNode slow = head, fast = head.next;

// Slow at head, fast k steps ahead (kth from end)
ListNode slow = head, fast = head;
for (int i = 0; i < k; i++) {
    fast = fast.next;
}
```

### 6. **Time Complexity**
- **Without cycle:** O(n) - fast reaches end in n/2 iterations
- **With cycle:** O(n) - they meet after at most n steps
- **Space:** Always O(1) - only two pointers!

## ❌ Common Mistakes I Made

### Mistake 1: Null Pointer Exception (Most Common!)
```java
// ❌ WRONG: Will crash if fast.next is null
while (fast != null) {
    fast = fast.next.next;  // NPE!
}

// ✅ CORRECT: Check BOTH conditions
while (fast != null && fast.next != null) {
    fast = fast.next.next;  // Safe!
}
```

### Mistake 2: Wrong Starting Position for Middle
```java
// For SECOND middle in even-length list:
ListNode slow = head, fast = head;  // Both start at head

// For FIRST middle in even-length list:
ListNode slow = head, fast = head.next;  // Fast one ahead
```

### Mistake 3: Forgetting to Reset Pointer in Two-Phase
```java
// ❌ WRONG: Not resetting slow to head in phase 2
if (slow == fast) {  // Found cycle
    while (slow != fast) {  // Infinite loop! They're already equal!
        slow = slow.next;
        fast = fast.next;
    }
}

// ✅ CORRECT: Reset slow to head first
if (slow == fast) {
    slow = head;  // Reset!
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
}
```

### Mistake 4: Moving Fast Wrong in Phase 2
```java
// ❌ WRONG: Still moving fast by 2 steps in phase 2
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next.next;  // ❌ Should be 1 step!
}

// ✅ CORRECT: Both move 1 step in phase 2
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;  // ✅ Both move together
}
```

### Mistake 5: Not Handling Empty/Single Node Lists
```java
// ❌ WRONG: No base case check
ListNode slow = head;
ListNode fast = head;
// Will crash if head is null!

// ✅ CORRECT: Check edge cases
if (head == null || head.next == null) {
    return false;  // No cycle possible
}
```

## 📝 Problem-Specific Patterns

### Pattern A: Happy Number (Array Traversal)
```java
public boolean isHappy(int n) {
    int slow = n;
    int fast = n;
    
    do {
        slow = getNext(slow);              // 1 step
        fast = getNext(getNext(fast));     // 2 steps
    } while (slow != fast);
    
    return slow == 1;  // Happy if cycle at 1
}

private int getNext(int n) {
    int sum = 0;
    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
    }
    return sum;
}
```

### Pattern B: Array as Implicit Linked List
```java
// Problem: Find duplicate in [1,n] array
// Treat array as linked list: nums[i] -> nums[nums[i]]

public int findDuplicate(int[] nums) {
    int slow = nums[0];
    int fast = nums[0];
    
    // Phase 1: Find meeting point
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow != fast);
    
    // Phase 2: Find cycle start (duplicate)
    slow = nums[0];
    while (slow != fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    
    return slow;
}
```

### Pattern C: Reverse Helper Function
```java
// Often needed for palindrome/reorder problems
private ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    
    return prev;
}
```

## 📝 Problems Solved

### Easy (3/3)
<!-- - [ ] Linked List Cycle (LC 141) - __min - ___
- [ ] Middle of Linked List (LC 876) - __min - ___
- [ ] Happy Number (LC 202) - __min - ___ -->

### Medium (5/5)
<!-- - [ ] Linked List Cycle II (LC 142) - __min - ___
- [ ] Palindrome Linked List (LC 234) - __min - ___
- [ ] Reorder List (LC 143) - __min - ___
- [ ] Find Duplicate Number (LC 287) - __min - ___
- [ ] Remove Nth From End (LC 19) - __min - ___ -->

### Hard (1/1)
<!-- - [ ] Circular Array Loop (LC 457) - __min - ___ -->

### Status Legend:
- ✅ = Solved first try
- ⚠️ = Solved with hints/struggle
- ❌➡️✅ = Couldn't solve initially, understood solution, coded myself

---

## 🎓 Key Learnings

1. **Always check `fast != null && fast.next != null`** - this prevents 90% of bugs
2. **Two-phase problems** (cycle start) need to reset one pointer
3. **Do-while vs while** matters - do-while moves first, then checks
4. **Middle finding** automatically handles odd/even lists (fast reaches end)
5. **This pattern gives O(1) space** - that's its superpower!

---

## 🔄 Pattern Variations I've Seen

| Variation | Example Problem | Key Insight |
|-----------|----------------|-------------|
| Basic cycle detection | LC 141 | Fast catches slow if cycle exists |
| Find cycle start | LC 142 | Two phases: detect then find start |
| Find middle | LC 876 | Fast reaches end → slow at middle |
| Palindrome check | LC 234 | Find middle → reverse → compare |
| Array as linked list | LC 287 | Use indices as "next" pointers |
| Happy number | LC 202 | Numbers form implicit linked list |

---

## 💡 When NOT to Use Fast & Slow

❌ **Array problems** where you need indices (use regular two pointers)
❌ **Need to track visited nodes** (use HashSet instead)
❌ **Tree/Graph traversal** (use DFS/BFS)
❌ **Finding ALL cycles** (this finds ONE cycle)
❌ **Doubly linked list** where you can go backwards

---

## 🆚 Fast & Slow vs Regular Two Pointers

| Aspect | Fast & Slow | Two Pointers |
|--------|-------------|--------------|
| Speed | Different (1x vs 2x) | Usually same |
| Direction | Same direction | Often opposite |
| Best for | Linked lists, cycles | Arrays, pairs |
| Space | O(1) | O(1) |
| Structure | Singly linked | Arrays |

---

## 🎯 Next Steps

<!-- - [ ] Master the two-phase cycle start (hardest concept)
- [ ] Practice array-as-linked-list problems
- [ ] Try combining with reverse function
- [ ] Move to Sliding Window pattern next -->

---

## 📊 Confidence Level: __/10

**Strong in:**
<!-- - Basic cycle detection
- Finding middle element -->

**Need work on:**
<!-- - Two-phase cycle start (math is confusing)
- Array as implicit linked list
- Combining with reverse/reorder -->

---

<!-- **Pattern Mastered:** ✅ ___
**Total Time Spent:** ~__ hours
**Problems Solved:** __/9
**Ready for interviews:** ___ -->

---

## 🧪 Quick Self-Test

**Q1:** Why do we check `fast != null && fast.next != null`?
<details>
<summary>Answer</summary>
Because fast moves 2 steps, so we need to ensure fast.next exists before doing fast.next.next!
</details>

**Q2:** In cycle start detection, why reset slow to head?
<details>
<summary>Answer</summary>
Math proof: distance from head to start = distance from meeting point to start. So reset one pointer and move both at same speed until they meet at the start!
</details>

**Q3:** When does slow reach middle?
<details>
<summary>Answer</summary>
When fast reaches the end (fast == null or fast.next == null). Fast moves 2x speed, so when it's done, slow is at middle!
</details>