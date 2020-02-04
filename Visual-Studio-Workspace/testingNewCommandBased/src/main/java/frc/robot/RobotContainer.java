/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.cuforge.libcu.Lasershark;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.MecanumDriveKinematicsConstraint;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static final Joystick joystick1 = new Joystick(1);
  public static final Joystick joystick2 = new Joystick(2);
  public static final XboxController xbox = new XboxController(0);

  public final DriveTrain drivetrain = new DriveTrain();
  public final Shooter shooter = new Shooter();
  public final ShooterDesiredSpeedFalcon1 rpmFalcon1 = new ShooterDesiredSpeedFalcon1();
  public final ShooterDesiredSpeedFalcon2 rpmFalcon2 = new ShooterDesiredSpeedFalcon2();
  public final LimelightAim limelightAim = new LimelightAim();
  public final Conveyor conveyor = new Conveyor();

  public final Drive drive = new Drive(drivetrain, xbox);
  // public final Shoot shoot = new Shoot(shooter, xbox, joystick2);
  public final Load load = new Load(shooter, joystick1, joystick2);
  public final ShootFalcon shootFalcon = new ShootFalcon(shooter, rpmFalcon1, rpmFalcon2, joystick1);
  public final Aim aim = new Aim(drivetrain, limelightAim);
  public final ConveyorForward conveyorForward = new ConveyorForward(conveyor);
  public final ConveyorStop conveyorStop = new ConveyorStop(conveyor);
  public final ConveyorShoot conveyorShoot = new ConveyorShoot(conveyor);

  public static final TalonFX falcon1 = new TalonFX(Constants.falcons1);
  public static final TalonFX falcon2 = new TalonFX(Constants.falcons2);
  public static final TalonFX frontleft = new TalonFX(Constants.frontLeft);
  public static final TalonFX frontright = new TalonFX(Constants.frontRight);
  public static final TalonFX backleft = new TalonFX(Constants.backLeft);
  public static final TalonFX backright = new TalonFX(Constants.backRight);

  public static AHRS ahrs = new AHRS(Port.kMXP);
  public static Lasershark lidar = new Lasershark(9);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */

  private final JoystickButton trigger = new JoystickButton(joystick1, 1);
  private JoystickButton aimLimelight = new JoystickButton(xbox, Constants.A);
  private JoystickButton convey = new JoystickButton(xbox, Constants.B);
  private JoystickButton conveyShoot = new JoystickButton(xbox, Constants.Y);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    aimLimelight.whileHeld(new Aim(drivetrain, limelightAim));
    convey.whileHeld(new ConveyorForward(conveyor));
    convey.whenReleased(new ConveyorStop(conveyor));
    conveyShoot.whileHeld(new ConveyorShoot(conveyor));
    conveyShoot.whenReleased(new ConveyorStop(conveyor));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Trajectory trajectory = null;
    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
        Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.m_kinematics);

    // An example trajectory to follow. All units in meters.
    
    trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(0, 1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(0, 2, new Rotation2d(0)),
        config
    );

    MecanumControllerCommand mecanumControllerCommand = new MecanumControllerCommand(
        trajectory,
        drivetrain::getPose,

        Constants.kFeedforward,
        Constants.m_kinematics,

        //Position contollers
        new PIDController(Constants.kPXController, 0, 0),
        new PIDController(Constants.kPYController, 0, 0),
        new ProfiledPIDController(Constants.kPThetaController, 0, 0,
                                  Constants.kThetaControllerConstraints),

        //Needed for normalizing wheel speeds
        Constants.kMaxSpeedMetersPerSecond,

        //Velocity PID's
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),
        new PIDController(Constants.kPDriveVel, 0, 0),

        drivetrain::getWheelSpeeds,

        drivetrain::setDriveSpeedControllersVolts, //Consumer for the output motor voltages

        drivetrain
    );
    // Run path following command, then stop at the end.
    return mecanumControllerCommand.andThen(() -> System.out.println("done"));
  }
}
