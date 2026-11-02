# Benchmark-Algorithms-Sorting

**اسم الطالب:** عبد الرحمن هيثم ميروان  **رقم القيد:** 3169047  **الشعبة:** B

برنامج Java (`SortingBenchmark.java`) يقيس زمن تنفيذ 5 خوارزميات ترتيب على مصفوفة بحجم N = 100,000 في 4 حالات، بالميكروثانية (`System.nanoTime()/1000`). القياس للترتيب فقط، والنسخ يتم قبل بدء المؤقت.

تشغيل: `java SortingBenchmark.java`

## جدول النتائج (µs)
| الخوارزمية | Random | Sorted Reversely | Sorted Partially | Sorted Fully |
|---|---:|---:|---:|---:|
| Selection Sort | 2,121,623 | 3,921,590 | 2,173,816 | 2,108,029 |
| Insertion Sort | 628,231 | 1,208,960 | 136,801 | 84 |
| Bubble Sort (flag) | 15,295,168 | 8,077,285 | 8,019,735 | 202 |
| Merge Sort | 16,679 | 3,848 | 6,728 | 4,000 |
| Quick Sort (pivot = first) | 9,255 | 4,520,409 | 6,076 | 3,606,683 |
