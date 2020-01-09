/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */

public class DriveTrain extends Subsystem {
  Spark leftMotor1, rightMotor1 ;
  RobotDrive driver;
  public DriveTrain (){
    leftMotor1 = new Spark (RobotMap.leftMotor1);
    rightMotor1 = new Spark (RobotMap.rightMotor1);
    driver = new RobotDrive(leftMotor1, rightMotor1);

   
    }
     public void Drive (double speed, double rotate){
       
     }


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {\
  
  

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}


