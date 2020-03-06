/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AimY extends PIDCommand {
  /**
   * Creates a new aimY.
   */
  public AimY(Shooter shooter, double setHeight) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kPAimY, Constants.kIAimY, Constants.kDAimY),
        // This should return the measurement
        shooter::getEncoder,
        // This should return the setpoint (can also be a constant)
        setHeight,
        // This uses the output
        output -> {
          // Use the output here
          shooter.aim(-output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(Constants.kAimYToleranceD, Constants.kAimYToleranceDPS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
