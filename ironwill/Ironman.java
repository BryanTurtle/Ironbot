package ironwill;

import robocode.*;
import java.awt.Color;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
//import robocode.WinEvent;
import robocode.TeamRobot;

/**
 * IronMan - a robot by Mister Turtle
 */
public class Ironman extends TeamRobot{
int Direc = 1; //direção
 public final double avoidwall=.20;
    public void run() {
        System.out.println("Iniciando Mark I");
        setBodyColor(Color.blue);
        setGunColor(Color.red);
        setRadarColor(Color.white);
        setBulletColor(Color.white);
        setScanColor(Color.yellow);
		
        setAdjustRadarForRobotTurn(true);// seta o radar para ficar parado enquanto o robô muda de posição
		setAdjustGunForRobotTurn(true); // mesma coisa para a arma
		turnRadarRightRadians(Double.POSITIVE_INFINITY);//radar gira para a direita continuamente
        

         while(true) {
		          double width = getBattleFieldWidth();
		 double height = getBattleFieldHeight();
		 double paredes = avoidwall*Math.max(width, height);
		 
         
		
		 while(true){
			  double yPosi= getY();
		
			 if (yPosi<paredes) {
               if (getHeading()<180) {
                   setTurnLeft(90);
               }else {setTurnRight(90);
              }
            }else if (yPosi> height-paredes) {
if (getHeading()<90){setTurnRight(90);
}else {setTurnLeft(90);
        }
       }
			else {setTurnRight(0);
setTurnLeft(0); 
              }
			setAhead(10);
			execute();
	  }
	 }
	}

 

    public void onScannedRobot(ScannedRobotEvent e) {
   if(isTeammate(e.getName()))
   {
   return;
   }
   double EPosic = e.getBearingRadians()+getHeadingRadians();
   double EVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - EPosic);
   double gunTurnAmt;
   setTurnRadarLeftRadians(getRadarTurnRemainingRadians());     

        // se aproxima do alvo se a distancia for muito grande.
        if (e.getDistance() > 100) {
		gunTurnAmt = robocode.util.Utils.normalRelativeAngle(EPosic - getGunHeadingRadians()+EVel/22);
		setTurnGunRightRadians(gunTurnAmt);
        setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(EPosic-getHeadingRadians()+EVel/getVelocity()));
		setAhead((e.getDistance() - 140)*Direc);
		fire(3);
		}
		
		if(e.getDistance() == 100) {
fire(1);
 }
        if (e.getDistance() <50) {
	 fire(3);
	 }
 }
     
    
    

    public void onHitByBullet(HitByBulletEvent e) {
        back(10);
		setTurnRight(17);
		setTurnLeft(18);
		setAhead(15);
		
    }

    public void onHitWall(HitWallEvent e) {
		back(40);
		setTurnRight(10);
		ahead(6);
		setTurnLeft(10);
     
		 }
 
    public void onHitRobot(HitRobotEvent e) {
    //se eu colidi com o robô inimigo
   e.isMyFault();
   e.getEnergy();
   if (e.isMyFault()) {
  		back(15);
		setTurnRight(9);
		setTurnLeft(5);
		ahead(2);
  	}else {
	scan();
	fire(3);
 }
    }
  }