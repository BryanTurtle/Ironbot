package ironwill;
import robocode.*;
import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.util.Utils;
//import robocode.Rules;

/**
 * IronMan - a robot by (your name here)
 */
public class Ironman extends AdvancedRobot
{

	public void run() {
	System.out.println("Iniciando Mark I");
    setBodyColor(Color.blue);
    setGunColor(Color.red);
	setRadarColor(Color.white);
    setBulletColor(Color.white);
	setScanColor(Color.yellow);    



  
do {
 if (getRadarTurnRemaining() == 0.0) {
  setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
 }execute();
   
}
		while(true); 
}
	



	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would lik
        double distance = e.getDistance();
		double Angulo = getHeadingRadians() + e.getBearingRadians();
		double Radar = Utils.normalRelativeAngle(Angulo - getRadarHeadingRadians());
	    double Turno = Math.min(Math.atan(36.0 / e.getDistance()), Rules.RADAR_TURN_RATE_RADIANS);
		double gunTurnAmt = Utils.normalRelativeAngle(e.getBearingRadians() + (getHeadingRadians() - getRadarHeadingRadians()));
		
        setTurnGunRight(gunTurnAmt);
        Radar += (Radar <0 ? - Turno : Turno);
	  setTurnRadarRightRadians(Radar);
		
		if(distance >= 100) {
fire(1);}
		if(distance < 100) {
fire(2);}
		if(distance <= 50) {
fire(3);}
		
		
		
      
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(60);
	}	
	public void onHitRobot(HitRobotEvent e) {
scan();
fire(3);
back(20);

}
}