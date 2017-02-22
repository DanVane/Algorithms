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
public class Decrease_and_conquer {


    private List<String> step = new ArrayList<String>();

    private void startDC(int fs, int fc) {
        set_step(fs, fc);
        if (--fs != 0) {
            startDC(fs, fc);
        }
    }

    private void set_step(int fs, int fc) {
        String str = "fs:" + fs + ", fc:" + 2 + "----" + "ss:" + (25 - fs) + ", sc:" + 0;
        step.add(str);
        str = "fs:" + fs + ", fc:" + 0 + "----" + "ss:" + (25 - fs) + ", sc:" + 2;
        step.add(str);
        str = "fs:" + fs + ", fc:" + 1 + "----" + "ss:" + (25 - fs) + ", sc:" + 1;
        step.add(str);
        str = "fs:" + --fs + ", fc:" + 1 + "----" + "ss:" + (25 - fs) + ", sc:" + 1;
        step.add(str);
        if (fs == 1) {
            str = "fs:" + fs + ", fc:" + 2 + "----" + "ss:" + (25 - fs) + ", sc:" + 0;
            step.add(str);
        }
    }
    
    private void print_step(){
        for (String str:step){
            System.out.println(str);
        }
        System.out.print("There are "+ (step.size()-1) +"steps");
    }

    public static void main(String[] args) {
        Decrease_and_conquer dc = new Decrease_and_conquer();
        dc.startDC(25, 2);
        dc.print_step();
    }
}
