package com.vitcom.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.vitcom.connector.Connector;
import com.vitcom.create.Create;

public class MakeGUI {
	
	public static Connection con;
	public static String table;
	public static String pack;
	public static String path;
	
//	public static void main(String[] args) {
	public MakeGUI() {
		//frame 설정
		JFrame frame = new JFrame("test");
		frame.setLocation(600, 400);
		
		Dimension dim = new Dimension(600,190);
		frame.setPreferredSize(dim);

		//테이블명 입력창
		JLabel tblLabel = new JLabel("테이블명:      ");
		JTextField tblTxt= new JTextField();
		JPanel tblPanel = new JPanel();
		
		tblPanel.setLayout(new BoxLayout(tblPanel, BoxLayout.X_AXIS));
		tblPanel.add(tblLabel);
		tblPanel.add(tblTxt);
		
		//DB정보 입력창
		JLabel dbLabel = new JLabel("DB URL:         ");
		JTextField dbTxt = new JTextField("예) jdbc:oracle:thin:@127.0.0.1:1521:xe/id/pw");
		dbTxt.setForeground(Color.GRAY);
		dbTxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(dbTxt.getText().equals("예) jdbc:oracle:thin:@127.0.0.1:1521:xe/id/pw")) {
					dbTxt.setText("");
					dbTxt.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(dbTxt.getText().isEmpty()) {
					dbTxt.setForeground(Color.GRAY);
					dbTxt.setText("예) jdbc:oracle:thin:@127.0.0.1:1521:xe/id/pw");
				}
			}
		});
		JButton dbBtn = new JButton("테스트");
		dbBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] uriArr = dbTxt.getText().split("/");
				String uri = uriArr[0];
				String id = uriArr[1];
				String pw = uriArr[2];
				try {
					con = new Connector(uri).getConnection(id, pw);
					if(con == null) {
						dbBtn.setText("접속 실패");
					} else {
						dbBtn.setText("접속 성공");
					}
				} catch (SQLException e1) {
					dbBtn.setText("접속 실패");
					e1.printStackTrace();
				}
			}
		});

		JPanel dbPanel = new JPanel();
		
		dbPanel.setLayout(new BoxLayout(dbPanel, BoxLayout.X_AXIS));
		dbPanel.add(dbLabel);
		dbPanel.add(dbTxt);
		dbPanel.add(dbBtn);

		//패키지명 입력창
		JLabel packLabel = new JLabel("패키지명:      ");
		JTextField packTxt = new JTextField("예) com.vitcom.user");
		packTxt.setForeground(Color.GRAY);
		packTxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(packTxt.getText().equals("예) com.vitcom.user")) {
					packTxt.setText("");
					packTxt.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(packTxt.getText().isEmpty()) {
					packTxt.setForeground(Color.GRAY);
					packTxt.setText("예) com.vitcom.user");
				}
			}
		});		
		JPanel packPanel = new JPanel();
		
		packPanel.setLayout(new BoxLayout(packPanel, BoxLayout.X_AXIS));
		packPanel.add(packLabel);
		packPanel.add(packTxt);
		
		//Out 경로 선택창
		JLabel pathLabel = new JLabel("선택된 경로: ");
		JTextField pathTxt = new JTextField();
		pathTxt.setEnabled(false);
		JButton pathBtn = new JButton("폴더 선택");
		pathBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("폴더 선택");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.showOpenDialog(frame);
				File file = chooser.getSelectedFile();
				pathTxt.setText(file==null?"폴더를 선택해주세요":file.getPath());
			}
		});
		
		JPanel pathPanel = new JPanel();
		pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
		pathPanel.add(pathLabel);
		pathPanel.add(pathTxt);
		pathPanel.add(pathBtn);
		
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
		
		outerPanel.add(tblPanel);
		outerPanel.add(dbPanel);
		outerPanel.add(packPanel);
		outerPanel.add(pathPanel);
		
		JButton createBtn = new JButton();
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table = tblTxt.getText();
				pack = packTxt.getText();
				path = pathTxt.getText();
				new Create(con);
			}
		});
		
		frame.add(outerPanel,BorderLayout.CENTER);
		frame.add(new JButton("생성"), BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	}
}
