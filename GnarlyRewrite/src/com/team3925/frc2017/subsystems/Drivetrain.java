package com.team3925.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.team3925.frc2017.RobotMap;
import com.team3925.utils.MiscMath;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Drivetrain extends Subsystem{
	
	private final CANTalon leftMaster  = RobotMap.DrivetrainMap.leftMaster;
	private final CANTalon rightMaster = RobotMap.DrivetrainMap.rightMaster;
	private AHRS ahrs;
	private final static int ENCODER_TICKS_PER_REV = 256;
	
	private final double Kp = 0.05;//0.05
	private final double Ki = 0;
	private final double Kd = 0;
	
	private final double Kf = 1.15; //0.7
	
		
	private TalonControlMode driveTrainMode;
	
	private static Drivetrain instance;
	public static Drivetrain getInstance() {
		if (instance == null)
			instance = new Drivetrain();
		return instance;
	}
	
	private Drivetrain() {
		
		ahrs = new AHRS(Port.kMXP);
		
		//handle drivetrain motor & sensor inversions here
		RobotMap.DrivetrainMap.leftMaster.setInverted   (false);
		RobotMap.DrivetrainMap.leftMaster.reverseOutput (false);
		
		RobotMap.DrivetrainMap.leftSlaveA.setInverted   (false);
		RobotMap.DrivetrainMap.leftSlaveA.reverseOutput (false);
		
		RobotMap.DrivetrainMap.leftSlaveB.setInverted   (false);
		RobotMap.DrivetrainMap.leftSlaveB.reverseOutput (false);
		
		
		RobotMap.DrivetrainMap.rightMaster.setInverted  (true );
		RobotMap.DrivetrainMap.rightMaster.reverseOutput(false);  //TODO: !! This should probably match with the above !!
		
		RobotMap.DrivetrainMap.rightSlaveA.setInverted  (false);
		RobotMap.DrivetrainMap.rightSlaveA.reverseOutput(false);
		
		RobotMap.DrivetrainMap.rightSlaveB.setInverted  (false);
		RobotMap.DrivetrainMap.rightSlaveB.reverseOutput(false);
		
		
		
		rightMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftMaster .setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		rightMaster.reverseSensor(true);
		leftMaster .reverseSensor(false);
		
		rightMaster.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		leftMaster .configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		
		
		//Talon Closed Loop Control Constants
		rightMaster.setProfile(0);
		rightMaster.setPID(Kp, Ki, Kd);
		rightMaster.setF(Kf);
		

		leftMaster.setProfile(0);
		leftMaster.setPID(Kp, Ki, Kd);
		leftMaster.setF(Kf);
		
		
		
//		LiveWindow.addActuator("DriveTrain", "Left Master"  , RobotMap.DrivetrainMap.leftMaster);
		LiveWindow.addActuator("DriveTrainLeft", "Left Slave A" , RobotMap.DrivetrainMap.leftSlaveA);
		LiveWindow.addActuator("DriveTrainLeft", "Left Slave B" , RobotMap.DrivetrainMap.leftSlaveB);
		
//		LiveWindow.addActuator("DriveTrain", "Right Master" , RobotMap.DrivetrainMap.rightMaster);
//		LiveWindow.addActuator("DriveTrain", "Right Slave A", RobotMap.DrivetrainMap.rightSlaveA);
//		LiveWindow.addActuator("DriveTrain", "Right Slave B", RobotMap.DrivetrainMap.rightSlaveB);
		
	}

	
	public void setRaw(double l, double r) {
		setControlModes(TalonControlMode.PercentVbus);
		
		l = MiscMath.capRange(-1, 1, l);
		r = MiscMath.capRange(-1, 1, r);
		
		leftMaster.set (l);
		rightMaster.set(r);
	}
	
	public void setVelocity(double l, double r) {
		setControlModes(TalonControlMode.Speed);
		
		leftMaster.setSetpoint(l);
		rightMaster.setSetpoint(r);
	}
	
	public double getLeftSpeed() { return leftMaster.getSpeed() ; }
	public double getRightSpeed(){ return rightMaster.getSpeed(); }
	
	public int getLeftVel() { return leftMaster.getEncVelocity() ; }
	public int getRightVel(){ return rightMaster.getEncVelocity(); }

	@Deprecated public CANTalon getLeftMaster () { return leftMaster ; }
	@Deprecated public CANTalon getRightMaster() { return rightMaster; }
	
	public TalonControlMode getControlModes() { return driveTrainMode; }
	
	public void setControlModes(TalonControlMode mode){
		if (driveTrainMode != mode) {
			leftMaster.changeControlMode (mode);
			rightMaster.changeControlMode(mode);
			driveTrainMode = mode;
		}
		
	}
	
	@Override
	protected void initDefaultCommand() {}

	public int getLeftEncoder() {
		return leftMaster.getEncPosition();
	}
	public int getRightEncoder() {
		return rightMaster.getEncPosition();
	}
	
	public double getGyroFusedHeading(){
		return ahrs.getFusedHeading();
	}

}
