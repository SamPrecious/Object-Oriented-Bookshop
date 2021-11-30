import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class purchasedFrame extends JFrame {  //This frame appears after the user finishes a purchase

	private JPanel contentPane;
	private JLabel purchasedLabel;
	private String money;
	

	/**
	 * Create the frame.
	 */
	public purchasedFrame(String money, String method) {
		this.money = money;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		purchasedLabel = new JLabel("Thank you for purchase");
		purchasedLabel.setFont(new Font("Arial", Font.BOLD, 18));
		purchasedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedLabel.setBounds(10, 35, 414, 49);
		contentPane.add(purchasedLabel);
		
		JLabel purchasedLabel_1 = new JLabel(money+" paid using "+method);
		purchasedLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		purchasedLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		purchasedLabel_1.setBounds(10, 123, 414, 49);
		contentPane.add(purchasedLabel_1);
	}

}
