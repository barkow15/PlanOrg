import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class EOPanelDeleteArrangement extends EOPanel {
   EOGUI gui = null;

   public EOPanelDeleteArrangement(EOGUI gui) {
      this.gui = gui;
      this.setLayout(null);
   
     
   }

   public void setVisible(boolean visible, Object data) {
   	// TODO - implement PanelStartMenu.setVisible
      super.setVisible(visible);
   }

   public void clearData() {
   	// TODO - implement PanelCreateArrangement.clearData
      throw new UnsupportedOperationException();
   }
}