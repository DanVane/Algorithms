/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author Wangdan
 */
public class Exhaustivity {

    private void exhaustivity_algo(int max, int min, int size) {
        if (max - min + 1 != size * size) {
            System.out.println("not correct numbers!!!");
        }

        String str = "";
        int[] res = new int[size * size];

        /*
            把每个排序看做一个自然数，则所有排列对应的数可按从小到大的顺序排列，
            从当前的排列产生下一个排列时则必然造成某一位置上的数字变大，这一位置
            显然应该尽量靠右，并且在它左边位置上的数字保持不变。这就意味着这一位置
            变成的数字来自于它的右边，并且变大的幅度要尽可能小，也就是说在它右边如
            有几个数同时比它大，应该用其中最小的来代替它。由于这一位置是满足上述条件
            的最右边的一位，所以在它右边的所有数字按逆序排列，即在这些数字的右边没有
            一个大于它的数.程序中先从右至左找到第一个位置,该位置上的数比它右边的数小，
            这个位置就是所要找的满足上述条件的位置, 然后再从右到左找到第一个比该位置
            上的数字大的数字所在的位置,将这两个位置上的数字交换,再将该位置右边的所有
            元素颠倒过来,即将它们按从小到大的顺序排列,就得到了下一个排列. 
         */
        for (int i = 0; i < res.length; i++) {
            res[i] = min++;
        }
        do {
            if (res.length == 0) {
                break;
            }
            str += judge_Sum(res, size);
            //print_Res(res);
            res = get_Res(res);
        } while (true);

        if (str.length() == 0) {
            str = "no solution!!!";
        }
        System.out.println(str);
    }

    private void print_Res(int[] res) {
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
        System.out.println("");
    }

    private int[] get_Res(int[] res) {
        // if no_change, return null;
        int l_flag = 0;
        int r_flag = res.length - 1;
        for (; r_flag > 0; r_flag--) {  //从右向左，寻找要交换的位置1
            if (res[r_flag] > res[r_flag - 1]) {
                break;
            }
        }
        if (r_flag == 0) {
            res = new int[0];
            return res;
        }
        r_flag--;
        for (l_flag = res.length - 1; l_flag > r_flag; l_flag--) {    //在位置1右边，从右向左，寻找要交换的位置2
            if (res[l_flag] > res[r_flag]) {
                break;
            }
        }
        //交换位置1和位置2
        res = swap(res, r_flag, l_flag);
        //把位置1后边的所有位反位排序
        for (int i = r_flag + 1, j = res.length - 1; i < j; i++, j--) {
            res = swap(res, i, j);
        }
        return res;
    }

    private int[] swap(int[] res, int i, int j) {
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
        return res;
    }

    private String judge_Sum(int[] res, int size) {
        String str = "";
        int[] sum = new int[size * 2 + 2];
        int[][] solu = new int[size][size];
        // 判断，打印结果
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                solu[j][k] = res[j * size + k];
            }
        }
        sum[2 * size] = 0;
        sum[2 * size + 1] = 0;
        for (int j = 1; j <= size; j++) {
            sum[j - 1] = 0;
            sum[size + j - 1] = 0;
            sum[2 * size] += solu[j - 1][j - 1];
            sum[2 * size + 1] += solu[j - 1][size - j];
            for (int k = 0; k < size; k++) {
                sum[j - 1] += solu[j - 1][k];
                sum[size + j - 1] += solu[k][j - 1];
            }
        }
        for (int j = 1; j < sum.length; j++) {
            if (sum[j - 1] != sum[j]) {
                return "";
            }
        }
        str += "Solution: \n";
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                str += solu[j][k] + "\t";
            }
            str += "\n";
        }

        return str;
    }

    public static void main(String[] args) {
        new Exhaustivity().exhaustivity_algo(9, 1, 3);
    }

}
