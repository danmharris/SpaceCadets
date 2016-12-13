package spacecadets2016.dmh2g16;

import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import java.awt.Graphics2D;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Eclipsy - a robot by (your name here)
 */
public class Eclipsy extends Robot
{
	boolean direction;
	double latestBearing;
	/**
	 * run: Eclipsy's default behavior
	 */
	public void run() {
		direction = true;
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		int angle = (int) Math.round(Math.random() * 10);
		// Robot main loop
		while(true) {
			this.latestBearing = this.getHeading();
				if (direction){
				this.turnGunRight(angle);
				turnRight(20);
				} else {
					this.turnGunLeft(angle);
					this.turnLeft(20);
				}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		direction = !direction;
		turnRight(e.getBearing()+90);
		turnGunRight(e.getBearing());
		fire(3);
		if (e.getDistance() < 150){
			back(100);
		} else {
			ahead(100);
		}
	
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		this.turnGunRight(e.getBearing());
		this.turnRight(e.getBearing()-90);
		fire(2);
		ahead(200);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(180);
		ahead(20);
	}	
	
	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		fire(1);
		super.onBulletHitBullet(event);
	}
	
	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		// TODO Auto-generated method stub
		//turnRight(30);
		super.onBulletMissed(event);
	}

	@Override
	public void onPaint(Graphics2D g) {
		// TODO Auto-generated method stub
		
		super.onPaint(g);
	}
}
