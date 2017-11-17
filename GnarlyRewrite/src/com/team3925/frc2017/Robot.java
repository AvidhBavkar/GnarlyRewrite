package com.team3925.frc2017;

import com.team3925.frc2017.commands.DriveManual;
import com.team3925.frc2017.commands.JaciTesting;
import com.team3925.frc2017.commands.MotionProfileCommand;
import com.team3925.vision.ChesDroid;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import jaci.pathfinder.Waypoint;

public class Robot extends IterativeRobot{
	
	public static ChesDroid chesDroid;
	private DriveManual drive;
	private MotionProfileCommand testing;
//	private JaciTesting testing;
	
	
	@Override
	public void robotInit() {
		drive = new DriveManual(OI.getInstance());
		chesDroid = new ChesDroid();
		testing = new MotionProfileCommand(new Waypoint[]{
				new Waypoint(0, 0, 0),
				new Waypoint(-5, 0, 0)
		});
		
//		testing = new JaciTesting();
	}
	
	
	@Override
	public void teleopInit() {
		drive.start();
	}
	
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
		System.out.println(Timer.getFPGATimestamp());
	}
	
	@Override
	public void autonomousInit() {
		testing.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
