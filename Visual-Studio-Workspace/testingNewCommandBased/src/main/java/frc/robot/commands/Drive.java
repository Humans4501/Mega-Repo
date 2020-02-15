/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_subsystem;
  private XboxController driver;

  private double deadzone = 0.1;
  private double x;
  private double y;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drive(DriveTrain subsystem, XboxController joystick1) {
    m_subsystem = subsystem;
    driver = joystick1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(driver.getRawAxis(Constants.STICK_LY)) < deadzone){
      x = 0;
    }else{
      x = driver.getRawAxis(Constants.STICK_LY);
    }
    if(Math.abs(driver.getRawAxis(Constants.STICK_LX)) < deadzone){
      y = 0;
    }else{
      y = driver.getRawAxis(Constants.STICK_LX);
    }
    // m_subsystem.drive(y, x, driver.getRawAxis(Constants.TRIGGER_L) - driver.getRawAxis(Constants.TRIGGER_R));
    m_subsystem.drive2(driver.getRawAxis(Constants.TRIGGER_L) - driver.getRawAxis(Constants.TRIGGER_R), driver.getRawAxis(Constants.STICK_LX));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
