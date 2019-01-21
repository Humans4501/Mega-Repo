package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command will run the winch until the limit switch is triggered.
 */
public class WinchRaiseArm extends Command {
	private static final double RAISE_ARM_WINCH_SPEED = 0.5;

	private boolean done;

	public WinchRaiseArm() {
		requires(Robot.winch);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("WINCH RAISE ARM");
		Robot.winch.resetLimitSwitch();
		done = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.winch.runWinch(RAISE_ARM_WINCH_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
