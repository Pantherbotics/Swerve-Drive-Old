package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.ParamEnum;

public class SwerveModule{
    private TalonSRX mDrive, mSteering;
    
    public SwerveModule(int kSteeringID, int kDriveID){
        mDrive = new TalonSRX(kDriveID);
        mSteering = new TalonSRX(kSteeringID);
        mSteering.setInverted(false);
        mSteering.setSensorPhase(true);
        mDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 10);
        mSteering.configSetParameter(ParamEnum.eFeedbackNotContinuous, 0, 0x00, 0x00, 0x00);
        mSteering.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		mSteering.config_kP(Constants.kPIDLoopIdx, Constants.kSwerveP, Constants.kTimeoutMs);
		mSteering.config_kI(Constants.kPIDLoopIdx, Constants.kSwerveI, Constants.kTimeoutMs);
		mSteering.config_kD(Constants.kPIDLoopIdx, Constants.kSwerveD, Constants.kTimeoutMs);
    }
    public int getDriveEncoder(){
        return (mDrive.getSelectedSensorVelocity(0));
    }
    public int getSteeringEncoder(){
        return (int)(Math.abs(mSteering.getSelectedSensorPosition(0)));
        //return (int)(((Math.abs(mSteering.getSelectedSensorPosition(0) % 1024)) - 156) * (1023.0/822.0));
    }
    public void setDrive(double power){
        mDrive.set(ControlMode.PercentOutput, power);
    }
    public void setSteering(double pos){
        mSteering.set(ControlMode.Position, pos);
    }
    public static int convertDegressToTicks(double angle){
        int out;
        out = (((int)angle + 180) * 1023/360);
        return out;
    }
    public int getSteeringError(){
        return mSteering.getClosedLoopError(Constants.kPIDLoopIdx);
    }
}
