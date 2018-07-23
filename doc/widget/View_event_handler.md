##  click button which has both `onClick` and "onLongClick"? 
- onLongClick true，只发生onLongClick。 => true = 消费了事件。不再继续传递
- onLongClick false，同时发生onLongClick和onClick。