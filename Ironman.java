package ironwill;
import robocode.*;
import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.util.Utils;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import robocode.TeamRobot;

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

  

 
 while(true){ setAhead(60);
   setTurnRight(40);
   setBack(50);
   setTurnLeft(40); 
 turnGunRight(gunTurnAmt);
 	int count = 0; 
	double gunTurnAmt; // Quanto virar a arma enquanto procurando.
	String trackName; // Nome do robô que o radar está procurando.
			// 
			count++;
			//  Virar para a esquerda se o robô inimigo não estiver no radar por mais de 2 turnos.
			if (count > 2) {
				gunTurnAmt = -10;
			}
			// Virar para direita se o robô inimigo não estiver no radar por mais de 5 turnos.
			if (count > 5) {
				gunTurnAmt = 10;
			}
			// Se o robô não encotrar o alvo por dez turnos, procurar um novo alvo.
			if (count > 11) {
				trackName = null;
}
}




	public void onScannedRobot(ScannedRobotEvent e) {
	
                    TeamRobot.isTeammate(Homem_AranhaBot);//Identifica um robô aliado.
					if (isTeammate(e.getName())) { return; } //se o radar escanear o robô aliado, não fazer nada.
	
		if (trackName != null && !e.getName().equals(trackName)) {
			return;
		}

		
		if (trackName == null) {
			trackName = e.getName();
			out.println("Engajando " + trackName);
		}

		count = 0;
		
		if (e.getDistance() > 150) {
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			setturnGunRight(gunTurnAmt); 
			setturnRight(e.getBearing());
			ahead(e.getDistance() - 140);
			return;
		}

		
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);

		// se distancia se o alvo se aproximar muito.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();
	}
		if(distance >= 100) {
fire(1);}
		if(distance < 100) {
fire(2);}
		if(distance <= 50) {
fire(3);}
		
		
		
      
		
	}


	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}
	
	
	 
	public void onHitWall(HitWallEvent e) {
		back(60);
	}	
	public void onHitRobot(HitRobotEvent e) {

fire(3);
back(20);

}
}