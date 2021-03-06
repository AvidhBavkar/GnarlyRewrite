package com.team3925.frc2017.commands;

import com.team3925.frc2017.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveManual extends Command {

	public interface DriveManualInput {
		public abstract double getFwd();

		public abstract double getLeft();
	}

	private final boolean doReverseWhenReversing;
	private DriveManualInput input;
	private double prelimLeft, prelimRight;
	private double fwd, turn;
	private double scale;

	public DriveManual(DriveManualInput input) {
		this.input = input;
		doReverseWhenReversing = false;
		requires(Drivetrain.getInstance());
	}

	@Override
	protected void initialize() {
		Drivetrain.getInstance().setRaw(0, 0);
	}

	@Override
	protected void execute() {
		fwd = input.getFwd();
		turn = input.getLeft();
		if (Math.abs(fwd) < 0.1)
			fwd = 0;
//		if (Math.abs(turn) < 0.1)
//			turn = 0;
//		turn = Math.signum(turn)*Math.pow(Math.abs(turn), .5);
		if (doReverseWhenReversing && fwd != 0)
			turn *= Math.signum(fwd);
		prelimLeft = fwd + turn;
		prelimRight = fwd - turn;
		if (prelimLeft != 0 || prelimRight != 0) {
			scale = Math.max(Math.abs(fwd), Math.abs(turn)) / Math.max(Math.abs(prelimLeft), Math.abs(prelimRight));
			prelimLeft *= scale;
			prelimRight *= scale;
		}
		Drivetrain.getInstance().setRaw(prelimLeft, prelimRight);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Drivetrain.getInstance().setRaw(0, 0);
	}

	@Override
	protected void interrupted() {
		Drivetrain.getInstance().setRaw(0, 0);
	}

}
