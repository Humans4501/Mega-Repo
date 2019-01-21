package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveAutoTimed2 extends TimedCommand {

	double Kp = 0.15;

	public DriveAutoTimed2(double timeout) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Initialize");
		Robot.analogGyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double angle = Robot.analogGyro.getAngle();
		System.out.printf("Angle:%.2f\n", angle);
		Robot.driveTrain.driveTime(0.85, angle * Kp);
	}

	// Called once after timeout
	@Override
	protected void end() {
		Robot.driveTrain.driveTime(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
