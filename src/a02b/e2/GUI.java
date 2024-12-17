package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private boolean Restart = false;
    
    public GUI(int size) {
        final Logic logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        JButton check = new JButton("Check > Restart");
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        main.add(BorderLayout.SOUTH, check);

        check.addActionListener(e -> {
            if (this.Restart) {
                this.cells.keySet().forEach(p -> p.setEnabled(true));
                this.cells.keySet().forEach(p -> p.setText(""));
                this.Restart = false;
                logic.restart();
            } else {
                final List<Position> positionsToDisable = logic.check();
                for (var cell : this.cells.entrySet()) {
                    if ( positionsToDisable.contains(cell.getValue()) ) {
                        cell.getKey().setEnabled(false);
                        this.Restart = true;
                    }
                }
            }
            
        });
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
                if ( !logic.set(cells.get(button)) ) {
                    button.setText("");
                } else {
                    button.setText("*");
                }
            }
        };
               
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }  
    
}
