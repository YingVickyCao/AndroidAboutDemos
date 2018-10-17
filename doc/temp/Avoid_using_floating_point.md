# Avoid using floating-point
## float VS integer
Choice?    
- float -> integer  

Why?     
- floating-point is about 2x slower than integer  

## float VS  double

Choice?    
- float -> double   (TODO)

Why?
- In speed terms, there's no difference between float and double on the more modern hardware. Space-wise, double is 2x larger.   
As with desktop machines, assuming space isn't an issue, you should prefer double to float.    
=>   
In speed, float = double.  
In space,  float  = 1/2 double. If space isn't an issue, use double.  
- float型数据一律转换为double型数据，为自动类型转换。 

## References:
- https://developer.android.com/training/articles/perf-tips