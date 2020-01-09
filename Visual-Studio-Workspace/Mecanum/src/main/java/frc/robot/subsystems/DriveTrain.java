/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.\
  MecanumDrive drive;
  WPI_TalonSRX frontLeft, frontRight, backLeft, backRight;
  public DriveTrain(){
    frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
    backLeft = new WPI_TalonSRX(RobotMap.backLeft);
    frontRight = new WPI_TalonSRX(RobotMap.frontRight);
    backRight = new WPI_TalonSRX(RobotMap.backRight);
    drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    
    
    }

  public void drive(double x, double y, double rotate) {
    drive.driveCartesian(x, -y, -rotate);
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }
}
