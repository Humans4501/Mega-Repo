
package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoConveyor extends Command {

	public GoConveyor() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.conveyor);
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
			Robot.conveyor.convey(speed, speed);
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
