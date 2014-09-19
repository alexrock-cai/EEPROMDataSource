package com.amphenol.gis.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.amphenol.agis.swing.kit.SwingEnterListenerKit;

public class SwingUserLogin extends JFrame implements ActionListener,ItemListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2962180698337806750L;
	JPanel panel1,panel2;
	JLabel userType,userLabel,passwordLabel;
	JTextField userTextField;
	JPasswordField passwordTextField;
	JButton yesBtn,cancelBtn,exitBtn;
	JComboBox<String> userSelect;
	String[] user={"学生用户","老师用户","管理员"};
	Container c;
	int i=0;
	
	public SwingUserLogin()
	{
		super("用户登录");
		userType= new JLabel("用户类型",JLabel.CENTER);
		userSelect = new JComboBox<String>(user);
		userSelect.addItemListener(this);
		userLabel = new JLabel("用户名：",JLabel.CENTER);
		passwordLabel = new JLabel("密码：",JLabel.CENTER);
		userTextField = new JTextField(10);
		passwordTextField = new JPasswordField(10);
		yesBtn = new JButton("确定");
		cancelBtn = new JButton("取消");
		exitBtn = new JButton("退出");
		
		yesBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		
		SwingEnterListenerKit.enterPressesWhenFocused(cancelBtn);
		SwingEnterListenerKit.enterPressesWhenFocused(exitBtn);
		SwingEnterListenerKit.enterPressesWhenFocused(yesBtn);
		SwingEnterListenerKit.enterPressesWhenFocused(userTextField, this);
		SwingEnterListenerKit.enterPressesWhenFocused(passwordTextField, this);
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 2));
		panel2 = new JPanel();
		c = getContentPane();
		c.setLayout(new BorderLayout());
		panel1.add(userType);
		panel1.add(userSelect);
		panel1.add(userLabel);
		panel1.add(userTextField);
		panel1.add(passwordLabel);
		panel1.add(passwordTextField);
		c.add(panel1,BorderLayout.CENTER);
		panel2.add(yesBtn);
		panel2.add(cancelBtn);
		panel2.add(exitBtn);
		c.add(panel2,BorderLayout.SOUTH);
		setBounds(300, 300, 300, 160);
		setVisible(true);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		i=userSelect.getSelectedIndex();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cancelBtn)
		{
			userTextField.setText("");
			passwordTextField.setText("");
			return;
		}
		if(e.getSource()==exitBtn)
		{
			System.exit(0);
		}
		else
		{
			System.out.println( new String(passwordTextField.getPassword()).trim().equals(""));
			if(userTextField.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "用户名不能为空");
				return;
			}
			if(new String(passwordTextField.getPassword()).trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "密码不能为空");
				return;
			}
			switch(i)
			{
			case 0:
				if(new String(passwordTextField.getPassword()).trim().equals("s") && userTextField.getText().trim().equals("s"))
				{
					JOptionPane.showMessageDialog(null, "学生用户登录成功!");
					
				}
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				break;
			case 1:
				if(new String(passwordTextField.getPassword()).trim().equals("t") && userTextField.getText().trim().equals("t"))
				{
					JOptionPane.showMessageDialog(null, "教师用户登录成功!");
					
				}
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				break;
			case 2:
				if(new String(passwordTextField.getPassword()).trim().equals("admin") && userTextField.getText().trim().equals("admin"))
				{
					JOptionPane.showMessageDialog(null, "管理员登录成功!");
					
				}
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				break;
			default:
				JOptionPane.showMessageDialog(null, "用户名或密码错误");
			}
		}
	}

}
