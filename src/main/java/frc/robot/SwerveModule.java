package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SwerveModule{
    private TalonSRX mDrive, mSteering;
    public SwerveModule(int kSteeringID, int kDriveID){
        mDrive = new TalonSRX(kDriveID);
        mSteering = new TalonSRX(kSteeringID);
        mSteering.setInverted(false);
        mDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 10);
    }
    public int getDriveEncoder(){
        return (mDrive.getSelectedSensorVelocity(0));
    }
    public int getSteeringEncoder(){
        return (int)(((Math.abs(mSteering.getSelectedSensorPosition(0) % 1024)) - 156) * (1023.0/822.0));
    }
    public void setDrive(double power){
        mDrive.set(ControlMode.PercentOutput, power);
    }
    public void setSteering(double power){
        mSteering.set(ControlMode.PercentOutput, power);
    }
}
