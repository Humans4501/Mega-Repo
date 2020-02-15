/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry rpmFalcons1;
  NetworkTableEntry rpmFalcons2;
  public static double rpmFalcon1;
  public static double rpmFalcon2;
  NetworkTableEntry limelightTX = table.getEntry("tx");;
  NetworkTableEntry limelightTA = table.getEntry("ta");
  ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
  private RobotContainer m_robotContainer;
  static double limelightX;
  static double limelightA;
  

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    RobotContainer.ahrs.reset();

    CommandScheduler.getInstance().setDefaultCommand(m_robotContainer.drivetrain, m_robotContainer.drive);
    CommandScheduler.getInstance().setDefaultCommand(m_robotContainer.climber, m_robotContainer.climb);
    CommandScheduler.getInstance().setDefaultCommand(m_robotContainer.shooter, m_robotContainer.elevate);

    rpmFalcons1 = tab.add("Desired RPM Falcon1", 0).getEntry();
    rpmFalcons2 = tab.add("Desired RPM Falcon2", 0).getEntry();

    RobotContainer.frontleft.setSelectedSensorPosition(0);
    RobotContainer.frontright.setSelectedSensorPosition(0);
    RobotContainer.backleft.setSelectedSensorPosition(0);
    RobotContainer.backright.setSelectedSensorPosition(0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // @SuppressWarnings({});
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("RPM Left Falcon1", (RobotContainer.falcon1.getSelectedSensorVelocity() * 600.0) / 2048.0);
    SmartDashboard.putNumber("RPM Right Falcon2",(RobotContainer.falcon2.getSelectedSensorVelocity() * 600.0) / 2048.0);

    SmartDashboard.putNumber("angle", RobotContainer.ahrs.getYaw());

    SmartDashboard.putNumber("frontLeft", RobotContainer.frontleft.getSelectedSensorPosition() * (-0.10351972333/2048));
    SmartDashboard.putNumber("frontRight", RobotContainer.frontright.getSelectedSensorPosition() * (0.10351972333/2048));
    SmartDashboard.putNumber("backLeft", RobotContainer.backleft.getSelectedSensorPosition() * (-0.10351972333/2048));
    SmartDashboard.putNumber("backRight", RobotContainer.backright.getSelectedSensorPosition() * (0.10351972333/2048));

    // SmartDashboard.putNumber("displacement x", RobotContainer.drivetrain.getPose().getTranslation().getX());
    // SmartDashboard.putNumber("displacement y", RobotContainer.drivetrain.getPose().getTranslation().getY());

    SmartDashboard.putNumber("distance", RobotContainer.lidar.getDistanceMeters());

    rpmFalcon1 = rpmFalcons1.getDouble(0);
    rpmFalcon2 = rpmFalcons2.getDouble(0);
    limelightX = limelightTX.getDouble(0.0);
    limelightA = limelightTA.getDouble(0.0);

  }

  public static double getLimelightX() {
    return limelightX;
  }

  public static double getLimelightA() {
    return limelightA;
  }
  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
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
    RobotContainer.ahrs.zeroYaw();
    RobotContainer.frontleft.setSelectedSensorPosition(0);
    RobotContainer.frontright.setSelectedSensorPosition(0);
    RobotContainer.backleft.setSelectedSensorPosition(0);
    RobotContainer.backright.setSelectedSensorPosition(0);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
