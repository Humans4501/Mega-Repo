/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Load extends CommandBase {
  private final Shooter shooter;
  private Joystick joystick1;
  private Joystick joystick2;
  /**
   * Creates a new Shoot.
   */
  public Load(Shooter subsystem, Joystick controller1, Joystick controller2) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = subsystem;
    joystick1 = controller1;
    joystick2 = controller2;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.load(joystick2.getRawAxis(3));
    // shooter.shoot(joystick.getRawAxis(2), joystick.getRawAxis(2));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shoot(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
