/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.TestShooter2;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final Joystick joystick1 = new Joystick(0);
  public static final Joystick joystick2 = new Joystick(1);

  public final DriveTrain drivetrain = new DriveTrain();
  public final Shooter shooter = new Shooter();
  public final TestShooter2 testshooter2 = new TestShooter2();
  public final ShooterDesiredSpeedFalcon1 rpmFalcon1 = new ShooterDesiredSpeedFalcon1();
  public final ShooterDesiredSpeedFalcon2 rpmFalcon2 = new ShooterDesiredSpeedFalcon2();

  public final Drive drive = new Drive(drivetrain, joystick1, joystick2);
  public final Shoot shoot = new Shoot(shooter, joystick1, joystick2);
  public final Shoot2 shoot2 = new Shoot2(testshooter2, joystick1, joystick2);
  public final Load load = new Load(shooter, joystick1, joystick2);
  public final ShootFalcon shootFalcon = new ShootFalcon(shooter, rpmFalcon1, rpmFalcon2, joystick1);

  public static final TalonFX falcon1 = new TalonFX(Constants.falcons1);
  public static final TalonFX falcon2 = new TalonFX(Constants.falcons2);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */


  private JoystickButton trigger = new JoystickButton(joystick1, 1);
  


  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    trigger.whileHeld(new Shoot2(testshooter2, joystick1, joystick2));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
