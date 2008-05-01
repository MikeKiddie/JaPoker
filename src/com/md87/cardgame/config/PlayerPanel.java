/*
 * Copyright (c) Chris 'MD87' Smith, 2007. All rights reserved.
 *
 * This code may not be redistributed without prior permission from the
 * aforementioned copyright holder(s).
 */

package com.md87.cardgame.config;

import com.md87.cardgame.ui.GameWindow;
import com.md87.cardgame.Player;
import com.md87.cardgame.config.controllers.ControllerInfo;
import com.md87.cardgame.interfaces.Game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Chris
 */
public class PlayerPanel extends JPanel {
    
    private static final int MAXPLAYERS = 14;
    
    private final JPanel innerPanel = new JPanel();
    
    private final List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    private final List<JTextField> textFields = new ArrayList<JTextField>();
    private final List<JSpinner> spinners = new ArrayList<JSpinner>();
    private final List<JComboBox> comboBoxes = new ArrayList<JComboBox>();
    
    public PlayerPanel() {
        super();
        
        innerPanel.setLayout(new GridBagLayout());
        
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.set(3, 3, 3, 3);
        
        for (int i = 0; i < MAXPLAYERS; i++) {
            final JCheckBox cb = new JCheckBox();
            if (i < 4) {
                cb.setSelected(true);
                if (i < 2) {
                    cb.setEnabled(false);
                }
            }
            
            final JTextField tf = new JTextField();
            tf.setText("Player " + (i + 1));
            tf.setPreferredSize(new Dimension(130, 20));
            
            final JSpinner sp = new JSpinner(new SpinnerNumberModel(1500, 100, 1000000000, 100));
            sp.setPreferredSize(new Dimension(100, 20));
            
            final JComboBox co = new JComboBox(ControllerInfo.getControllers());
            co.setPreferredSize(new Dimension(200, 20));
            
            if (i > 0) {
                co.setSelectedIndex(3);
            }
            
            checkBoxes.add(cb);
            textFields.add(tf);
            spinners.add(sp);
            comboBoxes.add(co);
            
            constraints.gridy = i;
            constraints.gridx = 0;
            innerPanel.add(cb, constraints);
            constraints.gridx++;
            innerPanel.add(tf, constraints);
            constraints.gridx++;
            innerPanel.add(sp, constraints);
            constraints.gridx++;
            innerPanel.add(co, constraints);
        }
        
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(innerPanel);
    }
    
    public void setMaxPlayers(final int maxPlayers) {
        for (int i = 2; i < MAXPLAYERS; i++) {
            if (i < maxPlayers) {
                checkBoxes.get(i).setEnabled(true);
                textFields.get(i).setEnabled(true);
                spinners.get(i).setEnabled(true);
                comboBoxes.get(i).setEnabled(true);
            } else {
                checkBoxes.get(i).setSelected(false);
                checkBoxes.get(i).setEnabled(false);
                textFields.get(i).setEnabled(false);
                spinners.get(i).setEnabled(false);
                comboBoxes.get(i).setEnabled(false);                
            }
        }
    }
    
    public int getNumPlayers() {
        int res = 0;
        
        for (int i = 0; i < MAXPLAYERS; i++) {
            if (checkBoxes.get(i).isSelected()) {
                res++;
            }
        }
        
        return res;
    }
    
    public List<Player> getPlayers(final Game game, final GameWindow window) {
        final List<Player> res = new ArrayList<Player>();
        
        for (int i = 0; i < MAXPLAYERS; i++) {
            if (checkBoxes.get(i).isSelected()) {
                res.add(new Player(game, textFields.get(i).getText(), 
                        (Integer) spinners.get(i).getValue(),
                        ((ControllerInfo) comboBoxes.get(i).getSelectedItem())
                        .getController(game, window)));
            }
        }
        
        return res;
    }
}
