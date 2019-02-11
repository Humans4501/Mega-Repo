/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.commands.AlignLimelight;
// import frc.robot.commands.AlignLimelight;
import frc.robot.commands.AutoMain;
import frc.robot.commands.DriveFor2Meters;
import frc.robot.commands.Drive;
import frc.robot.subsystems.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Robot instance;

  public static Drivetrain drivetrain = new Drivetrain();
  public static Winch winch = new Winch();
  public static Arm arm = new Arm();
  public static TurnPID turnPID = new TurnPID();
  public static StayStraightPID stayStraightPID = new StayStraightPID();
  public static MovePID movePID = new MovePID();
  public static Intake intake = new Intake();
  public static HatchManipulator hatchManipulator = new HatchManipulator();
  public static LimelightAlignment limelightAlignment = new LimelightAlignment();
  public static LimelightMoveForeward limelightMoveForeward = new LimelightMoveForeward();
  public static ArmPID armPID = new ArmPID();
  public static AHRS ahrs;
  public static Potentiometer pot;
  public static OI oi;

  // public static Encoder encr;
  // public static Encoder encl;
  
  


  Command m_autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    ahrs = new AHRS(SPI.Port.kMXP);
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
    camera.setResolution(1080, 1920);
    // encr = new Encoder(RobotMap.ENCR[0], RobotMap.ENCR[1], true, Encoder.EncodingType.k4X);
    // encl = new Encoder(RobotMap.ENCL[0], RobotMap.ENCL[1], false, Encoder.EncodingType.k4X);
    // encr.setDistancePerPulse(0.0049069);
    // encl.setDistancePerPulse(0.0049069);

    // double startMSec = System.currentTimeMillis();
    // while(ahrs.isCalibrating()){}
    // double deltaMSec = System.currentTimeMillis() - startMSec;
    // System.out.println("startup for: " + deltaMSec + " milliseconds.");



    movePID.encl.reset();
    movePID.encr.reset();
    ahrs.reset();

    // startMSec = System.currentTimeMillis();
    // // while(ahrs.isCalibrating()){}
    // deltaMSec = System.currentTimeMillis() - startMSec;
    // System.out.println("calibrated for: " + deltaMSec + " milliseconds.");

    instance = this;
    oi = new OI();
    // chooser.addObject("My Auto", new MyAutoCommand());
    m_autonomousCommand = new AlignLimelight();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("GyroAngle", ahrs.getAngle());
    SmartDashboard.putNumber("turn Speed", turnPID.speed);
    SmartDashboard.putNumber("Encoder Right", movePID.encr.getDistance());
    SmartDashboard.putNumber("Encoder Left", movePID.encl.getDistance());
    SmartDashboard.putNumber("Encoders", (movePID.encr.getDistance() + movePID.encl.getDistance())/2);
    SmartDashboard.putNumber("Potentiometer", armPID.returnPIDInput());
    SmartDashboard.putNumber("ArmPID", armPID.speed);
    SmartDashboard.putNumber("triggers", 0.5 * oi.getRightXboxX());
    SmartDashboard.putBoolean("straight?", stayStraightPID.isStraight());
    SmartDashboard.putBoolean("run a marathon?", movePID.isDone());


//post to smart dashboard periodically
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    armPID.setTarget(15);
    movePID.encl.reset();
    movePID.encr.reset();
    ahrs.reset();
  }

  @Override
  public void disabledPeriodic() {
    
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() { 
    ahrs.zeroYaw();
      /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
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
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
