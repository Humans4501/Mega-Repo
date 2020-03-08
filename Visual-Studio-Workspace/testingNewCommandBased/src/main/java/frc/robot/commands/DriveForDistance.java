/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveForDistance extends PIDCommand {
  /**
   * Creates a new DriveForDistance.
   */
  public DriveForDistance(DriveTrain drivetrain, double distance) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kPDriveDistance, Constants.kIDriveDistance, Constants.kDDriveDistance),
        // This should return the measurement
        drivetrain::getDistance,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          // Use the output here
        drivetrain.drive2(MathUtil.clamp(output, -0.6, 0.6), 0);
        System.out.println("I am Driving I think");
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(Constants.kDriveDistanceToleranceM, Constants.kDriveDistanceToleranceMPS);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
