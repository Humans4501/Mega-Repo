package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchHoldToRaiseArm extends Command {
	private static final double RAISE_ARM_WINCH_SPEED = 0.7;

	private boolean done;

	public WinchHoldToRaiseArm() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.winch);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("whinch start");
		done = false;
		Robot.winch.resetLimitSwitch();

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.winch.runWinch(RAISE_ARM_WINCH_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		return false;

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
