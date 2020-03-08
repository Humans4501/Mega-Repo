/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  /**
   * Creates a new climb.
   */

  Climber climber;
  XboxController controller;
  public Climb(Climber subsystem, XboxController xbox) {
    climber = subsystem;
    controller = xbox;
    addRequirements(climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedL = 0;
    double speedR = 0;
    if(-controller.getRawAxis(Constants.STICK_LY) < -0.2 || -controller.getRawAxis(Constants.STICK_LY) > 0.2){
      speedL = -controller.getRawAxis(Constants.STICK_LY);
    }
    if(-controller.getRawAxis(Constants.STICK_RY) < -0.2 || -controller.getRawAxis(Constants.STICK_RY) > 0.2){
      speedR = -controller.getRawAxis(Constants.STICK_RY);
    }
    climber.climb(speedL, speedR);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.climb(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
