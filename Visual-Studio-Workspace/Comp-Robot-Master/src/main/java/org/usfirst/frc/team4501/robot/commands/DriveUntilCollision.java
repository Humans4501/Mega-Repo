package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilCollision extends Command {
	long expiry;
	double lastX;
	double lastY;
	boolean running;

	int delayCounter;

	final static double kCollisionThreshold_DeltaG = 0.4;
	final static double Kp = 0.15;

	public DriveUntilCollision() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		expiry = System.currentTimeMillis() + 4000;
		lastX = Robot.builtInAccelerometer.getX();
		lastY = Robot.builtInAccelerometer.getY();
		delayCounter = 25;
		running = true;

		Robot.analogGyro.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (running) {
			double currX = Robot.builtInAccelerometer.getX();
			double currY = Robot.builtInAccelerometer.getY();

			double deltaX = currX - lastX;
			double deltaY = currY - lastY;

			lastX = currX;
			lastY = currY;

			delayCounter--;

			if (delayCounter < 0 && (Math.abs(deltaX) > kCollisionThreshold_DeltaG
					|| Math.abs(deltaY) > kCollisionThreshold_DeltaG)) {
				System.out.println("HIT");
				System.out.printf("deltaX=%.2f deltaY= %.2g\n", deltaX, deltaY);
				running = false;
				return;
			}

			if (System.currentTimeMillis() > expiry) {
				running = false;
				return;
			}

			double angle = Robot.analogGyro.getAngle();
			System.out.printf("Angle:%.2f\n", angle);
			Robot.driveTrain.driveTime(0.7, angle * Kp);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return !running;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.driveTime(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		running = false;
	}
}
