package 红黑树;

public class RedBlackTree<T extends Comparable<T>> {

    private RedBlackNode<T> root; //根节点
    private static final boolean RED = false; //定义红黑树标志
    private static final boolean BLACK = true;

    public RedBlackTree() {
        root = null;
    }

    public RedBlackNode<T> getParentNode(RedBlackNode<T> node) { //获得父节点
        return node != null ? node.parentNode : null;
    }

    public void setParentNode(RedBlackNode<T> node, RedBlackNode<T> parentNode) { //设置父节点
        if (node != null) {
            node.parentNode = parentNode;
        }
    }

    public boolean getColor(RedBlackNode<T> node) { //获得节点的颜色
        return node != null ? node.color : BLACK;
    }

    public boolean isRed(RedBlackNode<T> node) { //判断节点的颜色
        return node != null && node.color == RED;
    }

    public boolean isBlack(RedBlackNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RedBlackNode<T> node) { //设置节点的颜色
        if (node != null) {
            node.color = RED;
        }
    }

    public void setBlack(RedBlackNode<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    public void setColor(RedBlackNode<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    /***************** 前序遍历红黑树 *********************/
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(RedBlackNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.data + " ");
            preOrder(tree.leftNode);
            preOrder(tree.rightNode);
        }
    }

    /***************** 中序遍历红黑树 *********************/
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(RedBlackNode<T> tree) {
        if (tree != null) {
            inOrder(tree.leftNode);
            System.out.print(tree.data + " ");
            inOrder(tree.rightNode);
        }
    }

    /***************** 后序遍历红黑树 *********************/
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(RedBlackNode<T> tree) {
        if (tree != null) {
            postOrder(tree.leftNode);
            postOrder(tree.rightNode);
            System.out.print(tree.data + " ");
        }
    }

    /**************** 查找红黑树中键值为key的节点 ***************/
    public RedBlackNode<T> search(T key) {
        return search(root, key);
//		return search2(root, key); //使用递归的方法，本质一样的
    }

