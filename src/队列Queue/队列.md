# 队列Queue
队列特性：先进先出  
声明一个队列顶层接口
```
public interface Queue {
public void offer(Object object);//向队列尾添加元素

    public Object poll();//获取队列头元素，并且删除

    public Object peek();//获得队列元素，不删除

    public Iterator iterator();//获取迭代器

    public int size();//集合大小
}
```
基本操作不论述