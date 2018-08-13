package fatec2018;
import robocode.*;
import java.awt.Color;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

/**
 * IronMan - a robot by Bryan Pereira
 */
public class Ironman extends TeamRobot{
   int Direc = 1; 
   
 
    public void run() {
	double x= getX();
   double y= getY();
   double alt= getWidth();
   double larg=getHeight();
        System.out.println("Iniciando Mark I");
        setBodyColor(Color.blue);
        setGunColor(Color.red);
        setRadarColor(Color.white);
        setBulletColor(Color.white);
        setScanColor(Color.yellow);
	
	    
		
        setAdjustRadarForRobotTurn(true);// seta o radar para ficar parado enquanto o robô muda de posição.
		setAdjustGunForRobotTurn(true); // mesma coisa para a arma.
		turnRadarRightRadians(Double.POSITIVE_INFINITY);//radar gira para a direita continuamente.
        

         while(true) {      
if(x>=alt){
turnRight(180);
ahead(200);
execute();
}
if(x<=larg){
turnRight(180);
ahead(200);
execute();
}
if(x>=larg){
turnRight(180);
ahead(200);
execute();
}
if(y>=alt){
turnRight(180);
ahead(200);
execute();
}
if(y<=alt){
turnRight(180);
ahead(200);
execute();
   } 
  }
 }

 

    public void onScannedRobot(ScannedRobotEvent e) {
	
   if(isTeammate(e.getName()))
   {
   return;
   }
   if (e.getName().equals("samplesentry.BorderGuard")) {
            scan();
            return;
        }

   double EPosic = e.getBearingRadians()+getHeadingRadians();// pega a posição atual e para onde o robô está indo.
   double EVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - EPosic); //pega a velocidade do robô inimigo e calcula a tragetória 
   double gunTurnAmount;//o quanto a arma deve virar
   setTurnRadarLeftRadians(getRadarTurnRemainingRadians());     

        // se aproxima do alvo se a distancia for muito grande.
        if (e.getDistance() >= 100) {
		gunTurnAmount=robocode.util.Utils.normalRelativeAngle(EPosic-getGunHeadingRadians()+EVel/22); // normaliza o angulo no inimigo
		setTurnGunRightRadians(gunTurnAmount);//seta a direção da arma para o inimigo
		
		/** \/ pega o angulo do inimigo e vira até ele,  levando em conta posição atual - a posição
		futura e sua velocidade. */
        setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(EPosic-getHeadingRadians()+EVel/getVelocity()));
	
	//se movimenta na direção do inimigo, pegando a distancia e diminuindo ela por 140 blocos de distancia X a  posição do inimigo. \/
		setAhead((e.getDistance() - 140)*Direc);
		fire(2.5); //Atira no inimigo utilizando um ataque mais rapido que o fire 3.
		}
		
// \/ se o robô estiver muito perto utilizar o fire 3
        if (e.getDistance() <20) {
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
	public void avoideWall () {
		
	
      
}
  
  public void onWin(WinEvent e) {
       setTurnRight(20);
	   setTurnLeft(20);
	   ahead(10);
	   back(10);
	   turnLeft(20);
	   ahead(10);
	   turnRight(40);
	   ahead(0);
    }	

}
