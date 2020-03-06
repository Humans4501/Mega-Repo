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
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToDegrees extends PIDCommand {
  /**
   * Creates a new TurnToDegrees.
   */
  public TurnToDegrees(DriveTrain drivetrain, double degrees) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kPTurnDegrees, Constants.kITurnDegrees, Constants.kDTurnDegrees),
        // This should return the measurement
        drivetrain::getHeading,
        // This should return the setpoint (can also be a constant)
        degrees,
        // This uses the output
        output -> {
          // Use the output here
          drivetrain.drive2(0, output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(Constants.kTurnDegreesToleranceD, Constants.kTurnDegreesToleranceDPS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
