# 栈Stack
特性：先进后出  
声明一个顶层接口
```
public interface Stack {
    public void push(Object object);//向栈头尾添加元素

    public Object pop();//获取栈头元素，并且删除

    public Object peek();//获取栈头元素，不删除

    public Iterator iterator();//获取迭代器

    public int size();//集合大小
}
```
基本操作不论述