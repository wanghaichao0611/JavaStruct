package 计数排序算法;

public class CountSort {
    public static final int[] ARRAY = {2, 5, 3, 0, 2, 3, 0, 3};
    public static final int[] ARRAY2 = {95, 93, 92, 94, 92, 93, 95, 90};

    //优化前
    private static int[] sort(int[] array) {
        if (array.length < 2) return array;
        //找出数组的最大值
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        //初始化一个计数数组且值为0
        int[] countArray = new int[max + 1];
        for (int i = 0; i < countArray.length; i++) {
            countArray[i] = 0;
        }
        //填充计数数组
        for (int temp : array) {
            countArray[temp]++;
        }
        int o_index = 0;//原数组下标
        int n_index = 0;//计数数组下标
        while (o_index < array.length) {
            //只要计数数组的下标不为0，就将计数数组的值从新写回原数组
            if (countArray[n_index] != 0) {
                array[o_index] = n_index;//计数数组下标对应元素组的值
                countArray[n_index]--;//计数数组的值要-1
                o_index++;
            } else {
                n_index++;//上一个索引的值为0后开始下一个
            }
        }
        return array;
    }

    //优化后
    private static int[] sort2(int[] array) {
        if (array.length < 2) return array;
        //找出数组中的最大值和最小值
        int min = array[0], max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //定义一个偏移量,即最小值前面0~min的范围,这里直接用一个负数来表示
        int bias = 0 - min;
        //初始化一个计数数组且值为0
        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < countArray.length; i++) {
            countArray[i] = 0;
        }
        for (int temp : array) {
            countArray[temp + bias]++;
        }
        //填充计数数组
        int o_index = 0;//原数组下标
        int n_index = 0;//计数数组下标
        while (o_index < array.length) {
            if (countArray[n_index] != 0) {
                array[o_index] = n_index - bias;
                countArray[n_index]--;
                o_index++;
            } else {
                n_index++;
            }
        }
        return array;
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + "  ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        print(ARRAY);
        System.out.println("============================================");
        print(sort(ARRAY));
        System.out.println("=================优化排序====================");
        print(ARRAY2);
        System.out.println("============================================");
        print(sort2(ARRAY2));
    }
}

