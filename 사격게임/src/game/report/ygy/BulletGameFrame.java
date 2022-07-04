package game.report.ygy;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BulletGameFrame extends JFrame{
	public BulletGameFrame() {
		setTitle("사격 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel p = new GamePanel();
		setContentPane(p);
		setSize(300,300);
		setResizable(false);		
		setVisible(true);
		p.startGame();
	}
	
	public static void main(String [] args) {
		new BulletGameFrame();
	}
}
class GamePanel extends JPanel {
	private TargetThread targetThread=null;
	private JLabel baseLabel = new JLabel();
	private JLabel bulletLabel = new JLabel();
	private JLabel targetLabel;
	private JLabel scoreLabel = new JLabel();
	int score=0;
	
	public GamePanel() {
		setLayout(null);
		
		baseLabel.setSize(40, 40);
		baseLabel.setOpaque(true);
		baseLabel.setBackground(Color.pink);
		


		ImageIcon img = new ImageIcon("images/suho.jpg");
		targetLabel = new JLabel(img);
		targetLabel.setSize(img.getIconWidth(),img.getIconWidth());
		
		scoreLabel.setText("스코어 : "+score);
		scoreLabel.setSize(100, 30);

		ImageIcon  aImg = new ImageIcon("images/gun3.png");
		bulletLabel = new JLabel(aImg);
		bulletLabel.setSize(aImg.getIconWidth(),aImg.getIconHeight());
		add(baseLabel);
		add(targetLabel);
		add(bulletLabel);
		add(scoreLabel);
		
		// 이 패널에 마우스를 클릭하면 baseLabel이 키 입력을 받을 수 있도록 포커스를 강제 지정
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				baseLabel.setFocusable(true);
				baseLabel.requestFocus(); // 키보드 입력을 받도록 포커스 강제 지정			
			}
		});
	}
	
	public void startGame() {
		baseLabel.setLocation(this.getWidth()/2-20, this.getHeight()-40);
		bulletLabel.setLocation(this.getWidth()/2 - 5, this.getHeight()-80);			
		targetLabel.setLocation(0, 0);
		scoreLabel.setLocation(180,218);
		
		targetThread = new TargetThread(targetLabel);
		targetThread.start();
		
		baseLabel.setFocusable(true);
		baseLabel.requestFocus(); // 키보드 입력을 받도록 포커스 강제 지정			
		baseLabel.addKeyListener(new KeyAdapter() {
			BulletThread  bulletThread = null;
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					if(bulletThread==null || !bulletThread.isAlive()) {
						bulletThread = new BulletThread(bulletLabel, targetLabel, targetThread);
						bulletThread.start();
					}
				}
			}
		});
	}
	
	class TargetThread extends Thread {
		private JComponent target;
		public TargetThread(JComponent target) {
			this.target = target;
			target.setLocation(0, 0);
			target.getParent().repaint();
		}	
		
		@Override
		public void run() {
			int x = 0;
			int y = 0;
			int num=0;
			while(true) {
				if(num==0) x = target.getX()+10;
				else if(num==1) 	x = target.getX()+10;				
				if(num==0) {
					y = target.getY()+8;
					if(y>=40)
						num=1;
				}
				else if(num==1) {
					y = target.getY()-8;
					if(y<=0)
						num=0;
				}
				if(x > GamePanel.this.getWidth()) 	target.setLocation(0,0);
				else target.setLocation(x,y);
				
				target.getParent().repaint();
				try {
					sleep(40);
				}
				catch(InterruptedException e) {
					// the case of hit by a bullet
					target.setLocation(0, 0);
					target.getParent().repaint();
					try {
						sleep(500); // 0.5초 기다린 후에 계속한다.
					}catch(InterruptedException e2) {}					
				}
			}
		}			
	}
	
	class BulletThread extends Thread {
		private JComponent bullet, target;
		private Thread targetThread;
		public BulletThread(JComponent bullet, JComponent target, Thread targetThread) {
			this.bullet = bullet;
			this.target = target;
			this.targetThread = targetThread;	
		}
		
		@Override
		public void run() {
			
			while(true) {
				// 명중하였는지 확인
				if(hit()) {					
					targetThread.interrupt();
					bullet.setLocation(bullet.getParent().getWidth()/2 - 5, bullet.getParent().getHeight()-80);	
					score+=5;
					scoreLabel.setText("스코어 :"+ score);
					return;
				}
				else {
					int x = bullet.getX() ;
					int y = bullet.getY() - 5;
					if(y < 0) {
						bullet.setLocation(bullet.getParent().getWidth()/2 - 5, bullet.getParent().getHeight()-80);
						bullet.getParent().repaint();
						score-=5;
						scoreLabel.setText("스코어 :"+ score);
						return; // thread ends
					}
					bullet.setLocation(x, y);
					bullet.getParent().repaint();
				}
				try {
					sleep(20);
				}
				catch(InterruptedException e) {}
			}
		}
		
		private boolean hit() {
			if(targetContains(bullet.getX(), bullet.getY()) || 
					targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY()) ||
					targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY()+bullet.getHeight() - 1) ||
					targetContains(bullet.getX(), bullet.getY()+bullet.getHeight() - 1))
				return true;
			else
				return false;					
		}
		
		private boolean targetContains(int x, int y) {
			if(((target.getX() <= x) && (target.getX() + target.getWidth() - 1 >= x)) &&
					((target.getY() <= y)&& (target.getY() + target.getHeight() - 1 >= y))) {
				return true;
			}
			else
				return false;
			
		}
	}	
}
