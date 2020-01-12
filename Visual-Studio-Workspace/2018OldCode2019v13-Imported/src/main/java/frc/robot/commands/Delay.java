package frc.robot.commands;

import frc.robot.Robot;

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
