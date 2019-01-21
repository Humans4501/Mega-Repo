package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.Drive;
import frc.robot.commands.Lift;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScissorLift extends Subsystem {
	public static ScissorLift instance;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Talon lift;
	DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
	boolean isLimitSwitchSet;


	public ScissorLift() {
		instance = this;
		lift = new Talon(RobotMap.LIFT);



	}

	public void LiftMove(double speed) {
		lift.set(speed);
	}



	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Lift());
	}

	public void resetLimitSwitch() {
	}

	public boolean isLimitSwitchSet() {
		// System.out.println(limitSwitch.get());
		isLimitSwitchSet = limitSwitch.get();
		return isLimitSwitchSet;
	}


}
