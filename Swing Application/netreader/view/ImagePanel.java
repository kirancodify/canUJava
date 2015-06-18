/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netreader.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author kumark2
 */
public class ImagePanel extends JPanel{
private Image img;

  public ImagePanel(String img) {
      
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
      System.out.println("Entering here into this loop !!!");
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }
}
