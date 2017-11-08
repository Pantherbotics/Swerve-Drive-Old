package team3863.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import static team3863.robot.Robot.drivetrain;

/**
 * Created by Aaron on 10/31/2017.
 */
public class ZeroAll extends CommandGroup {

    public ZeroAll() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the arm.
        addSequential(new Zero(drivetrain.getModules()[0]));
        addSequential(new Zero(drivetrain.getModules()[1]));
        addSequential(new Zero(drivetrain.getModules()[2]));
        addSequential(new Zero(drivetrain.getModules()[3]));
    }
}
