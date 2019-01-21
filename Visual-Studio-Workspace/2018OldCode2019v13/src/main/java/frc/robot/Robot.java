/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DriveForwardaLil;
import frc.robot.commands.EVERYTHING;
import frc.robot.commands.GoConveyor;
import frc.robot.commands.GoIntake;
import frc.robot.commands.GoShoot;
import frc.robot.commands.L2R;
import frc.robot.commands.L2RScale;
import frc.robot.commands.LAKUCENTERLEFTFRONT;
import frc.robot.commands.LAKUCENTERLEFTSIDE;
import frc.robot.commands.LAKUCENTERRIGHTFRONT;
import frc.robot.commands.LAKUCENTERRIGHTSIDE;
import frc.robot.commands.LEFTFRONT;
import frc.robot.commands.LEFTSIDE;
import frc.robot.commands.R2L;
import frc.robot.commands.R2LScale;
import frc.robot.commands.RIGHTFRONT;
import frc.robot.commands.RIGHTSIDE;
import frc.robot.commands.SLOWWINCH;
import frc.robot.commands.SmoothDrive;
import frc.robot.commands.Turn90Left;
import frc.robot.commands.Turn90Right;
import frc.robot.subsystems.AnalogGyroTurnSubsystem;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Winch;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Robot instance;

	NetworkTable table;

	public static final Drivetrain driveTrain = new Drivetrain();
	public static final AnalogGyroTurnSubsystem analogGyroTurn = new AnalogGyroTurnSubsystem();

	public static final Intake intake = new Intake();
	public static final Shooter shooter = new Shooter();
	public static final Conveyor conveyor = new Conveyor();
	public static final Winch winch = new Winch();

	public String gameData = "";

	public static OI oi;
	public static Gyro analogGyro;
	public static BuiltInAccelerometer builtInAccelerometer;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<Command> m_chooser2 = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		instance = this;
		oi = new OI();
		analogGyro = new ADXRS450_Gyro();
		analogGyro.calibrate();
		builtInAccelerometer = new BuiltInAccelerometer(Accelerometer.Range.k4G);

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(160, 120);

		m_chooser.addDefault("LEFT SIDE", new LEFTSIDE());
		m_chooser2.addDefault("RIGHT SIDE", new RIGHTSIDE());
		m_chooser2.addDefault("SLOWWINCH", new SLOWWINCH(2));
		m_chooser2.addDefault("Center Right Front", new LAKUCENTERRIGHTFRONT());
		m_chooser2.addDefault("Center Right Side", new LAKUCENTERRIGHTSIDE());
		m_chooser.addDefault("Center Left Front", new LAKUCENTERLEFTFRONT());
		m_chooser.addDefault("Center Left Side", new LAKUCENTERLEFTSIDE());
		m_chooser2.addDefault("Right Front", new RIGHTFRONT());
		m_chooser.addDefault("Left Front", new LEFTFRONT());
		m_chooser2.addDefault("Left to Right Side Scale", new L2RScale());
		m_chooser2.addDefault("Left to Right Side", new L2R());
		m_chooser.addDefault("Right to Left Side Scale", new R2LScale());
		m_chooser.addDefault("Right to Left Side", new R2L());
		m_chooser2.addDefault("Turn 90 Left", new Turn90Left());
		m_chooser2.addDefault("Turn 90 Right", new Turn90Right());
		m_chooser2.addDefault("Drive Forward Just a Little", new DriveForwardaLil());
		m_chooser.addDefault("EVERYTHING", new EVERYTHING());
		m_chooser.addDefault("Drive Forward Just a Little", new DriveForwardaLil());

		// chooser.addDefault("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Left mode", m_chooser);
		SmartDashboard.putData("Right mode", m_chooser2);

		// NetworkTable.setIPAddress("10.95.1.55");
		// table = NetworkTable.getTable("limelight");
		// chooser.addDefault("My Auto", new MyAutoCommand());

		// m_chooser.addDefault("Test Timed Auto", new DriveAutoTimed(1));
		// m_chooser.addDefault("Test VisionPID", new VisionPID());
		//
		// m_chooser.addDefault("Center", new AutoCenterGroup());
		// m_chooser.addDefault("Left/Right", new AutoLeftorRightGroup());
	}

	@Override
	public void robotPeriodic() {
		super.robotPeriodic();

		// AnalogGyro
		SmartDashboard.putNumber("AnalogGyro_Angle", analogGyro.getAngle());
		SmartDashboard.putNumber("Acceleration X", builtInAccelerometer.getX());
		SmartDashboard.putNumber("Acceleration Y", builtInAccelerometer.getY());

		// L I M E L I G H T
		// double tx = table.getNumber("tx", 0);
		// double ty = table.getNumber("ty", 0);
		// double targetArea = table.getNumber("ta", 0);
		// double targetSkew = table.getNumber("ts", 0);
		// double targetView = table.getNumber("tv", 0);
		//
		// SmartDashboard.putNumber("targetView", targetView);
		// SmartDashboard.putNumber("tx", tx);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		Robot.driveTrain.shiftHigh();

		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				m_autonomousCommand = m_chooser.getSelected();
			} else {

				m_autonomousCommand = m_chooser2.getSelected();

			}
		}

		// Robot.driveTrain.shiftLow();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// SmartDashboard.putNumber("ty", ty);
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

		// Arm the winch limit switch.
		Robot.winch.resetLimitSwitch();

		// Start smooth driving.
		Scheduler.getInstance().add(new SmoothDrive());
		Scheduler.getInstance().add(new GoConveyor());
		Scheduler.getInstance().add(new GoIntake());
		Scheduler.getInstance().add(new GoShoot());

		Robot.driveTrain.shiftHigh();

		// Robot.driveTrain.shiftLow();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
