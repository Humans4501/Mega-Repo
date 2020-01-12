package frc.robot.subsystems;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnalogGyroTurnSubsystem extends PIDSubsystem {
	static double kP = 0.15;
	static double kI = 0.03;
	static double kD = 0;
	static double kF = 0;
	static double kToleranceDegrees = 2;
	static double kMaxOutputRange = 0.65;

	public double targetAngle;
	public double rotateSpeed;

	boolean running = false;

	public AnalogGyroTurnSubsystem() {
		super(kP, kI, kD);
		setInputRange(-180.0f, 180.0f);
		setOutputRange(-kMaxOutputRange, kMaxOutputRange);
		setAbsoluteTolerance(kToleranceDegrees);

		SmartDashboard.putData("AnalogGyroTurnPID", getPIDController());
	}

	public void setRelativeAngle(double angle) {
		targetAngle = angle;
		running = true;
		rotateSpeed = 0;
		Robot.analogGyro.reset();
		setSetpoint(angle);
	}

	public boolean isTurningDone() {
		double deltaAngle = Math.abs(safeGetAngle() - targetAngle);
		if (deltaAngle <= kToleranceDegrees) {
			running = false;
		}
		return !running;
	}

	@Override
	protected double returnPIDInput() {
		return Robot.analogGyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		rotateSpeed = running ? output : 0;
	}

	@Override
	protected void initDefaultCommand() {
	}

	public double safeGetAngle() {
		return (Robot.analogGyro != null) ? Robot.analogGyro.getAngle() : 0;
	}

}
