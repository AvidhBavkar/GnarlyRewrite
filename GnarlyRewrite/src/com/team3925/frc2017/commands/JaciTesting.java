package com.team3925.frc2017.commands;

import com.team3925.frc2017.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;

public class JaciTesting extends Command{
	
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Left Speedz" , Drivetrain.getInstance().getLeftSpeed());
		SmartDashboard.putNumber("Left Errorz ", Drivetrain.getInstance().getLeftMaster().getError());
		Drivetrain.getInstance().setVelocity(270,270);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
