/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon1;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon2;

public class Shoot extends CommandBase {
  private final Shooter shooter;
  private final ShooterDesiredSpeedFalcon1 pidF1;
  private final ShooterDesiredSpeedFalcon2 pidF2;
  private Joystick joystick;
  /**
   * Creates a new Shoot.
   */
  public Shoot(Shooter subsystem, ShooterDesiredSpeedFalcon1 pIDF1, ShooterDesiredSpeedFalcon2 pIDF2, Joystick controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = subsystem;
    pidF1 = pIDF1;
    pidF2 = pIDF2;
    joystick = controller;
    addRequirements(shooter);
    pidF1.enable();
    pidF2.enable();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    pidF1.setSetpoint(joystick.getRawAxis(2) * 6600.0);
    pidF2.setSetpoint(joystick.getRawAxis(2) * 6600.0);
    shooter.shoot(pidF1.currOutput, pidF2.currOutput);
    // shooter.shoot(joystick.getRawAxis(2), joystick.getRawAxis(2));
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
