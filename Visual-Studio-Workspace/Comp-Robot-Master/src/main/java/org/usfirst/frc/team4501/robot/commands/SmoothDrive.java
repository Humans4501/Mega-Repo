package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmoothDrive extends Command {

	public final double maxPowerPerSecond = 1;
	public final double maxTurnPerSecond = 1.5;

	public final double thresholdTurn = 0.6;
	public final double thresholdPower = 0.6;

	public double lastTurn;
	public double lastPower;
	public long lastTime;

	public SmoothDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		lastTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.instance.isTest() || !Robot.instance.isAutonomous()) {
			long now = System.currentTimeMillis();
			long deltaTime = now - lastTime;
			lastTime = now;

			double desiredPower = Robot.oi.getTriggers();
			double calculatedPower;

			double desiredTurn = Robot.oi.getLeftXboxX();
			double calculatedTurn;

			// accelerating
			if (desiredPower > lastPower) {
				calculatedPower = lastPower + maxPowerPerSecond * deltaTime / 1000;
			} else {
				calculatedPower = desiredPower;
			}
			calculatedPower = Math.min(calculatedPower, 1);
			calculatedPower = Math.max(calculatedPower, -1);

			if (calculatedPower > 0 && calculatedPower < thresholdPower && desiredPower > thresholdPower) {
				calculatedPower = thresholdPower;
			}

			// turning
			if (desiredTurn > lastTurn) {
				calculatedTurn = lastTurn + maxTurnPerSecond * deltaTime / 1000;
			} else if (desiredTurn < lastTurn) {
				calculatedTurn = lastTurn - maxTurnPerSecond * deltaTime / 1000;
			} else {
				calculatedTurn = desiredTurn;
			}

			calculatedTurn = Math.min(calculatedTurn, 1);
			calculatedTurn = Math.max(calculatedTurn, -1);

			if (calculatedTurn > 0 && calculatedTurn < thresholdTurn && desiredTurn > thresholdTurn) {
				calculatedTurn = thresholdTurn;
			} else if (calculatedTurn < 0 && calculatedTurn > -thresholdTurn && desiredTurn < -thresholdTurn) {
				calculatedTurn = -thresholdTurn;
			}

			Robot.driveTrain.driveTime(-calculatedPower, -calculatedTurn);

			lastPower = calculatedPower;
			lastTurn = calculatedTurn;

			// System.out.printf("desiredPower=%.2f calculatedPower=%.2f desiredTurn=%.2f
			// calculatedTurn=%.2f\n", desiredPower , calculatedPower , desiredTurn ,
			// calculatedTurn);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.driveTime(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run. Or when the command is canceled.
	@Override
	protected void interrupted() {
		Robot.driveTrain.driveTime(0, 0);
	}
}
