# StringBuffer

- thread-safe  
- mutable
- A string buffer is like a String, but can be modified
- String buffers are safe for use by multiple threads.
- it contains some particular sequence of characters, but the length and content of the sequence can be changed through certain method calls.
- Every string buffer has a capacity. If the internal buffer overflows, it is automatically made larger.
- The principal operations on a StringBuffer are the append and insert methods,
- Unless otherwise noted, passing a null argument to a constructor or method in this class will cause a NullPointerException to be thrown.

## References:
- StringBuffer https://developer.android.com/reference/java/lang/StringBuffer