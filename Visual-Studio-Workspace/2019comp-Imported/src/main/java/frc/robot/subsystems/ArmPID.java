/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */

public class ArmPID extends PIDSubsystem {
  public static ArmPID instance;
  static double Kp = 0.04;
  static double Ki = 0.0;
  static double Kd = 0.0;
  public double speed = 0;
  static double maxOutputRange = 0.75;
  public boolean done = false;
  static double targetAngle = 5;
  double offset;
  double potPrevious;
  double pot1;
  double pot2;
  double pot3;
  double pot4;
  double pot5;
  public double potValue;

  AnalogInput ai = new AnalogInput(RobotMap.POT);
  Potentiometer pot = new AnalogPotentiometer(ai, 3600, 0);
  
  /**
   * Add your docs here.
   */
  public ArmPID() {
    // Intert a subsystem name and PID values here
    super("ArmPID", Kp, Ki, Kd);
    offset = pot.get();
    instance = this;
    getPIDController().setOutputRange(-0.4, maxOutputRange);
    getPIDController().enable();
    getPIDController().setSetpoint(targetAngle);
 


    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public void setTarget(double target){
    targetAngle = target;
    getPIDController().setSetpoint(target);
  }

  public double getPot(){
    return pot.get() - offset;
  }
  
  public void setPot(){
    offset = pot.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
public double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    if(getPot() - potPrevious < 1 || getPot() - potPrevious > -1){
      pot5 = pot4;
      pot4 = pot3;
      pot3 = pot2;
      pot2 = pot1;
      pot1 = getPot();
    }
    potPrevious = getPot();

    potValue = (pot1 + pot2 + pot3 + pot4 + pot5)/5;
    return potValue;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    speed = -output;
    // Robot.arm.goArm(-output);
    

  }

  public boolean turnDone(){
    if (potValue - targetAngle < 3 && potValue - targetAngle > -3){
      done = true;
    }else{
      done = false;
    }
    return done;
  }
}
