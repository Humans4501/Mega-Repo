package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class GoShoot extends Command {

	public GoShoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.instance.isTest() || !Robot.instance.isAutonomous()) {
			double speed = Robot.oi.getRightTrigger2();
			if (speed > 0.8) {
				speed = 0.8;
			}
			if (Robot.oi.getLeftTrigger2() > 0) {
				Robot.shooter.shoot(Robot.oi.getLeftTrigger2(), Robot.oi.getLeftTrigger2());
			} else {
				Robot.shooter.shoot(speed, speed);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
