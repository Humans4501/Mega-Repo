package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class Delay extends TimedCommand {

	public Delay(double timeout) {
		super(timeout);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		Robot.analogGyro.reset();
	}
	
	

}
