# 单向循环链表
## 线性表的一种链式存储结构，由节点单向串连起来,并且首位相接形成一个环状，每个节点由数据data和指针next组成
![img.png](SingleCricleLink.png)  
循环在java中一个重要的区别是声明下个节点为尾节点的时候需要把尾节点的后指针指向头节点  
new Node(data,head);