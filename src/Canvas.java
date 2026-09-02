import java.awt.*;
import javax.swing.*;

public class Canvas extends JFrame {
	
	MyCanvas canvas;
	JButton button;
	Canvas() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton("Start");
		canvas = new MyCanvas(button);
		
		button.addActionListener(e -> canvas.start());
		
		this.add(button, BorderLayout.SOUTH);
		this.add(canvas, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}