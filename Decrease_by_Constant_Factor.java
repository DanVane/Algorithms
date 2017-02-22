/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wangdan
 */
public class Decrease_by_Constant_Factor {

    private List<String> steps = new ArrayList<String>();

    private List<Integer> init_Coins(int n) {
        List<Integer> coins = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            coins.add(1);
        }
        int rc = (int) (Math.random() * n);
        coins.set(rc, 0);
        return coins;
    }

    private void weight_Coins(List<Integer> coins) {
        int first_split = (coins.size()+1)/3;
        int sec_split = 2*first_split;
        int sum_1 = 0;
        int sum_2 =0;
        for(int i=0;i<coins.size();i++){
            if(i<first_split){
                sum_1 += coins.get(i);
            }else if(i<sec_split){
                sum_2+=coins.get(i);
            }else{
                break;
            }
        }
        if(sum_1==sum_2){
            //第三段
            if(coins.size()-sec_split==1){
                steps.add(coins.toString()+" the third");
                return ;
            }
            String str = "Split "+coins.toString()+" to 3 segs("+first_split+","
                    +first_split+","+(coins.size()-sec_split)+"), choose the third";
            steps.add(str);
            List<Integer> sub_coins = new ArrayList<Integer>();
            for(int i=sec_split;i<coins.size();i++){
                sub_coins.add(coins.get(i));
            }
            weight_Coins(sub_coins);
        }else if(sum_1<sum_2){
            //第一段
            if(first_split==1){
                steps.add(coins.toString()+" the first");
                return;
            }
            String str = "Split "+coins.toString()+" to 3 segs("+first_split+","
                    +first_split+","+(coins.size()-sec_split)+"), choose the first";
            steps.add(str);
            List<Integer> sub_coins = new ArrayList<Integer>();
            for(int i=0;i<first_split;i++){
                sub_coins.add(coins.get(i));
            }
            weight_Coins(sub_coins);
        }else{
            // 第二段
            if(sec_split-first_split==1){
                steps.add(coins.toString()+" the second");
                return;
            }
            String str = "Split "+coins.toString()+" to 3 segs("+first_split+","
                    +first_split+","+(coins.size()-sec_split)+"), choose the second";
            steps.add(str);
            List<Integer> sub_coins = new ArrayList<Integer>();
            for(int i=first_split;i<sec_split;i++){
                sub_coins.add(coins.get(i));
            }
            weight_Coins(sub_coins);
        }
    }

    private void print_Steps() {
        for(int i=0;i<steps.size();i++){
            System.out.println(steps.get(i));
        }
    }

    public static void main(String[] args) {
        Decrease_by_Constant_Factor dcf = new Decrease_by_Constant_Factor();
        List<Integer> coins = new ArrayList<Integer>();
        coins = dcf.init_Coins(21);
        dcf.weight_Coins(coins);
        dcf.print_Steps();
    }

}