    private RedBlackNode<T> search(RedBlackNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.data);
            if (cmp < 0) {
                x = x.leftNode;
            } else if (cmp > 0) {
                x = x.rightNode;
            } else {
                return x;
            }
        }
        return x;
    }

    //使用递归
    private RedBlackNode<T> search2(RedBlackNode<T> x, T key) {
        if (x == null) {
            return x;
        }
        int cmp = key.compareTo(x.data);
        if (cmp < 0) {
            return search2(x.leftNode, key);
        } else if (cmp > 0) {
            return search2(x.rightNode, key);
        } else {
            return x;
        }
    }

    /**************** 查找最小节点的值  **********************/
    public T minValue() {
        RedBlackNode<T> node = minNode(root);
        if (node != null) {
            return node.data;
        }
        return null;
    }

    private RedBlackNode<T> minNode(RedBlackNode<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.leftNode != null) {
            tree = tree.leftNode;
        }
        return tree;
    }

    /******************** 查找最大节点的值 *******************/
    public T maxValue() {
        RedBlackNode<T> node = maxNode(root);
        if (node != null) {
            return node.data;
        }
        return null;
    }

    private RedBlackNode<T> maxNode(RedBlackNode<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.rightNode != null) {
            tree = tree.rightNode;
        }
        return tree;
    }

    /********* 查找节点x的后继节点,即大于节点x的最小节点 ***********/
    public RedBlackNode<T> successor(RedBlackNode<T> x) {
        //如果x有右子节点，那么后继节点为“以右子节点为根的子树的最小节点”
        if (x.rightNode != null) {
            return minNode(x.rightNode);
        }
        //如果x没有右子节点，会出现以下两种情况：
        //1. x是其父节点的左子节点，则x的后继节点为它的父节点
        //2. x是其父节点的右子节点，则先查找x的父节点p，然后对p再次进行这两个条件的判断
        RedBlackNode<T> p = x.parentNode;
        while ((p != null) && (x == p.rightNode)) { //对应情况2
            x = p;
            p = x.parentNode;
        }
        return p; //对应情况1

    }

    /********* 查找节点x的前驱节点，即小于节点x的最大节点 ************/
    public RedBlackNode<T> predecessor(RedBlackNode<T> x) {
        //如果x有左子节点，那么前驱结点为“左子节点为根的子树的最大节点”
        if (x.leftNode != null) {
            return maxNode(x.leftNode);
        }
        //如果x没有左子节点，会出现以下两种情况：
        //1. x是其父节点的右子节点，则x的前驱节点是它的父节点
        //2. x是其父节点的左子节点，则先查找x的父节点p，然后对p再次进行这两个条件的判断
        RedBlackNode<T> p = x.parentNode;
        while ((p != null) && (x == p.leftNode)) { //对应情况2
            x = p;
            p = x.parentNode;
        }
        return p; //对应情况1
    }

    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RedBlackNode<T> x) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RedBlackNode<T> y = x.rightNode;
        x.rightNode = y.leftNode;

        if (y.leftNode != null)
            y.leftNode.parentNode = x;

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parentNode = x.parentNode;

        if (x.parentNode == null) {
            this.root = y; //如果x的父节点为空，则将y设为父节点
        } else {
            if (x == x.parentNode.leftNode) //如果x是左子节点
                x.parentNode.leftNode = y; //则也将y设为左子节点
            else
                x.parentNode.rightNode = y;//否则将y设为右子节点
        }

        //3. 将y的左子节点设为x，将x的父节点设为y
        y.leftNode = x;
        x.parentNode = y;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RedBlackNode<T> y) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RedBlackNode<T> x = y.leftNode;
        y.leftNode = x.rightNode;

        if (x.rightNode != null)
            x.rightNode.parentNode = y;

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        x.parentNode = y.parentNode;

        if (y.parentNode == null) {
            this.root = x; //如果x的父节点为空，则将y设为父节点
        } else {
            if (y == y.parentNode.rightNode) //如果x是左子节点
                y.parentNode.rightNode = x; //则也将y设为左子节点
            else
                y.parentNode.leftNode = x;//否则将y设为右子节点
        }

        //3. 将y的左子节点设为x，将x的父节点设为y
        x.rightNode = y;
        y.parentNode = x;
    }

    /*********************** 向红黑树中插入节点 **********************/
    public void insert(T key) {
        insert(new RedBlackNode<T>(null, null, null, key, RED));
    }

    //将节点插入到红黑树中，这个过程与二叉搜索树是一样的
    private void insert(RedBlackNode<T> node) {
        RedBlackNode<T> current = null; //表示最后node的父节点
        RedBlackNode<T> x = this.root; //用来向下搜索用的

        //1. 找到插入的位置
        while (x != null) {
            current = x;
            x = node.data.compareTo(x.data) < 0 ? x.leftNode : x.rightNode;
        }
        node.parentNode = current; //找到了位置，将当前current作为node的父节点

        //2. 接下来判断node是插在左子节点还是右子节点
        if (current != null) {
            int cmp = node.data.compareTo(current.data);
            if (cmp < 0) {
                current.leftNode = node;
            } else {
                current.rightNode = node;
            }
        } else {
            this.root = node;
        }

        //3. 将它重新修整为一颗红黑树
        insertFixUp(node);
    }

    private void insertFixUp(RedBlackNode<T> node) {
        RedBlackNode<T> parent, gparent; //定义父节点和祖父节点

        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while (((parent = getParentNode(node)) != null) && isRed(parent)) {
            gparent = getParentNode(parent);//获得祖父节点

            //若父节点是祖父节点的左子节点，下面else与其相反
            if (parent == gparent.leftNode) {
                RedBlackNode<T> uncle = gparent.rightNode; //获得叔叔节点

                //case1: 叔叔节点也是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent); //把父节点和叔叔节点涂黑
                    setBlack(uncle);
                    setRed(gparent); //把祖父节点涂红
                    node = gparent; //将位置放到祖父节点处
                    continue; //继续while，重新判断
                }

                //case2: 叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.rightNode) {
                    leftRotate(parent); //从父节点处左旋
                    RedBlackNode<T> tmp = parent; //然后将父节点和自己调换一下，为下面右旋做准备
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色，且当前节点是左子节点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else { //若父节点是祖父节点的右子节点,与上面的完全相反，本质一样的
                RedBlackNode<T> uncle = gparent.leftNode;

                //case1: 叔叔节点也是红色
                if (uncle != null & isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.leftNode) {
                    rightRotate(parent);
                    RedBlackNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        //将根节点设置为黑色
        setBlack(this.root);
    }

    /*********************** 删除红黑树中的节点 **********************/
    public void remove(T key) {
        RedBlackNode<T> node;
        if ((node = search(root, key)) != null) {
            remove(node);
        }
    }

    private void remove(RedBlackNode<T> node) {
        RedBlackNode<T> child, parent;
        boolean color;

        //1. 被删除的节点“左右子节点都不为空”的情况
        if ((node.leftNode != null) && (node.rightNode != null)) {
            //先找到被删除节点的后继节点，用它来取代被删除节点的位置
            RedBlackNode<T> replace = node;
            //  1). 获取后继节点
            replace = replace.rightNode;
            while (replace.leftNode != null)
                replace = replace.leftNode;

            //  2). 处理“后继节点”和“被删除节点的父节点”之间的关系
            if (getParentNode(node) != null) { //要删除的节点不是根节点
                if (node == getParentNode(node).leftNode) {
                    getParentNode(node).leftNode = replace;
                } else {
                    getParentNode(node).rightNode = replace;
                }
            } else { //否则
                this.root = replace;
            }

            //  3). 处理“后继节点的子节点”和“被删除节点的子节点”之间的关系
            child = replace.rightNode; //后继节点肯定不存在左子节点！
            parent = getParentNode(replace);
            color = getColor(replace);//保存后继节点的颜色
            if (parent == node) { //后继节点是被删除节点的子节点
                parent = replace;
            } else { //否则
                if (child != null)
                    setParentNode(child, parent);
                parent.leftNode = child;
                replace.rightNode = node.rightNode;
                setParentNode(node.rightNode, replace);
            }
            replace.parentNode = node.parentNode;
            replace.color = node.color; //保持原来位置的颜色
            replace.leftNode = node.leftNode;
            node.leftNode.parentNode = replace;

            if (color == BLACK) { //4. 如果移走的后继节点颜色是黑色，重新修整红黑树
                removeFixUp(child, parent);//将后继节点的child和parent传进去
            }
            node = null;
        }
    }

    //node表示待修正的节点，即后继节点的子节点（因为后继节点被挪到删除节点的位置去了）
    private void removeFixUp(RedBlackNode<T> node, RedBlackNode<T> parent) {
        RedBlackNode<T> other;

        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.leftNode == node) { //node是左子节点，下面else与这里的刚好相反
                other = parent.rightNode; //node的兄弟节点
                if (isRed(other)) { //case1: node的兄弟节点other是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.rightNode;
                }

                //case2: node的兄弟节点other是黑色的，且other的两个子节点也都是黑色的
                if ((other.leftNode == null || isBlack(other.leftNode)) &&
                        (other.rightNode == null || isBlack(other.rightNode))) {
                    setRed(other);
                    node = parent;
                    parent = getParentNode(node);
                } else {
                    //case3: node的兄弟节点other是黑色的，且other的左子节点是红色，右子节点是黑色
                    if (other.rightNode == null || isBlack(other.rightNode)) {
                        setBlack(other.leftNode);
                        setRed(other);
                        rightRotate(other);
                        other = parent.rightNode;
                    }

                    //case4: node的兄弟节点other是黑色的，且other的右子节点是红色，左子节点任意颜色
                    setColor(other, getColor(parent));
                    setBlack(parent);
                    setBlack(other.rightNode);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else { //与上面的对称
                other = parent.leftNode;

                if (isRed(other)) {
                    // Case 1: node的兄弟other是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.leftNode;
                }

                if ((other.leftNode == null || isBlack(other.leftNode)) &&
                        (other.rightNode == null || isBlack(other.rightNode))) {
                    // Case 2: node的兄弟other是黑色，且other的俩个子节点都是黑色的
                    setRed(other);
                    node = parent;
                    parent = getParentNode(node);
                } else {

                    if (other.leftNode == null || isBlack(other.leftNode)) {
                        // Case 3: node的兄弟other是黑色的，并且other的左子节点是红色，右子节点为黑色。
                        setBlack(other.rightNode);
                        setRed(other);
                        leftRotate(other);
                        other = parent.leftNode;
                    }

                    // Case 4: node的兄弟other是黑色的；并且other的左子节点是红色的，右子节点任意颜色
                    setColor(other, getColor(parent));
                    setBlack(parent);
                    setBlack(other.leftNode);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node != null)
            setBlack(node);
    }

    /****************** 销毁红黑树 *********************/
    public void clear() {
        destroy(root);
        root = null;
    }

    private void destroy(RedBlackNode<T> tree) {
        if (tree == null)
            return;
        if (tree.leftNode != null) {
            destroy(tree.leftNode);
        }
        if (tree.rightNode != null) {
            destroy(tree.rightNode);
        }
        tree = null;
    }

    /******************* 打印红黑树 *********************/
    public void print() {
        if (root != null) {
            print(root, root.data, 0);
        }
    }

    /*
     * key---节点的键值
     * direction--- 0:表示该节点是根节点
     *              1:表示该节点是它的父节点的左子节点
     *              2:表示该节点是它的父节点的右子节点
     */
    private void print(RedBlackNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (0 == direction) {
                System.out.println("根节点是" + tree.data);
            } else {
                System.out.println("子节点是" +
                        tree.data + (isRed(tree) ? "R" : "b") + key + (direction == 1 ? "right" : "left"));
            }
            print(tree.leftNode, tree.data, -1);
            print(tree.rightNode, tree.data, 1);
        }
    }
}
