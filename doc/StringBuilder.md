# StringBuilder

- mutable
- a single thread
- no synchronization
- faster
- API compatible with StringBuffer
- This class is designed for use as a drop-in replacement for StringBuffer in places where the string buffer was being used by a single thread (as is generally the case).
- append and insert methods
- Every string buffer has a capacity. If the internal buffer overflows, it is automatically made larger.
- Unless otherwise noted, passing a null argument to a constructor or method in this class will cause a NullPointerException to be thrown.

## Referebces:
- https://developer.android.com/reference/java/lang/StringBuilder