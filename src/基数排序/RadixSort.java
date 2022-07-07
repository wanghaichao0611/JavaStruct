package 基数排序;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {72,538,627,56,332,453,312,4};
        radixSort(array);
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] array){
        //初始化桶
        int[][] bucket = new int[10][array.length];
        //初始化一个数组用来存放指向每个桶中最大的元素的指针
        int[] bucketIndex = new int[10];
        //获取数组中最大的元素
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }
        //获取最大元素的长度
        int maxLength = (max+"").length();
        //将数组中的元素按照指定规则放入到桶中
        for (int i = 0; i < maxLength; i++) {
            int div = (int)Math.pow(10,i);
            for (int j = 0; j < array.length; j++) {
                //获取元素的个位、十位、百位、千位...
                int element = (array[j]/div)%10;
                bucket[element][bucketIndex[element]] = array[j];
                bucketIndex[element]++;
            }
            int index = 0;
            for (int k = 0; k < bucketIndex.length; k++) {
                //桶中有元素
                if (bucketIndex[k] != 0){
                    //取出桶中元素
                    for (int j = 0; j < bucketIndex[k]; j++) {
                        array[index++] = bucket[k][j];
                    }
                }
                bucketIndex[k] = 0;
            }
            System.out.println("第"+(i+1)+"次排序的结果为"+ Arrays.toString(array));
        }
    }
}

