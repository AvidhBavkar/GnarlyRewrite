package com.team3925.frc2017;

import com.team3925.frc2017.commands.DriveManual;
import com.team3925.vision.ChesDroid;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot{
	
	public static ChesDroid chesDroid;
	private DriveManual drive;
	
	
	@Override
	public void robotInit() {
		drive = new DriveManual(OI.getInstance());
		chesDroid = new ChesDroid();
	}
	
	
	@Override
	public void teleopInit() {
		drive.start();
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
}
