/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.cuforge.libcu.Lasershark;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
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

  public static final XboxController xbox2 = new XboxController(1);
  public static final XboxController xbox = new XboxController(0);

  public final DriveTrain drivetrain = new DriveTrain();
  public final Shooter shooter = new Shooter();
  public final ShooterDesiredSpeedFalcon1 rpmFalcon1 = new ShooterDesiredSpeedFalcon1();
  public final ShooterDesiredSpeedFalcon2 rpmFalcon2 = new ShooterDesiredSpeedFalcon2();
  public final LimelightAim limelightAim = new LimelightAim();
  public final Conveyor conveyor = new Conveyor();
  public final Intake intake_sub = new Intake();
  public final Climber climber = new Climber();

  public final Drive drive = new Drive(drivetrain, xbox);
  public final Climb climb = new Climb(climber, xbox2);
  public final Elevate elevate = new Elevate(shooter, xbox);
  public static RamseteCommand ramseteCommand;
  // public final Aim aim = new Aim(drivetrain, limelightAim);
  // public final ShootFalcon shootFalcon = new ShootFalcon(shooter, rpmFalcon1, rpmFalcon2);
  // public final Shoot shoot = new Shoot(shooter, xbox, joystick2);

  public static final TalonFX falcon1 = new TalonFX(Constants.falcons1);
  public static final TalonFX falcon2 = new TalonFX(Constants.falcons2);
  public static final TalonFX frontleft = new TalonFX(Constants.frontLeft);
  public static final TalonFX left1 = new TalonFX(Constants.left1);
  public static final TalonFX backleft = new TalonFX(Constants.backLeft);
  public static final TalonFX right1 = new TalonFX(Constants.right1);
  public static final TalonFX right2 = new TalonFX(Constants.right2);
  public static final TalonFX left2 = new TalonFX(Constants.left2);

  public static final PowerDistributionPanel pdp = new PowerDistributionPanel(60);

  public static AHRS ahrs = new AHRS(Port.kMXP);
  public static Lasershark lidar = new Lasershark(9);
  public static Encoder aimEncoder = new Encoder(7, 8);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */

  private JoystickButton aimLimelight = new JoystickButton(xbox, Constants.LB);
  private JoystickButton convey = new JoystickButton(xbox2, Constants.RB);
  private JoystickButton conveyShoot = new JoystickButton(xbox, Constants.Y);
  private JoystickButton intake = new JoystickButton(xbox2, Constants.LB);
  private JoystickButton intake2 = new JoystickButton(xbox, Constants.A);
  private JoystickButton shoot = new JoystickButton(xbox, Constants.RB);
  private JoystickButton intakeReverse = new JoystickButton(xbox2, Constants.B);
  private JoystickButton aimShooter = new JoystickButton(xbox, Constants.X);


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
    intake.whileHeld(new Load(intake_sub));
    intake.whenReleased(new LoadStop(intake_sub));
    intakeReverse.whileHeld(new IntakeReverse(intake_sub));
    intakeReverse.whenReleased(new LoadStop(intake_sub));
    intake2.whileHeld(new Load(intake_sub));
    intake2.whenReleased(new LoadStop(intake_sub));
    shoot.whileHeld(new ShootFalcon(shooter, rpmFalcon1, rpmFalcon2, conveyor));
    aimShooter.whenPressed(new AimY(shooter, 3));
    // shoot.whenReleased(new ShootStop(shooter));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
     var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(Constants.kFeedforward, Constants.differentialDriveKinematics,10);


    Trajectory trajectory = null;
    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
        Constants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.differentialDriveKinematics).addConstraint(autoVoltageConstraint);

    // An example trajectory to follow. All units in meters.
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            new Translation2d(1, 1),
            new Translation2d(2, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    ramseteCommand = new RamseteCommand(exampleTrajectory, drivetrain::getPose, new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta), 
    Constants.kFeedforward, Constants.differentialDriveKinematics, drivetrain::getWheelSpeeds, new PIDController(Constants.kPDriveVel,0,0),
     new PIDController(Constants.kPDriveVel,0,0), drivetrain::tankDriveVolts, drivetrain);

    // Run path following command, then stop at the end.
    return new Auto(drivetrain, intake_sub, conveyor, limelightAim, shooter, rpmFalcon1, rpmFalcon2);
  }
}
