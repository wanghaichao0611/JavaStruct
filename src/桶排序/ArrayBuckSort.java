package 桶排序;


import java.util.Arrays;

//此方法浪费数组内存，直接开辟最大值区间
public class ArrayBuckSort {
    //1.一维数组显示桶排序（主要是下标）(与计数排序类似)
    public static void bucketSortArray(int[] num) {

        //1.遍历原始数组，找到数组中的最大值
        int max = num[0];
        for (int k : num) {
            if (k > max) {
                max = k;
            }
        }
        //2.创建一个下标为原始数组中最大值的桶数组,该桶数组的下标代表元素，该数组下标所对应的值代表这个值出现的次数
        int[] bucketArray = new int[max + 1];
        System.out.print(Arrays.toString(Arrays.stream(bucketArray).toArray()));
        System.out.println("");
        // 再次遍历原始数组，得到原数组中存在的各个元素，以及出现的次数
        for (int k : num) {
            bucketArray[k]++; //下标【0-21】累加从0到1等
            System.out.print(bucketArray[k]);
        }
        System.out.println("");
        //3.遍历桶数组,外层循环从桶的第一位开始（即下表为零）；内层循环遍历桶数组中下标为i的值出现的次数
        int index = 0;
        for (int i = 0; i < bucketArray.length; i++) {
            for (int j = 0; j < bucketArray[i]; j++) { //如果存在0<已经增加的数值则说明可以把下标去赋值
                num[index++] = i;
            }
        }
    }

    //2.二维矩阵方式实现相比于上个函数，可以手动分配每个桶的范围，依旧浪费存储空间（主要存储的是真实数据且也必须大于0）
    public static void bucketSortTwoArray(int[] num, int bucketRange) {
        //1.先取最大值与最小值
        int max = num[0];
        int min = num[0];
        for (int number : num) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }
        //2.声明区间范围的桶
        //桶的数量
        int bucketSection = (max - min) / bucketRange + 1;
        int[][] bucketTemp = new int[num.length][bucketSection];
        for (int i = 0; i < num.length; i++) {
            bucketTemp[i][(num[i] - min) / bucketRange] = num[i];
        }
        //正常打印二维矩阵数据
        System.out.println("正常打印二维矩阵数据: ");
        for (int i = 0; i < bucketTemp.length; i++) {
            for (int j = 0; j < bucketTemp[i].length; j++) {
                System.out.print(bucketTemp[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("--------------");
        System.out.println("先列后行二维矩阵数据: ");
        int index = 0;
        for (int h = 0; h < bucketTemp.length; h++) {
            for (int k = 0; k < bucketTemp[h].length; k++) {
                //内部行列打印转换
                System.out.print(bucketTemp[k][h] + " ");
                if (bucketTemp[k][h] != 0) {
                    num[index++] = bucketTemp[k][h];
                }
            }
            System.out.println("");
        }

    }

    /**
     * 最优方案解决桶排序的问题
     *
     * @param bucketSize 作为每个桶所能放置多少个不同数值,即数值的类型
     *                   例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，
     *                   但是容量不限，即可以存放100个3
     */
    public static void bucketSortOptimize(int[] num, int bucketSize) {
        //1.先取最大值与最小值
        int max = num[0];
        int min = num[0];
        for (int number : num) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }
        //2.桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        int[] opArray = new int[bucketCount];
        int[] opArrayInner = new int[bucketCount];

    }

    public static void main(String[] args) {
        //数字范围小还可以使用，浪费存储空间
        int[] numArray = new int[]{22, 5, 6, 8, 5, 2, 9, 6};
        bucketSortArray(numArray);
        System.out.println(Arrays.toString(numArray));
        //差别过大就很夸张
        int[] numArrayTemp = new int[]{20, 50, 60, 80, 50, 20, 90, 60};
        bucketSortArray(numArrayTemp);
        System.out.println(Arrays.toString(numArrayTemp));
        //不包含O（>0的正整数），二维数组实现数据区间的方式，也浪费存储空间
        int[] numTwoArray = new int[]{20, 50, 60, 80, 50, 20, 90, 60};
        bucketSortTwoArray(numTwoArray, 10);
        System.out.println(Arrays.toString(numTwoArray));
        //最终优化方案
//        int[] numOp = new int[]{20, 50, 60, 80, 50, 20, 90, 60};
//        bucketSortOptimize(numOp, 10);
//        System.out.println(Arrays.toString(numOp));
    }
}

