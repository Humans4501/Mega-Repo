/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Drive;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon1;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon2;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final XboxController controller = new XboxController(0);
  private final Joystick joystick = new Joystick(2);

  private final TalonFX falcon1 = new TalonFX(Constants.falcons1);
  private final TalonFX falcon2 = new TalonFX(Constants.falcons2);

  private final ShooterDesiredSpeedFalcon1 pidF1 = new ShooterDesiredSpeedFalcon1();
  private final ShooterDesiredSpeedFalcon2 pidF2 = new ShooterDesiredSpeedFalcon2();
  private final DriveTrain drivetrain = new DriveTrain();
  private final Shooter shooter = new Shooter();

  private final Drive drive = new Drive(drivetrain, controller);
  private final Shoot shoot = new Shoot(shooter, pidF1, pidF2, joystick);

  private RobotContainer m_robotContainer;

  // private final I2C.Port i2cPort = I2C.Port.kOnboard;
  

  // private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  
  // private final ColorMatch m_colorMatcher = new ColorMatch();

  // private final Color kBlueTarget = ColorMatch.makeColor(0.149, 0.429, 0.421);
  // private final Color kGreenTarget = ColorMatch.makeColor(0.191, 0.555, 0.253);
  // private final Color kRedTarget = ColorMatch.makeColor(0.474, 0.368, 0.158);
  // private final Color kYellowTarget = ColorMatch.makeColor(0.319, 0.547, 0.134);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_robotContainer = new RobotContainer();
    // m_colorMatcher.addColorMatch(kBlueTarget);
    // m_colorMatcher.addColorMatch(kGreenTarget);
    // m_colorMatcher.addColorMatch(kRedTarget);
    // m_colorMatcher.addColorMatch(kYellowTarget);    

    CommandScheduler.getInstance().setDefaultCommand(drivetrain, drive);
    CommandScheduler.getInstance().setDefaultCommand(shooter, shoot);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.

    

    CommandScheduler.getInstance().run();

    // Color detectedColor = m_colorSensor.getColor();

    // String colorString;

    // ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    // if (match.color == kBlueTarget) {
    //   colorString = "Blue";
    // } else if (match.color == kRedTarget) {
    //   colorString = "Red";
    // } else if (match.color == kGreenTarget) {
    //   colorString = "Green";
    // } else if (match.color == kYellowTarget) {
    //   colorString = "Yellow";
    // } else {
    //   colorString = "Unknown";
    // }

    // SmartDashboard.putNumber("Red", detectedColor.red);
    // SmartDashboard.putNumber("Green", detectedColor.green);
    // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putNumber("Confidence", match.confidence);
    // SmartDashboard.putString("Detected Color", colorString);

    SmartDashboard.putNumber("RPM Left Falcon1", (falcon1.getSelectedSensorVelocity() * 600.0)/2048.0);
    SmartDashboard.putNumber("RPM Right Falcon2", (falcon2.getSelectedSensorVelocity() * 600.0)/2048.0);
    // shooterLeft.get


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
