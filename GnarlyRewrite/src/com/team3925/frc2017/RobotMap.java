package com.team3925.frc2017;

import com.ctre.CANTalon;
import com.team3925.utils.CANTalonFactory;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RobotMap {
	
	public static final class DrivetrainMap{
		public static final CANTalon leftMaster    = CANTalonFactory.createDefaultTalon(1);
		public static final CANTalon leftSlaveA    = CANTalonFactory.createPermanentSlaveTalon( 2, leftMaster.getDeviceID());
		public static final CANTalon leftSlaveB    = CANTalonFactory.createPermanentSlaveTalon(30, leftMaster.getDeviceID());
		
		public static final CANTalon rightMaster   = CANTalonFactory.createDefaultTalon(12);
		public static final CANTalon rightSlaveA   = CANTalonFactory.createPermanentSlaveTalon(11, rightMaster.getDeviceID());
		public static final CANTalon rightSlaveB   = CANTalonFactory.createPermanentSlaveTalon(29, rightMaster.getDeviceID());
	}
	
	public static final class ShooterMap{
		public static final CANTalon shooterMaster = CANTalonFactory.createDefaultTalon(3);
		public static final CANTalon shooterSlave  = CANTalonFactory.createPermanentSlaveTalon(9, 3);
		
		public static final CANTalon agitator 	   = CANTalonFactory.createDefaultTalon(13);
		public static final CANTalon loader   	   = CANTalonFactory.createDefaultTalon(5);
	}
	
	public static final class ClimberMap{
		public static final CANTalon climbMaster   = CANTalonFactory.createDefaultTalon(8);
		public static final CANTalon climbSlave    = CANTalonFactory.createPermanentSlaveTalon(4, climbMaster.getDeviceID());
		
	}
	
	public static final class IntakeMap{
		public static final CANTalon intakeMotor   = CANTalonFactory.createDefaultTalon(7);
		public static final DoubleSolenoid intake  = new DoubleSolenoid(4, 5);
	}

}
