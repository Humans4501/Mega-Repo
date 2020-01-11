/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.GoWinch;
import frc.robot.commands.StopWinch;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Winch extends Subsystem {
  public static Winch instance;
  double winchSpeed;

  Talon talon = new Talon(RobotMap.WINCH);
  DoubleSolenoid winchsol = new DoubleSolenoid(RobotMap.WHINCHSOL[0], RobotMap.WHINCHSOL[1]);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Winch(){
    instance = this;

    

  }

  public void goWinch(double speed){
    winchSpeed = speed;
    talon.set(winchSpeed);
    // System.out.println("we have been called");

  }

  public void pushRamp(){
    winchsol.set(DoubleSolenoid.Value.kForward);
  }
  public void retractRamp(){
    winchsol.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
