package 红黑树;


public class TestRedBlackTree {

    private static final int[] a = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    private static final boolean mDebugInsert = true;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = true;    // "删除"动作的检测开关(false，关闭；true，打开)

    public static void main(String[] args) {

        int iLen = a.length;
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();

        System.out.println("== 原始数据: ");
        for (int j : a) {
            System.out.println("data为: " + j);
        }

        for (int j : a) {
            tree.insert(j);
            // 设置mDebugInsert=true,测试"添加函数"
            if (mDebugInsert) {
                System.out.println("添加节点: " + j);
                System.out.println("树的详细信息:");
                tree.print();
            }
        }

        System.out.println("前序遍历: ");
        tree.preOrder();

        System.out.println("中序遍历: ");
        tree.inOrder();

        System.out.println("后序遍历: ");
        tree.postOrder();

        System.out.println("最小值: " + tree.minValue());
        System.out.println("最大值: " + tree.maxValue());
        System.out.println("树的详细信息: ");
        tree.print();

        // 设置mDebugDelete=true,测试"删除函数"
        if (mDebugDelete) {
            for (int j : a) {
                tree.remove(j);

                System.out.println("删除节点: " + j);
                System.out.println("树的详细信息: ");
                tree.print();
            }
        }
    }

}