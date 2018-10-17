# SparseArray
## `SparseArray.java` - `insert()`：     
```
public void put(int key, E value) {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, key); //二分查找.

        if (i >= 0) {  //如果当前这个i在数组中存在,那么表示插入了相同的key值,只需要将value的值进行覆盖..
            mValues[i] = value;
        } else {  //如果数组内部不存在的话,那么返回的数值必然是负数.
            i = ~i;  //因此需要取i的相反数.
            //i值小于mSize表示在这之前. mKey和mValue数组已经被申请了空间.只是键值被删除了.那么当再次保存新的值的时候.不需要额外的开辟新的内存空间.直接对数组进行赋值即可.
            if (i < mSize && mValues[i] == DELETED) {
                mKeys[i] = key;
                mValues[i] = value;
                return;
            }
            //当需要的空间要超出,但是mKey中存在无用的数值,那么需要调用gc()函数.
            if (mGarbage && mSize >= mKeys.length) {
                gc();
                
                // Search again because indices may have changed.
                i = ~ContainerHelpers.binarySearch(mKeys, mSize, key);
            }
            //如果需要的空间大于了原来申请的控件,那么需要为key和value数组开辟新的空间.
            if (mSize >= mKeys.length) {
                int n = ArrayUtils.idealIntArraySize(mSize + 1);
                //定义了一个新的key和value数组.需要大于mSize
                int[] nkeys = new int[n];
                Object[] nvalues = new Object[n];

                // Log.e("SparseArray", "grow " + mKeys.length + " to " + n);
                //对数组进行赋值也就是copy操作.将原来的mKey数组和mValue数组的值赋给新开辟的空间的数组.目的是为了添加新的键值对.
                System.arraycopy(mKeys, 0, nkeys, 0, mKeys.length);
                System.arraycopy(mValues, 0, nvalues, 0, mValues.length);
                //将数组赋值..这里只是将数组的大小进行扩大..放入键值对的操作不在这里完成.
                mKeys = nkeys;
                mValues = nvalues;
            }
            //如果i的值没有超过mSize的值.只需要扩大mKey的长度即可.
            if (mSize - i != 0) {
                // Log.e("SparseArray", "move " + (mSize - i));
                System.arraycopy(mKeys, i, mKeys, i + 1, mSize - i);
                System.arraycopy(mValues, i, mValues, i + 1, mSize - i);
            }
            //这里是用来完成放入操作的过程.
            mKeys[i] = key;
            mValues[i] = value;
            mSize++;
        }
    }
```