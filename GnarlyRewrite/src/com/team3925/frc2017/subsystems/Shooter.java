package com.team3925.frc2017.subsystems;

import java.util.Optional;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team3925.frc2017.Robot;
import com.team3925.frc2017.RobotMap;
import com.team3925.utils.MiscMath;
import com.team3925.vision.ChesDroid;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Shooter extends Subsystem{
	
	private final CANTalon masterShooter = RobotMap.ShooterMap.shooterMaster;
	private final CANTalon slaveShooter  = RobotMap.ShooterMap.shooterSlave;
	private final CANTalon loader 		 = RobotMap.ShooterMap.loader;
	private final CANTalon agitator		 = RobotMap.ShooterMap.agitator;
	private final ChesDroid chesDroid    = Robot.chesDroid;
	private static final int ENC_CODES_PER_REV = 4096;
	
	private static TalonControlMode shooterMode;
	
	
	private static Shooter instance;
	public static Shooter getInstance() {
		if (instance == null)
			instance = new Shooter();
		return instance;
	}
	
	private Shooter() {
		//handle inversions
		masterShooter.setInverted  (false);
		masterShooter.reverseOutput(false);
		
		slaveShooter.setInverted   (false);
		slaveShooter.reverseOutput (false);
		
		masterShooter.reverseSensor(false);
		
		loader.setInverted  	   (false);
		agitator.setInverted	   (false);
		
		masterShooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterShooter.configEncoderCodesPerRev(ENC_CODES_PER_REV);
		
		LiveWindow.addActuator("Shooter", "Master Shooter", masterShooter);
		LiveWindow.addActuator("Shooter", "Slave Shooter" , slaveShooter);
		
	}
	
	public void changeShooterControlMode(TalonControlMode mode) {
		if (shooterMode != mode) {
			masterShooter.changeControlMode(mode);
			shooterMode = mode;
		}
	}
	
	public void setShooterRaw(double pwr) {
		changeShooterControlMode(TalonControlMode.PercentVbus);
		masterShooter.set(MiscMath.capRange(pwr));
	}
	
	public void setShooterVelocity(double velocity) {
		changeShooterControlMode(TalonControlMode.Speed);
		masterShooter.setSetpoint(velocity);
	}
	
	public double getShooterSpeed() {
		return masterShooter.getSpeed();
	}
	
	
	@Deprecated public CANTalon getMasterShooter() { return masterShooter; }

	//Vision
	//TODO: maybe want to migrate the Robot.chesdroid into this class??
	public double getVisionDistance() {
		if (chesDroid.isConnected()){
			Optional<ChesDroid.VisionData> optionalData = Robot.chesDroid.getData();
			
			if (optionalData.isPresent()){
				ChesDroid.VisionData data = optionalData.get();
				
				return data.distance;
			}
		}
		return 0;
	}
	
	public double getVisionAngle() {
		if (chesDroid.isConnected()){
			Optional<ChesDroid.VisionData> optionalData = Robot.chesDroid.getData();
			
			if (optionalData.isPresent()){
				ChesDroid.VisionData data = optionalData.get();
				
				return data.error;
			}
		}
		return 0;
	}
	
	public boolean isTargetPresent(){
		return (chesDroid.isConnected() && Robot.chesDroid.getData().isPresent());
	}
	
	//End Vision
	
	
	@Override
	protected void initDefaultCommand() { }

}
