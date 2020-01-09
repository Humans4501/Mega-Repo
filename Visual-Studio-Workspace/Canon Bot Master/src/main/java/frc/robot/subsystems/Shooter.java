/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Solenoid loadSol = new Solenoid(RobotMap.loadSol);
  Relay fireRelay = new Relay(RobotMap.fireRelay);
  AnalogInput pressureSens = new AnalogInput(RobotMap.preassureSens);

  int counter = 0;
  public Shooter(){

  }

  public double getPreassure(){
    return (50 * pressureSens.getVoltage()) - 25;
  }
  public void startShoot(){
    fireRelay.set(Relay.Value.kForward);
  }
  public void stopShoot(){
    fireRelay.set(Relay.Value.kOff);
  }
  public void startLoad(){
    loadSol.set(true);
  }
  public void stopLoad(){
    loadSol.set(false);
  }
  public boolean doneLoading(double desiredPressure){

    return (getPreassure() >= desiredPressure);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
