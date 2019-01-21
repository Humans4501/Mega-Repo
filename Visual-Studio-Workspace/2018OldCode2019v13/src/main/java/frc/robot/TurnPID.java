package frc.robot;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Calibration distance=23.5 inches
//Calibration ty= 2.63
public class TurnPID extends PIDSubsystem {
	public static TurnPID instance;
	
	static double Kp = 0.12;
	static double Ki = 0.005;
	static double Kd = 0.1;
	static double MAX_RANGE = 1;
	
    public double currOutput;

	
	NetworkTable table;
	double tx;
	
	public TurnPID() {
		super("TurnPID", Kp, Ki, Kd);
		instance = this;
		System.out.println("TurnPID");
		getPIDController().setContinuous(false);
		getPIDController().setOutputRange(-MAX_RANGE, MAX_RANGE);
		SmartDashboard.putData("TurnPID", getPIDController());
		NetworkTable.setIPAddress("10.95.1.55");
		table = NetworkTable.getTable("limelight");
	}

	@Override
	protected double returnPIDInput() {
		// Read limelight horizontal error
		tx = table.getNumber("tx", 0);
		return tx;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.printf("TX=%.2g, TURN=%.2g", tx, -output);
		currOutput = -output;
		Drivetrain.instance.arcadeDrive(MovePID.instance.currOutput, currOutput);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
