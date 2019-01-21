package org.usfirst.frc.team4501.robot;

import org.usfirst.frc.team4501.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Calibration distance=23.5 inches
//Calibration ty= 2.63
public class MovePID extends PIDSubsystem {
	public static MovePID instance;
	
	static double Kp = 0.12;
	static double Ki = 0.005;
	static double Kd = 0.1;
	static double MAX_RANGE = 1;
	
    public double currOutput;

	
	NetworkTable table;
	double ty;
	
	public MovePID() {
		super("MovePID", Kp, Ki, Kd);
		instance = this;
		System.out.println("MovePID");
		getPIDController().setContinuous(false);
		getPIDController().setOutputRange(-MAX_RANGE, MAX_RANGE);
		getPIDController().setSetpoint(1.5);
		SmartDashboard.putData("MovePID", getPIDController());
		NetworkTable.setIPAddress("10.95.1.55");
		table = NetworkTable.getTable("limelight");
	}

	@Override
	protected double returnPIDInput() {
		// Read limelight vertical error
		ty = table.getNumber("ty", 0);
		return ty;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.printf("TY=%.2g, MOVE=%.2g\n", ty, output);
		currOutput = -output;
		Drivetrain.instance.arcadeDrive(currOutput, TurnPID.instance.currOutput);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
