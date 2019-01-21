/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.GoWinch;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Winch extends Subsystem {
  public static Winch instance;
  double winchSpeed;

  Talon talon = new Talon(RobotMap.WINCH);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Winch(){
    instance = this;

    

  }

  public void goWinch(double speed){
    winchSpeed = speed;
    talon.set(winchSpeed);
    System.out.println("we have been called");

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new GoWinch());
  }
}
