package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class L2RScale extends CommandGroup {

	// String gameData = "";

	public L2RScale() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		
	}
	
	protected void initialize() {
		Robot.instance.gameData = DriverStation.getInstance().getGameSpecificMessage();

		if (Robot.instance.gameData.length() > 0) {
			if (Robot.instance.gameData.charAt(1) == 'L') {
				addSequential(new DriveAutoTimed(5));
			} else {
				addSequential(new DriveAutoTimed2(Constants.LONGWAY_PART_1)); // 9.7 inches
				addSequential(new Delay(0.25));
				addSequential(new GyroTurn(90));
				addSequential(new DriveAutoTimed2(Constants.LONGWAY)); // inches
				addSequential(new Delay(0.25));
				addSequential(new GyroTurn(90));
				addSequential(new RunEverythingLong(10));
			}
		}

	}
}
