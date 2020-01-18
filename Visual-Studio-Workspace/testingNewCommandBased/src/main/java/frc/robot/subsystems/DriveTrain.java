/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Drive;

public class DriveTrain extends SubsystemBase {
  MecanumDrive drive;
  WPI_TalonFX frontLeft, frontRight, backLeft, backRight;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    frontLeft = new WPI_TalonFX(Constants.frontLeft);
    frontRight = new WPI_TalonFX(Constants.frontRight);
    backRight = new WPI_TalonFX(Constants.backRight);
    backLeft = new WPI_TalonFX(Constants.backLeft);

    drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  }

  public void drive(double x, double y, double rotate) {
    drive.driveCartesian(x, -y, -rotate, -Robot.ahrs.getAngle());
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  

}
