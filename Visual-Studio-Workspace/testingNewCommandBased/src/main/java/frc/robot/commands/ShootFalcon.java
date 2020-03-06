/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon1;
import frc.robot.subsystems.ShooterDesiredSpeedFalcon2;

public class ShootFalcon extends CommandBase {
  private final Shooter shooter;
  private final Conveyor conveyor;
  private final ShooterDesiredSpeedFalcon1 pidF1;
  private final ShooterDesiredSpeedFalcon2 pidF2;
  private double time = 0;
  private boolean readyToShoot = false;
  /**
   * Creates a new Shoot.
   */
  public ShootFalcon(Shooter subsystem, ShooterDesiredSpeedFalcon1 pIDF1, ShooterDesiredSpeedFalcon2 pIDF2, Conveyor subsystem2) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = subsystem;
    conveyor = subsystem2;
    pidF1 = pIDF1;
    pidF2 = pIDF2;
    addRequirements(shooter);
    // addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    pidF1.setSetpoint(-Constants.shooterSpeed);
    pidF2.setSetpoint(Constants.shooterSpeed);
    pidF1.enable();
    pidF2.enable();
    readyToShoot = false;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putNumber("output", pidF1.currOutput);
    if(time == 0){
      time = System.currentTimeMillis();
    }
    shooter.shoot(pidF1.currOutput, pidF2.currOutput);
    
      if (pidF1.getMeasurement() <= (-Constants.shooterSpeed + 150) && pidF1.getMeasurement() >= (-Constants.shooterSpeed - 150) 
      && pidF2.getMeasurement() <= (Constants.shooterSpeed+150) && pidF2.getMeasurement() >= (Constants.shooterSpeed-150)){
        // conveyor.conveyShoot(1, 1, 0.75);  
        readyToShoot = true;
      }
    
      if(readyToShoot){
        conveyor.conveyShoot(1,1,0.75);
      }
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shoot(0,0);
    conveyor.conveyShoot(0, 0, 0);
    pidF1.disable();
    pidF2.disable();
    time = 0;
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
