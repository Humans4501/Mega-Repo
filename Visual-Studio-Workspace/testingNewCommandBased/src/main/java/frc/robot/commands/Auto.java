/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimelightAim;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon1;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon2;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto extends SequentialCommandGroup {
  /**
   * Creates a new Auto.
   */
  public Auto(DriveTrain drivetrain, Intake intake, Conveyor conveyor, LimelightAim limelightAim, Shooter shooter, 
              ShooterDesiredSpeedFalcon1 rpm1, ShooterDesiredSpeedFalcon2 rpm2) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super( new ParallelRaceGroup(new DriveForDistance(drivetrain, 1), new Load(intake), new ConveyorForward(conveyor)),
           new ParallelRaceGroup(new Delay(0.5), new Load(intake)),
           new ParallelRaceGroup(new DriveForDistance(drivetrain, -0.5), new ConveyorForward(conveyor)),
           new AimEndable(drivetrain, limelightAim), new ShootFalcon(shooter, rpm1, rpm2, conveyor));
  }
}
