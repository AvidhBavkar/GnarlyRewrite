package com.team3925.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.team3925.frc2017.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Climber extends Subsystem{
	
	private final CANTalon climbMaster = RobotMap.ClimberMap.climbMaster;
	private final CANTalon climbSlave  = RobotMap.ClimberMap.climbSlave;
	
	private static Climber instance;
	public static Climber getInstance() {
		if (instance == null)
			instance = new Climber();
		return instance;
	}
	
	public Climber() {
		//handle inversions
		climbMaster.setInverted(false);
		climbMaster.reverseOutput(false);
		
		climbSlave.setInverted(false);
		climbSlave.reverseOutput(false);
		
		
		climbMaster.changeControlMode(TalonControlMode.PercentVbus);
		
		LiveWindow.addActuator("Climber"    , "Master Climber", climbMaster);
		LiveWindow.addActuator("Climb Slave", "Slave Climber" , climbSlave);
	}
	
	public void setClimber(double pwr) { climbMaster.set(pwr); }

	@Override
	protected void initDefaultCommand() { }

}
