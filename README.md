# COMP20290 Algorithms 
#### Spring Trimester, 2020
Java programs from weekly practical sessions and assignments for the module COMP20290.  
Developed using IntelliJ IDEA and Microsoft Office.

## Getting Started
### Prerequisites
* Install JDK 8 from Oracle [here](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html), 
in case you don't have it.
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) is the preferred IDE for the project.

### Build and run
Clone the repository into your IDE and build the project. Run [Main.java](./src/Main.java) to view the features of the repository.  
The documented results/graphs followed by algorithm implementations, are summarised below.  

## Documentation
Documented results and graphs for performance of some implemented algorithms. See ['docs'](./docs). 

| # | File | Type | Description |
|---| ---- | ---- | ----------- | 
| 1 | [Russian Peasant's Algorithm](./docs/Practical%201%20-%20Russian%20Multiplication.xlsx) | Excel | Russian Peasant's algorithm timing analysis with inputs of different sizes.
| 2 | [Complexity Analysis](./docs/Practical%202%20-%20Complexity%20Analysis.pdf) | PDF | Compares the performance of ThreeSumA and ThreeSumB to count triples summing to 0, with input integers in files of increasing sizes (8ints to 32Kints).
| 3 | [Sorting](./docs/Practical%204,%205,%206%20-%20Sorting%20Graphs.xlsx) | Excel | Compares performances of various sorting algorithms.<br> Also see PDFs: [Elementary Sorting](./docs/Practical%204%20-%20Elementary%20Sorting%20Algorithms.pdf), [Advanced Sorting I](./docs/Practical%205%20-%20Advanced%20Sorting%20Algorithms.pdf), [Advanced Sorting II](./docs/Practical%206%20-%20Advanced%20Sorting%20Algorithms%20II.pdf).
| 4 | [Substring Search](./docs/Practical%207%20-%20Search%20Time%20Graph.xlsx) | Excel | Compares the performance of brute force and KMP substring search algorithms, using data from input.txt.
| 5 | [Run Length Compression](./docs/Practical%209%20-%20Run%20Length%20Compression.pdf) |PDF | Run length compression experiments.
| 6 | [Assignment: Huffman Compression](./Huffman%20Compression.pdf) | PDF | Huffman tree drawing and compression analysis results. 

## Summary
Brief summary of the ['src'](./src) directory.

| Package | Topic | File | Description |  
|---| ----- | ---- | ----------- |
| p1 | [Algorithm Analysis](./src/p1) | [RussianPeasant](./src/p1/RussianPeasant.java) | Multiplies two numbers using the Russian Peasant's algorithm.
| p2 | [Complexity Analysis](./src/p2) | [ThreeSumA](./src/p2/ThreeSumA.java) | Uses brute force to count the number of triples in a file, summing to 0. <br> Complexity = O(n<sup>3</sup>)
| | | [ThreeSumB](./src/p2/ThreeSumB.java) | Uses the binary search algorithm to efficiently count the number of triples in a file, summing to 0. <br> Complexity = O(n<sup>2</sup> log n)
| p3 | [Recursion](./src/p3) | [Fibonacci](./src/p3/Fibonacci.java) | Computes the Nth Fibonacci number, taking N as user input.
| | | [Hanoi](./src/p3/Hanoi.java) | Solves the [Towers of Hanoi](https://en.wikipedia.org/wiki/Tower_of_Hanoi) problem, for a user specified number of disks.
| p4 | [Elementary Sorting](./src/p4) | [Sort](./src/p4/Sort.java) | Contains implementations of multiple sorting algorithms: selection, insertion, bogo, merge, enhanced merge, quick, and enhanced quick. <br> It implements the [Callable](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Callable.html) interface, so the call() method invokes any of the required sort methods on any specified array.
| | | [Timing](./src/p4/Timing.java) | Utility used in sort [TestRunner](./src/p4/TestRunner.java) to compute the nano time performance of a [Callable](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Callable.html) class object. 
| | | [TestRunner](./src/p4/TestRunner.java) | Test framework to compare performance of various types of sorts defined in the [Sort](./src/p4/Sort.java) class. Depending on the user's choice, <br> 1. It can run a timing analysis for sorting randomly generated arrays of different sizes. <br> 2. It can visualise the sorts for small sized arrays.
| | | [ElementarySortTest](./src/p4/ElementarySortTest.java) | Compares the performance of selection sort, insertion sort, and bogo sort. 
| p5 | [Advanced Sorting I](./src/p5) | [MergeSortTest](./src/p5/MergeSortTest.java) | Compares the performance of insertion sort, merge sort, and enhanced merge sort. 
| p6 | [Advanced Sorting II](./src/p6) | [QuickSortTest](./src/p6/QuickSortTest.java) | Compares the performance of merge sort, quick sort, and enhanced quick sort. 
| p7 | [Substring Search](./src/p7) | [BruteForce](./src/p7/BruteForce.java) | Brute force search for a pattern in text.
| | | [KMPSearch](./src/p7/KMPSearch.java) | Knuth-Morris-Pratt algorithm to search for a pattern in text.
| | | [SearchTest](./src/p7/SearchTest.java) | Compares the performance of brute force and KMP substring search algorithms, using data from input.txt.
| p8 | [Tries](./src/p8) | [Trie](./src/p8/Trie.java) | Builds a Trie data structure to help store and search for String keys.
| p9 | [Run Length Compression](./src/p9) | [RunLengthStrings](./src/p9/RunLengthStrings.java) | Compresses a String provided as a command line argument. <br> See class description for usage details.
| | | [CustomInput](./src/p9/CustomInput.java) | Generates a custom input file filled with ASCII null characters, suitable for binary run length encoding.
| | | 4runs.bin, <br> 4runsrle.bin, abra.txt, custom_input.txt, custom_input_rle.txt, q32x48.bin,<br> q32x48rle.bin,<br> q64x96.bin,<br> q64x96rle.bin | Data files for multiple experiments with run length compression. 
| a1| [Assignment: Huffman Compression](src/a1) | [Huffman](./src/a1/Huffman.java) | Compresses or decompresses files (provided as command line arguments) using the Huffman coding algorithm.
| | | genomeVirus.txt,<br> medTale.txt,<br> mobydick.txt,<br> q32x48.bin,<br> sample.txt | Data files for experiments with Huffman compression.
| | | genomeVirus_comp.txt, medTale_comp.txt, mobydick_comp.txt, q32x48_comp.bin, sample_comp.txt | Files after applying Huffman compression.
| | | genomeVirus_decomp.txt, medTale_decomp.txt, mobydick_decomp.txt, q32x48_decomp.bin, sample_decomp.txt | Files after decompressing files that were already compressed using Huffman algorithm. These files are identical to the original data files.
| | | q32x48_comp2.bin, sample_comp2.txt | Files obtained after compressing already compressed files q32x48_comp.bin and sample_comp.txt respectively.
| util| [Utilities](./src/util) | [BinaryDump](./src/util/BinaryDump.java), [BinaryIn](./src/util/BinaryIn.java), [BinaryOut](./src/util/BinaryOut.java), [BinaryStdIn](./src/util/BinaryStdIn.java), [BinaryStdOut](./src/util/BinaryStdOut.java), [HexDump](./src/util/HexDump.java), [In](./src/util/In.java), [MinPQ](./src/util/MinPQ.java), [RunLength](./src/util/RunLength.java), [StdIn](./src/util/StdIn.java), [StdOut](./src/util/StdOut.java) | Multiple utility programs written by Robert Sedgewick and Kevin Wayne, used throughout the repository, especially for [Run Length Compression](./src/p9) and the [Huffman Compression](src/a1) assignment. See class descriptions for usage details.

## Authors:
* [Rajit Banerjee](https://github.com/rajitbanerjee), 18202817
* Dr. Mark Matthews, lecturer  
Reference: Algorithms by Robert Sedgewick and Kevin Wayne.
