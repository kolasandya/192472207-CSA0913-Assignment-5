# Task A2 - Don't Read What You Don't Need

## A2.1 - Predict the Cost
- Reading a 50 MB attendance log 1 byte at a time using `FileInputStream.read()` results in **50,000,000 individual system calls** to the operating system kernel.
- **Why it is slow:** Each system call incurs context-switching overhead (switching from user space to kernel space), making I/O bound by OS trap overhead rather than disk read throughput.

## A2.2 - Redesign Using Buffering
- Wrapping `FileInputStream` in a `BufferedReader` or `BufferedInputStream` reads blocks of data (typically 8KB) into memory in a single system call.
- This reduces OS calls from ~50 million down to ~6,400 calls, improving execution speed by orders of magnitude.

## A2.3 - When Buffering Does Not Help
- Buffering provides little or no benefit when reading small, randomly scattered files or when every byte read is immediately followed by a long processing delay or synchronous network write where I/O is not the bottleneck.
