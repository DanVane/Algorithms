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
public class Divide_and_Conquer {

    private int[][] checkerboard = null;
    private int flag = 2;

    private void initDC(int n) {
        checkerboard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                checkerboard[i][j] = 1;
            }
        }
        int c_short = (int)( Math.random() * n);
        checkerboard[c_short][c_short] = 0;
    }

    private void startDC(int[] start) {
        
        int step = start[2];
        if (step == 2) {
            if (checkerboard[start[0]][start[1]] == 1) {
                checkerboard[start[0]][start[1]] = flag;
            }
            if (checkerboard[start[0] + 1][start[1]] == 1) {
                checkerboard[start[0] + 1][start[1]] = flag;
            }
            if (checkerboard[start[0]][start[1] + 1] == 1) {
                checkerboard[start[0]][start[1] + 1] = flag;
            }
            if (checkerboard[start[0] + 1][start[1] + 1] == 1) {
                checkerboard[start[0] + 1][start[1] + 1] = flag;
            }
            flag++;
        } else {
            paint_checkerboard(start);
            List<int[]> start_numbers = new ArrayList<int[]>();
            start_numbers.add(new int[]{start[0], start[1], step/2});
            start_numbers.add(new int[]{start[0] + step / 2, start[1], step/2});
            start_numbers.add(new int[]{start[0], start[1] + step / 2, step/2});
            start_numbers.add(new int[]{start[0] + step / 2, start[1] + step / 2, step/2});
            for (int i = 0; i < start_numbers.size(); i++) {
                startDC(start_numbers.get(i));
            }
        }
    }

    private void paint_checkerboard(int[] start) {
        int sub_step = start[2] / 2;
        int[] middle_positions = new int[]{start[0] + sub_step - 1, start[1] + sub_step - 1, start[0] + sub_step - 1, start[1] + sub_step,
            start[0] + sub_step, start[1] + sub_step - 1, start[0] + sub_step, start[1] + sub_step};
        for (int i = 0; i < middle_positions.length; i += 2) {
            if (checkerboard[middle_positions[i]][middle_positions[i + 1]] == 1) {
                checkerboard[middle_positions[i]][middle_positions[i + 1]] = flag;
            }
        }
        boolean ok = true;
        int[] position = new int[2];
        int[] start_positions = new int[]{start[0], start[1], start[0], start[1] + sub_step, 
            start[0] + sub_step, start[1], start[0] + sub_step, start[1] + sub_step};
        for (int i = 0; i < start_positions.length; i += 2) {
            ok = true;
            int[] start_position = new int[]{start_positions[i], start_positions[i + 1]};
            for (int j = 0; j < sub_step; j++) {
                for (int k = 0; k < sub_step; k++) {
                    if (checkerboard[start_position[0] + j][start_position[1] + k] == flag) {
                        position[0] = start_position[0] + j;
                        position[1] = start_position[1] + k;
                    } 
                    if ((1<checkerboard[start_position[0] + j][start_position[1] + k] &&
                            checkerboard[start_position[0] + j][start_position[1] + k]<flag ) ||
                            checkerboard[start_position[0] + j][start_position[1] + k]==0) {
                        ok = false;
                    }
                }
            }
            if (!ok) {
                checkerboard[position[0]][position[1]] = 1;
            }
        }
        flag++;
        
    }

    private void printDC() {
        for (int i = 0; i < checkerboard.length; i++) {
            for (int j = 0; j < checkerboard.length; j++) {
                System.out.print(checkerboard[i][j]+",");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Divide_and_Conquer dc = new Divide_and_Conquer();
        dc.initDC(8);
        dc.printDC();
        dc.startDC(new int[]{0, 0,8});
        dc.printDC();
    }

}
