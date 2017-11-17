package com.team3925.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.team3925.frc2017.RobotMap;
import com.team3925.utils.MiscMath;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends Subsystem{
	
	private static final double GEAR_INTAKE_SPEED = 1;
	private static final double GEAR_DETECTED_THRESHOLD_CURRENT = 0.8;
	
	private final CANTalon intakeMotor  = RobotMap.IntakeMap.intakeMotor;
	private final DoubleSolenoid intake = RobotMap.IntakeMap.intake;
	
	private static Intake instance;
	public static Intake getInstance() {
		if (instance == null)
			instance = new Intake();
		return instance;
	}
	
	private Intake() {
		//handle inversion
		intakeMotor.setInverted  (false);
		intakeMotor.reverseOutput(false);
		
		intakeMotor.changeControlMode(TalonControlMode.PercentVbus);
		
		setIntake(true);
		
		LiveWindow.addActuator("Gear Intake", "Intake Solenoid", intake);
		LiveWindow.addActuator("Gear Intake", "Intake Motor", intakeMotor);
	}
	
	public void setIntake(boolean isUp) {
		intake.set( (isUp) ? Value.kForward : Value.kReverse); 
	}
	
	public void setIntakeMotor(double speed) { intakeMotor.set(MiscMath.capRange(speed)); }
	public void setIntakeMotor() { intakeMotor.set(GEAR_INTAKE_SPEED); }
	
	public double getIntakeMotorCurrent() {return intakeMotor.getOutputCurrent(); }
	public boolean isGearDetected() { return (getIntakeMotorCurrent() >= GEAR_DETECTED_THRESHOLD_CURRENT); }
	
	
	

	@Override
	protected void initDefaultCommand() { }

}
