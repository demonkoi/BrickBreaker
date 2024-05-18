/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.brickbreaker;

import java.awt.Graphics;

/**
 *
 * @author lab_services_student
 */
public interface Object {

   public int getLeft();

   public int getRight();

   public int getTop();

   public int getBottom();

   public int getLength();

   public int getHeight();

   public int getSpeedx();

   public int getSpeedy();

   public void paint(Graphics g);

   public boolean destroy();
}
