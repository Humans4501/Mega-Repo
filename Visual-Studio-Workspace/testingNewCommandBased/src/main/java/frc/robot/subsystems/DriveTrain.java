/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  MecanumDrive drive;
  DifferentialDrive drive2;
  WPI_TalonFX frontLeft, frontRight, backLeft, backRight, left2, right2;
  DifferentialDriveOdometry m_odometry;
  DifferentialDriveWheelSpeeds wheelSpeeds;
  CANCoder left, right;
  

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    frontLeft = new WPI_TalonFX(Constants.frontLeft);
    frontRight = new WPI_TalonFX(Constants.frontRight);
    backRight = new WPI_TalonFX(Constants.backRight);
    backLeft = new WPI_TalonFX(Constants.backLeft);
    left2 = new WPI_TalonFX(Constants.left2);
    right2 = new WPI_TalonFX(Constants.right2);

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()), new Pose2d(0, 0, new Rotation2d()));

    drive2 = new DifferentialDrive(new SpeedControllerGroup(left2, frontRight), new SpeedControllerGroup(right2, backRight));
  }

  public DifferentialDriveKinematics getkinematics(){
    return Constants.differentialDriveKinematics;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return wheelSpeeds;
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(){
    m_odometry.resetPosition(new Pose2d(0, 0, new Rotation2d()), new Rotation2d());
  }

  public void setDriveSpeedControllersVolts(MecanumDriveMotorVoltages volts) {
    frontLeft.setVoltage(volts.frontLeftVoltage);
    backLeft.setVoltage(volts.rearLeftVoltage);
    frontRight.setVoltage(volts.frontRightVoltage);
    backRight.setVoltage(volts.rearRightVoltage);
  }
  public void drive2(double speed, double rotate){
    drive2.arcadeDrive(speed, rotate);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wheelSpeeds = new DifferentialDriveWheelSpeeds(RobotContainer.frontright.getSelectedSensorVelocity() * 0.0002032858, RobotContainer.backright.getSelectedSensorVelocity() * 0.0002032858);
    m_odometry.update(Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()),RobotContainer.frontright.getSelectedSensorPosition() * 0.00002032858, RobotContainer.backright.getSelectedSensorPosition() * -0.00002032858);
    SmartDashboard.putNumber("displacement x", getPose().getTranslation().getX());
    SmartDashboard.putNumber("displacement y", getPose().getTranslation().getY());
  }

  

}
