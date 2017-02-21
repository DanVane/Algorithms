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
public class Backtrack {

    private List<CState> state_ancestors = new ArrayList<CState>();
    private List<CState> state_successful = new ArrayList<CState>();

    private void backtracking_argo(CState c_state) {
        int already_queen = c_state.get_already_state();
        boolean success_flag = true;
        int n = c_state.get_state().length;
        save_current_state(c_state); //当前状态压栈
        CState temp_state = c_state.clone();
        for (int i = 0; i < n; i++) { //第already_queen行，第i列
            temp_state = c_state.clone();
            temp_state.set_c_state(already_queen, i);
            if (judge_reasonable(temp_state)) { //不冲突
                if (temp_state.get_already_state() == temp_state.get_state().length) { //解节点
                    state_successful.add(temp_state);
                    //寻找下一解
                    continue;
                }// 放置下一个皇后
                backtracking_argo((CState) temp_state.clone());
            } else if (i == n - 1) {    //无望节点，循环完毕，父节点无用；若循环未结束，则寻找下一位置
                success_flag = false;
            }
        }
        if (!success_flag) { //栈顶节点无用，出栈
            if (state_ancestors.size() > 0) {
                state_ancestors.remove(state_ancestors.size() - 1);
            }
        }
    }

    private void print_successful_state() {

        if (state_successful.size() == 0) {
            System.out.println("no solution");
            return;
        }
        System.out.println("There are " + state_successful.size() + " solutions");
        for (CState s : state_successful) {
            System.out.println("the solution:");
            s.print_itself();
        }
    }

    private boolean judge_reasonable(CState c_state) {
        return c_state.judge_reasonable();
    }

    private void save_current_state(CState c_state) {
        state_ancestors.add(c_state);
    }

    public static void main(String[] args) {
        int n = 4;
        CState c_state = new CState(n);
        Backtrack back = new Backtrack();
        back.backtracking_argo(c_state);
        back.print_successful_state();
    }

}

class CState {

    private int[][] c_state = null;

    public CState() {

    }

    public CState(int n) {
        c_state = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c_state[i][j] = 0;
            }
        }
    }

    public CState(int[][] state) {
        c_state = state;
    }

    public void set_c_state(int i, int j) {
        c_state[i][j] = 1;
    }

    public int[][] get_state() {
        return c_state;
    }

    public int get_already_state() {
        int s = 0;
        for (int i = 0; i < c_state.length; i++) {
            for (int j = 0; j < c_state.length; j++) {
                s += c_state[i][j];
            }
        }
        return s;
    }

    public boolean judge_reasonable() {
        //行
        for (int i = 0; i < c_state.length; i++) {
            int s = 0;
            for (int j = 0; j < c_state.length; j++) {
                s += c_state[i][j];
                if (s > 1) {
                    return false;
                }
            }
        }

        //列
        for (int i = 0; i < c_state.length; i++) {
            int s = 0;
            for (int j = 0; j < c_state.length; j++) {
                s += c_state[j][i];
                if (s > 1) {
                    return false;
                }
            }
        }
        //上三角对角线
        int s = 0;
        for (int step = 0; step <= c_state.length - 2; step++) {
            s = 0;
            int min_x = 0;
            int max_y = c_state.length - 1;
            for (;; min_x++) {
                int current_y = min_x + step;
                if (current_y >= c_state.length) {
                    break;
                }
                s += c_state[min_x][current_y];
                if (s > 1) {
                    return false;
                }
            }
        }
        //下三角对角线
        for (int step = 1; step <= c_state.length - 2; step++) {
            s = 0;
            int min_x = 0;
            int max_y = c_state.length - 1;
            for (;; min_x++) {
                int current_y = min_x + step;
                if (current_y >= c_state.length) {
                    break;
                }
                s += c_state[current_y][min_x];
                if (s > 1) {
                    return false;
                }
            }
        }
        //反上三角
        for (int step = 1; step <= c_state.length - 1; step++) {
            s = 0;
            int min_x = 0;
            int current_y = step;
            for (;; min_x++) {
                s += c_state[min_x][current_y];
                if (s > 1) {
                    return false;
                }
                current_y--;
                if (current_y < 0) {
                    break;
                }
            }
        }
        //反下三角
        for (int step = 1; step <= c_state.length - 2; step++) {
            s = 0;
            int min_x = c_state.length-1;
            int current_y = step;
            for (;; min_x--) {
                s += c_state[current_y][min_x];
                if (s > 1) {
                    return false;
                }
                current_y++;
                if (current_y >= c_state.length) {
                    break;
                }
            }
        }

        return true;
    }

    public boolean judge_successful() {
        if (judge_reasonable()) {
            if (get_already_state() == c_state.length) {
                return true;
            }
        }
        return false;
    }

    public void print_itself() {
        for (int i = 0; i < c_state.length; i++) {
            for (int j = 0; j < c_state.length; j++) {
                System.out.print(c_state[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void set_cstate(int[][] cs) {
        c_state = new int[cs.length][cs.length];
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs.length; j++) {
                c_state[i][j] = cs[i][j];
            }
        }
    }

    public CState clone() {
        CState cs = new CState();
        cs.set_cstate(c_state);
        return cs;
    }

}
