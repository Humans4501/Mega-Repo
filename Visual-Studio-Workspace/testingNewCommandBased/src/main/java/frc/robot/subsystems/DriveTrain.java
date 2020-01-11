/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive;

public class DriveTrain extends SubsystemBase {
  MecanumDrive drive;
  WPI_TalonSRX frontLeft, frontRight, backLeft, backRight;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    frontLeft = new WPI_TalonSRX(Constants.frontLeft);
    frontRight = new WPI_TalonSRX(Constants.frontRight);
    backRight = new WPI_TalonSRX(Constants.backRight);
    backLeft = new WPI_TalonSRX(Constants.backLeft);

    drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  }

  public void drive(double x, double y, double rotate) {
    drive.driveCartesian(x, -y, -rotate);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  

}
