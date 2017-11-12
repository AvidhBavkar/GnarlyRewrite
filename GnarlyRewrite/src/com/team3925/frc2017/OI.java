package com.team3925.frc2017;

import com.team3925.frc2017.commands.DriveManual.DriveManualInput;

import edu.wpi.first.wpilibj.Joystick;

public class OI implements DriveManualInput{
	
	private final Joystick wheel;
	private final Joystick xbox;
	private final Joystick stick;
	private static OI instance;
	
	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}
	
	private OI() {
		stick = new Joystick(0);
		wheel = new Joystick(1);
		xbox  = new Joystick(2);
	}
	
	@Override
	public double getLeft() {
		return wheel.getRawAxis(0);
	}
	
	@Override public double getFwd() {
		return -stick.getRawAxis(1);
	}
}
