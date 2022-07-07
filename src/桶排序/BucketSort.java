package 桶排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//此方法设置区间并且取最大值与最小值的差，对于开辟最大值是优化，开辟的区间一般取左闭右开
public class BucketSort {
    public static final int[] ARRAY = {35, 23, 48, 9, 16, 24, 5, 11, 32, 17};

    /**
     * @param bucketSize 作为每个桶所能放置多少个不同数值,即数值的类型
     *                   例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，
     *                   但是容量不限，即可以存放100个3
     */
    public static List<Integer> sort(List<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2) {
            return array;
        }
        int max = array.get(0);
        int min = array.get(0);
        // 找到最大值最小值
        for (Integer integer : array) {
            if (integer > max) {
                max = integer;
            }
            if (integer < min) {
                min = integer;
            }
        }
        //获取桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        //构建桶，初始化
        List<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        List<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<>());
        }
        //将原数组的数据分配到桶中
        for (Integer integer : array) {
            //区间范围
            bucketArr.get((integer - min) / bucketSize).add(integer);
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) {
                resultArr.addAll(bucketArr.get(i));
            } else {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                //对桶中的数据再次用桶进行排序
                List<Integer> temp = sort(bucketArr.get(i), bucketSize);
                resultArr.addAll(temp);
            }
        }
        return resultArr;
    }

    public static void print(List<Integer> array) {
        for (int i : array) {
            System.out.print(i + "  ");
        }
    }

    public static void main(String[] args) {
        System.out.println("初始顺序:");
        print(Arrays.stream(ARRAY).boxed().collect(Collectors.toList()));
        System.out.println("");
        System.out.println("排序顺序:");
        print(sort(Arrays.stream(ARRAY).boxed().collect(Collectors.toList()), 2));
    }
}
