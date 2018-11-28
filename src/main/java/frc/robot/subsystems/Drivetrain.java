//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.util.Odometry;

public class Drivetrain extends Subsystem{
    private TalonSRX mLeftA = new TalonSRX(Constants.kLeftA);
    private TalonSRX mLeftB = new TalonSRX(Constants.kLeftB);
    private TalonSRX mRightA = new TalonSRX(Constants.kRightA);
    private TalonSRX mRightB = new TalonSRX(Constants.kRightB);
    public int setpoint = 100;
    //variables for Odometry
    private double lastPos, currentPos, dPos;
    volatile double x, y, theta;

    public Drivetrain(){
        mLeftB.follow(mLeftA);
        mRightB.follow(mRightA);
        mLeftA.setInverted(true);
        mLeftB.setInverted(true);
        mRightA.setInverted(false);
        mRightB.setInverted(false);
    }
    public Odometry getOdometry() {
        return new Odometry(x, y, theta);
    }
    public void setOdometry(Odometry odo){
        this.x = odo.getX();
        this.y = odo.getY();
        this.theta = odo.getTheta();
    }
    public void geterror(){
        return Odometry.geterror();
    }

    public void initDefaultCommand(){
        setDefaultCommand(new Drive());
    }
    public void setMotorPower(double left, double right){
        double inta = Constants.kP;
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }
}
