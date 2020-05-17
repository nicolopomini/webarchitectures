/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch18.ass5.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author pomo
 */
public class Document implements Serializable {
    private ArrayList<String> content;
    
    public Document() {
        this.content = new ArrayList<>();
    }
    public void addString(String s) {
        this.content.add(s);
    }

    @Override
    public String toString() {
        String total = "";
        for(String s: this.content)
            total += s;
        return total;
    }
    
}
