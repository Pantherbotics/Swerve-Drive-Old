package team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team3863.robot.RobotMap;
import team3863.robot.Robot;

import static team3863.robot.Robot.drivetrain;
import static team3863.robot.Robot.oi;


/**
 * Following Ether's whitepaper: https://www.chiefdelphi.com/media/papers/2426
 */
public class Drive extends Command {

	double y, x, z, t, a, b, c, d;
	double l = RobotMap.WHEEL_BASE;
	double w = RobotMap.TRACK_WIDTH;
	double r = RobotMap.RADIUS;
	double[] w1, w2, w3, w4;

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		w1 = new double[2];
		w2 = new double[2];
		w3 = new double[2];
		w4 = new double[2];
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//full disclosure: I HAVE NO IDEA HOW THIS CODE WORKS AND THIS ALGO ISN'T MINE
		y = oi.leftJoy.getY();
		x = oi.leftJoy.getX();
		z = oi.rightJoy.getX();
		t = drivetrain.getHeadingRadians();
		double temp = y*Math.cos(t) + x*Math.sin(t);
		x = -y*Math.sin(t) + x*Math.cos(t);
		y = temp;

		a = x - z*(l/r);
		b = x + z*(l/r);
		c = y - z*(w/r);
		d = y + z*(w/r);

		w1[0] = Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2));
		w1[1] = Math.atan2(b, c) * 180/Math.PI;

		w2[0] = Math.sqrt(Math.pow(b, 2) + Math.pow(d, 2));
		w2[1] = Math.atan2(b, d) * 180/Math.PI;

		w3[0] = Math.sqrt(Math.pow(a, 2) + Math.pow(d, 2));
		w3[1] = Math.atan2(a, d) * 180/Math.PI;

		w4[0] = Math.sqrt(Math.pow(a, 2) + Math.pow(c, 2));
		w4[1] = Math.atan2(a, c) * 180/Math.PI;

		/*normalize wheel speeds*/
		double max = w1[0];
		if(w2[0] > max)
			max = w2[0];
		if(w3[0] > max)
			max = w3[0];
		if(w4[0] > max)
			max = w4[0];
		if(max > 1){
			w1[0]/=max;
			w2[0]/=max;
			w3[0]/=max;
			w4[0]/=max;
		}

		drivetrain.setTopRight(w1);
		drivetrain.setTopLeft(w2);
		drivetrain.setBottomLeft(w3);
		drivetrain.setBottomRight(w4);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
