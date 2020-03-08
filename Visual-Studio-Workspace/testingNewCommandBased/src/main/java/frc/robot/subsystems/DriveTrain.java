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
  WPI_TalonFX frontLeft, left1, backLeft, right1, left2, right2;
  DifferentialDriveOdometry m_odometry;
  DifferentialDriveWheelSpeeds wheelSpeeds;
  SpeedControllerGroup left, right;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    frontLeft = new WPI_TalonFX(Constants.frontLeft);
    left1 = new WPI_TalonFX(Constants.left1);
    right1 = new WPI_TalonFX(Constants.right1);
    backLeft = new WPI_TalonFX(Constants.backLeft);
    left2 = new WPI_TalonFX(Constants.left2);
    right2 = new WPI_TalonFX(Constants.right2);

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()), new Pose2d(0, 0, new Rotation2d()));

    left = new SpeedControllerGroup(left2, left1);
    right = new SpeedControllerGroup(right2, right1);

    drive2 = new DifferentialDrive(left, right);
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

  public double getDistance(){
    return ((RobotContainer.left1.getSelectedSensorPosition() * 0.00002032858) + (RobotContainer.right1.getSelectedSensorPosition() * -0.00002032858))/2;
  }

  public double getHeading(){
    return RobotContainer.ahrs.getYaw();
  }

  public void resetOdometry(){
    m_odometry.resetPosition(new Pose2d(0, 0, new Rotation2d()), new Rotation2d());
  }
 public void tankDriveVolts(double leftVolts, double rightVolts) {
    left.setVoltage(leftVolts);
    right.setVoltage(-rightVolts);
    drive2.feed();
  }
  public void drive2(double speed, double rotate){
    drive2.arcadeDrive(speed, 0.75 * rotate);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wheelSpeeds = new DifferentialDriveWheelSpeeds(RobotContainer.left1.getSelectedSensorVelocity() * 0.0002032858, RobotContainer.right1.getSelectedSensorVelocity() * 0.0002032858);
    m_odometry.update(Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()),RobotContainer.left1.getSelectedSensorPosition() * 0.00002032858, RobotContainer.right1.getSelectedSensorPosition() * -0.00002032858);
    SmartDashboard.putNumber("displacement x", getPose().getTranslation().getX());
    SmartDashboard.putNumber("displacement y", getPose().getTranslation().getY());
  }

  

}
