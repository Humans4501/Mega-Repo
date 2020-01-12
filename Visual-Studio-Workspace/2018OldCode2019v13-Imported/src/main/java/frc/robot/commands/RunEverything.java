package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class RunEverything extends TimedCommand {

	public RunEverything(double timeout) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
		requires(Robot.shooter);
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.intake(1, 0);
		Robot.shooter.shoot(1, 1);
		Robot.conveyor.convey(0.8, 0.8);

	}

	// Called once after timeout
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
