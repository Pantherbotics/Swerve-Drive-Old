package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SwerveModule{
    private TalonSRX mDrive, mSteering;    
    public SwerveModule(){
        mDrive = new TalonSRX(Constants.kDriveID);
        mSteering = new TalonSRX(Constants.kSteeringID);
        mSteering.setInverted(false);
        mDrive.setInverted(false);
        mDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        setDrivePower(0.25);
        setSteeringPower(0.25);
    }
    public void setDrivePower(double power){
        mDrive.set(ControlMode.PercentOutput, power);
    }
    public void setSteeringPower(double power){
        mSteering.set(ControlMode.PercentOutput, power);
    }
}
