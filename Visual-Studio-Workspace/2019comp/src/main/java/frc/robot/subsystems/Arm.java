/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmDefault;
import frc.robot.commands.GoArm;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Arm extends Subsystem {
  public static Arm instance;

  double armSpeed;

  Talon armr = new Talon(RobotMap.ARM1);
  Talon arml = new Talon(RobotMap.ARM2);

  DigitalInput limitSwitchTop = new DigitalInput(RobotMap.LIMITSWITCHTOP);
  DigitalInput limitSwitchBot = new DigitalInput(RobotMap.LIMITSWITCHBOT);

  SpeedControllerGroup arm = new SpeedControllerGroup(armr, arml);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Arm(){
    instance = this;

    

  }

  public void goArm(double speed){
    // armSpeed = (((limitSwitchTop.get() && speed < 0) || (limitSwitchBot.get() && speed > 0) ) ? speed : 0);
    if (!limitSwitchBot.get() && speed < 0){
      armSpeed = 0;
    }else if (!limitSwitchTop.get() && speed > 0){
      armSpeed = 0;
    }else{
      armSpeed = speed;
    }
    // armSpeed = speed;
    
    arm.set(armSpeed);
    // System.out.println("we have been called");

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new GoArm());
    setDefaultCommand(new ArmDefault());

  }
}
